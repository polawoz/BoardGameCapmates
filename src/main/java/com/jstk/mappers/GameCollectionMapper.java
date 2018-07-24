package com.jstk.mappers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.jstk.data.GameType;
import com.jstk.data.GameTypeTO;
import com.jstk.data.User;

public class GameCollectionMapper {


	
	public List<GameType> copyUsersGameCollection(User user){
		
		List<GameType> usersGameCollectionCopy = new ArrayList<>(user.getGameCollection());
		
		return usersGameCollectionCopy;
	}

	public GameType createGameTypeEntityOnlyWithNameParameter(String gameTypeName) {

		GameType gameTypeOnlyWithName = new GameType(gameTypeName);

		
		return gameTypeOnlyWithName;
	}

	public GameType createGameTypeEntityWithoutGameID(GameTypeTO gameTypeTO) {
		
		GameType gameTypeWithoutGameID = new GameType(gameTypeTO.getName(), 
				gameTypeTO.getMinimumNumberOfPlayers(), gameTypeTO.getMaximumNumberOfPlayers());
		return gameTypeWithoutGameID;
	}
	
	
	
	//mozliwe ze to zbedne
	public Map<Long, String> createGameIDDictionary(Set<GameType> systemsGameCollection){
		// czy nie bedzie problemu ze przypisuje do Map nie HashMap?
		Map<Long, String> gameIDDictionary = new HashMap<>();
		
		gameIDDictionary= systemsGameCollection.stream()
				.collect(Collectors.toMap(GameType::getGameTypeID, GameType::getName));
		
		
		return gameIDDictionary;
	}

	
	
	

}
