package com.jstk.BoardGameCapmates;

import static org.junit.Assert.*;

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

import com.jstk.BoardGameCapmates.data.AvailabilityPeriod;
import com.jstk.BoardGameCapmates.data.AvailabilityPeriodTO;
import com.jstk.BoardGameCapmates.data.Challenge;
import com.jstk.BoardGameCapmates.data.Time;
import com.jstk.BoardGameCapmates.data.User;
import com.jstk.BoardGameCapmates.enums.DayOfTheWeek;
import com.jstk.BoardGameCapmates.mappers.PlayabilityMapper;
import com.jstk.BoardGameCapmates.repository.CancelledAvailabilityPeriodsDao;
import com.jstk.BoardGameCapmates.repository.UserDao;
import com.jstk.BoardGameCapmates.services.PlayabilityManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class PlayabilityManagerTest {

	
	@Autowired
	private PlayabilityMapper playabilityMapper;
	@Autowired
	private UserDao userDao;
	@Autowired
	private CancelledAvailabilityPeriodsDao cancelledAvailabilityPeriodsDao;
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
		
		cancelledAvailabilityPeriodsDao.resetRepo();
		AvailabilityPeriod cancelledAvailabilityPeriod = new AvailabilityPeriod(1L, DayOfTheWeek.MONDAY, new Time(15,30), new Time(16,30));
		cancelledAvailabilityPeriod.setComment("Ide do denstysty");
		cancelledAvailabilityPeriodsDao.getListOfCancelledAvailabilityPeriods().add(cancelledAvailabilityPeriod);
		
	
		
		
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
	public void shouldFindUsersCancelledAvailabilityPeriodList(){
		
		
		//when
		List<AvailabilityPeriod> usersCancelledAvailabilityPeriodList =
				playabilityManager.findUsersCancelledAvailabilityPeriodList(1L);
		
		//then
		assertTrue(usersCancelledAvailabilityPeriodList.size()==1);
		assertTrue(usersCancelledAvailabilityPeriodList.get(0).getComment().equals("Ide do denstysty"));
		
		
	}
	
	
	@Test
	public void shouldAddAvailabilityPeriod(){
		
		
		//when
		AvailabilityPeriodTO newAvailabilityPeriodTO = new AvailabilityPeriodTO(DayOfTheWeek.THURSDAY, new Time(17,45), new Time(19,20));
		playabilityManager.addAvailabilityPeriod(3L, newAvailabilityPeriodTO);
		
		//then
		List<AvailabilityPeriod> usersList = userDao.findOneUserEntity(3L).getAvailabilityPeriodList();
		assertTrue(usersList.get(usersList.size()-1).getDayOfTheWeek().equals(newAvailabilityPeriodTO.getDayOfTheWeek()));
		
	}
	
	
	
	//@Test
	public void shouldEditAvailabilityPeriod(){
		
		//when
		AvailabilityPeriodTO availabilityPeriodToChangeTO = new AvailabilityPeriodTO(DayOfTheWeek.MONDAY, new Time(13, 30), new Time(18,30));
		AvailabilityPeriodTO availabilityPeriodAfterChangesTO = new AvailabilityPeriodTO(null, null, new Time(17,15));
		
		playabilityManager.editAvailabilityPeriod(1L, availabilityPeriodToChangeTO, availabilityPeriodAfterChangesTO);
		
		//then
		List<AvailabilityPeriod> usersList = userDao.findOneUserEntity(1L).getAvailabilityPeriodList();
		assertTrue(usersList.get(0).getEndingTime().equals(availabilityPeriodAfterChangesTO.getEndingTime()));
		assertTrue(usersList.get(0).getDayOfTheWeek().equals(DayOfTheWeek.MONDAY));

		
		
		
	}
	
	
	@Test
	public void shouldRemoveAvailabilityPeriod(){
		
		AvailabilityPeriodTO availabilityPeriodToRemoveTO = new AvailabilityPeriodTO(DayOfTheWeek.MONDAY, new Time(13,30), new Time(18,30));
		//when

		playabilityManager.removeAvailabilityPeriod(1L, availabilityPeriodToRemoveTO, "Ide do dentysty");
		
		//then
		assertTrue(userDao.findOneUserEntity(1L).getAvailabilityPeriodList().isEmpty());
		assertTrue(cancelledAvailabilityPeriodsDao.getUsersCancelledAvailabilityPeriodsList(1L).size()==1);
		assertTrue(cancelledAvailabilityPeriodsDao.getUsersCancelledAvailabilityPeriodsList(1L).get(0).getComment().equals("Ide do dentysty"));
		
		
		
	}
	
	
	
	
	
	
	
	@Test
	public void shouldReturnListWithPossibleChallenges(){
		
		//given
		
		
		
		//when
		List<Challenge> challengesList = playabilityManager.createListOfPossibleChallenges(1L);
		
		
		
		
		//then
		assertTrue(challengesList.size()==2);
		assertTrue(challengesList.get(0).getChallengerUserID()==1L);
		assertTrue(challengesList.get(1).getChallengerUserID()==1L);
		assertTrue(challengesList.get(0).getOpponentUserID()==2L);
		assertTrue(challengesList.get(1).getOpponentUserID()==3L);
		assertTrue(challengesList.get(0).getBeginingTime().getHour()==15 &&
				challengesList.get(0).getBeginingTime().getMinute()==30);
		assertTrue(challengesList.get(0).getEndingTime().getHour()==16 &&
				challengesList.get(0).getEndingTime().getMinute()==30);

		
		
		
	}
	
	
	
	
	
}
