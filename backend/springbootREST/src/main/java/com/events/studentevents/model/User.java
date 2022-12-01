package com.events.studentevents.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/* 
 * Classes here represent the structure of the data stored in the database
 * For e.g. the user table should store id, email, password, and we create associated fields
 * The type and name of the fields in this class must match EXACTLY to the type and name of 
 * fields in the table. 
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
/*
 * Model for user's basic info in Users table
 */
public class User {
	public int userId;
	public String email;
	//Should not return password to frontend
	//and password can only be changed through a DAO method
	public String password;
	public String displayName;

}
