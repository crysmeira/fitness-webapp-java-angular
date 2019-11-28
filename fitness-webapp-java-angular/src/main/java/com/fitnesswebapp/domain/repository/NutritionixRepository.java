package com.fitnesswebapp.domain.repository;

import java.io.IOException;
import java.util.List;

import org.springframework.boot.configurationprocessor.json.JSONException;

import com.fitnesswebapp.domain.exception.FitnessException;
import com.fitnesswebapp.model.nutritionix.Exercise;
import com.fitnesswebapp.model.nutritionix.Food;
import com.fitnesswebapp.model.nutritionix.Nutrient;

/**
 * Interface for repository used to establish connection between Fitness Webapp and Nutritionix API in order to 
 * request and retrieve data.
 *
 * @author Crystiane Meira
 */
public interface NutritionixRepository {

	/**
	 * Searches for food based on the given query.
	 *
	 * @param query The food query.
	 * @return A list containing all food results that match the give query.
	 * @throws IOException If an exception happens attempting to establish a connection between this app and 
	 * Nutritionix API or when trying to read its response.
	 * @throws JSONException If an exception happens trying to operate on JSON.
	 * @throws FitnessException If there is a problem trying to connect to Nutritionix API.
	 */
	public List<Food> searchFood(String query) throws IOException, JSONException, FitnessException;

	/**
	 * Searches for the nutrition information for the given food id.
	 *
	 * @param foodId The food id.
	 * @return A {@link Nutrient} object containing the nutrient information for the given food id.
	 * @throws IOException If an exception happens attempting to establish a connection between this app and 
	 * Nutritionix API or when trying to read its response.
	 * @throws JSONException If an exception happens trying to operate on JSON.
	 * @throws FitnessException If there is a problem trying to connect to Nutritionix API.
	 */
	public Nutrient getNutrient(String foodId) throws IOException, JSONException, FitnessException;

	/**
	 * Searches for an estimate of calories burned for the given exercise and its duration.
	 *
	 * @param exercise The exercise.
	 * @param durationInMinutes The duration of the exercise in minutes.
	 * @param email The email for the user logged in.
	 * @return An {@link Exercise} object containing information about the exercise and its duration.
	 * @throws IOException If an exception happens attempting to establish a connection between this app and 
	 * Nutritionix API or when trying to read its response.
	 * @throws JSONException If an exception happens trying to operate on JSON.
	 * @throws FitnessException If there is a problem trying to connect to Nutritionix API.
	 */
	public Exercise getExercise(String exercise, int durationInMinutes, String email) 
			                    throws IOException, JSONException, FitnessException;

}
