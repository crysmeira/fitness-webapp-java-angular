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
import com.fitnesswebapp.api.assembler.fitness.FoodDiaryEntryModelAssembler;
import com.fitnesswebapp.api.model.fitness.FoodDiaryEntryModel;
import com.fitnesswebapp.api.model.fitness.input.FoodDiaryEntryInput;
import com.fitnesswebapp.domain.model.fitness.FoodDiaryEntry;
import com.fitnesswebapp.domain.model.fitness.User;
import com.fitnesswebapp.domain.service.FoodDiaryEntryService;
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

	private final FoodDiaryEntryService foodDiaryService;
	private final UserService userService;
	private final FoodDiaryEntryModelAssembler foodDiaryEntryModelAssembler;
	private final FoodDiaryEntryInputDisassembler foodDiaryEntryInputDisassembler;

	@Autowired
	public FoodDiaryController(@Qualifier(BeanNames.FOOD_DIARY_ENTRY_SERVICE) final FoodDiaryEntryService fitnessService,
			@Qualifier(BeanNames.USER_SERVICE) final UserService userService,
			@Qualifier(BeanNames.FOOD_DIARY_ENTRY_MODEL_ASSEMBLER) FoodDiaryEntryModelAssembler foodDiaryEntryModelAssembler,
			@Qualifier(BeanNames.FOOD_DIARY_ENTRY_INPUT_DISASSEMBLER) 
				FoodDiaryEntryInputDisassembler foodDiaryEntryInputDisassembler) {
		this.foodDiaryService = fitnessService;
		this.userService = userService;
		this.foodDiaryEntryModelAssembler = foodDiaryEntryModelAssembler;
		this.foodDiaryEntryInputDisassembler = foodDiaryEntryInputDisassembler;
	}

	/**
	 * Saves the food diary entries.
	 *
	 * @param foodDiaryEntries A list containing the food diary entries to be saved.
	 * @param userEmail The email address for the user logged in.
	 * @return A list containing the food diary entries saved.
	 */
	@PostMapping(value = "/{email}")
	public List<FoodDiaryEntryModel> saveFoodDiaryEntries(@RequestBody final List<FoodDiaryEntryInput> foodDiaryEntriesInput, 
			@PathVariable("email") final String userEmail) {
		final User user = userService.getUser(userEmail);
		List<FoodDiaryEntry> foodDiaryEntries = foodDiaryEntryInputDisassembler.toCollectionDomainObject(foodDiaryEntriesInput);
		return foodDiaryEntryModelAssembler.toCollectionModel(foodDiaryService.saveFoodDiaryEntries(foodDiaryEntries, user));
	}

	/**
	 * Gets the food diary entries for the current day.
	 *
	 * @param userEmail The email address for the user logged in.
	 * @return A list of food diary entries for the current day. If no food diary is found for the current day, return null.
	 */
	@GetMapping(value = "/{email}")
	public List<FoodDiaryEntryModel> getFoodDiaryEntriesForToday(@PathVariable("email") final String userEmail) {
		final User user = userService.getUser(userEmail);
		return foodDiaryEntryModelAssembler.toCollectionModel(foodDiaryService.getFoodDiaryEntriesForToday(user));
	}
	
}
