package com.jstk.mappers;


import com.jstk.data.ProfileInformationTO;
import com.jstk.data.User;

public class UserMapper {
	
	
	

	public ProfileInformationTO createOneUsersProfileInformation(User searchedUser) {
		
		String firstName=searchedUser.getFirstName();
		String lastName=searchedUser.getLastName();
		String emailAddress=searchedUser.getEmailAddress();
		String lifeMotto=searchedUser.getLifeMotto();
		
		ProfileInformationTO profileInformation = new ProfileInformationTO(firstName, lastName, emailAddress,
				lifeMotto);
		
		return profileInformation;

	}

}
