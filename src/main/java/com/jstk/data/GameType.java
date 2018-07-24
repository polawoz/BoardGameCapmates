package com.jstk.data;

public class GameType {

	private long gameTypeID;
	private String name;
	private int minimumNumberOfPlayers;
	private int maximumNumberOfPlayers;
	
	public GameType(String name, int minimumNumberOfPlayers, int maximumNumberOfPlayers){
		this.name=name;
		this.maximumNumberOfPlayers=maximumNumberOfPlayers;
		this.minimumNumberOfPlayers=minimumNumberOfPlayers;
		
	}
	
	public GameType(String name){
		this.name=name;
		
	}

	public long getGameTypeID() {
		return gameTypeID;
	}

	public void setGameTypeID(long gameTypeID) {
		this.gameTypeID = gameTypeID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMinimumNumberOfPlayers() {
		return minimumNumberOfPlayers;
	}

	public void setMinimumNumberOfPlayers(int minimumNumberOfPlayers) {
		this.minimumNumberOfPlayers = minimumNumberOfPlayers;
	}

	public int getMaximumNumberOfPlayers() {
		return maximumNumberOfPlayers;
	}

	public void setMaximumNumberOfPlayers(int maximumNumberOfPlayers) {
		this.maximumNumberOfPlayers = maximumNumberOfPlayers;
	}
	
	
	
	
}
