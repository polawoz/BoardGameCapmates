package com.jstk.BoardGameCapmates.data;

public class ProfileInformationTO {

	private String firstName;
	private String lastName;
	private String emailAddress;
	private String lifeMotto;

	public ProfileInformationTO(String firstName, String lastName, String emailAddress, String lifeMotto) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.lifeMotto = lifeMotto;

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

}
