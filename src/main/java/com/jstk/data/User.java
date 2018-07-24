package com.jstk.data;

import java.util.List;
import java.util.Set;

public class User {
	
	private Long userID;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private String lifeMotto;
	private String password;
	//to do repozytorium
	private List<GameType> gameCollection;
	private Set<AvailabilityPeriod> availabilityPeriodSet;
	
	
	public String getFirstName() {
		return firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public String getEmailAddress() {
		return emailAddress;
	}


	public String getLifeMotto() {
		return lifeMotto;
	}


	public long getUserID(){
		return userID;
	}
	
	
	public Set<AvailabilityPeriod> getAvailabilityPeriodSet(){
		return availabilityPeriodSet;
	}
	
	
	public void setFirstName(String newFirstName){
		this.firstName=newFirstName;
		
	}
	
	public void setLastName(String newLastName){
		this.lastName=newLastName;
	}
	
	
	public void setEmailAddress(String newEmailAddress){
		this.emailAddress=newEmailAddress;
		
	}
	
	
	public void setLifeMotto(String newLifeMotto){
		this.lifeMotto=newLifeMotto;
		
	}
	
	
	
	public void setPassoword(String newPassword){
		this.password=newPassword;
	}
	
	
	public List<GameType> getGameCollection(){
		return gameCollection;
		
	}
	

}
