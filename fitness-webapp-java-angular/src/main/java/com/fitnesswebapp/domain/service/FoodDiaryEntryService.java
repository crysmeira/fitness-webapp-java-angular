package com.fitnesswebapp.domain.service;

import java.util.List;

import com.fitnesswebapp.domain.model.fitness.FoodDiaryEntry;
import com.fitnesswebapp.domain.model.fitness.User;

/**
 * Interface for service to operate on food diary entry objects.
 *
 * @author Crystiane Meira
 */
public interface FoodDiaryEntryService {

	/**
	 * Saves the food diary entries.
	 *
	 * @param foodDiaryEntries The food diary entries containing information for food consumed in the current day.
	 * @param user The user logged in.
	 * @return A list containing the food diary entries saved.
	 */
	public List<FoodDiaryEntry> saveFoodDiaryEntries(List<FoodDiaryEntry> foodDiaryEntries, User user);

	/**
	 * Gets the food diary entries for the current day.
	 *
	 * @param user The user logged in.
	 * @return A list containing the food diary entries for the current day or null if there is no food diary entry.
	 */
	public List<FoodDiaryEntry> getFoodDiaryEntriesForToday(User user);

}
