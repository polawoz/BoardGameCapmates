package com.jstk.BoardGameCapmates;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;

import com.jstk.BoardGameCapmates.repository.UserDao;
import com.jstk.BoardGameCapmates.services.PlayabilityManager;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AspectTesting {

	@Autowired
	private UserDao userDao;
	@Autowired
	private PlayabilityManager playabilityManager;

	@Test
	public void shouldTestFindUserEntity() {

		// given
		userDao.findOneUserEntity(3L);

	}

	@Test
	public void shouldCountTimeElapsed() {

		// given
		playabilityManager.createListOfPossibleChallenges(1L);

	}

}
