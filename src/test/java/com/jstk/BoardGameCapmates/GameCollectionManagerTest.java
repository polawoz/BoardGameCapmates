package com.jstk.BoardGameCapmates;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.jstk.BoardGameCapmates.data.GameType;
import com.jstk.BoardGameCapmates.data.User;
import com.jstk.BoardGameCapmates.mappers.GameCollectionMapper;
import com.jstk.BoardGameCapmates.repository.GameCollectionDao;
import com.jstk.BoardGameCapmates.repository.UserDao;
import com.jstk.BoardGameCapmates.services.GameCollectionManager;

public class GameCollectionManagerTest {

	private GameCollectionMapper gameCollectionMapper;
	private UserDao userDao;
	private GameCollectionDao gameCollectionDao;

	@Test
	public void shouldReturnUsersGameCollection() {

		// given
		List<User> listOfUsers = new ArrayList<>();
		listOfUsers.add(
				new User(1L, "Jan", "Nowak", "jan.nowak@skrzynka.com", "Najzyciowsze zyciowe motto", "hasloNowaka11"));
		listOfUsers.add(new User(2L, "Jacek", "Staszek", "jacek.stasz@skrzynka.com",
				"W zyciu nie kieruje sie zyciowymi mottami", "hasloStaszka22"));
		listOfUsers.add(new User(3L, "Jan", "Kowalski", "jan.kowalski@skrzynka.com", "Zycie jest nowela",
				"hasloKowalskiego33"));
		this.userDao = new UserDao();
		userDao.replaceUsersList(listOfUsers);
		List<GameType> newGameCollection = new ArrayList<>();
		GameType newGameType = new GameType("Monopoly", 2, 6);
		newGameType.setGameTypeID(1L);
		newGameCollection.add(newGameType);
		GameType secondNewGameType = new GameType("Cluedo", 2, 6);
		secondNewGameType.setGameTypeID(2L);
		newGameCollection.add(secondNewGameType);
		userDao.findOneUserEntity(3L).setGameCollection(newGameCollection);

		// when
		this.gameCollectionDao = new GameCollectionDao();
		this.gameCollectionMapper = new GameCollectionMapper();
		GameCollectionManager gameCollectionManager = new GameCollectionManager(gameCollectionMapper, userDao,
				gameCollectionDao);

		List<GameType> usersGameCollection = gameCollectionManager.findUsersGameCollection(3L);

		// then
		assertTrue(usersGameCollection.contains(newGameType));
		assertTrue(usersGameCollection.contains(secondNewGameType));
		assertTrue(usersGameCollection.size() == 2);

	}

	@Test
	public void shouldRemoveGameFromGameCollection() {

		// given
		List<User> listOfUsers = new ArrayList<>();
		listOfUsers.add(
				new User(1L, "Jan", "Nowak", "jan.nowak@skrzynka.com", "Najzyciowsze zyciowe motto", "hasloNowaka11"));
		listOfUsers.add(new User(2L, "Jacek", "Staszek", "jacek.stasz@skrzynka.com",
				"W zyciu nie kieruje sie zyciowymi mottami", "hasloStaszka22"));
		listOfUsers.add(new User(3L, "Jan", "Kowalski", "jan.kowalski@skrzynka.com", "Zycie jest nowela",
				"hasloKowalskiego33"));
		this.userDao = new UserDao();
		userDao.replaceUsersList(listOfUsers);
		List<GameType> newGameCollection = new ArrayList<>();
		GameType newGameType = new GameType("Monopoly", 2, 6);
		newGameType.setGameTypeID(1L);
		newGameCollection.add(newGameType);
		GameType secondNewGameType = new GameType("Cluedo", 2, 6);
		secondNewGameType.setGameTypeID(2L);
		newGameCollection.add(secondNewGameType);
		userDao.findOneUserEntity(3L).setGameCollection(newGameCollection);

		// when
		this.gameCollectionDao = new GameCollectionDao();
		this.gameCollectionMapper = new GameCollectionMapper();
		GameCollectionManager gameCollectionManager = new GameCollectionManager(gameCollectionMapper, userDao,
				gameCollectionDao);
		gameCollectionManager.removeGameFromUsersCollection(3L, "Monopoly");

		// then
		assertFalse(userDao.findOneUserEntity(3L).getGameCollection().contains(newGameType));
		assertTrue(userDao.findOneUserEntity(3L).getGameCollection().contains(secondNewGameType));
		assertTrue(userDao.findOneUserEntity(3L).getGameCollection().size() == 1);

	}

	@Test
	public void shouldReturnTrueWhenCheckingIfGameTypeIsInSystemsGameCollection() {

		// given
		List<User> listOfUsers = new ArrayList<>();
		listOfUsers.add(
				new User(1L, "Jan", "Nowak", "jan.nowak@skrzynka.com", "Najzyciowsze zyciowe motto", "hasloNowaka11"));
		listOfUsers.add(new User(2L, "Jacek", "Staszek", "jacek.stasz@skrzynka.com",
				"W zyciu nie kieruje sie zyciowymi mottami", "hasloStaszka22"));
		listOfUsers.add(new User(3L, "Jan", "Kowalski", "jan.kowalski@skrzynka.com", "Zycie jest nowela",
				"hasloKowalskiego33"));
		this.userDao = new UserDao();
		userDao.replaceUsersList(listOfUsers);

		this.gameCollectionMapper = new GameCollectionMapper();
		this.gameCollectionDao = new GameCollectionDao();

		GameType gameType = new GameType("Chinczyk", 2, 4);
		gameType.setGameTypeID(1L);
		gameCollectionDao.getSystemsGameCollection().add(gameType);

		// when
		GameCollectionManager gameCollectionManager = new GameCollectionManager(gameCollectionMapper, userDao,
				gameCollectionDao);

		boolean result = gameCollectionManager.checkIfGameTypeIsInTheSystemsGameCollectionByName("Chinczyk");

		// then
		assertTrue(result);

	}

	@Test
	public void shouldReturnFalseWhenCheckingIfGameTypeIsInSystemsGameCollection() {

		// given
		List<User> listOfUsers = new ArrayList<>();
		listOfUsers.add(
				new User(1L, "Jan", "Nowak", "jan.nowak@skrzynka.com", "Najzyciowsze zyciowe motto", "hasloNowaka11"));
		listOfUsers.add(new User(2L, "Jacek", "Staszek", "jacek.stasz@skrzynka.com",
				"W zyciu nie kieruje sie zyciowymi mottami", "hasloStaszka22"));
		listOfUsers.add(new User(3L, "Jan", "Kowalski", "jan.kowalski@skrzynka.com", "Zycie jest nowela",
				"hasloKowalskiego33"));
		this.userDao = new UserDao();
		userDao.replaceUsersList(listOfUsers);

		this.gameCollectionMapper = new GameCollectionMapper();
		this.gameCollectionDao = new GameCollectionDao();

		GameType gameType = new GameType("Monopoly", 2, 4);
		gameType.setGameTypeID(1L);
		gameCollectionDao.getSystemsGameCollection().add(gameType);

		// when
		GameCollectionManager gameCollectionManager = new GameCollectionManager(gameCollectionMapper, userDao,
				gameCollectionDao);

		boolean result = gameCollectionManager.checkIfGameTypeIsInTheSystemsGameCollectionByName("Chinczyk");

		// then
		assertFalse(result);

	}

	
	@Test
	public void shouldReturnTrueWhenCheckingIfUsersGameCollectionContainsGameTypeByName() {

		// given
		List<User> listOfUsers = new ArrayList<>();
		listOfUsers.add(
				new User(1L, "Jan", "Nowak", "jan.nowak@skrzynka.com", "Najzyciowsze zyciowe motto", "hasloNowaka11"));
		listOfUsers.add(new User(2L, "Jacek", "Staszek", "jacek.stasz@skrzynka.com",
				"W zyciu nie kieruje sie zyciowymi mottami", "hasloStaszka22"));
		listOfUsers.add(new User(3L, "Jan", "Kowalski", "jan.kowalski@skrzynka.com", "Zycie jest nowela",
				"hasloKowalskiego33"));
		this.userDao = new UserDao();
		userDao.replaceUsersList(listOfUsers);

		this.gameCollectionMapper = new GameCollectionMapper();
		this.gameCollectionDao = new GameCollectionDao();

		GameType gameType = new GameType("Monopoly", 2, 4);
		gameType.setGameTypeID(1L);
		GameType secondGameType = new GameType("Chinczyk", 2, 4);
		secondGameType.setGameTypeID(2L);
		gameCollectionDao.getSystemsGameCollection().add(gameType);
		gameCollectionDao.getSystemsGameCollection().add(secondGameType);
		
		userDao.findOneUserEntity(2L).getGameCollection().add(gameType);
		userDao.findOneUserEntity(2L).getGameCollection().add(secondGameType);
		

		// when
		GameCollectionManager gameCollectionManager = new GameCollectionManager(gameCollectionMapper, userDao,
				gameCollectionDao);

		boolean result = gameCollectionManager.checkIfUsersGameCollectionContainsGameTypeWithThatName(2L, "Chinczyk");

		// then
		assertTrue(result);
	
		
	}
	
	
	
	
	
	@Test
	public void shouldReturnFalseWhenCheckingIfUsersGameCollectionContainsGameTypeByName() {

		// given
		List<User> listOfUsers = new ArrayList<>();
		listOfUsers.add(
				new User(1L, "Jan", "Nowak", "jan.nowak@skrzynka.com", "Najzyciowsze zyciowe motto", "hasloNowaka11"));
		listOfUsers.add(new User(2L, "Jacek", "Staszek", "jacek.stasz@skrzynka.com",
				"W zyciu nie kieruje sie zyciowymi mottami", "hasloStaszka22"));
		listOfUsers.add(new User(3L, "Jan", "Kowalski", "jan.kowalski@skrzynka.com", "Zycie jest nowela",
				"hasloKowalskiego33"));
		this.userDao = new UserDao();
		userDao.replaceUsersList(listOfUsers);

		this.gameCollectionMapper = new GameCollectionMapper();
		this.gameCollectionDao = new GameCollectionDao();

		GameType gameType = new GameType("Monopoly", 2, 4);
		gameType.setGameTypeID(1L);
		GameType secondGameType = new GameType("Chinczyk", 2, 4);
		secondGameType.setGameTypeID(2L);
		gameCollectionDao.getSystemsGameCollection().add(gameType);
		gameCollectionDao.getSystemsGameCollection().add(secondGameType);
		
		userDao.findOneUserEntity(2L).getGameCollection().add(gameType);
		userDao.findOneUserEntity(2L).getGameCollection().add(secondGameType);
		

		// when
		GameCollectionManager gameCollectionManager = new GameCollectionManager(gameCollectionMapper, userDao,
				gameCollectionDao);

		boolean result = gameCollectionManager.checkIfUsersGameCollectionContainsGameTypeWithThatName(2L, "Pan tu nie stal");

		// then
		assertFalse(result);
	
		
	}
	
	
	public void shouldAddGameExistingInTheSystemToUsersCollection(){
		
		//given
		List<User> listOfUsers = new ArrayList<>();
		listOfUsers.add(
				new User(1L, "Jan", "Nowak", "jan.nowak@skrzynka.com", "Najzyciowsze zyciowe motto", "hasloNowaka11"));
		listOfUsers.add(new User(2L, "Jacek", "Staszek", "jacek.stasz@skrzynka.com",
				"W zyciu nie kieruje sie zyciowymi mottami", "hasloStaszka22"));
		listOfUsers.add(new User(3L, "Jan", "Kowalski", "jan.kowalski@skrzynka.com", "Zycie jest nowela",
				"hasloKowalskiego33"));
		this.userDao = new UserDao();
		userDao.replaceUsersList(listOfUsers);

		this.gameCollectionMapper = new GameCollectionMapper();
		this.gameCollectionDao = new GameCollectionDao();

		GameType gameType = new GameType("Monopoly", 2, 4);
		gameType.setGameTypeID(1L);
		GameType secondGameType = new GameType("Chinczyk", 2, 4);
		secondGameType.setGameTypeID(2L);
		GameType thirdGameType = new GameType("Eurobiznes", 2, 4);
		secondGameType.setGameTypeID(3L);
		gameCollectionDao.getSystemsGameCollection().add(gameType);
		gameCollectionDao.getSystemsGameCollection().add(secondGameType);
		gameCollectionDao.getSystemsGameCollection().add(thirdGameType);
		
		userDao.findOneUserEntity(2L).getGameCollection().add(gameType);
		userDao.findOneUserEntity(2L).getGameCollection().add(secondGameType);
		
		
		
		
		//when
		GameCollectionManager gameCollectionManager = new GameCollectionManager(gameCollectionMapper, userDao,
				gameCollectionDao);
		
		
		
		
		
		//then
		
		
		
		
	}
	
	
	
	
	

}
