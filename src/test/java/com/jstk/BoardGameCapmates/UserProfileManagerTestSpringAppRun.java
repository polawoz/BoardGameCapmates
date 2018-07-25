package com.jstk.BoardGameCapmates;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jstk.BoardGameCapmates.data.ProfileInformationTO;
import com.jstk.BoardGameCapmates.data.User;
import com.jstk.BoardGameCapmates.mappers.UserMapper;
import com.jstk.BoardGameCapmates.repository.UserDao;
import com.jstk.BoardGameCapmates.services.UserProfileManager;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserProfileManagerTestSpringAppRun {

	@Autowired
	private UserProfileManager userProfileManager;
	@Autowired
	private UserDao userDao;
	@Autowired
	private UserMapper userMapper;

	
	

	@Test
	public void shouldReturnEqualProfileInformation() {

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
	public void shouldReturnProfileInformationWithChangedFirstName() {

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
	public void shouldReturnProfileInformationWithChangedLastName() {

		// given
	

		// when
		ProfileInformationTO result = userProfileManager.changeLastName(2L, "Stanislawski");

		// then
		assertEquals("Jacunio", result.getFirstName());
		assertEquals("Stanislawski", result.getLastName());
		assertEquals("jacek.stasz@skrzynka.com", result.getEmailAddress());
		assertEquals("W zyciu nie kieruje sie zyciowymi mottami", result.getLifeMotto());

	}

	@Test
	public void shouldReturnProfileInformationWithChangedEMailAddress() {

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
	public void shouldReturnProfileInformationWithChangedLifeMotto() {

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
	public void shouldChangeUsersPassword() {

		// given
	
		
		//when
		userProfileManager.changePassword(2L, "hasloStaszka22", "hasloStanislawa22");
		
		
		//then
		User userWhoChangedHisPassoword = userDao.findOneUserEntity(2L);
		
		assertEquals("hasloStanislawa22", userWhoChangedHisPassoword.getPassword());
	
	}
	
	@Test
	public void shouldThrowIllegalArgumentExceptionWhenUserTriesToChangeHisPassword() {

		// given
		
		//when
		boolean exceptionThrown = false;
		try{
		userProfileManager.changePassword(2L, "hasloStacha22", "hasloStanislawka22");
		}
		catch(IllegalArgumentException e){
			exceptionThrown=true;
		}
		
		//then
		assertTrue(exceptionThrown);
		User userWhoChangedHisPassoword = userDao.findOneUserEntity(2L);
		assertEquals("hasloStanislawa22", userWhoChangedHisPassoword.getPassword());
	
	}
	

}
