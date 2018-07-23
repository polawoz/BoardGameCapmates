package com.jstk.data;

import com.jstk.enums.DayOfTheWeek;

public class AvailabilityPeriod {
	
	private DayOfTheWeek dayOfTheWeek;
	private int periodBegginingMinute;
	private int periodEndingMinute;
	
	
	public AvailabilityPeriod(DayOfTheWeek dayOfTheWeek, String beggingingTime, String endingTime){
		
		this.dayOfTheWeek=dayOfTheWeek;
		
		//podzielic Stringa na liczby i policzyc minuty
		//na tej podstawie bedziemy porownywac czy ktos sie miesci w przedziale dostepnosci
		
		
	}
	
	

}
