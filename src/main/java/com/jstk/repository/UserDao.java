package com.jstk.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.jstk.data.GameLogEntity;
import com.jstk.data.GameType;
import com.jstk.data.ProfileInformationTO;
import com.jstk.data.User;

@Repository
public class UserDao {

	private List<User> usersList;

	public UserDao() {
		this.usersList = new ArrayList<User>();
	}

	

	public User findOneUserEntity(Long userID) {

		User searchedUser = usersList.stream().filter(x -> userID.equals(x.getUserID()))
				.findAny()
				.orElse(null);
		

		return searchedUser;
	}

	
	
	
	
	
	
	

	public HashMap<Long, List<GameType>> findAllUsersCollectionLists() {

		HashMap<Long, List<GameType>> mapOfUsersCollectionLists = new HashMap<>();
		for (int i = 0; i < usersList.size(); i++) {
			mapOfUsersCollectionLists.put(usersList.get(i).getUserID(), usersList.get(i).getGameCollection());

		}

		return mapOfUsersCollectionLists;
	}

}
