package com.jstk.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jstk.data.GameType;
import com.jstk.data.User;
import com.jstk.mappers.GameCollectionMapper;
import com.jstk.repository.GameCollectionDao;
import com.jstk.repository.UserDao;

@Service
public class GameCollectionManager {

	private final GameCollectionMapper gameCollectionMapper;
	private final UserDao userDao;
	private final GameCollectionDao gameCollectionDao;

	@Autowired
	public GameCollectionManager(GameCollectionMapper gameCollectionMapper, UserDao userDao,
			GameCollectionDao gameCollectionDao) {
		this.gameCollectionMapper = gameCollectionMapper;
		this.userDao = userDao;
		this.gameCollectionDao = gameCollectionDao;

	}

	public List<GameType> getUsersGameCollection(Long userID) {

		HashMap<Long, List<GameType>> mapOfUsersCollectionLists = userDao.findAllUsersCollectionLists();

		List<GameType> usersGameCollection = new ArrayList<>(
				gameCollectionMapper.mapSourceCollectionToGetUsersGamesCollection(mapOfUsersCollectionLists, userID));

		return usersGameCollection;
	}

	public void removeGameFromUsersCollection(Long userID, GameType gameFromCollection) {

		getUsersGameCollection(userID).remove(gameFromCollection);
		//usuwa z tej nowej listy a jak wprowadzic te zmiane do repository?

	}

	public void addGameToUsersCollection(Long userID, GameType gameTypeToBeAddedToCollection) {
		// czy moge tutaj nie korzystac z mappera?
		boolean gameTypeIsNotInTheSystemsGameCollection = !gameCollectionDao.getSystemsGameCollection()
				.contains(gameTypeToBeAddedToCollection);

		if (gameTypeIsNotInTheSystemsGameCollection) {
			gameCollectionDao.addGameTypeToSystemsGameCollection(gameTypeToBeAddedToCollection);

		}
		getUsersGameCollection(userID).add(gameTypeToBeAddedToCollection);

	}

}
