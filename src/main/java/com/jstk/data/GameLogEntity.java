package com.jstk.data;

public class GameLogEntity {

	private Long userID;
	private Long gameID;
	private int result;
	
	
	public GameLogEntity(Long userID, Long gameID, int result){
		
		this.userID=userID;
		this.gameID=gameID;
		this.result=result;
		
		
	}


	public Long getUserID() {
		return userID;
	}


	public void setUserID(Long userID) {
		this.userID = userID;
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
