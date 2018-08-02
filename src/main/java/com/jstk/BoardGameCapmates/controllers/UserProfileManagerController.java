package com.jstk.BoardGameCapmates.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jstk.BoardGameCapmates.data.ProfileInformationTO;
import com.jstk.BoardGameCapmates.exceptions.NoUserWithThatIDException;
import com.jstk.BoardGameCapmates.services.UserProfileManager;

@ResponseBody
@Controller
public class UserProfileManagerController {

	
	
	
	@Autowired
	private UserProfileManager userProfileManager;
	

	
	@RequestMapping(value = "/users-profile-information", method = RequestMethod.GET)
	public ProfileInformationTO findUsersProfileInformation(@RequestParam("userID") long userID) throws NoUserWithThatIDException {

	
		return userProfileManager.findUserProfileInformation(userID);

	}



	@RequestMapping(value = "/change-life-motto", method = RequestMethod.PUT)
	public @ResponseBody ProfileInformationTO changeUsersLifeMotto(@RequestParam("userID") long userID,
			@RequestParam("lifeMotto") String newLifeMotto) throws NoUserWithThatIDException {

	
		return userProfileManager.changeLifeMotto(userID, newLifeMotto);
	}
	
	

	
	
	
}
