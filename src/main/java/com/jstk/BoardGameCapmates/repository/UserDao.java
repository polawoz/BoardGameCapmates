package com.jstk.BoardGameCapmates.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.jstk.BoardGameCapmates.data.AvailabilityPeriod;
import com.jstk.BoardGameCapmates.data.GameType;
import com.jstk.BoardGameCapmates.data.User;

@Repository
public class UserDao {

	private List<User> usersList;
	private static final Logger LOGGER = LoggerFactory.getLogger(UserDao.class);

	public UserDao() {
		this.usersList = new ArrayList<User>();

	}

	// pomocnicze do testow
	public void replaceUsersList(List<User> previouslyCreatedList) {
		this.usersList = previouslyCreatedList;

	}

	
	public List<User> findUsersList(){
		
		return usersList;
	}
	
	
	
	public User findOneUserEntity(Long userID) {

		User searchedUser = usersList.stream().filter(x -> userID.equals(x.getUserID())).findAny().orElse(null);

		LOGGER.info("All books were found repo");

		return searchedUser;
	}

	public void removeGameTypeFromOneUsersGameCollection(Long userID, GameType gameTypeEntityOnlyWithName) {

		List<GameType> usersGameCollection = usersList.stream().filter(x -> userID.equals(x.getUserID())).findAny()
				.orElse(null).getGameCollection();

		GameType searchedGameType = usersGameCollection.stream()
				.filter(x -> gameTypeEntityOnlyWithName.getName().equals(x.getName())).findFirst().orElse(null);

		if (searchedGameType == null) {
			throw new NoSuchElementException("You cannot remove a game type that is not in your collection!");
		} else {
			usersGameCollection.remove(searchedGameType);
		}

	}

	public GameType findGameTypeFromUsersGameCollection(Long userID, GameType gameTypeEntityOnlyWithName) {

		List<GameType> usersGameCollection = usersList.stream().filter(x -> userID.equals(x.getUserID())).findAny()
				.orElse(null).getGameCollection();

		GameType searchedGameType = usersGameCollection.stream()
				.filter(x -> gameTypeEntityOnlyWithName.getName().equals(x.getName())).findFirst().orElse(null);

		return searchedGameType;

	}

	public AvailabilityPeriod findAvailabilityPeriod(AvailabilityPeriod searchedEntity) {

		AvailabilityPeriod searchedPeriodFromRepo = findOneUserEntity(searchedEntity.getUserID())
				.getAvailabilityPeriodList().stream()
				.filter(x -> searchedEntity.getDayOfTheWeek().equals(x.getDayOfTheWeek()))
				.filter(x -> searchedEntity.getPeriodBegginingMinute() == x.getPeriodBegginingMinute())
				.filter(x -> searchedEntity.getPeriodEndingMinute() == x.getPeriodEndingMinute()).findFirst()
				.orElse(null);

		return searchedPeriodFromRepo;

	}

	public void changeUsersAvailabilityPeriod(AvailabilityPeriod entityCopyToChange,
			AvailabilityPeriod entityAfterChanges) {

		AvailabilityPeriod availabilityPeriodToChangeEntity = findAvailabilityPeriod(entityCopyToChange);

		if (!entityAfterChanges.getDayOfTheWeek().equals(null)) {
			availabilityPeriodToChangeEntity.setDayOfTheWeek(entityAfterChanges.getDayOfTheWeek());
		}

		if (!entityAfterChanges.getBeggingingTime().equals(null)) {
			availabilityPeriodToChangeEntity.setBeggingingTime(entityAfterChanges.getBeggingingTime());
		}

		if (!entityAfterChanges.getEndingTime().equals(null)) {
			availabilityPeriodToChangeEntity.setEndingTime(entityAfterChanges.getEndingTime());
		}

	}

}
