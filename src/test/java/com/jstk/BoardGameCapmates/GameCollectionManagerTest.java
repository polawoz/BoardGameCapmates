package com.jstk.BoardGameCapmates;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.jstk.data.GameType;
import com.jstk.data.User;
import com.jstk.mappers.GameCollectionMapper;
import com.jstk.repository.GameCollectionDao;
import com.jstk.repository.UserDao;
import com.jstk.services.GameCollectionManager;

public class GameCollectionManagerTest {

	
	private GameCollectionMapper gameCollectionMapper;
	private UserDao userDao;
	private GameCollectionDao gameCollectionDao;
	
	@Test
	public void shouldReturnUsersGameCollection(){
		
		//given
		List<User> listOfUsers = new ArrayList<>();
		listOfUsers.add(
				new User(1L, "Jan", "Nowak", "jan.nowak@skrzynka.com", "Najzyciowsze zyciowe motto", "hasloNowaka11"));
		listOfUsers.add(new User(2L, "Jacek", "Staszek", "jacek.stasz@skrzynka.com",
				"W zyciu nie kieruje sie zyciowymi mottami", "hasloStaszka22"));
		listOfUsers.add(new User(3L, "Jan", "Kowalski", "jan.kowalski@skrzynka.com", "Zycie jest nowela",
				"hasloKowalskiego33"));
		this.userDao= new UserDao();
		userDao.replaceUsersList(listOfUsers);
		List<GameType> newGameCollection = new ArrayList<>();
		GameType newGameType =new GameType("Monopoly", 2, 6);
		newGameType.setGameTypeID(1L);
		newGameCollection.add(newGameType);
		GameType secondNewGameType =new GameType("Cluedo", 2, 6);
		secondNewGameType.setGameTypeID(2L);
		newGameCollection.add(secondNewGameType);
		userDao.findOneUserEntity(3L).setGameCollection(newGameCollection);
		
		
		//when
		this.gameCollectionDao= new GameCollectionDao();
		this.gameCollectionMapper = new GameCollectionMapper();
		GameCollectionManager gameCollectionManager = new GameCollectionManager(gameCollectionMapper, userDao, gameCollectionDao);
		
		List<GameType> usersGameCollection = gameCollectionManager.findUsersGameCollection(3L);
		
		
		//then
		assertTrue(usersGameCollection.contains(newGameType));
		assertTrue(usersGameCollection.contains(secondNewGameType));
		
		
		
		
	}
	
	@Test
	public void shouldRemoveGameFromGameCollection(){
		
		//given
		List<User> listOfUsers = new ArrayList<>();
		listOfUsers.add(
				new User(1L, "Jan", "Nowak", "jan.nowak@skrzynka.com", "Najzyciowsze zyciowe motto", "hasloNowaka11"));
		listOfUsers.add(new User(2L, "Jacek", "Staszek", "jacek.stasz@skrzynka.com",
				"W zyciu nie kieruje sie zyciowymi mottami", "hasloStaszka22"));
		listOfUsers.add(new User(3L, "Jan", "Kowalski", "jan.kowalski@skrzynka.com", "Zycie jest nowela",
				"hasloKowalskiego33"));
		this.userDao= new UserDao();
		userDao.replaceUsersList(listOfUsers);
		List<GameType> newGameCollection = new ArrayList<>();
		GameType newGameType =new GameType("Monopoly", 2, 6);
		newGameType.setGameTypeID(1L);
		newGameCollection.add(newGameType);
		GameType secondNewGameType =new GameType("Cluedo", 2, 6);
		secondNewGameType.setGameTypeID(2L);
		newGameCollection.add(secondNewGameType);
		userDao.findOneUserEntity(3L).setGameCollection(newGameCollection);
		
		
		//when
		this.gameCollectionDao= new GameCollectionDao();
		this.gameCollectionMapper = new GameCollectionMapper();
		GameCollectionManager gameCollectionManager = new GameCollectionManager(gameCollectionMapper, userDao, gameCollectionDao);
		gameCollectionManager.removeGameFromUsersCollection(3L, "Monopoly");
		
		
		//then
		assertFalse(userDao.findOneUserEntity(3L).getGameCollection().contains(newGameType));
		assertTrue(userDao.findOneUserEntity(3L).getGameCollection().contains(secondNewGameType));
		assertTrue(userDao.findOneUserEntity(3L).getGameCollection().size()==1);
		
	}
	
	
	public void shouldReturnTrueWhenCheckingIfGameTypeIsInSystemsGameCollection(){
		
		
	}
	
	
	
	
}
