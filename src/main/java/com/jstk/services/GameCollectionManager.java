package com.jstk.services;

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

	
	
	public List<GameType> findUsersGameCollection(Long userID) {

		User searchedUser = userDao.findOneUserEntity(userID);
		List<GameType> usersGameCollection = gameCollectionMapper.copyUsersGameCollection(searchedUser);


		return usersGameCollection;
	}
	
	

	
	

	public void removeGameFromUsersCollection(Long userID, GameType gameFromCollection) {

		
		User searchedUser = userDao.findOneUserEntity(userID);
		searchedUser.getGameCollection().remove(gameFromCollection);


	}

	
	
	
	
	public void addGameToUsersCollection(Long userID, GameTypeTO gameTypeToBeAddedToCollection) {
		
		//sprawdzamy czy juz jest
		
		boolean gameTypeIsNotInTheSystemsGameCollection = !gameCollectionDao.getSystemsGameCollection()
				.contains(gameTypeToBeAddedToCollection);

		if (gameTypeIsNotInTheSystemsGameCollection) {
			//wczesniej powinna byc jakas walidacja
			//tu tworzymy encje z pustym gameID
			
			gameCollectionDao.addGameTypeToSystemsGameCollection(gameTypeToBeAddedToCollection);

		}
		
		
		User searchedUser = userDao.findOneUserEntity(userID);
		searchedUser.getGameCollection().add(gameTypeToBeAddedToCollection);
		

	}

}
