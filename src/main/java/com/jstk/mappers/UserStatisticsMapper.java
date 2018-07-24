package com.jstk.mappers;


import java.util.List;
import java.util.stream.Collectors;

import com.jstk.data.GameLogEntity;
import com.jstk.data.UsersHistoryRecordTO;

public class UserStatisticsMapper {

	public List<UsersHistoryRecordTO> mapSourceCollection(List<GameLogEntity> listOfOneUsersGamesLogs) {
		//tu bym jednak zmienila zeby UsersHistoryRecordTO nie mial paramatru gameID tylko name
		List<UsersHistoryRecordTO> historyOfGamesPlayedByOneUser = listOfOneUsersGamesLogs.stream()
				.map(this::convertEntityToTO).collect(Collectors.toList());

		
		
		return historyOfGamesPlayedByOneUser;
	}

	public UsersHistoryRecordTO convertEntityToTO(GameLogEntity entity) {
		
		UsersHistoryRecordTO converted=new UsersHistoryRecordTO(entity.getGameID(), entity.getResult());
	
		return converted;
	}
	
	

	

	
	
	
	
	
}
