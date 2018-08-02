package com.jstk.BoardGameCapmates.controllersTests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.util.ArrayList;
import java.util.List;

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

import com.jstk.BoardGameCapmates.BoardGameCapmatesApplication;
import com.jstk.BoardGameCapmates.controllers.GameCollectionManagerController;
import com.jstk.BoardGameCapmates.data.GameTypeTO;
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
	public void testShouldFindGameByParameters() throws Exception {

		// given

		List<GameTypeTO> searchList = new ArrayList<>();
		GameTypeTO fourthGameType = new GameTypeTO("Europa", 3, 10);
		GameTypeTO thirdGameType = new GameTypeTO("Eurobiznes", 3, 6);

		searchList.add(fourthGameType);
		searchList.add(thirdGameType);

		Mockito.when(gameCollectionManagerServiceMock.findGamesByParameters(Mockito.anyString(), Mockito.anyInt(),
				Mockito.anyInt())).thenReturn(searchList);

		String expectedJson = "[" + "{" + "\"name\":\"Europa\"," + "\"minimumNumberOfPlayers\":3,"
				+ "\"maximumNumberOfPlayers\":10" + "}," +

		"{" + "\"name\":\"Eurobiznes\"," + "\"minimumNumberOfPlayers\":3," + "\"maximumNumberOfPlayers\":6" + "}" + "]";

		// when
		ResultActions resultActions = mockMvc.perform(get("/findGame?name=Euro"));

		// then
		Mockito.verify(gameCollectionManagerServiceMock, Mockito.times(1)).findGamesByParameters(Mockito.anyString(),
				Mockito.anyInt(), Mockito.anyInt());

		resultActions.andExpect(content().json(expectedJson, false));

	}

}
