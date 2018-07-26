package com.jstk.BoardGameCapmates.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.jstk.BoardGameCapmates.data.AvailabilityPeriod;
import com.jstk.BoardGameCapmates.data.AvailabilityPeriodTO;
import com.jstk.BoardGameCapmates.data.User;

@Component
public class PlayabilityMapper {

	public List<AvailabilityPeriod> copyUsersAvailabilityPeriodList(User user) {

		List<AvailabilityPeriod> listFromRepository = user.getAvailabilityPeriodList();

		return new ArrayList<>(listFromRepository);

	}

	public List<AvailabilityPeriod> createUsersCancelledAvailabilityPeriodsList(
			List<AvailabilityPeriod> usersAvailabilityPeriodsList) {

		List<AvailabilityPeriod> copyOfUsersAvailabilityPeriodList = new ArrayList<>();

		for (int i = 0; i < usersAvailabilityPeriodsList.size(); i++) {
			AvailabilityPeriod availabilityPeriodEntity = usersAvailabilityPeriodsList.get(i);
			AvailabilityPeriod availabilityPeriodTO = new AvailabilityPeriod(availabilityPeriodEntity.getUserID(),
					availabilityPeriodEntity.getDayOfTheWeek(), availabilityPeriodEntity.getBeginningTime(),
					availabilityPeriodEntity.getEndingTime());
			availabilityPeriodTO.setComment(availabilityPeriodEntity.getComment());
			copyOfUsersAvailabilityPeriodList.add(availabilityPeriodTO);

		}

		return copyOfUsersAvailabilityPeriodList;

	}

	public AvailabilityPeriod createAvailabilityPeriodEntityFromTO(Long userID,
			AvailabilityPeriodTO newAvailabilityPeriodTO) {

		AvailabilityPeriod availabilityPeriodEntity = new AvailabilityPeriod(userID,
				newAvailabilityPeriodTO.getDayOfTheWeek(), newAvailabilityPeriodTO.getBeggingingTime(),
				newAvailabilityPeriodTO.getEndingTime());

		return availabilityPeriodEntity;
	}

}
