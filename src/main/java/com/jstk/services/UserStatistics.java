package com.jstk.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jstk.data.GameLogEntity;
import com.jstk.data.UsersHistoryRecordTO;
import com.jstk.enums.StatisticsType;
import com.jstk.mappers.UserStatisticsMapper;
import com.jstk.repository.GameHistoryDao;

@Service
public class UserStatistics {

	private final GameHistoryDao gameHistoryDao;
	private final UserStatisticsMapper userStatisticsMapper;

	@Autowired
	public UserStatistics(GameHistoryDao gameHistoryDao, UserStatisticsMapper userStatisticsMapper) {
		this.gameHistoryDao = gameHistoryDao;
		this.userStatisticsMapper = userStatisticsMapper;
	}

	
	
	
public List<UsersHistoryRecordTO> getHistoryOfGamesPlayed(Long userID){
		
		List<GameLogEntity> listOfOneUsersGamesLogs = gameHistoryDao.findListOfOneUsersGameLogs(userID);
		
		List<UsersHistoryRecordTO> historyOfGamesPlayedByOneUser = new ArrayList<>(userStatisticsMapper.mapSourceCollection(listOfOneUsersGamesLogs));
		
		
		return historyOfGamesPlayedByOneUser;
	}























	public HashMap<StatisticsType, Double> checkStatistics(Long userID) {

		HashMap<StatisticsType, Double> statisticsMap = new HashMap<StatisticsType, Double>();
		
		List<UsersHistoryRecordTO> historyOfGamesPlayedByOneUser = getHistoryOfGamesPlayed(userID);
		
		
		//czy tutaj robie filtrowanie na streamie?
		//1. zliczenie ile jest jest elementow na liscie z historia
		//2. zsumowanie result, zeby zliczyc wygrane
		//3. obliczenie procentowego udzialu wygranych 
		
		//nastepnie wrzucam te dane na HashMape z odpowiednim enumem jako klucz
		
		
		
	

		return statisticsMap;
	}

	
	
	public int calculateLevel(Long userID) {

		int level = 0;
		// tutaj na podstawie statisticsMap wyliczyc level;

		return level;
	}

	public int getRankingLevel(Long userID) {

		int rankingLevel = 0;
		// tutaj pobrac utworzona gdzies liste rankingowa i znalezc na ktorej
		// pozycji jest ten User

		return rankingLevel;
	}

}
