package com.fitnesswebapp.domain.service;

import java.util.List;

import com.fitnesswebapp.domain.exception.FitnessException;
import com.fitnesswebapp.domain.model.fitness.ExerciseDiaryEntry;
import com.fitnesswebapp.domain.model.fitness.User;

/**
 * Interface for service to operate on exercise diary objects.
 *
 * @author Crystiane Meira
 */
public interface ExerciseDiaryEntryService {

	/**
	 * Saves the exercise diary entry.
	 *
	 * @param exerciseDiaryEntry The exercise diary entry containing information for an exercise.
	 * @param user The user logged in.
	 * @return The ExerciseDiaryEntry saved using the given exercise diary entry.
	 * @throws FitnessException If exerciseDiaryEntry or user is null.
	 */
	public ExerciseDiaryEntry saveExerciseDiaryEntry(ExerciseDiaryEntry exerciseDiaryEntry, User user) throws FitnessException;

	/**
	 * Gets the exercise diary for the current day.
	 *
	 * @param user The user logged in.
	 * @return The FoodDiary or null if there is no exercise diary for the current day.
	 * @throws FitnessException If user is null.
	 */
	public List<ExerciseDiaryEntry> getExerciseDiaryForToday(User user) throws FitnessException;
}
