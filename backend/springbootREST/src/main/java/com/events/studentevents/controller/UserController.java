package com.events.studentevents.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.events.studentevents.dao.UserDAO;
import com.events.studentevents.model.*;


@RestController
public class UserController {
	
	
	//Creates a new instance of the UserDAO interface, and uses this to return appropriate values
	@Autowired
	private UserDAO uDao;
	
	
	/*
	 * Get all preference types available for user to choose 
	 */
	@GetMapping("/api/getPreferenceTypes")
	@ResponseBody
	public List<String> getPreferenceTypes(HttpServletResponse response){
		response.addHeader("Access-Control-Allow-Origin", "*");
		return uDao.getPreferenceTypes();
	}
	

	@GetMapping("/api/addPreferenceToUser/{preferenceName}/{id}")
	@ResponseBody
	public void addPreferenceToUser(@PathVariable("preferenceName") String preferenceName, @PathVariable("id") int id, HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		//get userId from session
		uDao.addPreferenceToUser(id, preferenceName);
		//redirect to api/user to show updated changes?
	}
	

	@GetMapping("/api/removePreferenceFromUser/{preferenceName}/{id}")
	@ResponseBody
	public void removePreferenceFromUser(@PathVariable("preferenceName") String preferenceName, @PathVariable("id") int id, HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		// get userId from session
		uDao.removePreferenceFromUser(id, preferenceName);
		// redirect to api/user to show updated changes?
	}
	

	@GetMapping("/api/modifyNotificationSetting/{preferenceName}/{id}")
	@ResponseBody
	public void modifyNotificationSetting(@PathVariable("preferenceName") String preferenceName, @PathVariable("id") int id, HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		// get userId from session
		uDao.modifyNotificationSetting(id, preferenceName);
		// redirect to api/user to show updated changes?
	}
	

	/*
	 * Get user's information including email, displayName, and list of {preferenceName, alert}
	 */
	@GetMapping("/api/user/{id}")
	@ResponseBody
	public UserInfo getUser(@PathVariable("id") int id, HttpServletResponse response) {
		//should get id from session
		response.addHeader("Access-Control-Allow-Origin", "*");
		UserInfo user =  uDao.getUser(id);
		return user;
	}
	
	/*
	 * Send back userId to front end if authentication is successful; -1 if not
	 */
	@GetMapping("/api/authenticate/{email}/{password}")
	@ResponseBody
	public int authenticateUser(@PathVariable("email") String email, @PathVariable("password") String password, HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		try {
			//should store user id returned in session
			int userId = uDao.authenticateUser(email, password);
			if (userId > 0) {
				return 1;
			}
			
			return -1;
		} catch (EmptyResultDataAccessException e) {
			return -1;
		}
	}

	//PostMapping is another annotation option for any POST requests
	/*
	 * Send back userId to front end if registration is successful; -1 if not
	 */
	@GetMapping("/api/registerUser/{email}/{password}/{displayName}/{preferences}")
	@ResponseBody
	public int insertUser(@PathVariable("email") String email, @PathVariable("password") String password, 
			@PathVariable("displayName") String displayName, @PathVariable("preferences") ArrayList<String> prefs, HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		UserInfo user = new UserInfo();
		user.email = email;
		user.password = password;
		user.displayName = displayName;
		user.preferences = new ArrayList<Preference>();
		for(String p:prefs) {
			Preference pref = new Preference(p);
			user.preferences.add(pref);
		}
		return uDao.registerUser(user);
	}

	@GetMapping("/api/addEvent/{eventTitle}/{description}/{preferenceCategory}/{location}/{eventDateTime}/{createdUserId}")
	@ResponseBody
	public void addEvent(@PathVariable("eventTitle") String eventTitle, @PathVariable("description") String description, 
			@PathVariable("preferenceCategory") String preferenceCategory, @PathVariable("location") String location,
			@PathVariable("eventDateTime") String eventDateTime, @PathVariable("createdUserId") int createdUserId, HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		Event event = new Event();
		event.name = eventTitle;
		event.description = description;
		event.activityType = preferenceCategory;
		event.eventLocation = location;
		event.createdUserId = createdUserId;
		//DateTime string passed by front end should be in this format: "YYYY-MM-DD hh:mm:ss"
		event.eventDateTime = event.convertStringtoDateTime(eventDateTime);
		uDao.addEvent(event);
		
	}
	
	@GetMapping("/api/modifyEvent/{eventId}/{eventTitle}/{description}/{preferenceCategory}/{location}/{eventDateTime}/{createdUserId}")
	@ResponseBody
	public void modifyEvent(@PathVariable("eventId") int eventId, @PathVariable("eventTitle") String eventTitle, @PathVariable("description") String description, 
			@PathVariable("preferenceCategory") String preferenceCategory, @PathVariable("location") String location,
			@PathVariable("eventDateTime") String eventDateTime, @PathVariable("createdUserId") int createdUserId, HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		Event event = new Event();
		event.eventId = eventId;
		event.name = eventTitle;
		event.description = description;
		event.activityType = preferenceCategory;
		event.eventLocation = location;
		event.createdUserId = createdUserId;
		//DateTime string passed by front end should be in this format: "YYYY-MM-DD hh:mm:ss"
		event.eventDateTime = event.convertStringtoDateTime(eventDateTime);
		uDao.modifyEvent(event);
		
	}
	
	@GetMapping("/api/getAllEvents")
	@ResponseBody
	public List<Event> getAllEvents(HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		return uDao.getAllActiveEvents();
		
	}
	
	@GetMapping("/api/getMatchingEvents/{id}")
	@ResponseBody
	public List<Event> getMatchingEvents(@PathVariable("id") int userId, HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		return uDao.getMatchingEvents(userId);
		
	}
	
	
}
