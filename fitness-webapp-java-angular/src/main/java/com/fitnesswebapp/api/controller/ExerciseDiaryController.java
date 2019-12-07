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

import com.fitnesswebapp.api.assembler.fitness.ExerciseDiaryEntryInputDisassembler;
import com.fitnesswebapp.api.assembler.fitness.ExerciseDiaryEntryModelAssembler;
import com.fitnesswebapp.api.model.fitness.ExerciseDiaryEntryModel;
import com.fitnesswebapp.api.model.fitness.input.ExerciseDiaryEntryInput;
import com.fitnesswebapp.domain.exception.FitnessException;
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
public class ExerciseDiaryController {

	private final ExerciseDiaryEntryService exerciseDiaryService;
	private final UserService userService;
	private final ExerciseDiaryEntryModelAssembler exerciseDiaryEntryModelAssembler;
	private final ExerciseDiaryEntryInputDisassembler exerciseDiaryEntryInputDisassembler;

	@Autowired
	public ExerciseDiaryController(
			@Qualifier(BeanNames.EXERCISE_DIARY_SERVICE) final ExerciseDiaryEntryService fitnessService,
			@Qualifier(BeanNames.USER_SERVICE) final UserService userService,
			ExerciseDiaryEntryModelAssembler exerciseDiaryEntryModelAssembler,
			ExerciseDiaryEntryInputDisassembler exerciseDiaryEntryInputDisassembler) {
		this.exerciseDiaryService = fitnessService;
		this.userService = userService;
		this.exerciseDiaryEntryModelAssembler = exerciseDiaryEntryModelAssembler;
		this.exerciseDiaryEntryInputDisassembler = exerciseDiaryEntryInputDisassembler;
	}

	/**
	 * Creates a {@link ExerciseDiaryEntry} by using the given exercise entry.
	 *
	 * @param exerciseDiaryEntryInput The object containing information about the exercise.
	 * @param userEmail The email address for the user logged in.
	 * @return The {@link ExerciseDiaryEntry} saved.
	 * @throws FitnessException If exerciseDiary is null, if userEmail is null or empty, or if a user is not found for
	 * the given email.
	 */
	@PostMapping(value = "/{email}")
	public ExerciseDiaryEntryModel saveExerciseDiary(
			@RequestBody final ExerciseDiaryEntryInput exerciseDiaryEntryInput, 
			@PathVariable("email") final String userEmail)
					throws FitnessException {
		final User user = userService.getUser(userEmail);
		ExerciseDiaryEntry exerciseDiaryEntry = exerciseDiaryEntryInputDisassembler.toDomainObject(exerciseDiaryEntryInput);
		exerciseDiaryEntry = exerciseDiaryService.saveExerciseDiaryEntry(exerciseDiaryEntry, user);
		return exerciseDiaryEntryModelAssembler.toModel(exerciseDiaryEntry);
	}

	/**
	 * Gets the exercise diary for the current day.
	 *
	 * @param userEmail The email address for the user logged in.
	 * @return The {@link ExerciseDiary} for the current day. If none exercise diary is found for the current day, 
	 * return null.
	 * @throws FitnessException If userEmail is null or empty or if a user is not found for the given email.
	 */
	@GetMapping(value = "/{email}")
	public List<ExerciseDiaryEntryModel> getExerciseDiaryForToday(@PathVariable("email") final String userEmail) 
			throws FitnessException {
		final User user = userService.getUser(userEmail);
		return exerciseDiaryEntryModelAssembler.toCollectionModel(exerciseDiaryService.getExerciseDiaryForToday(user));
	}
}
