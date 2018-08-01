package com.jstk.BoardGameCapmates;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jstk.BoardGameCapmates.data.ProfileInformationTO;
import com.jstk.BoardGameCapmates.data.User;
import com.jstk.BoardGameCapmates.exceptions.NoUserWithThatIDException;
import com.jstk.BoardGameCapmates.mappers.UserMapper;
import com.jstk.BoardGameCapmates.repository.UserDao;
import com.jstk.BoardGameCapmates.services.UserProfileManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class UserProfileManagerServiceSpringUnitTest {

	@Autowired
	private UserDao userDao;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserProfileManager userProfileManager;

	@Before
	public void reset() {

		List<User> listOfUsers = new ArrayList<>();
		listOfUsers.add(
				new User(1L, "Jan", "Nowak", "jan.nowak@skrzynka.com", "Najzyciowsze zyciowe motto", "hasloNowaka11"));
		listOfUsers.add(new User(2L, "Jacek", "Staszek", "jacek.stasz@skrzynka.com",
				"W zyciu nie kieruje sie zyciowymi mottami", "hasloStaszka22"));
		listOfUsers.add(new User(3L, "Jan", "Kowalski", "jan.kowalski@skrzynka.com", "Zycie jest nowela",
				"hasloKowalskiego33"));

		userDao.replaceUsersList(listOfUsers);

	}

	@Configuration
	static class UserProfileManagerTestContextConfiguration {
		@Bean
		public UserDao userDao() {

			return new UserDao();
		}

		@Bean
		public UserMapper userMapper() {

			return new UserMapper();
		}

		@Bean
		public UserProfileManager userProfileManager() {
			return new UserProfileManager(userMapper(), userDao());

		}

	}

	@Test
	public void shouldReturnEqualProfileInformation() throws NoUserWithThatIDException{

		// given

		// when
		ProfileInformationTO result = userProfileManager.findUserProfileInformation(3L);

		// then
		assertEquals("Jan", result.getFirstName());
		assertEquals("Kowalski", result.getLastName());
		assertEquals("jan.kowalski@skrzynka.com", result.getEmailAddress());
		assertEquals("Zycie jest nowela", result.getLifeMotto());

	}

	@Test
	public void shouldReturnProfileInformationWithChangedFirstName() throws NoUserWithThatIDException{

		// given

		// when
		ProfileInformationTO result = userProfileManager.changeFirstName(2L, "Jacunio");

		// then
		assertEquals("Jacunio", result.getFirstName());
		assertEquals("Staszek", result.getLastName());
		assertEquals("jacek.stasz@skrzynka.com", result.getEmailAddress());
		assertEquals("W zyciu nie kieruje sie zyciowymi mottami", result.getLifeMotto());

	}

	@Test
	public void shouldReturnProfileInformationWithChangedLastName() throws NoUserWithThatIDException{

		// given

		// when
		ProfileInformationTO result = userProfileManager.changeLastName(2L, "Stanislawski");

		// then
		assertEquals("Jacek", result.getFirstName());
		assertEquals("Stanislawski", result.getLastName());
		assertEquals("jacek.stasz@skrzynka.com", result.getEmailAddress());
		assertEquals("W zyciu nie kieruje sie zyciowymi mottami", result.getLifeMotto());

	}

	@Test
	public void shouldReturnProfileInformationWithChangedEMailAddress() throws NoUserWithThatIDException{

		// given

		// when
		ProfileInformationTO result = userProfileManager.changeEMail(3L, "jan.kiedyskowalski@skrzynka.com");

		// then
		assertEquals("Jan", result.getFirstName());
		assertEquals("Kowalski", result.getLastName());
		assertEquals("jan.kiedyskowalski@skrzynka.com", result.getEmailAddress());
		assertEquals("Zycie jest nowela", result.getLifeMotto());

	}

	@Test
	public void shouldReturnProfileInformationWithChangedLifeMotto() throws NoUserWithThatIDException{

		// given

		// when
		ProfileInformationTO result = userProfileManager.changeLifeMotto(1L, "Najzyciowsze zyciowe motto zycia");

		// then
		assertEquals("Jan", result.getFirstName());
		assertEquals("Nowak", result.getLastName());
		assertEquals("jan.nowak@skrzynka.com", result.getEmailAddress());
		assertEquals("Najzyciowsze zyciowe motto zycia", result.getLifeMotto());

	}

	@Test
	public void shouldChangeUsersPassword() throws NoUserWithThatIDException{

		// given

		// when
		userProfileManager.changePassword(2L, "hasloStaszka22", "hasloStanislawa22");

		// then
		User userWhoChangedHisPassoword = userDao.findOneUserEntity(2L);

		assertEquals("hasloStanislawa22", userWhoChangedHisPassoword.getPassword());

	}

	@Test
	public void shouldThrowIllegalArgumentExceptionWhenUserTriesToChangeHisPassword() throws NoUserWithThatIDException{

		// given

		// when
		boolean exceptionThrown = false;
		try {
			userProfileManager.changePassword(2L, "hasloStacha22", "hasloStanislawa22");
		} catch (IllegalArgumentException e) {
			exceptionThrown = true;
		}

		// then
		assertTrue(exceptionThrown);
		User userWhoChangedHisPassoword = userDao.findOneUserEntity(2L);
		assertEquals("hasloStaszka22", userWhoChangedHisPassoword.getPassword());

	}

}
