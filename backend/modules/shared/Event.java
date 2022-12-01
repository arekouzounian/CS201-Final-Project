package backend.modules.shared;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Event {
	
	// changes: added eventId, changed activityType from arraylist to string, changed timestamp to sql
	
    String name;
    String description;
    String activityType;
    Timestamp postedDateTime;
    Timestamp eventDateTime;
    String eventLocation;
    boolean expired;
    String createdUserName;
    int eventId;
    
    public Event(String n, String d, String a, Timestamp p, Timestamp e, String l, boolean ex, String cu, int eid) {
    	name = n;
    	description = d;
    	a = activityType;
    	postedDateTime = p;
    	eventDateTime = e;
    	eventLocation = l;
    	expired = ex;
    	createdUserName = cu;
    	eventId = eid;
    }
    
    public String getName() {
    	return name;
    }
    public String getDescription() {
    	return description;
    }
    public String getActivityType() {
    	return activityType;
    }
    public Timestamp getPostedDateTime() {
    	return postedDateTime;
    }
    public Timestamp getEventDateTime() {
    	return eventDateTime;
    }
    public String getEventLocation() {
    	return eventLocation;
    }
    public boolean getExpired() {
    	return expired;
    }
    public String getCreatedUserName() {
    	return createdUserName;
    }
    public int getEventId() {
    	return eventId;
    }
}
