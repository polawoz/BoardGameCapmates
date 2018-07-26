package com.jstk.BoardGameCapmates.services;

import java.util.ArrayList;
import java.util.List;

import com.jstk.BoardGameCapmates.data.AvailabilityPeriod;
import com.jstk.BoardGameCapmates.data.AvailabilityPeriodTO;
import com.jstk.BoardGameCapmates.data.Challenge;
import com.jstk.BoardGameCapmates.data.Time;
import com.jstk.BoardGameCapmates.data.User;
import com.jstk.BoardGameCapmates.mappers.PlayabilityMapper;
import com.jstk.BoardGameCapmates.repository.CancelledAvailabilityPeriodsDao;
import com.jstk.BoardGameCapmates.repository.UserDao;

public class PlayabilityManager {

	private final PlayabilityMapper playabilityMapper;
	private final UserDao userDao;
	private final CancelledAvailabilityPeriodsDao cancelledAvailabilityPeriodsDao;

	public PlayabilityManager(PlayabilityMapper playabilityMapper, UserDao userDao,
			CancelledAvailabilityPeriodsDao cancelledAvailabilityPeriodsDao) {
		this.playabilityMapper = playabilityMapper;
		this.userDao = userDao;
		this.cancelledAvailabilityPeriodsDao = cancelledAvailabilityPeriodsDao;
	}

	public List<AvailabilityPeriod> findUsersAvailabilityPeriodList(Long userID) {
		User searchedUser = userDao.findOneUserEntity(userID);
		List<AvailabilityPeriod> usersAvailabilityPeriodList = playabilityMapper
				.copyUsersAvailabilityPeriodList(searchedUser);

		return usersAvailabilityPeriodList;

	}

	public List<AvailabilityPeriod> findUsersCancelledAvailabilityPeriodList(Long userID) {

		List<AvailabilityPeriod> usersCancelledAvailabilityPeriodsList = cancelledAvailabilityPeriodsDao
				.getUsersCancelledAvailabilityPeriodsList(userID);

		List<AvailabilityPeriod> cancelledAvailabilityPeriodListTO = playabilityMapper
				.createUsersCancelledAvailabilityPeriodsList(usersCancelledAvailabilityPeriodsList);

		return cancelledAvailabilityPeriodListTO;
	}

	public void addAvailabilityPeriod(Long userID, AvailabilityPeriodTO newAvailabilityPeriodTO) {

		User searchedUser = userDao.findOneUserEntity(userID);

		AvailabilityPeriod availabilityPeriodEntityFromTO = playabilityMapper
				.createAvailabilityPeriodEntityFromTO(userID, newAvailabilityPeriodTO);

		searchedUser.getAvailabilityPeriodList().add(availabilityPeriodEntityFromTO);

	}

	public void editAvailabilityPeriod(Long userID, AvailabilityPeriodTO availabilityPeriodToChangeTO,
			AvailabilityPeriodTO availabilityPeriodAfterChangesTO) {

		AvailabilityPeriod entityCopyToChange = playabilityMapper.createAvailabilityPeriodEntityFromTO(userID,
				availabilityPeriodToChangeTO);

		AvailabilityPeriod entityAfterChanges = playabilityMapper.createAvailabilityPeriodEntityFromTO(userID,
				availabilityPeriodAfterChangesTO);

		userDao.changeUsersAvailabilityPeriod(entityCopyToChange, entityAfterChanges);

	}

	public void removeAvailabilityPeriod(Long userID, AvailabilityPeriodTO availabilityPeriodToRemoveTO,
			String comment) {

		AvailabilityPeriod entityCopyToRemove = playabilityMapper.createAvailabilityPeriodEntityFromTO(userID,
				availabilityPeriodToRemoveTO);

		AvailabilityPeriod searchedEntityInRepo = userDao.findAvailabilityPeriod(entityCopyToRemove);

		if (comment != null) {
			searchedEntityInRepo.setComment(comment);
		}

		userDao.findOneUserEntity(userID).getAvailabilityPeriodList().remove(searchedEntityInRepo);

		cancelledAvailabilityPeriodsDao.getUsersCancelledAvailabilityPeriodsList(userID).add(searchedEntityInRepo);

	}

	public List<Challenge> createListOfPossibleChallenges(Long userID) {

		List<Challenge> listOfPossibleChallenges = new ArrayList<>();
		
		User userLookingForChallengesFromRepo = userDao.findOneUserEntity(userID);
		
		List<User> usersListFromDao = userDao.findUsersList();
		
		
		User currentlyCheckedUser=null;
		for(int i=0; i<usersListFromDao.size(); i++){
			
			currentlyCheckedUser= usersListFromDao.get(i);
			if(currentlyCheckedUser.equals(userLookingForChallengesFromRepo)){
				continue;
			}
			
			List<Challenge> challengesListWithCurrentlyCheckedUser = 
					checkBothUsersAvailabilityPeriodLists(userLookingForChallengesFromRepo, currentlyCheckedUser);
			
			listOfPossibleChallenges.addAll(challengesListWithCurrentlyCheckedUser);
			
		}
		
		return listOfPossibleChallenges;
	}

	private List<Challenge> checkBothUsersAvailabilityPeriodLists(User userLookingForChallengesFromRepo,
			User currentlyCheckedUser) {

		
		List<Challenge> listOfPossibleChallengesOfTwoCurrentlyCheckedUsers= new ArrayList<>();
		
		List<AvailabilityPeriod> usersLookingForChallengesAvailabilityPeriodsList= 
				userLookingForChallengesFromRepo.getAvailabilityPeriodList();
		
		List<AvailabilityPeriod> currentlyCheckedUserAvailabilityPeriodsList= 
				currentlyCheckedUser.getAvailabilityPeriodList();

		for(int i=0; i<usersLookingForChallengesAvailabilityPeriodsList.size();i++){
			
			for(int j=0; j<currentlyCheckedUserAvailabilityPeriodsList.size();i++){
				
				AvailabilityPeriod currentlyCheckedAvailabilityPeriodOfUserLookingForChallenges=
				usersLookingForChallengesAvailabilityPeriodsList.get(i);
				
				AvailabilityPeriod currentlyCheckedAvailabilityPeriodOfCurrentlyCheckedOpponent=
						currentlyCheckedUserAvailabilityPeriodsList.get(j);
				
				Challenge challenge = createNewChallengeIfPossible(currentlyCheckedAvailabilityPeriodOfUserLookingForChallenges,
						currentlyCheckedAvailabilityPeriodOfCurrentlyCheckedOpponent);
				
				if(challenge.equals(null)){
					continue;
				}
				listOfPossibleChallengesOfTwoCurrentlyCheckedUsers.add(challenge);
				
				
			}
			
		}
		return null;
		
	}

	private Challenge createNewChallengeIfPossible(
			AvailabilityPeriod currentlyCheckedAvailabilityPeriodOfUserLookingForChallenges,
			AvailabilityPeriod currentlyCheckedAvailabilityPeriodOfCurrentlyCheckedOpponent) {
		
		if(!currentlyCheckedAvailabilityPeriodOfUserLookingForChallenges.getDayOfTheWeek()
				.equals(currentlyCheckedAvailabilityPeriodOfCurrentlyCheckedOpponent.getDayOfTheWeek())){
			return null;	
		}
		
		int beggingingTimeDifference = currentlyCheckedAvailabilityPeriodOfUserLookingForChallenges.getPeriodBegginingMinute() -
				currentlyCheckedAvailabilityPeriodOfCurrentlyCheckedOpponent.getPeriodBegginingMinute();
		
		int endingTimeDifference = currentlyCheckedAvailabilityPeriodOfUserLookingForChallenges.getPeriodEndingMinute() -
				currentlyCheckedAvailabilityPeriodOfCurrentlyCheckedOpponent.getPeriodEndingMinute();
		
	
		
		if(beggingingTimeDifference==0 && endingTimeDifference==0){
			return new Challenge(currentlyCheckedAvailabilityPeriodOfUserLookingForChallenges.getUserID(),
					currentlyCheckedAvailabilityPeriodOfCurrentlyCheckedOpponent.getUserID(),
					currentlyCheckedAvailabilityPeriodOfUserLookingForChallenges.getDayOfTheWeek(),
					currentlyCheckedAvailabilityPeriodOfUserLookingForChallenges.getBeggingingTime(),
					currentlyCheckedAvailabilityPeriodOfUserLookingForChallenges.getEndingTime());
			
		}
		
		int possibleChallengeStartTimeMinute; 
		int possibleChallengeEndTimeMinute;
		
		//czyli looking zaczyna pozniej niz opponent
		if(beggingingTimeDifference>0){
			
			possibleChallengeStartTimeMinute=currentlyCheckedAvailabilityPeriodOfUserLookingForChallenges.getPeriodBegginingMinute();
		}
		else{
			possibleChallengeStartTimeMinute=currentlyCheckedAvailabilityPeriodOfCurrentlyCheckedOpponent.getPeriodBegginingMinute();
		}
		
		//czyli looking konczy pozniej niz opponent
		if(endingTimeDifference>0){
			possibleChallengeEndTimeMinute= currentlyCheckedAvailabilityPeriodOfCurrentlyCheckedOpponent.getPeriodEndingMinute();
		}
		else{
			possibleChallengeEndTimeMinute=currentlyCheckedAvailabilityPeriodOfUserLookingForChallenges.getPeriodEndingMinute();
		}
		
		
		int challengeDurationInMinutes = possibleChallengeEndTimeMinute-possibleChallengeStartTimeMinute;
		
		
		if(challengeDurationInMinutes<60){
			return null;
		}
		else{
			double dividingRestStart = ((double)possibleChallengeStartTimeMinute)%(60d);
			int minutesStart = (int) Math.round(60d * dividingRestStart);
			int hourStart = possibleChallengeStartTimeMinute/60;
			
			Time begginingTime = new Time(hourStart, minutesStart);
			

			double dividingRestEnd = ((double)possibleChallengeEndTimeMinute)%(60d);
			int minutesEnd = (int) Math.round(60d * dividingRestEnd);
			int hourEnd = possibleChallengeEndTimeMinute/60;
			
			Time endingTime = new Time(hourEnd, minutesEnd);
			
			return new Challenge(currentlyCheckedAvailabilityPeriodOfUserLookingForChallenges.getUserID(),
					currentlyCheckedAvailabilityPeriodOfCurrentlyCheckedOpponent.getUserID(),
					currentlyCheckedAvailabilityPeriodOfUserLookingForChallenges.getDayOfTheWeek(),
					begginingTime,endingTime);
			
			
		}

	}

}
