package com.fitnesswebapp.domain.service;

import java.util.List;

import com.fitnesswebapp.domain.model.fitness.ExerciseDiaryEntry;
import com.fitnesswebapp.domain.model.fitness.User;

/**
 * Interface for service to operate on exercise diary entry objects.
 *
 * @author Crystiane Meira
 */
public interface ExerciseDiaryEntryService {

	/**
	 * Saves the exercise diary entry.
	 *
	 * @param exerciseDiaryEntry The exercise diary entry containing information for an exercise.
	 * @param user The user logged in.
	 * @return The {@link ExerciseDiaryEntry} saved using the given exercise diary entry.
	 */
	public ExerciseDiaryEntry saveExerciseDiaryEntry(ExerciseDiaryEntry exerciseDiaryEntry, User user);

	/**
	 * Gets the exercise diary entries for the current day.
	 *
	 * @param user The user logged in.
	 * @return A list containing the exercise diary entries for the current day or null if there is no exercise diary entry.
	 */
	public List<ExerciseDiaryEntry> getExerciseDiaryForToday(User user);
}
