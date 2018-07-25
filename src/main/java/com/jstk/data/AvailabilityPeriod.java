package com.jstk.data;

import com.jstk.enums.DayOfTheWeek;

public class AvailabilityPeriod {
	
	//userID
	private DayOfTheWeek dayOfTheWeek;
	private int periodBegginingMinute;
	private int periodEndingMinute;
	private String status;
	private String comment;
	
	
	
	public AvailabilityPeriod(DayOfTheWeek dayOfTheWeek, String beggingingTime, String endingTime){
		
		this.dayOfTheWeek=dayOfTheWeek;
		
		//podzielic Stringa na liczby i policzyc minuty
		//na tej podstawie bedziemy porownywac czy ktos sie miesci w przedziale dostepnosci
		
		
	}
	
	

}
