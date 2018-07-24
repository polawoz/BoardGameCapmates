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
		
		systemsGameCollection.add(gameToBeAdded);
		
	}

}
