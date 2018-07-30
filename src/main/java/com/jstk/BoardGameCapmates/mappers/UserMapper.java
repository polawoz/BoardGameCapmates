package com.jstk.BoardGameCapmates.mappers;

import org.springframework.stereotype.Component;

import com.jstk.BoardGameCapmates.data.ProfileInformationTO;
import com.jstk.BoardGameCapmates.data.User;

@Component
public class UserMapper {


	
	
	public ProfileInformationTO createOneUsersProfileInformation(User searchedUser) {

		String firstName = searchedUser.getFirstName();
		String lastName = searchedUser.getLastName();
		String emailAddress = searchedUser.getEmailAddress();
		String lifeMotto = searchedUser.getLifeMotto();

		ProfileInformationTO profileInformation = new ProfileInformationTO(firstName, lastName, emailAddress,
				lifeMotto);

		return profileInformation;

	}

}
