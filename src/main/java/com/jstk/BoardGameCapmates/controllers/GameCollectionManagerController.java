package com.jstk.BoardGameCapmates.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jstk.BoardGameCapmates.data.GameType;
import com.jstk.BoardGameCapmates.data.GameTypeTO;
import com.jstk.BoardGameCapmates.exceptions.GameIsAlreadyInUsersCollectionException;
import com.jstk.BoardGameCapmates.exceptions.NoGameToMeetConditionsException;
import com.jstk.BoardGameCapmates.exceptions.NoUserWithThatIDException;
import com.jstk.BoardGameCapmates.services.GameCollectionManager;

@ResponseBody
@Controller
public class GameCollectionManagerController {

	@Autowired
	private GameCollectionManager gameCollectionManager;


	@RequestMapping(value = "/users-game-collection", method = RequestMethod.GET)
	public List<GameType> findUsersGameCollection(@RequestParam("userID") long userID) throws NoUserWithThatIDException {

		
		return gameCollectionManager.findUsersGameCollection(userID);

	}

	@RequestMapping(value = "/add-game-to-collection", method = RequestMethod.POST)
	public List<GameType> findUsersGameCollection(@RequestParam("userID") long userID,
			@RequestBody GameTypeTO gameTypeTO) throws GameIsAlreadyInUsersCollectionException, NoUserWithThatIDException {

		gameCollectionManager.addGameToUsersCollection(userID, gameTypeTO);
		return gameCollectionManager.findUsersGameCollection(userID);

	}
	
	
	@RequestMapping(value="/findGame", method=RequestMethod.GET)
	public List<GameTypeTO> findGameByParameters(@RequestParam(value="name", required=false, defaultValue="") String name,
			@RequestParam(value="minNo", required=false, defaultValue="20") int minNoPlayers, 
			@RequestParam(value="maxNo", required=false, defaultValue="2") int maxNoPlayers) throws NoGameToMeetConditionsException{
		
		
		
		return gameCollectionManager.findGamesByParameters(name, minNoPlayers, maxNoPlayers);
	}

	

	

}
