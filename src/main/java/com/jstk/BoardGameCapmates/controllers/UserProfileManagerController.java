package com.jstk.BoardGameCapmates.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jstk.BoardGameCapmates.data.ProfileInformationTO;
import com.jstk.BoardGameCapmates.services.UserProfileManager;

@ResponseBody
@Controller
public class UserProfileManagerController {

	
	
	
	@Autowired
	UserProfileManager userProfileManager;
	
	
	@RequestMapping(value = "/users-profile-information", method = RequestMethod.GET)
	public ProfileInformationTO findUsersProfileInformation(@RequestParam("stringUserID") String stringUserID) {

		Long userID = Long.valueOf(stringUserID);
		return userProfileManager.findUserProfileInformation(userID);

	}

	// @RequestMapping(value="/users-profile-information/{id}",
	// method=RequestMethod.GET)
	// public ProfileInformationTO
	// findUsersProfileInformation(@PathVariable("id") String stringUserID){
	//
	// Long userID = Long.valueOf(stringUserID);
	// return userProfileManager.findUserProfileInformation(userID);
	//
	// }

	@RequestMapping(value = "/change-life-motto", method = RequestMethod.PUT)
	public @ResponseBody ProfileInformationTO changeUsersLifeMotto(@RequestParam("id") String stringUserID,
			@RequestBody String newLifeMotto) {

		Long userID = Long.valueOf(stringUserID);

		return userProfileManager.changeLifeMotto(userID, newLifeMotto);
	}
}
