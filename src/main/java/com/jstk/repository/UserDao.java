package com.jstk.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Repository;

import com.jstk.data.GameType;
import com.jstk.data.User;

@Repository
public class UserDao {

	private List<User> usersList;

	public UserDao() {
		this.usersList = new ArrayList<User>();
	}
	
	//pomocnicze do testow
	public void replaceUsersList(List<User> previouslyCreatedList){
		this.usersList=previouslyCreatedList;
		
	}
	

	public User findOneUserEntity(Long userID) {

		User searchedUser = usersList.stream().filter(x -> userID.equals(x.getUserID())).findAny().orElse(null);

		return searchedUser;
	}

	public void removeGameTypeFromOneUsersGameCollection(Long userID, GameType gameTypeEntityOnlyWithName) {

		List<GameType> usersGameCollection = usersList.stream().filter(x -> userID.equals(x.getUserID())).findAny()
				.orElse(null).getGameCollection();

		GameType searchedGameType = usersGameCollection.stream()
				.filter(x -> gameTypeEntityOnlyWithName.getName().equals(x.getName())).findFirst().orElse(null);

		if (searchedGameType == null) {
			throw new NoSuchElementException("You cannot remove a game type that is not in your collection!");
		} else {
			usersGameCollection.remove(searchedGameType);
		}

	}

	public GameType findGameTypeFromUsersGameCollection(Long userID, GameType gameTypeEntityOnlyWithName){
		
		
		List<GameType> usersGameCollection = usersList.stream().filter(x -> userID.equals(x.getUserID())).findAny()
				.orElse(null).getGameCollection();

		GameType searchedGameType = usersGameCollection.stream()
				.filter(x -> gameTypeEntityOnlyWithName.getName().equals(x.getName())).findFirst().orElse(null);
		
		return searchedGameType;

	}
	
	
	
}
