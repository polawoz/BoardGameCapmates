package com.jstk.BoardGameCapmates.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jstk.BoardGameCapmates.data.GameLogEntity;
import com.jstk.BoardGameCapmates.data.UsersHistoryRecordTO;
import com.jstk.BoardGameCapmates.enums.StatisticsType;
import com.jstk.BoardGameCapmates.mappers.UserStatisticsMapper;
import com.jstk.BoardGameCapmates.repository.GameHistoryDao;

@Service
public class UserStatisticsManager {

	private final GameHistoryDao gameHistoryDao;
	private final UserStatisticsMapper userStatisticsMapper;

	@Autowired
	public UserStatisticsManager(GameHistoryDao gameHistoryDao, UserStatisticsMapper userStatisticsMapper) {
		this.gameHistoryDao = gameHistoryDao;
		this.userStatisticsMapper = userStatisticsMapper;

	}

	public List<UsersHistoryRecordTO> getHistoryOfGamesPlayed(Long userID) {

		List<GameLogEntity> listOfOneUsersGamesLogs = gameHistoryDao.findListOfOneUsersGameLogs(userID);

		List<UsersHistoryRecordTO> historyOfGamesPlayedByOneUser = new ArrayList<>(
				userStatisticsMapper.mapSourceCollection(listOfOneUsersGamesLogs));

		return historyOfGamesPlayedByOneUser;
	}

	public HashMap<StatisticsType, Integer> checkStatistics(Long userID) {

		List<GameLogEntity> listOfOneUsersGamesLogs = gameHistoryDao.findListOfOneUsersGameLogs(userID);

		HashMap<StatisticsType, Integer> statisticsMap = new HashMap<StatisticsType, Integer>();

		Integer numberOfGamesPlayed = listOfOneUsersGamesLogs.size();

		List<GameLogEntity> listOfGamesWon = listOfOneUsersGamesLogs.stream().filter(x -> 1 == x.getResult())
				.collect(Collectors.toList());
		Integer numberOfGamesWon = listOfGamesWon.size();

		Integer winningRatioInPrecentage = 0;
		if (numberOfGamesPlayed != 0) {

			winningRatioInPrecentage = (numberOfGamesWon * 100) / (numberOfGamesPlayed);

		}

		statisticsMap.put(StatisticsType.NUMBER_OF_GAMES_PLAYED, numberOfGamesPlayed);
		statisticsMap.put(StatisticsType.NUMBER_OF_GAMES_WON, numberOfGamesWon);
		statisticsMap.put(StatisticsType.WON_GAMES_RATIO, winningRatioInPrecentage);
		statisticsMap.put(StatisticsType.NUMBER_OF_GAMES_LOST, numberOfGamesPlayed - numberOfGamesWon);

		return statisticsMap;
	}

	public int calculateLevel(Long userID) {

		HashMap<StatisticsType, Integer> statisticsMap = checkStatistics(userID);

		int level = 1;

		if (statisticsMap.get(StatisticsType.WON_GAMES_RATIO) >= 25
				&& statisticsMap.get(StatisticsType.NUMBER_OF_GAMES_PLAYED) >= 20) {
			level = 2;
		}

		else if (statisticsMap.get(StatisticsType.WON_GAMES_RATIO) >= 50
				&& statisticsMap.get(StatisticsType.NUMBER_OF_GAMES_PLAYED) >= 40) {
			level = 3;
		}

		else if (statisticsMap.get(StatisticsType.WON_GAMES_RATIO) >= 60
				&& statisticsMap.get(StatisticsType.NUMBER_OF_GAMES_PLAYED) >= 60) {
			level = 4;
		}

		else if (statisticsMap.get(StatisticsType.WON_GAMES_RATIO) >= 70
				&& statisticsMap.get(StatisticsType.NUMBER_OF_GAMES_PLAYED) >= 80) {
			level = 5;
		}

		else if (statisticsMap.get(StatisticsType.WON_GAMES_RATIO) >= 80
				&& statisticsMap.get(StatisticsType.NUMBER_OF_GAMES_PLAYED) >= 100) {
			level = 6;
		}

		return level;
	}

	public List<Entry<Long, Integer>> findRankingForOneGameType(Long gameID) {

		List<GameLogEntity> listOfOneGameTypeLogs = gameHistoryDao.findListOfOneGameTypeLogs(gameID);

		HashMap<Long, Integer> gameTypeRankingUnsortedMap = new HashMap<>();

		for (GameLogEntity gameLogEntityFromOneGameType : listOfOneGameTypeLogs) {

			if (gameTypeRankingUnsortedMap.containsKey(gameLogEntityFromOneGameType.getUserID())) {

				int previousValue = gameTypeRankingUnsortedMap.get(gameLogEntityFromOneGameType.getUserID());

				gameTypeRankingUnsortedMap.put(gameLogEntityFromOneGameType.getUserID(),
						previousValue + gameLogEntityFromOneGameType.getResult());

			} else {
				gameTypeRankingUnsortedMap.put(gameLogEntityFromOneGameType.getUserID(),
						gameLogEntityFromOneGameType.getResult());
			}

		}

		return sortUsersByValuesDescending(gameTypeRankingUnsortedMap);
	}

	static <K, V extends Comparable<? super V>> List<Entry<K, V>> sortUsersByValuesDescending(Map<K, V> map) {

		List<Entry<K, V>> sortedList = new ArrayList<Entry<K, V>>(map.entrySet());

		Collections.sort(sortedList, new Comparator<Entry<K, V>>() {
			@Override
			public int compare(Entry<K, V> e1, Entry<K, V> e2) {
				return e2.getValue().compareTo(e1.getValue());
			}
		});

		return sortedList;
	}

	public int getRankingSpot(Long userID, Long gameID) {

		int rankingSpot = 0;
		List<Entry<Long, Integer>> rankingForOneGameType = findRankingForOneGameType(gameID);

		for (int i = 0; i < rankingForOneGameType.size(); i++) {

			if (rankingForOneGameType.get(i).getKey().equals(userID)) {
				rankingSpot = i + 1;
				break;
			}

		}

		if (rankingSpot == 0) {
			throw new IllegalArgumentException("This user is not in the this games ranking!");
		}

		return rankingSpot;
	}

}
