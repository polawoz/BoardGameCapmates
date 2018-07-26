package com.jstk.BoardGameCapmates;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jstk.BoardGameCapmates.data.GameType;
import com.jstk.BoardGameCapmates.data.GameTypeTO;
import com.jstk.BoardGameCapmates.data.User;
import com.jstk.BoardGameCapmates.mappers.GameCollectionMapper;
import com.jstk.BoardGameCapmates.repository.GameCollectionDao;
import com.jstk.BoardGameCapmates.repository.UserDao;
import com.jstk.BoardGameCapmates.services.GameCollectionManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class GameCollectionManagerSpringUnitTest {

	
	@Autowired
	private GameCollectionManager gameCollectionManager;
	@Autowired
	private GameCollectionMapper gameCollectionMapper;
	@Autowired
	private UserDao userDao;
	@Autowired
	private GameCollectionDao gameCollectionDao;

	
	@Before
	public void reset(){
	
		
		
		
		gameCollectionDao.reset();
		GameType gameType = new GameType("Chinczyk", 2, 4);
		gameType.setGameTypeID(1L);
		gameCollectionDao.getSystemsGameCollection().add(gameType);
		GameType secondGameType = new GameType("Monopoly", 2, 4);
		secondGameType.setGameTypeID(2L);
		gameCollectionDao.getSystemsGameCollection().add(secondGameType);
		GameType thirdGameType = new GameType("Eurobiznes", 2, 4);
		thirdGameType.setGameTypeID(3L);
		gameCollectionDao.getSystemsGameCollection().add(thirdGameType);
		
		

		List<User> listOfUsers = new ArrayList<>();
		listOfUsers.add(new User(1L, "Jan", "Nowak", "jan.nowak@skrzynka.com", "Najzyciowsze zyciowe motto",
				"hasloNowaka11"));
		listOfUsers.add(new User(2L, "Jacek", "Staszek", "jacek.stasz@skrzynka.com",
				"W zyciu nie kieruje sie zyciowymi mottami", "hasloStaszka22"));
		listOfUsers.add(new User(3L, "Jan", "Kowalski", "jan.kowalski@skrzynka.com", "Zycie jest nowela",
				"hasloKowalskiego33"));

		userDao.replaceUsersList(listOfUsers);
		
		userDao.findOneUserEntity(3L).getGameCollection().add(gameCollectionDao.findGameType(new GameType("Chinczyk")));
		userDao.findOneUserEntity(3L).getGameCollection().add(gameCollectionDao.findGameType(new GameType("Monopoly")));
		
		
		
		
		
		
	}
	
	
	@Configuration
	static class GameCollectionManagerTestContextConfiguration {
		
		@Bean
		public GameCollectionMapper gameCollectionMapper() {

			return new GameCollectionMapper();
		}

		@Bean
		public GameCollectionDao gameCollectionDao() {


			return new GameCollectionDao();
		}
		
		
		
		@Bean
		public UserDao userDao() {

	
			

			return new UserDao();
		}

		
		@Bean
		public GameCollectionManager gameCollectionManager(){
			return new GameCollectionManager(gameCollectionMapper(), userDao(), gameCollectionDao());
		}

	}
	
	

	@Test
	public void shouldReturnUsersGameCollection() {

		// given

	
		// when
		
		List<GameType> usersGameCollection = gameCollectionManager.findUsersGameCollection(3L);

		// then

		assertTrue(usersGameCollection.get(0).getName().equals("Chinczyk"));
		assertTrue(usersGameCollection.get(0).getGameTypeID()==1L);
		assertTrue(usersGameCollection.get(1).getName().equals("Monopoly"));
		assertTrue(usersGameCollection.get(1).getGameTypeID()==2L);
		assertTrue(usersGameCollection.size() == 2);

	}

	@Test
	public void shouldRemoveGameFromGameCollection() {

		// given
	

		// when
		
		gameCollectionManager.removeGameFromUsersCollection(3L, "Monopoly");

		// then
		assertTrue(userDao.findOneUserEntity(3L).getGameCollection().size() == 1);
		assertFalse(userDao.findOneUserEntity(3L).getGameCollection().get(0).getName().equals("Monopoly"));
		assertFalse(userDao.findOneUserEntity(3L).getGameCollection().get(0).getGameTypeID()==2L);


	}

	@Test
	public void shouldReturnTrueWhenCheckingIfGameTypeIsInSystemsGameCollection() {

		// given
		

		// when
		

		boolean result = gameCollectionManager.checkIfGameTypeIsInTheSystemsGameCollectionByName("Chinczyk");

		// then
		assertTrue(result);

	}

	@Test
	public void shouldReturnFalseWhenCheckingIfGameTypeIsInSystemsGameCollection() {

		// given
	
		// when
		boolean result = gameCollectionManager.checkIfGameTypeIsInTheSystemsGameCollectionByName("FajnaGierka");

		// then
		assertFalse(result);

	}

	@Test
	public void shouldReturnTrueWhenCheckingIfUsersGameCollectionContainsGameTypeByName() {

		// given
		
		// when

		boolean result = gameCollectionManager.checkIfUsersGameCollectionContainsGameTypeWithThatName(3L, "Chinczyk");

		// then
		assertTrue(result);

	}

	@Test
	public void shouldReturnFalseWhenCheckingIfUsersGameCollectionContainsGameTypeByName() {

		// given
	

		// when

		boolean result = gameCollectionManager.checkIfUsersGameCollectionContainsGameTypeWithThatName(3L,
				"Pan tu nie stal");

		// then
		assertFalse(result);

	}

	@Test
	public void shouldAddGameExistingInTheSystemToUsersCollection() throws IllegalArgumentException {

		// given
		
	

		// when
		gameCollectionManager.addGameToUsersCollection(3L, new GameTypeTO("Eurobiznes", 2, 4));

		// then
		List<GameType> usersGameCollection = gameCollectionManager.findUsersGameCollection(3L);
		assertTrue(usersGameCollection.get(usersGameCollection.size()-1).getName().equals("Eurobiznes"));
		assertTrue(usersGameCollection.get(usersGameCollection.size()-1).getGameTypeID()==3L);
		assertTrue(usersGameCollection.size() == 3);
		

	}

}
