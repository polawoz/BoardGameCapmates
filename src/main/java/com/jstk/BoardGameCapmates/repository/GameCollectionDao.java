package com.jstk.BoardGameCapmates.repository;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.jstk.BoardGameCapmates.data.GameType;

@Repository
public class GameCollectionDao {

	private Set<GameType> systemsGameCollection;

	public GameCollectionDao() {
		this.systemsGameCollection = new HashSet<>();

	}

	public void reset() {
		systemsGameCollection.clear();
	}

	public Set<GameType> getSystemsGameCollection() {

		return systemsGameCollection;
	}

	private long getNextGameID() {

		return (long) systemsGameCollection.size() + 1;

	}

	public void addGameTypeToSystemsGameCollection(GameType gameToBeAdded) {

		gameToBeAdded.setGameTypeID(getNextGameID());
		systemsGameCollection.add(gameToBeAdded);

	}

	public GameType findGameType(GameType gameTypeEntityOnlyWithName) {

		GameType foundGameType = systemsGameCollection.stream()
				.filter(x -> gameTypeEntityOnlyWithName.getName().equals(x.getName())).findFirst().orElse(null);

		return foundGameType;
	}

}
