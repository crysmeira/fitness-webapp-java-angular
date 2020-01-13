package com.fitnesswebapp.domain.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.fitnesswebapp.domain.exception.FoodDiaryEntryAlreadyExistsException;
import com.fitnesswebapp.domain.exception.InvalidInputException;
import com.fitnesswebapp.domain.exception.UserNotFoundException;
import com.fitnesswebapp.domain.model.fitness.FoodDiaryEntry;
import com.fitnesswebapp.domain.model.fitness.User;
import com.fitnesswebapp.domain.repository.FoodDiaryEntryRepository;
import com.fitnesswebapp.domain.service.FoodDiaryEntryService;
import com.fitnesswebapp.utils.BeanNames;
import com.fitnesswebapp.utils.ErrorCodes;

/**
 * Implementation of {@link FoodDiaryEntryService}.
 *
 * @author Crystiane Meira
 */
@Service(BeanNames.FOOD_DIARY_ENTRY_SERVICE)
public class FoodDiaryEntryServiceImpl implements FoodDiaryEntryService {

	private static final Logger logger = LogManager.getLogger(FoodDiaryEntryServiceImpl.class);

	private final FoodDiaryEntryRepository foodDiaryEntryRepository;

	@Autowired
	public FoodDiaryEntryServiceImpl(final FoodDiaryEntryRepository foodDiaryEntryRepository) {
		this.foodDiaryEntryRepository = foodDiaryEntryRepository;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<FoodDiaryEntry> saveFoodDiaryEntries(final List<FoodDiaryEntry> foodDiaryEntries, final User user) {
		if (foodDiaryEntries == null || foodDiaryEntries.isEmpty()) {
			throw new InvalidInputException(HttpStatus.BAD_REQUEST, ErrorCodes.ERROR_404002);
		}
		if (user == null) {
			throw new UserNotFoundException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCodes.ERROR_500016);
		}

		LocalDate today = LocalDate.now();
		today = LocalDate.now();
		if (isThereFoodDiaryForToday(user.getUserId(), today)) {
			throw new FoodDiaryEntryAlreadyExistsException(HttpStatus.CONFLICT, ErrorCodes.ERROR_409001, new String[] {today.toString()});
		}

		return foodDiaryEntries.stream()
							.map(foodDiaryEntry -> {
								foodDiaryEntry.setDate(LocalDate.now());
								foodDiaryEntry.setUser(user);
								return foodDiaryEntryRepository.save(foodDiaryEntry);
							})
							.collect(Collectors.toList());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<FoodDiaryEntry> getFoodDiaryEntriesForToday(final User user) {
		if (user == null) {
			throw new UserNotFoundException(HttpStatus.NOT_FOUND, ErrorCodes.ERROR_404001);
		}

		final LocalDate today = LocalDate.now();
		logger.debug("Retrieving food diary for day: {}.", today);

		List<FoodDiaryEntry> foodDiaryEntries = foodDiaryEntryRepository.findFoodDiaryEntryByUserAndDate(user.getUserId(), today);
		if (foodDiaryEntries == null) {
			foodDiaryEntries = new ArrayList<>();
		}

		return foodDiaryEntries;
	}

	/*
	 * Checks if there is already a food diary saved in the database for the current day.
	 */
	private boolean isThereFoodDiaryForToday(final Long userId, final LocalDate today) {
		logger.debug("Checking if there is already a food diary for date {}.", today);

		return !CollectionUtils.isEmpty(foodDiaryEntryRepository.findFoodDiaryEntryByUserAndDate(userId, today));
	}
}
