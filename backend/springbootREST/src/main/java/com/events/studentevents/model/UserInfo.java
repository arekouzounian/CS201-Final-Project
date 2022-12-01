package com.events.studentevents.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
/*
 * Model that stores all user's info including email, pw, display name, and list of preferences
 * as well as their corresponding notifications settings
 * For front end to dispay on user's page
 */
public class UserInfo extends User{
	public ArrayList<Preference> preferences;
	
}
