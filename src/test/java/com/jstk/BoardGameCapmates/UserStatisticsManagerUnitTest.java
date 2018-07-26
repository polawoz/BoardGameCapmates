package com.jstk.BoardGameCapmates;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jstk.BoardGameCapmates.data.GameLogEntity;
import com.jstk.BoardGameCapmates.data.UsersHistoryRecordTO;
import com.jstk.BoardGameCapmates.enums.StatisticsType;
import com.jstk.BoardGameCapmates.mappers.UserStatisticsMapper;
import com.jstk.BoardGameCapmates.repository.GameHistoryDao;
import com.jstk.BoardGameCapmates.services.UserStatisticsManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class UserStatisticsManagerUnitTest {

	@Autowired
	private UserStatisticsManager userStatisticsManager;
	@Autowired
	private GameHistoryDao gameHistoryDao;
	@Autowired
	private UserStatisticsMapper userStatisticsMapper;

	@Configuration
	static class UserStatisticsManagerTestContextConfiguration {

		@Bean
		public GameHistoryDao gameHistoryDao() {
			GameHistoryDao gameHistoryDao = new GameHistoryDao();

			GameLogEntity gameLogEntityGameOnePlayerOne = new GameLogEntity(1L, 1L, 1);
			gameHistoryDao.getListOfGameLogs().add(gameLogEntityGameOnePlayerOne);
			GameLogEntity gameLogEntityGameOnePlayerTwo = new GameLogEntity(2L, 1L, 0);
			gameHistoryDao.getListOfGameLogs().add(gameLogEntityGameOnePlayerTwo);

			GameLogEntity gameLogEntityGameTwoPlayerOne = new GameLogEntity(1L, 2L, 1);
			gameHistoryDao.getListOfGameLogs().add(gameLogEntityGameTwoPlayerOne);
			GameLogEntity gameLogEntityGameTwoPlayerThree = new GameLogEntity(3L, 2L, 0);
			gameHistoryDao.getListOfGameLogs().add(gameLogEntityGameTwoPlayerThree);

			GameLogEntity gameLogEntityGameThreePlayerTwo = new GameLogEntity(2L, 1L, 1);
			gameHistoryDao.getListOfGameLogs().add(gameLogEntityGameThreePlayerTwo);
			GameLogEntity gameLogEntityGameThreePlayerThree = new GameLogEntity(3L, 1L, 0);
			gameHistoryDao.getListOfGameLogs().add(gameLogEntityGameThreePlayerThree);

			GameLogEntity gameLogEntityGameFourPlayerOne = new GameLogEntity(1L, 1L, 0);
			gameHistoryDao.getListOfGameLogs().add(gameLogEntityGameFourPlayerOne);
			GameLogEntity gameLogEntityGameFourPlayerThree = new GameLogEntity(3L, 1L, 1);
			gameHistoryDao.getListOfGameLogs().add(gameLogEntityGameFourPlayerThree);

			GameLogEntity gameLogEntityGameFivePlayerTwo = new GameLogEntity(2L, 1L, 1);
			gameHistoryDao.getListOfGameLogs().add(gameLogEntityGameFivePlayerTwo);
			GameLogEntity gameLogEntityGameFivePlayerThree = new GameLogEntity(3L, 1L, 0);
			gameHistoryDao.getListOfGameLogs().add(gameLogEntityGameFivePlayerThree);

			GameLogEntity gameLogEntityGameSixPlayerOne = new GameLogEntity(1L, 1L, 1);
			gameHistoryDao.getListOfGameLogs().add(gameLogEntityGameSixPlayerOne);
			GameLogEntity gameLogEntityGameSixPlayerTwo = new GameLogEntity(2L, 1L, 0);
			gameHistoryDao.getListOfGameLogs().add(gameLogEntityGameSixPlayerTwo);

			GameLogEntity gameLogEntityGameSevenPlayerOne = new GameLogEntity(1L, 1L, 1);
			gameHistoryDao.getListOfGameLogs().add(gameLogEntityGameSevenPlayerOne);
			GameLogEntity gameLogEntityGameSevenPlayerThree = new GameLogEntity(3L, 1L, 0);
			gameHistoryDao.getListOfGameLogs().add(gameLogEntityGameSevenPlayerThree);

			GameLogEntity gameLogEntityGameEightPlayerOne = new GameLogEntity(1L, 1L, 0);
			gameHistoryDao.getListOfGameLogs().add(gameLogEntityGameEightPlayerOne);
			GameLogEntity gameLogEntityGameEightPlayerThree = new GameLogEntity(3L, 1L, 1);
			gameHistoryDao.getListOfGameLogs().add(gameLogEntityGameEightPlayerThree);

			return gameHistoryDao;

		}

		@Bean
		public UserStatisticsMapper userStatisticsMapper() {

			UserStatisticsMapper userStatisticsMapper = new UserStatisticsMapper();

			return userStatisticsMapper;

		}

		@Bean
		public UserStatisticsManager userStatisticsManager() {

			return new UserStatisticsManager(gameHistoryDao(), userStatisticsMapper());
		}

	}

	@Test
	public void shouldReturnHistoryOfGamesPlayedByOneUser() {

		// given

		// when
		List<UsersHistoryRecordTO> usersHistory = userStatisticsManager.getHistoryOfGamesPlayed(1L);

		// then

		assertTrue(usersHistory.get(0).getGameID() == 1L);
		assertTrue(usersHistory.get(0).getResult() == 1);
		assertTrue(usersHistory.get(1).getGameID() == 2L);
		assertTrue(usersHistory.get(1).getResult() == 1);
		assertTrue(usersHistory.get(2).getGameID() == 1L);
		assertTrue(usersHistory.get(2).getResult() == 0);

	}

	@Test
	public void shouldReturnCorrectStatisticsMap() {

		// given

		// when
		HashMap<StatisticsType, Integer> usersStatisticsMap = userStatisticsManager.checkStatistics(1L);

		// then
		assertTrue(usersStatisticsMap.get(StatisticsType.NUMBER_OF_GAMES_LOST) == 2);
		assertTrue(usersStatisticsMap.get(StatisticsType.NUMBER_OF_GAMES_WON) == 4);
		assertTrue(usersStatisticsMap.get(StatisticsType.NUMBER_OF_GAMES_PLAYED) == 6);
		assertTrue(usersStatisticsMap.get(StatisticsType.WON_GAMES_RATIO) == (4 * 100 / 6));

	}

	@Test
	public void shouldCalculateCorrectUserLevel() {

		// given

		// when
		int usersLevel = userStatisticsManager.calculateLevel(1L);

		// then
		assertTrue(usersLevel == 1);

	}

	@Test
	public void shouldReturnRankingForOneGameType() {

		// given

		// when
		List<Entry<Long, Integer>> rankingForOneGame = userStatisticsManager.findRankingForOneGameType(1L);

		// then

		assertTrue(1L == rankingForOneGame.get(0).getKey());
		assertTrue(3 == rankingForOneGame.get(0).getValue());
		assertTrue(2L == rankingForOneGame.get(1).getKey());
		assertTrue(2 == rankingForOneGame.get(1).getValue());
		assertTrue(3L == rankingForOneGame.get(2).getKey());
		assertTrue(2 == rankingForOneGame.get(2).getValue());

	}

	@Test
	public void shouldReturnUsersRankingSpot() {

		// given

		// when
		int firstRankingSpot = userStatisticsManager.getRankingSpot(1L, 1L);
		int secondRankingSpot = userStatisticsManager.getRankingSpot(2L, 1L);
		int thirdRankingSpot = userStatisticsManager.getRankingSpot(3L, 1L);

		// then
		assertTrue(firstRankingSpot == 1);
		assertTrue(secondRankingSpot == 2);
		assertTrue(thirdRankingSpot == 3);

	}

	@Test
	public void shouldThrowIllegalArgumentExceptionWhenSearchingRankingSpotForUserWhoIsNotInTheRanking() {

		// when
		boolean exceptionThrown = false;

		try {
			userStatisticsManager.getRankingSpot(2L, 2L);
		} catch (IllegalArgumentException e) {
			exceptionThrown = true;

		}

		// then
		assertTrue(exceptionThrown);

	}

}
