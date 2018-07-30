package com.jstk.BoardGameCapmates.repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.jstk.BoardGameCapmates.data.GameSearchParameters;
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

	public List<GameType> findGameByParameters(GameSearchParameters parametersTO) {

		List<GameType> listOfFoundGames = new ArrayList<>();

		if (parametersTO.getName() != null && parametersTO.getMinimumNumberOfPlayers() != 0
				&& parametersTO.getMaximumNumberOfPlayers() != 0) {

			listOfFoundGames = systemsGameCollection.stream()
					.filter(x -> x.getName().contains(parametersTO.getName())
							&& parametersTO.getMinimumNumberOfPlayers() == x.getMinimumNumberOfPlayers()
							&& parametersTO.getMaximumNumberOfPlayers() == x.getMaximumNumberOfPlayers())
					.collect(Collectors.toList());

		}
		

		if (parametersTO.getName() == null && parametersTO.getMinimumNumberOfPlayers() != 0
				&& parametersTO.getMaximumNumberOfPlayers() != 0) {

			listOfFoundGames = systemsGameCollection.stream()
					.filter(x -> parametersTO.getMinimumNumberOfPlayers() == x.getMinimumNumberOfPlayers()
							&& parametersTO.getMaximumNumberOfPlayers() == x.getMaximumNumberOfPlayers())
					.collect(Collectors.toList());

		}
		
		
		
		
		

		return listOfFoundGames;

	}

}
