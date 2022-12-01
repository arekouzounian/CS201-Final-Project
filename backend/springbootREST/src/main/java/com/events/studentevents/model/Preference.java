package com.events.studentevents.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
/*
 * Model to return user's preferences and their corresponding notifications
 * For front end to display on user's page
 */
public class Preference {
	public String preferenceName;
	//default notification setting for any newly added preference is false
	//user can change this setting in user's page
	public boolean alert = false;

	
	public Preference(String s) {
		this.preferenceName = s;
	}
	
	public Preference() {
	}
//	
//	public Preference(String s, boolean a) {
//		this.alert = a;
//		this.preferenceName = s;
//	}

}
