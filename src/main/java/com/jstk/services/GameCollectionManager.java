package com.jstk.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jstk.data.GameType;
import com.jstk.data.GameTypeTO;
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

	public List<GameType> findUsersGameCollection(Long userID) {

		User searchedUser = userDao.findOneUserEntity(userID);
		List<GameType> usersGameCollection = gameCollectionMapper.copyUsersGameCollection(searchedUser);

		return usersGameCollection;
	}

	public void removeGameFromUsersCollection(Long userID, String gameTypeName) {

		GameType gameTypeEntityOnlyWithName = gameCollectionMapper
				.createGameTypeEntityOnlyWithNameParameter(gameTypeName);

		userDao.removeGameTypeFromOneUsersGameCollection(userID, gameTypeEntityOnlyWithName);

	}

	//zalozenie: w systemie nie ma dwoch gier o takiej samej nazwie
	public boolean checkIfGameTypeIsInTheSystemsGameCollectionByName(String gameTypeName) {

		GameType gameTypeEntityOnlyWithName = gameCollectionMapper
				.createGameTypeEntityOnlyWithNameParameter(gameTypeName);

		GameType gameTypeFoundInTheSystemsGameCollection = gameCollectionDao.findGameType(gameTypeEntityOnlyWithName);

		if (gameTypeFoundInTheSystemsGameCollection == null) {
			return false;
		}

		return true;

	}

	public boolean checkIfUsersGameCollectionContainsGameTypeWithThatName(Long userID,
			String gameTypeSearchedInUsersCollectionName) {

		//optional isPresent()
		
		GameType gameTypeEntityOnlyWithName = gameCollectionMapper
				.createGameTypeEntityOnlyWithNameParameter(gameTypeSearchedInUsersCollectionName);

		GameType searchedGameType = userDao.findGameTypeFromUsersGameCollection(userID, gameTypeEntityOnlyWithName);

		if (searchedGameType != null) {
			return true;
		}

		return false;

	}

	public void addGameToUsersCollection(Long userID, GameTypeTO gameTypeToBeAddedToCollection) {

		boolean gameTypeIsNotInTheSystemsGameCollection = !checkIfGameTypeIsInTheSystemsGameCollectionByName(
				gameTypeToBeAddedToCollection.getName());

		if (gameTypeIsNotInTheSystemsGameCollection) {

			GameType gameTypeEntityWithoutGameID = gameCollectionMapper
					.createGameTypeEntityWithoutGameID(gameTypeToBeAddedToCollection);

			gameCollectionDao.addGameTypeToSystemsGameCollection(gameTypeEntityWithoutGameID);

		}

		boolean usersCollectionAlreadyContainsGameTypeWithThatName = checkIfUsersGameCollectionContainsGameTypeWithThatName(
				userID, gameTypeToBeAddedToCollection.getName());

		if (usersCollectionAlreadyContainsGameTypeWithThatName) {
			throw new IllegalArgumentException("This users game collection already contains game type with that name!");
		} else {
			GameType gameTypeEntityOnlyWithName = gameCollectionMapper
					.createGameTypeEntityOnlyWithNameParameter(gameTypeToBeAddedToCollection.getName());

			GameType gameTypeFromSystemGameCollection = gameCollectionDao.findGameType(gameTypeEntityOnlyWithName);
			userDao.findOneUserEntity(userID).getGameCollection().add(gameTypeFromSystemGameCollection);

		}

	}

}
