package com.jstk.BoardGameCapmates.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jstk.BoardGameCapmates.data.GameType;
import com.jstk.BoardGameCapmates.data.ProfileInformationTO;
import com.jstk.BoardGameCapmates.services.GameCollectionManager;
import com.jstk.BoardGameCapmates.services.UserProfileManager;

@ResponseBody
@Controller
public class UsersGameCollectionController {

	@Autowired
	GameCollectionManager gameCollectionManager;

	@Autowired
	UserProfileManager userProfileManager;

	@RequestMapping(value = "/users-game-collection", method = RequestMethod.GET)
	public List<GameType> findUsersGameCollection(@RequestParam("stringUserID") String stringUserID) {

		Long userID = Long.valueOf(stringUserID);
		return gameCollectionManager.findUsersGameCollection(userID);

	}

	@RequestMapping(value="/users-profile-information", method=RequestMethod.GET)
	public ProfileInformationTO findUsersProfileInformation(@RequestParam("stringUserID") String stringUserID){
		
		Long userID = Long.valueOf(stringUserID);
		return userProfileManager.findUserProfileInformation(userID);
	
	}

//	@RequestMapping(value = "/change-life-motto", method = RequestMethod.PUT) 
//	public String changeUsersLifeMotto(@ModelAttribute("newBook") String newLifeMotto, Model model){
//		
//		userProfileManager.changeLifeMotto(userID, newLifeMotto);
//		
//		return newLifeMotto;
//	}

	
	
	
	
	
	
	
	
	
}
