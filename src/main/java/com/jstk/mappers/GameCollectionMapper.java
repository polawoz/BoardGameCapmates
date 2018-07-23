package com.jstk.mappers;

import java.util.HashMap;
import java.util.List;

import com.jstk.data.GameType;

public class GameCollectionMapper {

	public List<GameType> mapSourceCollectionToGetUsersGamesCollection(
			HashMap<Long, List<GameType>> mapOfUsersCollectionLists, Long userID) {

		List<GameType> mappedSourceCollection = mapOfUsersCollectionLists.get(userID);

		return mappedSourceCollection;
	}

}
