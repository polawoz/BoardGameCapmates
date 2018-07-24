package com.jstk.data;

public class UsersHistoryRecordTO {
	
	private Long gameID;
	private int result;
	
	
	
	public UsersHistoryRecordTO(Long gameID, int result) {
		this.gameID = gameID;
		this.result = result;
	}



	public Long getGameID() {
		return gameID;
	}



	public void setGameID(Long gameID) {
		this.gameID = gameID;
	}



	public int getResult() {
		return result;
	}



	public void setResult(int result) {
		this.result = result;
	}
	
	
	
	
	

}
