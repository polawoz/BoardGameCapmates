package com.jstk.BoardGameCapmates.data;

import com.jstk.BoardGameCapmates.enums.DayOfTheWeek;

public class AvailabilityPeriodTO {
	
	private DayOfTheWeek dayOfTheWeek;
	private Time beggingingTime;
	private Time endingTime;
	
	public AvailabilityPeriodTO(DayOfTheWeek dayOfTheWeek, Time beggingingTime, Time endingTime) {
		this.dayOfTheWeek = dayOfTheWeek;
		this.beggingingTime = beggingingTime;
		this.endingTime = endingTime;
	}

	public DayOfTheWeek getDayOfTheWeek() {
		return dayOfTheWeek;
	}

	public void setDayOfTheWeek(DayOfTheWeek dayOfTheWeek) {
		this.dayOfTheWeek = dayOfTheWeek;
	}

	public Time getBeggingingTime() {
		return beggingingTime;
	}

	public void setBeggingingTime(Time beggingingTime) {
		this.beggingingTime = beggingingTime;
	}

	public Time getEndingTime() {
		return endingTime;
	}

	public void setEndingTime(Time endingTime) {
		this.endingTime = endingTime;
	}
	
	

}
