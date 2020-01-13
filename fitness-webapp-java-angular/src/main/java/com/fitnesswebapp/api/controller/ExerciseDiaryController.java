package com.fitnesswebapp.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fitnesswebapp.api.assembler.fitness.ExerciseDiaryEntryInputDisassembler;
import com.fitnesswebapp.api.assembler.fitness.ExerciseDiaryEntryModelAssembler;
import com.fitnesswebapp.api.model.fitness.ExerciseDiaryEntryModel;
import com.fitnesswebapp.api.model.fitness.input.ExerciseDiaryEntryInput;
import com.fitnesswebapp.domain.model.fitness.ExerciseDiaryEntry;
import com.fitnesswebapp.domain.model.fitness.User;
import com.fitnesswebapp.domain.service.ExerciseDiaryEntryService;
import com.fitnesswebapp.domain.service.UserService;
import com.fitnesswebapp.utils.BeanNames;

/**
 * Handles the requests to Fitness Webapp for exercise diary operations.
 *
 * @author Crystiane Meira
 */
@RestController
@RequestMapping("/exercise-diaries")
@CrossOrigin(origins = "http://locahost:4200")
public class ExerciseDiaryController {

	private final ExerciseDiaryEntryService exerciseDiaryService;
	private final UserService userService;
	private final ExerciseDiaryEntryModelAssembler exerciseDiaryEntryModelAssembler;
	private final ExerciseDiaryEntryInputDisassembler exerciseDiaryEntryInputDisassembler;

	@Autowired
	public ExerciseDiaryController(
			@Qualifier(BeanNames.EXERCISE_DIARY_ENTRY_SERVICE) final ExerciseDiaryEntryService fitnessService,
			@Qualifier(BeanNames.USER_SERVICE) final UserService userService,
			@Qualifier(BeanNames.EXERCISE_DIARY_ENTRY_MODEL_ASSEMBLER) 
				ExerciseDiaryEntryModelAssembler exerciseDiaryEntryModelAssembler,
			@Qualifier(BeanNames.EXERCISE_DIARY_ENTRY_INPUT_DISASSEMBLER) 
				ExerciseDiaryEntryInputDisassembler exerciseDiaryEntryInputDisassembler) {
		this.exerciseDiaryService = fitnessService;
		this.userService = userService;
		this.exerciseDiaryEntryModelAssembler = exerciseDiaryEntryModelAssembler;
		this.exerciseDiaryEntryInputDisassembler = exerciseDiaryEntryInputDisassembler;
	}

	/**
	 * Creates an exercise diary entry by using the given exercise entry.
	 *
	 * @param exerciseDiaryEntryInput The object containing information about the exercise.
	 * @param userEmail The email address for the user logged in.
	 * @return The exercise diary entry saved.
	 */
	@PostMapping(value = "/{email}")
	public ExerciseDiaryEntryModel saveExerciseDiary(
			@RequestBody @Valid final ExerciseDiaryEntryInput exerciseDiaryEntryInput, 
			@PathVariable("email") final String userEmail) {
		final User user = userService.getUser(userEmail);
		ExerciseDiaryEntry exerciseDiaryEntry = exerciseDiaryEntryInputDisassembler.toDomainObject(exerciseDiaryEntryInput);
		exerciseDiaryEntry = exerciseDiaryService.saveExerciseDiaryEntry(exerciseDiaryEntry, user);
		return exerciseDiaryEntryModelAssembler.toModel(exerciseDiaryEntry);
	}

	/**
	 * Gets the exercise diary for the current day.
	 *
	 * @param userEmail The email address for the user logged in.
	 * @return The exercise diary entry for the current day. If no exercise diary is found for the current day, 
	 * return null.
	 */
	@GetMapping(value = "/{email}")
	public List<ExerciseDiaryEntryModel> getExerciseDiaryForToday(@PathVariable("email") final String userEmail) {
		final User user = userService.getUser(userEmail);
		return exerciseDiaryEntryModelAssembler.toCollectionModel(exerciseDiaryService.getExerciseDiaryForToday(user));
	}
}
