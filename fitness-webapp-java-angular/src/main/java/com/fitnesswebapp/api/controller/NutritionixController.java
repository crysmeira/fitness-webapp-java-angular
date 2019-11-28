package com.fitnesswebapp.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fitnesswebapp.domain.exception.FitnessException;
import com.fitnesswebapp.domain.service.NutritionixService;
import com.fitnesswebapp.model.nutritionix.Exercise;
import com.fitnesswebapp.model.nutritionix.Food;
import com.fitnesswebapp.model.nutritionix.Nutrient;
import com.fitnesswebapp.utils.FitnessConstants;

/**
 * This controller receives requests that require communication with Nutritionix API.
 *
 * @author Crystiane Meira
 */
@RestController
@RequestMapping("/nutritionix")
public class NutritionixController {

	private final NutritionixService nutritionixService;

	@Autowired
	public NutritionixController(
			@Qualifier(FitnessConstants.NUTRITIONIX_SERVICE_BEAN) final NutritionixService nutritionixService) {
		this.nutritionixService = nutritionixService;
	}

	/**
	 * Searches for all food that match the given query.
	 *
	 * @param query The query to filter food.
	 * @return A list containing all the food that match the given query.
	 * @throws FitnessException If an exception occurs when trying to search for a food in Nutritionix API.
	 */
	@GetMapping(value = "/food/{query}")
	public List<Food> searchFood(@PathVariable("query") final String query) throws FitnessException {
		return nutritionixService.searchFood(query);
	}

	/**
	 * Given the food id, gets the nutritional details for the food.
	 *
	 * @param nixItemId The food id.
	 * @return A {@link Nutrient} object containing the nutrients for the specified food.
	 * @throws FitnessException If an exception occurs when trying to search for nutrient details in Nutritionix API.
	 */
	@GetMapping(value = "/nutrients/{nixItemId}")
	public Nutrient getNutrient(@PathVariable("nixItemId") final String nixItemId) throws FitnessException {
		return nutritionixService.getNutrient(nixItemId);
	}

	/**
	 * Given the duration and type of the exercise, returns a {@link Exercise} object containing other details.
	 *
	 * @param exerciseName The name of the exercise.
	 * @param durationInMinutes The duration of the exercise in minutes.
	 * @return An {@link Exercise} object with additional details for the given exercise and its duration.
	 * @throws FitnessException If one of the situations happen:
	 * <ul>
	 *   <li>If an exception occurs when trying to search for exercise details in Nutritionix API.</li>
	 *   <li>The name of the exercise is null or empty.</li>
	 *   <li>The duration of the exercise is an invalid value.</li>
	 * </ul>
	 */
	@GetMapping(value = "/exercises/{name}/{duration}/{email}")
	public Exercise getExercise(@PathVariable("name") final String exerciseName,
			@PathVariable("duration") final int durationInMinutes,
			@PathVariable("email") final String email) throws FitnessException {
		return nutritionixService.getExercise(exerciseName, durationInMinutes, email);
	}
}
