package com.jstk.BoardGameCapmates;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.jstk.BoardGameCapmates.data.ProfileInformationTO;
import com.jstk.BoardGameCapmates.data.User;
import com.jstk.BoardGameCapmates.mappers.UserMapper;
import com.jstk.BoardGameCapmates.repository.UserDao;
import com.jstk.BoardGameCapmates.services.UserProfileManager;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BoardGameCapmatesApplication.class)
public class UserProfileManagerMockTest {
	
	
	
	@Mock
	private UserDao userDaoMock;
	
	@Autowired
	private UserMapper userMapper;

	@InjectMocks
	private UserProfileManager userProfileManagerServiceMock;

	
	
	
	@Before
	public void setUp(){
	
		
		
		
//		MockitoAnnotations.initMocks(userDaoMock);
//
//		Mockito.reset(userDaoMock);
		
		final String firstName = "Andrzej";
		final String lastName = "Piaseczny";
		final String emailAddress = "andrzej.p@skrzynka.com";
		final String lifeMotto = "Co najlepsze to jest dzisiaj";
		
		ReflectionTestUtils.setField(userProfileManagerServiceMock, "userMapper", userMapper); 
		
		User user= new User(7766L, firstName,
				lastName, emailAddress, lifeMotto, "piasek");
		
		Mockito.when(userDaoMock.findOneUserEntity(7766L)).thenReturn(user);
		
		
		
	}
	
	
	@Test
	public void shouldReturnAndrzejPiasecznysProfileInformation(){
		
		
		//given
		final String firstName = "Andrzej";
		final String lastName = "Piaseczny";
		final String emailAddress = "andrzej.p@skrzynka.com";
		final String lifeMotto = "Co najlepsze to jest dzisiaj";
		
		

		// when
		ProfileInformationTO result = userProfileManagerServiceMock.findUserProfileInformation(7766L);
		

		// then
		Assert.assertEquals(firstName, result.getFirstName());
		Assert.assertEquals(lastName, result.getLastName());
		Assert.assertEquals(emailAddress, result.getEmailAddress());
		Assert.assertEquals(lifeMotto, result.getLifeMotto());

	
		
	}
	

}
