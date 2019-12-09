package com.fitnesswebapp.domain.service.impl;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fitnesswebapp.api.model.fitness.StatisticsModel;
import com.fitnesswebapp.domain.exception.FitnessException;
import com.fitnesswebapp.domain.model.fitness.ExerciseDiaryEntry;
import com.fitnesswebapp.domain.model.fitness.FoodDiaryEntry;
import com.fitnesswebapp.domain.model.fitness.User;
import com.fitnesswebapp.domain.repository.ExerciseDiaryEntryRepository;
import com.fitnesswebapp.domain.repository.FoodDiaryEntryRepository;
import com.fitnesswebapp.domain.service.StatisticsService;
import com.fitnesswebapp.utils.BeanNames;
import com.fitnesswebapp.utils.ErrorCodes;
import com.fitnesswebapp.utils.FitnessConstants;

/**
 * Implementation of {@link StatisticsService}.
 *
 * @author Crystiane Meira
 */
@Service(BeanNames.STATISTICS_SERVICE)
public class StatisticsServiceImpl implements StatisticsService {

	private static final Logger logger = LogManager.getLogger(StatisticsServiceImpl.class);

	private final FoodDiaryEntryRepository foodDiaryEntryRepository;
	private final ExerciseDiaryEntryRepository exerciseDiaryEntryRepository;

	@Autowired
	public StatisticsServiceImpl(final FoodDiaryEntryRepository foodDiaryEntryRepository, 
			final ExerciseDiaryEntryRepository exerciseDiaryEntryRepository) {
		this.foodDiaryEntryRepository = foodDiaryEntryRepository;
		this.exerciseDiaryEntryRepository = exerciseDiaryEntryRepository;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public StatisticsModel getStatistics(final User user, final int numDays) throws FitnessException {
		if (user == null) {
			throw new FitnessException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCodes.ERROR_500018);
		}
		if (numDays < 0) {
			throw new FitnessException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCodes.ERROR_500019, 
									   new String[] {String.valueOf(numDays)});
		}

		final LocalDate today = LocalDate.now();
		final LocalDate firstDay = LocalDate.now().minusDays(numDays-1);
		logger.debug("Finding statistics from {} to {}.", firstDay, today);

		final List<FoodDiaryEntry> foodDiaryEntries = foodDiaryEntryRepository
													  .findFoodDiaryEntryForLastDDays(user.getUserId(), firstDay, today);
		final List<ExerciseDiaryEntry> exerciseDiaries = exerciseDiaryEntryRepository
														 .findExerciseDiaryEntryForLastDDays(user.getUserId(), firstDay, today);

		final Map<LocalDate, Map<String, Double>> foodData = new HashMap<>();

		foodDiaryEntries.stream().forEach(entry -> {
			final Map<String, Double> nutritionData = new HashMap<>();

			nutritionData.put(FitnessConstants.STATISTICS_CALORIES, 
					          nutritionData.getOrDefault(FitnessConstants.STATISTICS_CALORIES, 0.0) + entry.getCalories());
			nutritionData.put(FitnessConstants.STATISTICS_CARBOHYDRATE, 
					          nutritionData.getOrDefault(FitnessConstants.STATISTICS_CARBOHYDRATE, 0.0) 
					          + entry.getTotalCarbohydrate());
			nutritionData.put(FitnessConstants.STATISTICS_FAT, 
					          nutritionData.getOrDefault(FitnessConstants.STATISTICS_FAT, 0.0) + entry.getTotalFat());
			nutritionData.put(FitnessConstants.STATISTICS_PROTEIN, 
					          nutritionData.getOrDefault(FitnessConstants.STATISTICS_PROTEIN, 0.0) + entry.getTotalProtein());

			foodData.put(entry.getDate(), nutritionData);
		});

		final Map<LocalDate, Map<String, Long>> exerciseData = new HashMap<>();

		exerciseDiaries.stream().forEach(exerciseDiary -> {
			final Map<String, Long> nutritionData = exerciseData.getOrDefault(exerciseDiary.getDate(), new HashMap<>());

			nutritionData.put(FitnessConstants.STATISTICS_CALORIES, 
					          nutritionData.getOrDefault(FitnessConstants.STATISTICS_CALORIES, 0l) 
					          + exerciseDiary.getCalories());
			nutritionData.put(FitnessConstants.STATISTICS_DURATION, 
					          nutritionData.getOrDefault(FitnessConstants.STATISTICS_DURATION, 0l) 
					          + exerciseDiary.getDuration());

			exerciseData.put(exerciseDiary.getDate(), nutritionData);
		});

		final StatisticsModel statistics = new StatisticsModel();
		statistics.setFoodData(foodData);
		statistics.setExerciseData(exerciseData);

		return statistics;
	}
}
