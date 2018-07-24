package com.jstk.mappers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.jstk.data.GameType;
import com.jstk.data.User;

public class GameCollectionMapper {


	
	public List<GameType> copyUsersGameCollection(User user){
		
		List<GameType> usersGameCollectionCopy = new ArrayList<>(user.getGameCollection());
		
		return usersGameCollectionCopy;
	}

}
