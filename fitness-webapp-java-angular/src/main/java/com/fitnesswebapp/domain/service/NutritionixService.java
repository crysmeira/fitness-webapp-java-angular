package com.fitnesswebapp.domain.service;

import java.util.List;

import com.fitnesswebapp.domain.model.nutritionix.Exercise;
import com.fitnesswebapp.domain.model.nutritionix.Food;
import com.fitnesswebapp.domain.model.nutritionix.Nutrient;

/**
 * Interface for service to communicate with Nutritionix API.
 *
 * @author Crystiane Meira
 */
public interface NutritionixService {

	/**
	 * Searches for all food that matches the given query.
	 *
	 * @param query The query for food.
	 * @return A list containing all food that matches the given query.
	 */
	public List<Food> searchFood(String query);

	/**
	 * Searches for the nutrient details for a given food id.
	 *
	 * @param foodId The food id.
	 * @return Nutrient details for the given food id.
	 */
	public Nutrient getNutrient(String nixItemId);

	/**
	 * Searches for the exercise details for a given exercise and execution time.
	 *
	 * @param exercise The exercise.
	 * @param durationInMinutes The duration of the exercise in minutes.
	 * @param The email for the user logged in.
	 * @return Exercise details for the given exercise and execution time.
	 */
	public Exercise getExercise(String exercise, int durationInMinutes, String email);

}