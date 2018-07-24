package com.jstk.repository;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.jstk.data.GameType;

@Repository
public class GameCollectionDao {
	
	
	private Set<GameType> systemsGameCollection;
	
	
	public GameCollectionDao(){
		this.systemsGameCollection=new HashSet<>();
		
	}
	
	
	public Set<GameType> getSystemsGameCollection(){
		
		return systemsGameCollection;
	}
	
	
	public void addGameTypeToSystemsGameCollection(GameType gameToBeAdded){
		//jak dodawac ID?? jaki typ? rzutowanie?
		int newGameID = systemsGameCollection.size()+1;
		
		gameToBeAdded.setGameTypeID(newGameID);
		systemsGameCollection.add(gameToBeAdded);
		
	}


	public GameType findGameType(GameType gameTypeEntityOnlyWithName) {

		GameType foundGameType = systemsGameCollection.stream()
				.filter(x-> gameTypeEntityOnlyWithName.getName().equals(x.getName()))
				.findFirst()
				.orElse(null);
		
		
		return foundGameType;
	}

}
