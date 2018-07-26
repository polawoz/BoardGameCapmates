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

		List<AvailabilityPeriod> copyOfUsersAvailabilityPeriodList = new ArrayList<>(usersAvailabilityPeriodsList);

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
