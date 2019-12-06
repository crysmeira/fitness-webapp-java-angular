package com.fitnesswebapp.domain.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fitnesswebapp.domain.exception.FitnessException;
import com.fitnesswebapp.domain.model.fitness.FoodDiary;
import com.fitnesswebapp.domain.model.fitness.FoodDiaryEntry;
import com.fitnesswebapp.domain.model.fitness.User;
import com.fitnesswebapp.domain.repository.FoodDiaryRepository;
import com.fitnesswebapp.domain.service.FoodDiaryService;
import com.fitnesswebapp.utils.BeanNames;
import com.fitnesswebapp.utils.ErrorCodes;

/**
 * Implementation of {@link FoodDiaryService}.
 *
 * @author Crystiane Meira
 */
@Service(BeanNames.FOOD_DIARY_SERVICE)
public class FoodDiaryServiceImpl implements FoodDiaryService {

	private static final Logger logger = LogManager.getLogger(FoodDiaryServiceImpl.class);

	private final FoodDiaryRepository foodDiaryRepository;

	public FoodDiaryServiceImpl(final FoodDiaryRepository diaryRepository) {
		foodDiaryRepository = diaryRepository;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FoodDiary saveFoodDiary(final List<FoodDiaryEntry> foodDiaryEntries, final User user) throws FitnessException {
		if (foodDiaryEntries == null || foodDiaryEntries.isEmpty()) {
			throw new FitnessException(HttpStatus.INTERNAL_SERVER_ERROR.value(), ErrorCodes.ERROR_500007);
		}
		if (user == null) {
			throw new FitnessException(HttpStatus.INTERNAL_SERVER_ERROR.value(), ErrorCodes.ERROR_500016);
		}

		LocalDate today = LocalDate.now();
		today = LocalDate.now();//.minusDays(new Random().nextInt(1000));
		if (isThereFoodDiaryForToday(user.getUserId(), today)) {
			throw new FitnessException(HttpStatus.INTERNAL_SERVER_ERROR.value(), ErrorCodes.ERROR_500008, new String[] {today.toString()});
		}

		final FoodDiary diary = new FoodDiary();
		diary.setFoodDiaryEntries(foodDiaryEntries);
		diary.setDate(today);
		diary.setUser(user);

		return foodDiaryRepository.save(diary);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FoodDiary getFoodDiaryForToday(final User user) throws FitnessException {
		if (user == null) {
			throw new FitnessException(HttpStatus.INTERNAL_SERVER_ERROR.value(), ErrorCodes.ERROR_500017);
		}

		final LocalDate today = LocalDate.now();
		logger.debug("Retrieving food diary for day: {}.", today);

		FoodDiary foodDiary = foodDiaryRepository.findFoodDiaryByUserAndDate(user.getUserId(), today);
		if (foodDiary == null) {
			foodDiary = new FoodDiary();
		}

		return foodDiary;
	}

	/*
	 * Checks if there is already a food diary saved in the database for the current day.
	 *
	 */
	private boolean isThereFoodDiaryForToday(final Long userId, final LocalDate today) {
		logger.debug("Checking if there is already a food diary for date {}.", today);

		return foodDiaryRepository.findFoodDiaryByUserAndDate(userId, today) != null;
	}
}
