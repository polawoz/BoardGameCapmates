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
import com.jstk.BoardGameCapmates.services.GameCollectionManager;

@ResponseBody
@Controller
public class GameCollectionManagerController {

	@Autowired
	private GameCollectionManager gameCollectionManager;


	@RequestMapping(value = "/users-game-collection", method = RequestMethod.GET)
	public List<GameType> findUsersGameCollection(@RequestParam("stringUserID") String stringUserID) {

		Long userID = Long.valueOf(stringUserID);
		return gameCollectionManager.findUsersGameCollection(userID);

	}

	@RequestMapping(value = "/add-game-to-collection", method = RequestMethod.POST)
	public List<GameType> findUsersGameCollection(@RequestParam("stringUserID") String stringUserID,
			@RequestBody GameTypeTO gameTypeTO) {

		Long userID = Long.valueOf(stringUserID);
		gameCollectionManager.addGameToUsersCollection(userID, gameTypeTO);
		return gameCollectionManager.findUsersGameCollection(userID);

	}

	// @RequestMapping(value = "/add-game-to-collection", method =
	// RequestMethod.POST)
	// public List<GameType>
	// findUsersGameCollection(@RequestParam("stringUserID") String
	// stringUserID,
	// @RequestParam("gameName") String name,
	// @RequestParam("maxPlayers") int maximumNumberOfPlayers,
	// @RequestParam("minPlayers") int minimumNumberOfPlayers) {
	//
	//
	// Long userID = Long.valueOf(stringUserID);
	// GameTypeTO gameTypeToBeAddedToCollection= new GameTypeTO(name,
	// minimumNumberOfPlayers,
	// maximumNumberOfPlayers);
	// gameCollectionManager.addGameToUsersCollection(userID,
	// gameTypeToBeAddedToCollection);
	// return gameCollectionManager.findUsersGameCollection(userID);
	//
	// }

	

}
