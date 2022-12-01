package backend.modules.shared;

import java.sql.Timestamp;


public class Notification {
    String eventTitle;
    Timestamp eventDateTime;
    int eventId;
    
    public Notification(String e, Timestamp d, int ei) {
    	eventTitle = e;
    	eventDateTime = d;
    	eventId = ei;
    }
}
