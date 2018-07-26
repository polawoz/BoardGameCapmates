package com.jstk.BoardGameCapmates.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.jstk.BoardGameCapmates.data.AvailabilityPeriod;

@Repository
public class CancelledAvailabilityPeriodsDao {
	
	List<AvailabilityPeriod> listOfCancelledAvailabilityPeriods;
	
	public CancelledAvailabilityPeriodsDao(){
		this.listOfCancelledAvailabilityPeriods=new ArrayList<>();
		
	}
	
	
	
	public void addACancelledAvailabilityPeriod(AvailabilityPeriod cancelledAvailabilityPeriod){
		
		listOfCancelledAvailabilityPeriods.add(cancelledAvailabilityPeriod);
		
	}
	
	
	public List<AvailabilityPeriod> getUsersCancelledAvailabilityPeriodsList(Long userID){
		
		List<AvailabilityPeriod> searchedUsersAvailabilityPeriodsList = new ArrayList<>();
				
		searchedUsersAvailabilityPeriodsList = listOfCancelledAvailabilityPeriods.stream()
				.filter(x-> userID.equals(x.getUserID())).collect(Collectors.toList());
		
		return searchedUsersAvailabilityPeriodsList;
		
	}
	
	
	
	public void resetRepo(){
		listOfCancelledAvailabilityPeriods.clear();
	}
	
	
	public void removeCancelledAvailabillityPeriodFrom3MonthsAgo(){
		
		
	}
	
	

}
