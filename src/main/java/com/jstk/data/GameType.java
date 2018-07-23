package com.jstk.data;

public class GameType {

	private String name;
	private int minimumNumberOfPlayers;
	private int maximumNumberOfPlayers;
	
	public GameType(String name, int minimumNumberOfPlayers, int maximumNumberOfPlayers){
		this.name=name;
		this.maximumNumberOfPlayers=maximumNumberOfPlayers;
		this.minimumNumberOfPlayers=minimumNumberOfPlayers;
		
	}
	
	
}
