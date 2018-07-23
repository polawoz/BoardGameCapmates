package com.jstk.mappers;

import java.util.HashMap;


import com.jstk.data.ProfileInformation;

public class UserMapper {
	
	
	

	public ProfileInformation getOneUsersProfileInformation(
			HashMap<Long, ProfileInformation> mapOfAllUsersProfileInformation, Long userID) {
				
	
		
		
		ProfileInformation usersProfileInformation = mapOfAllUsersProfileInformation.get(userID);
		
		return usersProfileInformation;

	}

}
