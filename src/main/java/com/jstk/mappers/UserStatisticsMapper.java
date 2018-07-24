package com.jstk.mappers;


import java.util.List;
import java.util.stream.Collectors;

import com.jstk.data.GameLogEntity;
import com.jstk.data.UsersHistoryRecordTO;

public class UserStatisticsMapper {

	public List<UsersHistoryRecordTO> mapSourceCollection(List<GameLogEntity> listOfOneUsersGamesLogs) {

		List<UsersHistoryRecordTO> historyOfGamesPlayedByOneUser = listOfOneUsersGamesLogs.stream()
				.map(this::convertEntityToTO).collect(Collectors.toList());

		
		
		return historyOfGamesPlayedByOneUser;
	}

	public UsersHistoryRecordTO convertEntityToTO(GameLogEntity entity) {
		
		UsersHistoryRecordTO converted=null;
		//TO DO
		
		
		return converted;
	}

	

	
	
	
	
	
}
