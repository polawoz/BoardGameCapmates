package com.jstk.services;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jstk.data.ProfileInformation;
import com.jstk.mappers.UserMapper;
import com.jstk.repository.UserDao;

@Service
public class UserProfileManager {
	
	private final UserMapper userMapper;
	private final UserDao userDao;
	
	
	@Autowired
	public UserProfileManager(UserMapper userMapper, UserDao userDao){
		this.userMapper=userMapper;
		this.userDao=userDao;
		
	}
	
	
	
	
	public ProfileInformation getUserProfileInformation(Long userID){
		
		HashMap<Long, ProfileInformation> mapOfAllUsersProfileInformation = userDao.findAllUsersProfileInformation();
		ProfileInformation usersProfileInformation = userMapper.getOneUsersProfileInformation(mapOfAllUsersProfileInformation, userID);
		
		
		return usersProfileInformation;
	
	}
	
	
	public void changeFirstName(Long userID, String newFirstName){
	
	
	}
	
	
	public void changeLastName(Long userID, String newLastName){

		
	}
	
	public void saveChanges(){
		
	}

	

}
