package com.jstk.data;

public class RankingRecordTO {
	
	private Long userID;
	private int pointsSum;
	private int rankingSpot;
	
	public RankingRecordTO(Long userID, int pointsSum, int rankingSpot) {
		this.userID = userID;
		this.pointsSum = pointsSum;
		this.rankingSpot = rankingSpot;
	}

	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public int getPointsSum() {
		return pointsSum;
	}

	public void setPointsSum(int pointsSum) {
		this.pointsSum = pointsSum;
	}

	public int getRankingSpot() {
		return rankingSpot;
	}

	public void setRankingSpot(int rankingSpot) {
		this.rankingSpot = rankingSpot;
	}
	
	

}
