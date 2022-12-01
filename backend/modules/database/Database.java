package backend.modules.database;
import java.util.ArrayList;
import backend.modules.shared.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.security.Timestamp;

/*
 * Usage of class: call the approriate public method for needed query.
 * Each query creates a new connection to database and disconnects when done with the query
 * So that user of the class does not need keep track of calling connect/disconnect themselves
 */
public class Database{
    protected Connection conn = null;

    public Database(){
        //set variables here if necessary
    }
    /*
     * Connect to the cloudsql database using Connector/J
     */
    protected void connect(){
        try{
            conn = DriverManager.getConnection("jdbc:mysql://34.28.203.195/projectData?user=root&password=12345678");
        }catch (SQLException sqle) {
			System.out.println ("Fail to connect to db: " + sqle.getMessage());
		}
    }

    /* 
     * Disconnect from cloudsql database after finishing with each query
     */
    protected void disconnect(){
        try {
            if (conn != null) {
                conn.close();
            }
            
        } catch (SQLException sqle) {
            System.out.println("Fail to disconnect from db: " + sqle.getMessage());
        }
    }

    /*
     * Check if the email and password already exist in the database
     * @return userId (a positive int) if they exist; -1 if user does not exist
     */
    public int authenticateUser(String email, String password){
        int userId = -1;
        PreparedStatement ps = null;
		try {
            connect();
            //insert to Users table
			ps = conn.prepareStatement("SELECT userId FROM users WHERE email = ? AND users.password = ?");
			ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            //get userId in order to return userId to caller
            if(rs.next()){
                userId = rs.getInt("userId");
                rs.close();
            }

		}catch (SQLException sqle) {
			System.out.println ("Exception when authenticating user: " + sqle.getMessage());
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}	
                disconnect();	
			} catch (SQLException sqle) {
				System.out.println("Exception when finalizing authentication: " + sqle.getMessage());
			}
        }
        return userId;
    }

    /*
     * Add user and their info to the database 
     * @return userId if registered successfully; -1 if failed to add user
     */
    public int registerUser(String email, String password, String displayName, ArrayList<String> preferences){
		PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        
        int userId = -1;
		try {
            connect();
            //if email exists already, registration fails
            ps1 = conn.prepareStatement("SELECT email FROM users WHERE email = ?");
            ps1.setString(1, email);
            ResultSet rs1 = ps1.executeQuery();
            if(rs1.next()){
                return userId;
            }

            //insert to Users table
			ps = conn.prepareStatement("INSERT INTO users(email, password, displayName) VALUES (?, ?, ?)", new String[] { "userId" });
			ps.setString(1, email);
            ps.setString(2, password);
            ps.setString(3, displayName);
            ps.executeUpdate();
            
            //get userId of newly inserted row
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                userId = rs.getInt("userId");
                rs.close();
            }
            
            //insert to Preferences table
            String sqlString = "INSERT INTO preferences(userId, preferenceId, alert)"
                            + " SELECT ?, (SELECT preferenceId FROM preferenceTypes pref WHERE pref.preferenceName = ?), FALSE";
            ps2 = conn.prepareStatement(sqlString);
            for(String pref : preferences){
                ps2.setInt(1, userId);
                ps2.setString(2, pref);
                ps2.execute();
            }
		}catch (SQLException sqle) {
			System.out.println ("Exception when adding new user: " + sqle.getMessage());
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}	
                disconnect();	
			} catch (SQLException sqle) {
				System.out.println("Exception when finalizaing registration: " + sqle.getMessage());
			}
		}
        return userId;
    }

    /*
     * Remove a user's preference
     * @param userId: should be retrieved from front end in order to identify the user
     * @param preference: preference to be removed
     */
    public void removePreferenceFromUser(int userId, String preference){
        PreparedStatement ps = null;
		try {
            connect();
            String sqlString = "DELETE FROM preferences pref" 
                            +" WHERE pref.userId = ?"
                            +" AND pref.preferenceId = (SELECT preferenceId FROM preferencetypes types WHERE types.preferenceName = ?)";
			ps = conn.prepareStatement(sqlString);
			ps.setInt(1, userId);
            ps.setString(2, preference);
            ps.execute();

		}catch (SQLException sqle) {
			System.out.println ("Exception when removing user's preference: " + sqle.getMessage());
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}	
                disconnect();	
			} catch (SQLException sqle) {
				System.out.println("Exception when closing prepared statement: " + sqle.getMessage());
			}
        }
    }

    /*
     * Add a user's preference
     * @param userId: should be retrieved from front end in order to identify the user
     * @param preference: preference to be added
     */
    public void addPreferenceToUser(int userId, String preference){
        PreparedStatement ps = null;
		try {
            connect();
            String sqlString = "INSERT INTO preferences(userId, preferenceID, alert)"
                            + " VALUES (?, (SELECT types.preferenceId FROM preferencetypes types WHERE types.preferenceName = ?), FALSE)";
			ps = conn.prepareStatement(sqlString);
			ps.setInt(1, userId);
            ps.setString(2, preference);
            ps.executeUpdate();

		}catch (SQLException sqle) {
			System.out.println ("Exception when adding user's preference: " + sqle.getMessage());
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}	
                disconnect();	
			} catch (SQLException sqle) {
				System.out.println("Exception when finalizing user's preference addition: " + sqle.getMessage());
			}
        }
    }
    
    /*
     * Modify the notification setting of a user for a preference. 
     * The default alert boolean for all preferences added is false.
     * If alert == false, change it to true when this method is called. 
     * If alert == true, change to false.
     * @param userId: should be retrieved from front end in order to identify the user
     * @param preference: the activity preference where user would like to receive/not receive notification
     */
    public void modifyNotificationSetting(int userId, String preference){
        PreparedStatement ps = null;
		try {
            connect();
            String sqlString = "UPDATE preferences pref" 
                            + " SET pref.alert = NOT pref.alert" 
                            + " WHERE pref.userId = ? AND pref.preferenceId = (SELECT types.preferenceId FROM preferencetypes types WHERE types.preferenceName = ?)";
			ps = conn.prepareStatement(sqlString);
			ps.setInt(1, userId);
            ps.setString(2, preference);
            ps.executeUpdate();

		}catch (SQLException sqle) {
			System.out.println ("Exception when modifying notification setting: " + sqle.getMessage());
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}	
                disconnect();	
			} catch (SQLException sqle) {
				System.out.println("Exception when finalizing modification of a notication setting: " + sqle.getMessage());
			}
        }
    }

    /*
     * Get the display name and list of activity/time/notification preferences.
     * Use this object to send to front end (as Json) in order to display user's information
     * on the User page
     * @param userId: should be retrieved from front end in order to identify the user 
     * @return UserInfo if the user exists; null if the user does not exist
     * Note: preferences include activities and times (weekdays)
     */
    public UserInfo getUserInfo(int userId){
        UserInfo user = null;

        PreparedStatement ps = null;
        //PreparedStatement ps2 = null;
		try {
            connect();
            //get user's basic info
            String sqlString = "SELECT user.email, user.displayName, types.preferenceName, pref.alert"
                            +" FROM users user" 
                            +" LEFT JOIN preferences pref ON user.userId = pref.userId" 
                            +" LEFT JOIN preferencetypes types ON pref.preferenceId = types.preferenceId" 
                            +" WHERE userId = ?";
			ps = conn.prepareStatement(sqlString);
			ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            //if query result has no row, do nothing
            //else, update user's info with query result
            
            if(rs.next()){
                user = new UserInfo();
                user.displayName = rs.getString("displayName");
                user.email = rs.getString("email");
                
                //get user's preferences and notification settings
                do{
                    user.preferences.add(rs.getString("preferenceName"));
                    user.notifications.add(rs.getBoolean("alert"));
                }while(rs.next());
            }
            rs.close();
		}catch (SQLException sqle) {
			System.out.println ("Exception when getting user's info: " + sqle.getMessage());
		} finally {
			try {
				if(ps != null) {
					ps.close();
				}	
                disconnect();	
			} catch (SQLException sqle) {
				System.out.println("Exception when finalizing retrieval of user's info: " + sqle.getMessage());
			}
		}
        return user;
    }

    /*
     * Get all preference types available for users to choose
     * Purpose: can be used for front end to present their list of preferences to users to choose
     */
    public ArrayList<String> getPreferenceTypes() {
    	ArrayList<String> preferenceTypes = new ArrayList<String>();
    	try {
            connect();
            Statement st = conn.createStatement();
            ResultSet rs  = st.executeQuery("SELECT preferenceName FROM preferencetypes");
            //iterate through result and append to list 
            while(rs.next()){
                preferenceTypes.add(rs.getString("preferenceName"));
            }
            rs.close();
		}catch (SQLException sqle) {
			System.out.println ("Exception when getting all preference types: " + sqle.getMessage());
		} finally {
			disconnect();
        }
    	return preferenceTypes;
    }
    /*
     * Add to the RSVP table whenever a user RSVP to an event
     */
    public void addRSVP(int eventId, int userId){

    }

    /*
     * Delete an RSVP entry whenever a user un-RSVP from an event
     */
    public void deleteRSVP(int eventId, int userId){

    }

    /*
     * Add an event to the event table whenever a user creates a event.
     * The user is also auto-RSVPed to the event
     * New event's expired var should be set to false
     * postedEventTime column in database should be automatically set to current time for the newly inserted entry
     * @param activityType: the event can only be tagged with one activity type
     */
    public void addEvent(String name, String description, String activityType, Timestamp eventDateTime, String eventLocation, int createdUserId){

    }

    /* 
     * Modify an existing event's description, event date/time, activity type, etc.
     */
    public void modifyEvent(){

    }

    /*
     * Return all events that match certain preferences.
     * Used for the user's personal feed
     */
    public ArrayList<Event> getMatchingEvents(int userId){
        return null;
    }

    /*
     * Return notifications of all events that will start in an hour or less.
     * To be sent to the front end for alert notifications display.
     */
    public ArrayList<Notification> getNotifications(int userId){
        return null;
    }
}