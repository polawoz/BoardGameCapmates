package com.jstk.BoardGameCapmates.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jstk.BoardGameCapmates.data.GameType;
import com.jstk.BoardGameCapmates.data.GameTypeTO;
import com.jstk.BoardGameCapmates.data.User;
import com.jstk.BoardGameCapmates.exceptions.GameIsAlreadyInUsersCollectionException;
import com.jstk.BoardGameCapmates.exceptions.NoGameToMeetConditionsException;
import com.jstk.BoardGameCapmates.exceptions.NoUserWithThatIDException;
import com.jstk.BoardGameCapmates.mappers.GameCollectionMapper;
import com.jstk.BoardGameCapmates.repository.GameCollectionDao;
import com.jstk.BoardGameCapmates.repository.UserDao;

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

	public List<GameType> findUsersGameCollection(Long userID) throws NoUserWithThatIDException {

		User searchedUser = userDao.findOneUserEntity(userID);

		List<GameType> usersGameCollection = gameCollectionMapper.copyUsersGameCollection(searchedUser);

		return usersGameCollection;
	}

	public void removeGameFromUsersCollection(Long userID, String gameTypeName) {

		GameType gameTypeEntityOnlyWithName = gameCollectionMapper
				.createGameTypeEntityOnlyWithNameParameter(gameTypeName);

		userDao.removeGameTypeFromOneUsersGameCollection(userID, gameTypeEntityOnlyWithName);

	}

	// zalozenie: w systemie nie ma dwoch gier o takiej samej nazwie
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

		GameType gameTypeEntityOnlyWithName = gameCollectionMapper
				.createGameTypeEntityOnlyWithNameParameter(gameTypeSearchedInUsersCollectionName);

		GameType searchedGameType = userDao.findGameTypeFromUsersGameCollection(userID, gameTypeEntityOnlyWithName);

		if (searchedGameType != null) {
			return true;
		}

		return false;

	}

	public void addGameToUsersCollection(Long userID, GameTypeTO gameTypeToBeAddedToCollection)
			throws GameIsAlreadyInUsersCollectionException, NoUserWithThatIDException {

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
			throw new GameIsAlreadyInUsersCollectionException();
		} else {
			GameType gameTypeEntityOnlyWithName = gameCollectionMapper
					.createGameTypeEntityOnlyWithNameParameter(gameTypeToBeAddedToCollection.getName());

			GameType gameTypeFromSystemGameCollection = gameCollectionDao.findGameType(gameTypeEntityOnlyWithName);
			userDao.findOneUserEntity(userID).getGameCollection().add(gameTypeFromSystemGameCollection);

		}

	}

	public List<GameTypeTO> findGamesByParameters(String name, int minNoPlayers, int maxNoPlayers)
			throws NoGameToMeetConditionsException {

		List<GameTypeTO> listOfFoundGames = gameCollectionMapper
				.createListOfGameTypeTO(gameCollectionDao.findGameByParameters(name, minNoPlayers, maxNoPlayers));

		if (listOfFoundGames.size() == 0) {
			throw new NoGameToMeetConditionsException();
		}

		return listOfFoundGames;

	}

}
