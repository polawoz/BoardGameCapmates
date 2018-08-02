package com.jstk.BoardGameCapmates.controllersTests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.jstk.BoardGameCapmates.BoardGameCapmatesApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BoardGameCapmatesApplication.class)
@WebAppConfiguration
public class UserProfileManagerControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void shouldReturnUsersProfileInformation() throws Exception {
		// given
		final String firstName = "Jan";
		final String lastName = "Nowak";
		final String emailAddress = "jan.nowak@skrzynka.com";
		final String lifeMotto = "Najzyciowsze zyciowe motto";

		// when
		ResultActions resultActions = mockMvc.perform(get("/users-profile-information").param("userID", "1"));

		// then
		resultActions.andExpect(status().isOk()).andExpect(jsonPath("firstName").value(firstName))
				.andExpect(jsonPath("lastName").value(lastName)).andExpect(jsonPath("emailAddress").value(emailAddress))
				.andExpect(jsonPath("lifeMotto").value(lifeMotto));

	}

}
