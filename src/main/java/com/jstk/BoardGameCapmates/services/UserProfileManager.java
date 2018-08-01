package com.jstk.BoardGameCapmates.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jstk.BoardGameCapmates.data.ProfileInformationTO;
import com.jstk.BoardGameCapmates.data.User;
import com.jstk.BoardGameCapmates.exceptions.NoUserWithThatIDException;
import com.jstk.BoardGameCapmates.mappers.UserMapper;
import com.jstk.BoardGameCapmates.repository.UserDao;

@Service
public class UserProfileManager {

	private final UserMapper userMapper;
	private final UserDao userDao;

	@Autowired
	public UserProfileManager(UserMapper userMapper, UserDao userDao) {
		this.userMapper = userMapper;
		this.userDao = userDao;

	}

	public ProfileInformationTO findUserProfileInformation(Long userID) throws NoUserWithThatIDException {

	
		User searchedUser = userDao.findOneUserEntity(userID);

		ProfileInformationTO usersProfileInformation = userMapper.createOneUsersProfileInformation(searchedUser);

		return usersProfileInformation;

	}

	public ProfileInformationTO changeFirstName(Long userID, String newFirstName) throws NoUserWithThatIDException {

		User searchedUser = userDao.findOneUserEntity(userID);
		searchedUser.setFirstName(newFirstName);

		ProfileInformationTO usersProfileInformation = userMapper.createOneUsersProfileInformation(searchedUser);

		return usersProfileInformation;

	}

	public ProfileInformationTO changeLastName(Long userID, String newLastName) throws NoUserWithThatIDException {

		User searchedUser = userDao.findOneUserEntity(userID);
		searchedUser.setLastName(newLastName);

		ProfileInformationTO usersProfileInformation = userMapper.createOneUsersProfileInformation(searchedUser);

		return usersProfileInformation;

	}

	public ProfileInformationTO changeEMail(Long userID, String newEMail) throws NoUserWithThatIDException {

		User searchedUser = userDao.findOneUserEntity(userID);
		searchedUser.setEmailAddress(newEMail);

		ProfileInformationTO usersProfileInformation = userMapper.createOneUsersProfileInformation(searchedUser);

		return usersProfileInformation;

	}

	public ProfileInformationTO changeLifeMotto(Long userID, String newLifeMotto) throws NoUserWithThatIDException {

		User searchedUser = userDao.findOneUserEntity(userID);
		searchedUser.setLifeMotto(newLifeMotto);

		ProfileInformationTO usersProfileInformation = userMapper.createOneUsersProfileInformation(searchedUser);

		return usersProfileInformation;

	}

	public void changePassword(Long userID, String oldPassword, String newPassword) throws NoUserWithThatIDException {

		User searchedUser = userDao.findOneUserEntity(userID);
		if (searchedUser.getPassword().equals(oldPassword)) {
			searchedUser.setPassoword(newPassword);
		} else {
			throw new IllegalArgumentException("Old password in not correct!");
		}

	}


}
