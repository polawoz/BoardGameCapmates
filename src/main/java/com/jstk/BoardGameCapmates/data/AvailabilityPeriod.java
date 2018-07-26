package com.jstk.BoardGameCapmates.data;

import com.jstk.BoardGameCapmates.enums.DayOfTheWeek;

public class AvailabilityPeriod {

	private Long userID;
	private DayOfTheWeek dayOfTheWeek;
	private Time beggingingTime;
	private Time endingTime;
	private int periodBegginingMinute;
	private int periodEndingMinute;
	private String comment;

	public Time getBeginningTime() {
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

	public AvailabilityPeriod(Long userID, DayOfTheWeek dayOfTheWeek, Time beggingingTime, Time endingTime) {
		this.userID = userID;
		this.dayOfTheWeek = dayOfTheWeek;
		this.beggingingTime = beggingingTime;
		this.endingTime = endingTime;
		this.periodBegginingMinute = this.beggingingTime.getHour() * 60 + this.beggingingTime.getMinute();
		this.periodEndingMinute = this.endingTime.getHour() * 60 + this.endingTime.getMinute();

	}

	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public DayOfTheWeek getDayOfTheWeek() {
		return dayOfTheWeek;
	}

	public void setDayOfTheWeek(DayOfTheWeek dayOfTheWeek) {
		this.dayOfTheWeek = dayOfTheWeek;
	}

	public int getPeriodBegginingMinute() {
		return periodBegginingMinute;
	}

	public void setPeriodBegginingMinute(int periodBegginingMinute) {
		this.periodBegginingMinute = periodBegginingMinute;
	}

	public int getPeriodEndingMinute() {
		return periodEndingMinute;
	}

	public void setPeriodEndingMinute(int periodEndingMinute) {
		this.periodEndingMinute = periodEndingMinute;
	}

	public String getComment() {

		return comment;
	}

	public void setComment(String comment) {

		this.comment = comment;
	}

}
