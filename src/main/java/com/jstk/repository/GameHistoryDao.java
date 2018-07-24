package com.jstk.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.jstk.data.GameLogEntity;

@Repository
public class GameHistoryDao {

	private List<GameLogEntity> listOfGameLogs;

	public GameHistoryDao() {
		
		this.listOfGameLogs = new ArrayList<>();

		//this.listOfGameLogs.add(new GameLogEntry(0L, 0L, 1));

	}

	public List<GameLogEntity> getListOfGameLogs() {

		return listOfGameLogs;
	}

	public List<GameLogEntity> findListOfOneUsersGameLogs(Long userID) {
		List<GameLogEntity> listOfOneUsersGameLogs = new ArrayList<>();

		listOfOneUsersGameLogs = listOfGameLogs.stream().filter(x -> userID.equals(x.getUserID()))
				.collect(Collectors.toList());

		return listOfOneUsersGameLogs;
	}

}
