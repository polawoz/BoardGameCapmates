package com.jstk.data;

import java.util.ArrayList;
import java.util.List;


public class User {
	
	private Long userID;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private String lifeMotto;
	private String password;
	//to do repozytorium
	private List<GameType> gameCollection;
	private List<AvailabilityPeriod> availabilityPeriodList;
	
	//pomocniczo do testow
	public User(Long userID, String firstName, String lastName, String emailAddress, String lifeMotto, String password) {
		this.userID = userID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.lifeMotto = lifeMotto;
		this.password = password;
		this.gameCollection=new ArrayList<>();
		this.availabilityPeriodList = new ArrayList<>();
	}


	
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
	
	public String getPassword(){
		return password;
	}
	
	
	public List<AvailabilityPeriod> getAvailabilityPeriodList(){
		return availabilityPeriodList;
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
	
	//metoda do testow
	public void setGameCollection(List<GameType> newGameCollection){
		this.gameCollection=newGameCollection;
		
	}
	
	

}
