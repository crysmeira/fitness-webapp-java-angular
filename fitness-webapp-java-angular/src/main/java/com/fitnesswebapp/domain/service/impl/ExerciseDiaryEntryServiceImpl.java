package com.fitnesswebapp.domain.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fitnesswebapp.domain.exception.FitnessException;
import com.fitnesswebapp.domain.model.fitness.ExerciseDiaryEntry;
import com.fitnesswebapp.domain.model.fitness.User;
import com.fitnesswebapp.domain.repository.ExerciseDiaryEntryRepository;
import com.fitnesswebapp.domain.service.ExerciseDiaryEntryService;
import com.fitnesswebapp.utils.BeanNames;
import com.fitnesswebapp.utils.ErrorCodes;

/**
 * Implementation of {@link ExerciseDiaryEntryService}.
 *
 * @author Crystiane Meira
 */
@Service(BeanNames.EXERCISE_DIARY_ENTRY_SERVICE)
public class ExerciseDiaryEntryServiceImpl implements ExerciseDiaryEntryService {

	private static final Logger logger = LogManager.getLogger(ExerciseDiaryEntryServiceImpl.class);

	private final ExerciseDiaryEntryRepository exerciseDiaryEntryRepository;

	@Autowired
	public ExerciseDiaryEntryServiceImpl(final ExerciseDiaryEntryRepository exerciseDiaryEntryRepository) {
		this.exerciseDiaryEntryRepository = exerciseDiaryEntryRepository;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ExerciseDiaryEntry saveExerciseDiaryEntry(final ExerciseDiaryEntry exerciseDiaryEntry, final User user) 
			throws FitnessException {
		if (exerciseDiaryEntry == null) {
			throw new FitnessException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCodes.ERROR_500006);
		}
		if (user == null) {
			throw new FitnessException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCodes.ERROR_500014);
		}

		exerciseDiaryEntry.setDate(LocalDate.now());//.minusDays(new Random().nextInt(1000)));
		exerciseDiaryEntry.setUser(user);
		return exerciseDiaryEntryRepository.save(exerciseDiaryEntry);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ExerciseDiaryEntry> getExerciseDiaryForToday(final User user) throws FitnessException {
		if (user == null) {
			throw new FitnessException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCodes.ERROR_500015);
		}

		final LocalDate today = LocalDate.now();

		logger.debug("Retrieving food diary for day: {}.", today);
		return exerciseDiaryEntryRepository.findExerciseDiaryEntryByUserAndDate(user.getUserId(), today);
	}
}
