package com.jstk.BoardGameCapmates;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jstk.BoardGameCapmates.repository.UserDao;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {
	
	
	@Autowired
	private UserDao userDao;
	
	@Test
	public void shouldTestFindUserEntity(){
		
		//given
		userDao.findOneUserEntity(3L);
		
		
		
		
	}
	
	

}
