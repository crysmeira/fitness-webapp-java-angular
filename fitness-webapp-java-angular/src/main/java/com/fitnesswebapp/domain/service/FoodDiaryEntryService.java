package com.fitnesswebapp.domain.service;

import java.util.List;

import com.fitnesswebapp.domain.exception.FitnessException;
import com.fitnesswebapp.domain.model.fitness.FoodDiaryEntry;
import com.fitnesswebapp.domain.model.fitness.User;

/**
 * Interface for service to operate on food diary objects.
 *
 * @author Crystiane Meira
 */
public interface FoodDiaryEntryService {

	/**
	 * Saves the food diary entries.
	 *
	 * @param foodDiaryEntries The food diary entries containing information for food consumed in the current day.
	 * @param user The user logged in.
	 * @return The FoodDiary saved using the given food diary entries.
	 * @throws FitnessException If foodDiaryEntries is null or empty, if there is already a food diary saved for the same day or if user is null.
	 */
	public List<FoodDiaryEntry> saveFoodDiaryEntries(List<FoodDiaryEntry> foodDiaryEntries, User user) throws FitnessException;

	/**
	 * Gets the food diary for the current day.
	 *
	 * @param user The user logged in.
	 * @return The FoodDiary or null if there is no food diary for the current day.
	 * @throws FitnessException If user is null.
	 */
	public List<FoodDiaryEntry> getFoodDiaryEntriesForToday(User user) throws FitnessException;

}
