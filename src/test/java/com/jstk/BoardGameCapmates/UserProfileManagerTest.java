package com.jstk.BoardGameCapmates;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jstk.data.ProfileInformationTO;
import com.jstk.data.User;
import com.jstk.mappers.UserMapper;
import com.jstk.repository.UserDao;
import com.jstk.services.UserProfileManager;

@SpringBootTest
public class UserProfileManagerTest {

	@Autowired
	private UserDao userDao;
	@Autowired
	private UserMapper userMapper;

	@Test
	public void shouldReturnEqualProfileInformation() {

		// given

		List<User> listOfUsers = new ArrayList<>();
		listOfUsers.add(
				new User(1L, "Jan", "Nowak", "jan.nowak@skrzynka.com", "Najzyciowsze zyciowe motto", "hasloNowaka11"));
		listOfUsers.add(new User(2L, "Jacek", "Staszek", "jacek.stasz@skrzynka.com",
				"W zyciu nie kieruje sie zyciowymi mottami", "hasloStaszka22"));
		listOfUsers.add(new User(3L, "Jan", "Kowalski", "jan.kowalski@skrzynka.com", "Zycie jest nowela",
				"hasloKowalskiego33"));
		this.userDao = new UserDao();
		this.userMapper = new UserMapper();
		userDao.replaceUsersList(listOfUsers);
		UserProfileManager userProfileManager = new UserProfileManager(userMapper, userDao);

		// when
		ProfileInformationTO result = userProfileManager.findUserProfileInformation(3L);

		// then
		assertEquals("Jan", result.getFirstName());
		assertEquals("Kowalski", result.getLastName());
		assertEquals("jan.kowalski@skrzynka.com", result.getEmailAddress());
		assertEquals("Zycie jest nowela", result.getLifeMotto());

	}

	@Test
	public void shouldReturnProfileInformationWithChangedFirstName() {

		// given
		List<User> listOfUsers = new ArrayList<>();
		listOfUsers.add(
				new User(1L, "Jan", "Nowak", "jan.nowak@skrzynka.com", "Najzyciowsze zyciowe motto", "hasloNowaka11"));
		listOfUsers.add(new User(2L, "Jacek", "Staszek", "jacek.stasz@skrzynka.com",
				"W zyciu nie kieruje sie zyciowymi mottami", "hasloStaszka22"));
		listOfUsers.add(new User(3L, "Jan", "Kowalski", "jan.kowalski@skrzynka.com", "Zycie jest nowela",
				"hasloKowalskiego33"));
		this.userDao = new UserDao();
		this.userMapper = new UserMapper();
		userDao.replaceUsersList(listOfUsers);
		UserProfileManager userProfileManager = new UserProfileManager(userMapper, userDao);

		// when
		ProfileInformationTO result = userProfileManager.changeFirstName(2L, "Jacunio");

		// then
		assertEquals("Jacunio", result.getFirstName());
		assertEquals("Staszek", result.getLastName());
		assertEquals("jacek.stasz@skrzynka.com", result.getEmailAddress());
		assertEquals("W zyciu nie kieruje sie zyciowymi mottami", result.getLifeMotto());

	}

	@Test
	public void shouldReturnProfileInformationWithChangedLastName() {

		// given
		List<User> listOfUsers = new ArrayList<>();
		listOfUsers.add(
				new User(1L, "Jan", "Nowak", "jan.nowak@skrzynka.com", "Najzyciowsze zyciowe motto", "hasloNowaka11"));
		listOfUsers.add(new User(2L, "Jacek", "Staszek", "jacek.stasz@skrzynka.com",
				"W zyciu nie kieruje sie zyciowymi mottami", "hasloStaszka22"));
		listOfUsers.add(new User(3L, "Jan", "Kowalski", "jan.kowalski@skrzynka.com", "Zycie jest nowela",
				"hasloKowalskiego33"));
		this.userDao = new UserDao();
		this.userMapper = new UserMapper();
		userDao.replaceUsersList(listOfUsers);
		UserProfileManager userProfileManager = new UserProfileManager(userMapper, userDao);

		// when
		ProfileInformationTO result = userProfileManager.changeLastName(2L, "Stanislawski");

		// then
		assertEquals("Jacek", result.getFirstName());
		assertEquals("Stanislawski", result.getLastName());
		assertEquals("jacek.stasz@skrzynka.com", result.getEmailAddress());
		assertEquals("W zyciu nie kieruje sie zyciowymi mottami", result.getLifeMotto());

	}

	@Test
	public void shouldReturnProfileInformationWithChangedEMailAddress() {

		// given
		List<User> listOfUsers = new ArrayList<>();
		listOfUsers.add(
				new User(1L, "Jan", "Nowak", "jan.nowak@skrzynka.com", "Najzyciowsze zyciowe motto", "hasloNowaka11"));
		listOfUsers.add(new User(2L, "Jacek", "Staszek", "jacek.stasz@skrzynka.com",
				"W zyciu nie kieruje sie zyciowymi mottami", "hasloStaszka22"));
		listOfUsers.add(new User(3L, "Jan", "Kowalski", "jan.kowalski@skrzynka.com", "Zycie jest nowela",
				"hasloKowalskiego33"));
		this.userDao = new UserDao();
		this.userMapper = new UserMapper();
		userDao.replaceUsersList(listOfUsers);
		UserProfileManager userProfileManager = new UserProfileManager(userMapper, userDao);

		// when
		ProfileInformationTO result = userProfileManager.changeEMail(3L, "jan.kiedyskowalski@skrzynka.com");

		// then
		assertEquals("Jan", result.getFirstName());
		assertEquals("Kowalski", result.getLastName());
		assertEquals("jan.kiedyskowalski@skrzynka.com", result.getEmailAddress());
		assertEquals("Zycie jest nowela", result.getLifeMotto());

	}

	@Test
	public void shouldReturnProfileInformationWithChangedLifeMotto() {

		// given
		List<User> listOfUsers = new ArrayList<>();
		listOfUsers.add(
				new User(1L, "Jan", "Nowak", "jan.nowak@skrzynka.com", "Najzyciowsze zyciowe motto", "hasloNowaka11"));
		listOfUsers.add(new User(2L, "Jacek", "Staszek", "jacek.stasz@skrzynka.com",
				"W zyciu nie kieruje sie zyciowymi mottami", "hasloStaszka22"));
		listOfUsers.add(new User(3L, "Jan", "Kowalski", "jan.kowalski@skrzynka.com", "Zycie jest nowela",
				"hasloKowalskiego33"));
		this.userDao = new UserDao();
		this.userMapper = new UserMapper();
		userDao.replaceUsersList(listOfUsers);
		UserProfileManager userProfileManager = new UserProfileManager(userMapper, userDao);

		// when
		ProfileInformationTO result = userProfileManager.changeLifeMotto(1L, "Najzyciowsze zyciowe motto zycia");

		// then
		assertEquals("Jan", result.getFirstName());
		assertEquals("Nowak", result.getLastName());
		assertEquals("jan.nowak@skrzynka.com", result.getEmailAddress());
		assertEquals("Najzyciowsze zyciowe motto zycia", result.getLifeMotto());

	}
	@Test
	public void shouldChangeUsersPassword() {

		// given
		List<User> listOfUsers = new ArrayList<>();
		listOfUsers.add(
				new User(1L, "Jan", "Nowak", "jan.nowak@skrzynka.com", "Najzyciowsze zyciowe motto", "hasloNowaka11"));
		listOfUsers.add(new User(2L, "Jacek", "Staszek", "jacek.stasz@skrzynka.com",
				"W zyciu nie kieruje sie zyciowymi mottami", "hasloStaszka22"));
		listOfUsers.add(new User(3L, "Jan", "Kowalski", "jan.kowalski@skrzynka.com", "Zycie jest nowela",
				"hasloKowalskiego33"));
		this.userDao = new UserDao();
		this.userMapper = new UserMapper();
		userDao.replaceUsersList(listOfUsers);
		UserProfileManager userProfileManager = new UserProfileManager(userMapper, userDao);
		
		//when
		userProfileManager.changePassword(2L, "hasloStaszka22", "hasloStanislawa22");
		
		
		//then
		User userWhoChangedHisPassoword = userDao.findOneUserEntity(2L);
		
		assertEquals("hasloStanislawa22", userWhoChangedHisPassoword.getPassword());
	
	}
	
	@Test
	public void shouldThrowIllegalArgumentExceptionWhenUserTriesToChangeHisPassword() {

		// given
		List<User> listOfUsers = new ArrayList<>();
		listOfUsers.add(
				new User(1L, "Jan", "Nowak", "jan.nowak@skrzynka.com", "Najzyciowsze zyciowe motto", "hasloNowaka11"));
		listOfUsers.add(new User(2L, "Jacek", "Staszek", "jacek.stasz@skrzynka.com",
				"W zyciu nie kieruje sie zyciowymi mottami", "hasloStaszka22"));
		listOfUsers.add(new User(3L, "Jan", "Kowalski", "jan.kowalski@skrzynka.com", "Zycie jest nowela",
				"hasloKowalskiego33"));
		this.userDao = new UserDao();
		this.userMapper = new UserMapper();
		userDao.replaceUsersList(listOfUsers);
		UserProfileManager userProfileManager = new UserProfileManager(userMapper, userDao);
		
		//when
		boolean exceptionThrown = false;
		try{
		userProfileManager.changePassword(2L, "hasloStacha22", "hasloStanislawa22");
		}
		catch(IllegalArgumentException e){
			exceptionThrown=true;
		}
		
		//then
		assertTrue(exceptionThrown);
		User userWhoChangedHisPassoword = userDao.findOneUserEntity(2L);
		assertEquals("hasloStaszka22", userWhoChangedHisPassoword.getPassword());
	
	}
	

}
