package com.jstk.BoardGameCapmates.data;

import com.jstk.BoardGameCapmates.enums.DayOfTheWeek;

public class Challenge {
	
	private Long challengerUserID;
	private Long opponentUserID;
	private DayOfTheWeek dayOfTheWeek;
	private Time beggingingTime;
	private Time endingTime;
	
	
	public Challenge(Long challengerUserID, Long opponentUserID, DayOfTheWeek dayOfTheWeek, Time beggingingTime,
			Time endingTime) {
		this.challengerUserID = challengerUserID;
		this.opponentUserID = opponentUserID;
		this.dayOfTheWeek = dayOfTheWeek;
		this.beggingingTime = beggingingTime;
		this.endingTime = endingTime;
	}
	
	

}
