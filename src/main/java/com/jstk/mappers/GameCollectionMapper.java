package com.jstk.mappers;

import java.util.ArrayList;
import java.util.List;

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
	
	
	

}
