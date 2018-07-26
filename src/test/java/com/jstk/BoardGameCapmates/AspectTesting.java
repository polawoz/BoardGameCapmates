package com.jstk.BoardGameCapmates;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import com.jstk.BoardGameCapmates.data.AvailabilityPeriod;
import com.jstk.BoardGameCapmates.data.Time;
import com.jstk.BoardGameCapmates.data.User;
import com.jstk.BoardGameCapmates.enums.DayOfTheWeek;
import com.jstk.BoardGameCapmates.mappers.PlayabilityMapper;
import com.jstk.BoardGameCapmates.repository.CancelledAvailabilityPeriodsDao;
import com.jstk.BoardGameCapmates.repository.UserDao;
import com.jstk.BoardGameCapmates.services.PlayabilityManager;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AspectTesting {
	
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private PlayabilityManager playabilityManager;
	
	
	@Before
	public void reset(){
	
		
		List<User> listOfUsers = new ArrayList<>();
		User userOne = new User(1L, "Jan", "Nowak", "jan.nowak@skrzynka.com", "Najzyciowsze zyciowe motto", "hasloNowaka11");
		userOne.getAvailabilityPeriodList().add(new AvailabilityPeriod(1L, DayOfTheWeek.MONDAY, new Time(13, 30), new Time(18,30)));
		
		listOfUsers.add(userOne);
		User userTwo = new User(2L, "Jacek", "Staszek", "jacek.stasz@skrzynka.com",
				"W zyciu nie kieruje sie zyciowymi mottami", "hasloStaszka22");
		userTwo.getAvailabilityPeriodList().add(new AvailabilityPeriod(2L, DayOfTheWeek.MONDAY, new Time(15, 30), new Time(16,30)));
		listOfUsers.add(userTwo);
		
		User userThree = new User(3L, "Jan", "Kowalski", "jan.kowalski@skrzynka.com", "Zycie jest nowela",
				"hasloKowalskiego33");
		userThree.getAvailabilityPeriodList().add(new AvailabilityPeriod(3L, DayOfTheWeek.MONDAY, new Time(14, 30), new Time(16,30)));
		listOfUsers.add(userThree);

		userDao.replaceUsersList(listOfUsers);
		

		
	
		
		
	}
	
	
	
	@Configuration
	static class PlayabilityManagerTestConfigiuration{
		
		@Bean
		public PlayabilityMapper playabilityMapper(){
			
			return new PlayabilityMapper();
		}
		
		
		@Bean
		public UserDao userDao(){
			
			return new UserDao();
		}
		
		
		@Bean 
		public CancelledAvailabilityPeriodsDao cancelledAvailabilityPeriodsDao(){
			return new CancelledAvailabilityPeriodsDao();
			
		}
		
		@Bean
		public PlayabilityManager playabilityManager(){
			return new PlayabilityManager(playabilityMapper(), userDao(), cancelledAvailabilityPeriodsDao());
		}
		
		
		
	}
	
	
	@Test
	public void shouldTestFindUserEntity(){
		
		//given
		userDao.findOneUserEntity(3L);
		
		
		
		
	}
	
	
	@Test
	public void shouldCountTimeElapsed(){
		
		//given
		playabilityManager.createListOfPossibleChallenges(1L);
		
		
	}
	
	

}
