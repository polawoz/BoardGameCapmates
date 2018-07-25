package com.jstk.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jstk.data.GameLogEntity;
import com.jstk.data.RankingRecordTO;
import com.jstk.data.UsersHistoryRecordTO;
import com.jstk.enums.StatisticsType;
import com.jstk.mappers.UserStatisticsMapper;
import com.jstk.repository.GameCollectionDao;
import com.jstk.repository.GameHistoryDao;

@Service
public class UserStatistics {

	private final GameHistoryDao gameHistoryDao;
	private final UserStatisticsMapper userStatisticsMapper;
	private final GameCollectionDao gameCollectionDao;

	@Autowired
	public UserStatistics(GameHistoryDao gameHistoryDao, UserStatisticsMapper userStatisticsMapper, GameCollectionDao gameCollectionDao) {
		this.gameHistoryDao = gameHistoryDao;
		this.userStatisticsMapper = userStatisticsMapper;
		this.gameCollectionDao=gameCollectionDao;
	}

	
	
	
public List<UsersHistoryRecordTO> getHistoryOfGamesPlayed(Long userID){
		
		List<GameLogEntity> listOfOneUsersGamesLogs = gameHistoryDao.findListOfOneUsersGameLogs(userID);
		
		List<UsersHistoryRecordTO> historyOfGamesPlayedByOneUser = new ArrayList<>(userStatisticsMapper.mapSourceCollection(listOfOneUsersGamesLogs));
		
		
		
		return historyOfGamesPlayedByOneUser;
	}








	public HashMap<StatisticsType, Integer> checkStatistics(Long userID) {
		
		List<GameLogEntity> listOfOneUsersGamesLogs = gameHistoryDao.findListOfOneUsersGameLogs(userID);
		
		HashMap<StatisticsType, Integer> statisticsMap = new HashMap<StatisticsType, Integer>();
		
		Integer numberOfGamesPlayed= listOfOneUsersGamesLogs.size();
		
		List<GameLogEntity> listOfGamesWon= listOfOneUsersGamesLogs.stream().filter(x-> 1==x.getResult()).collect(Collectors.toList());
		Integer numberOfGamesWon = listOfGamesWon.size();
		

		Integer winningRatioInPrecentage = 0;
		if(numberOfGamesPlayed!=0){
			
			winningRatioInPrecentage= (numberOfGamesWon*100)/(numberOfGamesPlayed);
			
		}
		
		statisticsMap.put(StatisticsType.NUMBER_OF_GAMES_PLAYED, numberOfGamesPlayed);
		statisticsMap.put(StatisticsType.NUMBER_OF_GAMES_WON, numberOfGamesWon);
		statisticsMap.put(StatisticsType.WON_GAMES_RATIO, winningRatioInPrecentage);
		
		

		return statisticsMap;
	}

	
	
	public int calculateLevel(Long userID) {

		HashMap<StatisticsType, Integer> statisticsMap = checkStatistics(userID);
		
		int level = 0;
		
		if(statisticsMap.get(StatisticsType.WON_GAMES_RATIO)>=25 && statisticsMap.get(StatisticsType.NUMBER_OF_GAMES_PLAYED)>=20){
			level=1;
		}
		
		else if(statisticsMap.get(StatisticsType.WON_GAMES_RATIO)>=50 && statisticsMap.get(StatisticsType.NUMBER_OF_GAMES_PLAYED)>=40){
			level=2;
		}
		
		else if(statisticsMap.get(StatisticsType.WON_GAMES_RATIO)>=60 && statisticsMap.get(StatisticsType.NUMBER_OF_GAMES_PLAYED)>=60){
			level=3;
		}

		
		else if(statisticsMap.get(StatisticsType.WON_GAMES_RATIO)>=70 && statisticsMap.get(StatisticsType.NUMBER_OF_GAMES_PLAYED)>=80){
			level=4;
		}
		
		else if(statisticsMap.get(StatisticsType.WON_GAMES_RATIO)>=80 && statisticsMap.get(StatisticsType.NUMBER_OF_GAMES_PLAYED)>=100){
			level=5;
		}
		
		
		return level;
	}

	
	public List<RankingRecordTO> findRankingForOneGameType(Long gameID){
		
		List<GameLogEntity> listOfOneGameTypeLogs = gameHistoryDao.findListOfOneGameTypeLogs(gameID);
		
		HashMap<Long, Integer> gameTypeRankingUnsortedMap= new HashMap<>();

		for(GameLogEntity gameLogEntityFromOneGameType : listOfOneGameTypeLogs){
			
			if(gameTypeRankingUnsortedMap.containsKey(gameLogEntityFromOneGameType.getUserID())){
				
				int previousValue = gameTypeRankingUnsortedMap.get(gameLogEntityFromOneGameType.getUserID());
				
				gameTypeRankingUnsortedMap.put(gameLogEntityFromOneGameType.getUserID(), previousValue+gameLogEntityFromOneGameType.getResult());
				
			} else{
				gameTypeRankingUnsortedMap.put(gameLogEntityFromOneGameType.getUserID(), gameLogEntityFromOneGameType.getResult());
			}
			
		}
		
		
		
		Map<Long, Integer> sortedMap =
		        gameTypeRankingUnsortedMap.entrySet().stream()
		                .sorted(Map.Entry.comparingByValue())
		                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
		                        (e1, e2) -> e2, LinkedHashMap::new));
		
		
		
		//przekazuje ja do mappera, ktory potworzy obiekty RankingRecordTO i zwroci ich liste
		//
		
		
		
		
		
		
		return rankingRecord;
	}
	
	public int getRankingLevel(Long userID, String gameTypeName) {

		int rankingLevel = 0;
		
		
		
		// tutaj pobrac utworzona wczesniej liste rankingowa i znalezc na ktorej
		// pozycji jest ten User
		//uwzglednic ze gracz wgl w to nie gral
		return rankingLevel;
	}

}
