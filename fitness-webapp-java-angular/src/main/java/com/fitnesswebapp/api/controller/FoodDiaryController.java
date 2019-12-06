package com.fitnesswebapp.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fitnesswebapp.api.assembler.fitness.FoodDiaryEntryInputDisassembler;
import com.fitnesswebapp.api.assembler.fitness.FoodDiaryModelAssembler;
import com.fitnesswebapp.api.model.fitness.FoodDiaryModel;
import com.fitnesswebapp.api.model.fitness.input.FoodDiaryEntryInput;
import com.fitnesswebapp.domain.exception.FitnessException;
import com.fitnesswebapp.domain.model.fitness.FoodDiary;
import com.fitnesswebapp.domain.model.fitness.FoodDiaryEntry;
import com.fitnesswebapp.domain.model.fitness.User;
import com.fitnesswebapp.domain.service.FoodDiaryService;
import com.fitnesswebapp.domain.service.UserService;
import com.fitnesswebapp.utils.BeanNames;

/**
 * Handles the requests to Fitness Webapp for food diary operations.
 *
 * @author Crystiane Meira
 */
@RestController
@RequestMapping("/food-diaries")
public class FoodDiaryController {

	private final FoodDiaryService foodDiaryService;
	private final UserService userService;
	private final FoodDiaryModelAssembler foodDiaryModelAssembler;
	private final FoodDiaryEntryInputDisassembler foodDiaryEntryInputDisassembler;

	@Autowired
	public FoodDiaryController(@Qualifier(BeanNames.FOOD_DIARY_SERVICE) final FoodDiaryService fitnessService,
			@Qualifier(BeanNames.USER_SERVICE) final UserService userService,
			FoodDiaryModelAssembler foodDiaryModelAssembler,
			FoodDiaryEntryInputDisassembler foodDiaryEntryInputDisassembler) {
		this.foodDiaryService = fitnessService;
		this.userService = userService;
		this.foodDiaryModelAssembler = foodDiaryModelAssembler;
		this.foodDiaryEntryInputDisassembler = foodDiaryEntryInputDisassembler;
	}

	/**
	 * Creates a {@link FoodDiary} by using the given food diary entries.
	 *
	 * @param foodDiaryEntries A list containing the diary entries to be saved.
	 * @param userEmail The email address for the user logged in.
	 * @return The {@link FoodDiary} saved.
	 * @throws FitnessException If foodDiaryEntries or userEmail is null or empty, if there is already a food diary saved for the
	 * same day or if a user is not found for the given email.
	 */
	@PostMapping(value = "/{email}")
	public FoodDiaryModel saveFoodDiary(@RequestBody final List<FoodDiaryEntryInput> foodDiaryEntriesInput, 
			@PathVariable("email") final String userEmail) throws FitnessException {
		final User user = userService.getUser(userEmail);
		List<FoodDiaryEntry> foodDiaryEntries = foodDiaryEntryInputDisassembler.toCollectionDomainObject(foodDiaryEntriesInput);
		FoodDiary foodDiary = foodDiaryService.saveFoodDiary(foodDiaryEntries, user);
		return foodDiaryModelAssembler.toModel(foodDiary);
	}

	/**
	 * Gets the food diary for the current day.
	 *
	 * @param userEmail The email address for the user logged in.
	 * @return The {@link FoodDiary} for the current day. If none food diary is found for the current day, return null.
	 * @throws FitnessException If userEmail is null or empty or if a user is not found for the given email.
	 */
	@GetMapping(value = "/{email}")
	public FoodDiaryModel getFoodDiaryForToday(@PathVariable("email") final String userEmail) throws FitnessException {
		final User user = userService.getUser(userEmail);
		FoodDiary foodDiary = foodDiaryService.getFoodDiaryForToday(user);
		return foodDiaryModelAssembler.toModel(foodDiary);
	}
}
