package com.fitnesswebapp.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fitnesswebapp.api.assembler.nutritionix.ExerciseModelAssembler;
import com.fitnesswebapp.api.assembler.nutritionix.FoodModelAssembler;
import com.fitnesswebapp.api.assembler.nutritionix.NutrientModelAssembler;
import com.fitnesswebapp.api.model.nutritionix.ExerciseModel;
import com.fitnesswebapp.api.model.nutritionix.FoodModel;
import com.fitnesswebapp.api.model.nutritionix.NutrientModel;
import com.fitnesswebapp.domain.model.nutritionix.Exercise;
import com.fitnesswebapp.domain.model.nutritionix.Food;
import com.fitnesswebapp.domain.model.nutritionix.Nutrient;
import com.fitnesswebapp.domain.service.NutritionixService;
import com.fitnesswebapp.utils.BeanNames;

/**
 * This controller receives requests that require communication with Nutritionix API.
 *
 * @author Crystiane Meira
 */
@RestController
@RequestMapping("/nutritionix")
public class NutritionixController {

	private final NutritionixService nutritionixService;
	private final NutrientModelAssembler nutrientModelAssembler;
	private final FoodModelAssembler foodModelAssembler;
	private final ExerciseModelAssembler exerciseModelAssembler;

	@Autowired
	public NutritionixController(
			@Qualifier(BeanNames.NUTRITIONIX_SERVICE) final NutritionixService nutritionixService,
			@Qualifier(BeanNames.NUTRIENT_MODEL_ASSEMBLER) final NutrientModelAssembler nutrientModelAssembler,
			@Qualifier(BeanNames.FOOD_MODEL_ASSEMBLER) final FoodModelAssembler foodModelAssembler,
			@Qualifier(BeanNames.EXERCISE_MODEL_ASSEMBLER) final ExerciseModelAssembler exerciseModelAssembler) {
		this.nutritionixService = nutritionixService;
		this.nutrientModelAssembler = nutrientModelAssembler;
		this.foodModelAssembler = foodModelAssembler;
		this.exerciseModelAssembler = exerciseModelAssembler;
	}

	/**
	 * Searches for all food that match the given query.
	 *
	 * @param query The query to filter food.
	 * @return A list containing all the food that match the given query.
	 */
	@GetMapping(value = "/food/{query}")
	public List<FoodModel> searchFood(@PathVariable("query") final String query) {
		List<Food> foodList = nutritionixService.searchFood(query);
		return foodModelAssembler.toCollectionModel(foodList);
	}

	/**
	 * Given the food id, gets the nutritional details for the food.
	 *
	 * @param nixItemId The food id.
	 * @return A nutrient object containing the nutrients for the specified food.
	 */
	@GetMapping(value = "/nutrients/{nixItemId}")
	public NutrientModel getNutrient(@PathVariable("nixItemId") final String nixItemId) {
		Nutrient nutrient = nutritionixService.getNutrient(nixItemId);
		return nutrientModelAssembler.toModel(nutrient);
	}

	/**
	 * Given the duration and type of the exercise, returns an exercise object containing other details.
	 *
	 * @param exerciseName The name of the exercise.
	 * @param durationInMinutes The duration of the exercise in minutes.
	 * @return An exercise object with additional details for the given exercise and its duration.
	 */
	@GetMapping(value = "/exercises/{name}/{duration}/{email}")
	public ExerciseModel getExercise(@PathVariable("name") final String exerciseName,
			@PathVariable("duration") final int durationInMinutes,
			@PathVariable("email") final String email) {
		Exercise exercise = nutritionixService.getExercise(exerciseName, durationInMinutes, email);
		return exerciseModelAssembler.toModel(exercise);
	}
}
