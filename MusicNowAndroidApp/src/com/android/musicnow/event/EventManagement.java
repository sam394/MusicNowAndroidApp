package com.android.musicnow.event;


public class EventManagement {
	
	private static EventManagement singleton = null;
	
	private EventManagement() {
		// do all setup
	}
	
	public static synchronized EventManagement getInstance() {
		if(singleton == null) {
			singleton = new EventManagement();
		}
		return singleton;
	}
	
	public void getEventByGPS(String latitude, String longitude) throws Exception {
		throw new Exception("not implemented yet");
	}
	
	public void getEventByGPS(String latitude, String longitude, Integer radius, String genre, String performerName, String venueName) throws Exception {
		throw new Exception("not implemented yet");
	}

	public void getEventByCityState(String city, String state, Integer radius, String genre, String performerName, String venueName) throws Exception {
		throw new Exception("not implemented yet");
	}
	
	public void getEventByCityState(String city, String state) throws Exception {
		throw new Exception("not implemented yet");
	}
	
	public void getEventByZipCode(String zipCode) throws Exception {
		throw new Exception("not implemented yet");
	}
	
	public void getEventByZipCode(String zipCode, Integer radius, String genre, String performerName, String venueName) throws Exception {
		throw new Exception("not implemented yet");
	}
}
