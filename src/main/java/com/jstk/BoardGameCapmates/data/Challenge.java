package com.jstk.BoardGameCapmates.data;

import com.jstk.BoardGameCapmates.enums.DayOfTheWeek;

public class Challenge {
	
	private Long challengerUserID;
	private Long opponentUserID;
	private DayOfTheWeek dayOfTheWeek;
	private Time beginningTime;
	private Time endingTime;
	
	
	public Challenge(Long challengerUserID, Long opponentUserID, DayOfTheWeek dayOfTheWeek, Time beginningTime,
			Time endingTime) {
		this.challengerUserID = challengerUserID;
		this.opponentUserID = opponentUserID;
		this.dayOfTheWeek = dayOfTheWeek;
		this.beginningTime = beginningTime;
		this.endingTime = endingTime;
	}


	public Long getChallengerUserID() {
		return challengerUserID;
	}


	public Long getOpponentUserID() {
		return opponentUserID;
	}


	public DayOfTheWeek getDayOfTheWeek() {
		return dayOfTheWeek;
	}


	public Time getBeginingTime() {
		return beginningTime;
	}


	public Time getEndingTime() {
		return endingTime;
	}
	
	

}
