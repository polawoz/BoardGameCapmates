package com.jstk.BoardGameCapmates.controllersTests;

import static org.junit.Assert.*;

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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.jstk.BoardGameCapmates.BoardGameCapmatesApplication;
import com.jstk.BoardGameCapmates.controllers.GameCollectionManagerController;
import com.jstk.BoardGameCapmates.services.GameCollectionManager;



@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BoardGameCapmatesApplication.class)
@WebAppConfiguration
public class GameCollectionManagerControllerTest {

	
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	
	@Mock
	private GameCollectionManager gameCollectionManagerServiceMock;
	
	@Autowired
	private GameCollectionManagerController gameCollectionManagerController;
	
	
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(gameCollectionManagerServiceMock);
		Mockito.reset(gameCollectionManagerServiceMock);
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		ReflectionTestUtils.setField(gameCollectionManagerController, "gameCollectionManager",
				gameCollectionManagerServiceMock);
		
		
		
	}

	@Test
	public void testFindUsersGameCollectionLong() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindUsersGameCollectionLongGameTypeTO() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindGameByParameters() {
		fail("Not yet implemented");
	}

}
