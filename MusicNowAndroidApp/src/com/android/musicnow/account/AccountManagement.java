package com.android.musicnow.account;

import java.sql.Time;
import java.util.Currency;
import java.sql.Date;
import java.util.List;

public class AccountManagement {

	private static AccountManagement singleton = null;
	
	private AccountManagement() {
		// do all setup
	}
	
	public static synchronized AccountManagement getInstance() {
		if(singleton == null) {
			singleton = new AccountManagement();
		}
		return singleton;
	}
	
	public void authenticateLogin(String username, String password) throws Exception {
		throw new Exception("not implemented yet");
	}

	public void retrieveLogInInformation(String emailAddress ) throws Exception {
		throw new Exception("not implement yet");
	}

	public void updateNewPerformerAccount(Account account, String name, String emailAddress, String musicGenre) throws Exception {
		throw new Exception("not implemented yet");
	}
	
	public void updateNewPerformerAccount(Account account, String name, String emailAddress, String musicGenre, String image, String bio, List<Object> upcomingEvents, String weblink) throws Exception {
		throw new Exception("not implemented yet");
	}
	
	public void updateNewVenueAccount(Account account, String name, List<String> address, String emailAddress) throws Exception {
		throw new Exception("not implemented yet");
	}
	
	public void updateNewVenueAccount(Account account, String name, List<String> address, String emailAddress, Date date, Time time, boolean twentyOne, Currency coverCharger, String venueWebLink, String performerWebLink) throws Exception {
		throw new Exception("not implemented yet");
	}
	
	public void updateEvent(Account performer, Account venue, Date date, Time time) throws Exception {
		throw new Exception("not implemented yet");
	}
	
	public void retrieveEvent(String referenceLink) throws Exception {
		throw new Exception("not implemented yet");
	}
	
	public void deleteEvent(String referenceLink) throws Exception {
		throw new Exception("not implemented yet");
	}

	public void retrieveAccount(String accountName) throws Exception {
		throw new Exception("not implemented yet");	
	}
}
