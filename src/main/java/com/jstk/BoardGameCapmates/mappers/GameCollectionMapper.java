package com.jstk.BoardGameCapmates.mappers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.jstk.BoardGameCapmates.data.GameType;
import com.jstk.BoardGameCapmates.data.GameTypeTO;
import com.jstk.BoardGameCapmates.data.User;

@Component
public class GameCollectionMapper {

	public List<GameType> copyUsersGameCollection(User user) {

		List<GameType> usersGameCollectionCopy = new ArrayList<>(user.getGameCollection());

		// for(int i=0; i<user.getGameCollection().size(); i++){
		// GameType gameFromCollection = user.getGameCollection().get(i);
		// GameType gameFromCollectionCopy = new
		// GameType(gameFromCollection.getName(),
		// gameFromCollection.getMinimumNumberOfPlayers(),
		// gameFromCollection.getMaximumNumberOfPlayers());
		// usersGameCollectionCopy.add(gameFromCollectionCopy);
		// }

		return usersGameCollectionCopy;
	}

	public GameType createGameTypeEntityOnlyWithNameParameter(String gameTypeName) {

		GameType gameTypeOnlyWithName = new GameType(gameTypeName);

		return gameTypeOnlyWithName;
	}

	public GameType createGameTypeEntityWithoutGameID(GameTypeTO gameTypeTO) {

		GameType gameTypeWithoutGameID = new GameType(gameTypeTO.getName(), gameTypeTO.getMinimumNumberOfPlayers(),
				gameTypeTO.getMaximumNumberOfPlayers());
		return gameTypeWithoutGameID;
	}

	public GameTypeTO createGameTypeTo(GameType gameTypeEntity) {

		return new GameTypeTO(gameTypeEntity.getName(), gameTypeEntity.getMinimumNumberOfPlayers(),
				gameTypeEntity.getMaximumNumberOfPlayers());
	}

	public List<GameTypeTO> createListOfGameTypeTO(List<GameType> listOfGameTypeEntities) {

		List<GameTypeTO> listOfGameTypeTO= new ArrayList<>();

		for (GameType entity : listOfGameTypeEntities) {

			listOfGameTypeTO.add(createGameTypeTo(entity));

		}
		return listOfGameTypeTO;

	}

}
