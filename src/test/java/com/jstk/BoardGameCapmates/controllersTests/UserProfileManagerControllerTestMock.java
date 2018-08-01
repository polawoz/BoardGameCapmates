package com.jstk.BoardGameCapmates.controllersTests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.jstk.BoardGameCapmates.BoardGameCapmatesApplication;
import com.jstk.BoardGameCapmates.controllers.UserProfileManagerController;
import com.jstk.BoardGameCapmates.data.ProfileInformationTO;
import com.jstk.BoardGameCapmates.services.UserProfileManager;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BoardGameCapmatesApplication.class)
@WebAppConfiguration
public class UserProfileManagerControllerTestMock {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Mock
	private UserProfileManager userProfileManagerServiceMock;

	@Autowired
	private UserProfileManagerController userProfileManagerControllerMock;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(userProfileManagerServiceMock);
		Mockito.reset(userProfileManagerServiceMock);
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		ReflectionTestUtils.setField(userProfileManagerControllerMock, "userProfileManager",
				userProfileManagerServiceMock);

	}

	@Test
	public void shouldReturnUsersProfileInformation() throws Exception {
		// given

		final String firstName = "Andrzej";
		final String lastName = "Piaseczny";
		final String emailAddress = "andrzej.p@skrzynka.com";
		final String lifeMotto = "Najzyciowsze zyciowe motto";

		ProfileInformationTO usersProfileInformation = new ProfileInformationTO(firstName, lastName, emailAddress,
				lifeMotto);

		Mockito.when(userProfileManagerServiceMock.findUserProfileInformation(7766L))
				.thenReturn(usersProfileInformation);

		// when
		ResultActions resultActions = mockMvc.perform(get("/users-profile-information?userID=7766"));
		

		// then
		resultActions.andExpect(status().isOk()).andExpect(jsonPath("firstName").value(firstName))
				.andExpect(jsonPath("lastName").value(lastName)).andExpect(jsonPath("emailAddress").value(emailAddress))
				.andExpect(jsonPath("lifeMotto").value(lifeMotto));
	}
	
	
	@Test
	public void shouldChangeUsersLifeMotto() throws Exception{
		
		//given
		final String firstName = "Andrzej";
		final String lastName = "Piaseczny";
		final String emailAddress = "andrzej.p@skrzynka.com";
		final String lifeMotto = "Nowe zyciowe motto";
		final Long id = 7766L;
		ProfileInformationTO usersProfileInformation = new ProfileInformationTO(firstName, lastName, emailAddress,
				lifeMotto);
		
		Mockito.when(userProfileManagerServiceMock.changeLifeMotto(id, lifeMotto))
		.thenReturn(usersProfileInformation);
		
		//when
		ResultActions resultActions = mockMvc.perform(put("/change-life-motto?userID="+id+"&lifeMotto="+lifeMotto));
		//nazwa sciezki.content()
		
		//then
		resultActions.andExpect(status().isOk()).andExpect(jsonPath("firstName").value(firstName))
		.andExpect(jsonPath("lastName").value(lastName)).andExpect(jsonPath("emailAddress").value(emailAddress))
		.andExpect(jsonPath("lifeMotto").value(lifeMotto));
		
		Mockito.verify(userProfileManagerServiceMock, Mockito.times(1)).changeLifeMotto(id, lifeMotto);  
		
		
		
		
	}

}
