package com.events.studentevents.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
@Data
@AllArgsConstructor
@NoArgsConstructor
/*
 * Model for event
 */
public class Event {
	public int eventId;
    public String name;
    public String description;
    public String activityType;
    //"yyyy-MM-ddTHH:mm:ss-08:00" is the format of the string that front end will receive
    public ZonedDateTime eventDateTime;
    public String eventLocation;
    public boolean expired;
    public int createdUserId;
    //do not need postedDateTime because we're not using it for anything?
    
    
    //https://stackoverflow.com/questions/44925840/java-time-format-datetimeparseexception-text-could-not-be-parsed-at-index-3
    //https://stackoverflow.com/questions/62221005/java-8-convert-date-string-into-date-with-timezone
    public ZonedDateTime convertStringtoDateTime(String s) {
    	DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm", Locale.ENGLISH);
    	LocalDateTime ldt = LocalDateTime.parse(s, f);
    	ZoneId z = ZoneId.of("America/Los_Angeles");
    	ZonedDateTime zdt = ldt.atZone(z);  
		return zdt;
    }
}
