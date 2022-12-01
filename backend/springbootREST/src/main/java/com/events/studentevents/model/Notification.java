package com.events.studentevents.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.security.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor

/* 
 * Model for notifcations for all rsvp'd events that will start in an hour or less
 * For front end to display to user at page load
*/
public class Notification {
    public String eventTitle;
    public Timestamp eventDateTime;
    public int eventId;
}
