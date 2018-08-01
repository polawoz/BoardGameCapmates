package com.jstk.BoardGameCapmates.repository;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;


import com.jstk.BoardGameCapmates.data.GameType;
import com.jstk.BoardGameCapmates.exceptions.NoGameToMeetConditionsException;

@Repository
public class GameCollectionDao {

	private Set<GameType> systemsGameCollection;

	public GameCollectionDao() {
		this.systemsGameCollection = new HashSet<>();
		

		GameType gameType = new GameType("Chinczyk", 2, 4);
		gameType.setGameTypeID(1L);
		systemsGameCollection.add(gameType);
		GameType secondGameType = new GameType("Monopoly", 2, 6);
		secondGameType.setGameTypeID(2L);
		systemsGameCollection.add(secondGameType);
		GameType thirdGameType = new GameType("Eurobiznes", 3, 6);
		thirdGameType.setGameTypeID(3L);
		systemsGameCollection.add(thirdGameType);
		GameType fourthGameType = new GameType("Europa", 3, 10);
		fourthGameType.setGameTypeID(4L);
		systemsGameCollection.add(fourthGameType);
		

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

	public List<GameType> findGameByParameters(String name, int minNoPlayers, int maxNoPlayers) {

		List<GameType> listOfFoundGames = systemsGameCollection.stream()
				.filter(x-> x.getName().toLowerCase().contains(name.toLowerCase()))
				.collect(Collectors.toList())
				.stream()
				.filter(x-> x.getMinimumNumberOfPlayers()<=minNoPlayers)
				.collect(Collectors.toList())
				.stream()
				.filter(x-> x.getMaximumNumberOfPlayers()>=maxNoPlayers)
				.collect(Collectors.toList());
		
	
		return listOfFoundGames;

	}

}
