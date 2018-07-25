package com.jstk.services;

import java.util.ArrayList;
import java.util.List;

import com.jstk.data.AvailabilityPeriod;
import com.jstk.data.User;
import com.jstk.enums.DayOfTheWeek;

public class PlayabilityManager {
	
	private final PlayabilityMapper playabilityMapper;
	private final UserDao userDao;
	//private final 
	
	
	
	
	public List<AvailabilityPeriod> findUsersAvailabilityPeriodList(Long userID){
		
		
		
		
	}
	
	
	
	public void addAvailabilityPeriod(Long userID){
		
		user.getAvailabilityPeriodSet().add(new AvailabilityPeriod(DayOfTheWeek.MONDAY, "15:30", "18:30"));
		
	}
	
	
	public void editAvailabilityPeriod(Long userID){
		
	
	}
	
	public void removeAvailabilityPeriod(Long userID){
		
		
		AvailabilityPeriod availabilityPeriod = new AvailabilityPeriod(DayOfTheWeek.MONDAY, "15:30", "18:30");
		user.getAvailabilityPeriodSet().remove(availabilityPeriod);
		// co z tym komentarzem
		
		
	}
	
	
	public List<User> showUsersWithSimilarAvailability(Long userID){
		
		List<User> listOfUsersWithSimilarAvailability = new ArrayList<>();
		
		//metoda przeszukujaca w bazie uzytkownikow po ich dostepnosci i zapisywanie tego 
		//na liste
		
		//juz tutaj tworzenie automatycznego challenge
		return listOfUsersWithSimilarAvailability;
	}
	
	
	

}
