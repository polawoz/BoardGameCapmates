package com.jstk.BoardGameCapmates.data;

import java.util.List;

public class Game {

	private long gameID;
	private List<Long> participantsIDs;
	private Long challengerID;
	private Long winnerID;
	
	
	public Game(long gameID, List<Long> participantsIDs, Long challengerID, Long winnerID) {
		this.gameID = gameID;
		this.participantsIDs = participantsIDs;
		this.challengerID = challengerID;
		this.winnerID = winnerID;
	}
	
	
	
	
}
