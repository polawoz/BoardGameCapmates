package com.jstk.BoardGameCapmates.repositoryTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jstk.BoardGameCapmates.data.GameType;
import com.jstk.BoardGameCapmates.repository.GameCollectionDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class GameCollectionDaoTest {

	@Autowired
	private GameCollectionDao gameCollectionDao;

	@Before
	public void reset() {

		
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

	}

	@Configuration
	static class GameCollectionDaoTestConfiguration {

		@Bean
		public GameCollectionDao gameCollectionDao() {

			return new GameCollectionDao();

		}

	}

	@Test
	public void shouldAddGameToSystemsGameCollection() {

		// given
		GameType gameToBeAdded = new GameType("Scrabble", 2, 6);

		// when
		gameCollectionDao.addGameTypeToSystemsGameCollection(gameToBeAdded);

		// then
		assertTrue(gameCollectionDao.getSystemsGameCollection().size() == 4);
		assertTrue(gameCollectionDao.getSystemsGameCollection().contains(gameToBeAdded));

	}

	@Test
	public void shouldFindGameTypeMonopoly() {

		// given
		GameType gameTypeEntityOnlyWithName = new GameType("Monopoly");

		// when
		GameType gameTypeFound = gameCollectionDao.findGameType(gameTypeEntityOnlyWithName);

		// then
		assertTrue(gameTypeEntityOnlyWithName.getName().equals(gameTypeFound.getName()));

	}

}
