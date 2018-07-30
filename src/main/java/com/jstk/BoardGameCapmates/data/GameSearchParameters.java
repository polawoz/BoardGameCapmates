package com.jstk.BoardGameCapmates.data;

public class GameSearchParameters {
	
	private String name;
	private int minimumNumberOfPlayers;
	private int maximumNumberOfPlayers;
	
	
	
	public GameSearchParameters(String name, int minimumNumberOfPlayers, int maximumNumberOfPlayers) {
		this.name = name;
		this.minimumNumberOfPlayers = minimumNumberOfPlayers;
		this.maximumNumberOfPlayers = maximumNumberOfPlayers;
	}



	public String getName() {
		return name;
	}



	public int getMinimumNumberOfPlayers() {
		return minimumNumberOfPlayers;
	}



	public int getMaximumNumberOfPlayers() {
		return maximumNumberOfPlayers;
	}
	
	
	
	

}
