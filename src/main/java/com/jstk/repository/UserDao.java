package com.jstk.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jstk.data.GameType;
import com.jstk.data.ProfileInformation;
import com.jstk.data.User;

@Repository
public class UserDao {

	private List<User> usersList;

	public UserDao() {
		this.usersList = new ArrayList<User>();
	}

	public HashMap<Long, ProfileInformation> findAllUsersProfileInformation() {

		HashMap<Long, ProfileInformation> mapOfAllUsersProfileInformation = new HashMap<>();
		String firstName=null;
		String lastName=null;
		String emailAddress=null;
		String lifeMotto=null;
		for (int i = 0; i < usersList.size(); i++) {

			ProfileInformation profileInformation = new ProfileInformation(firstName, lastName, emailAddress,
					lifeMotto);
			mapOfAllUsersProfileInformation.put(usersList.get(i).getUserID(), profileInformation);

		}

		return mapOfAllUsersProfileInformation;
	}

	public HashMap<Long, List<GameType>> findAllUsersCollectionLists() {

		HashMap<Long, List<GameType>> mapOfUsersCollectionLists = new HashMap<>();
		for (int i = 0; i < usersList.size(); i++) {
			mapOfUsersCollectionLists.put(usersList.get(i).getUserID(), usersList.get(i).getGameCollection());

		}

		return mapOfUsersCollectionLists;
	}

}
