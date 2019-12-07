package com.fitnesswebapp.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fitnesswebapp.api.assembler.fitness.UserInputDisassembler;
import com.fitnesswebapp.api.assembler.fitness.UserModelAssembler;
import com.fitnesswebapp.api.model.fitness.UserModel;
import com.fitnesswebapp.api.model.fitness.input.UserInput;
import com.fitnesswebapp.domain.exception.FitnessException;
import com.fitnesswebapp.domain.model.fitness.User;
import com.fitnesswebapp.domain.service.UserService;
import com.fitnesswebapp.utils.BeanNames;

/**
 * Handles the requests to Fitness Webapp for user operations.
 *
 * @author Crystiane Meira
 */
@RestController
@RequestMapping("/users")
public class UserController {

	private final UserService userService;
	private final UserModelAssembler userModelAssembler;
	private final UserInputDisassembler userInputDisassembler;

	@Autowired
	public UserController(@Qualifier(BeanNames.USER_SERVICE) final UserService userService,
			final UserModelAssembler userModelAssembler,
			final UserInputDisassembler userInputDisassembler) {
		this.userService = userService;
		this.userModelAssembler = userModelAssembler;
		this.userInputDisassembler = userInputDisassembler;
	}

	/**
	 * Creates a user.
	 *
	 * @param userInput The user to be saved.
	 * @return The user saved.
	 * @throws FitnessException If user is null or empty or if there is already a user saved with the same 
	 * email address.
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UserModel registerUser(@RequestBody final UserInput userInput) throws FitnessException {
		User user = userInputDisassembler.toDomainObject(userInput);
		user = userService.saveUser(user);
		return userModelAssembler.toModel(user);
	}

	/**
	 * Retrieves a user based on the given email address.
	 *
	 * @param email The email address.
	 * @return The user that matches the given email address.
	 * @throws FitnessException If email is null or empty.
	 */
	@GetMapping(value = "/{email}")
	public UserModel getUserByEmail(@PathVariable("email") final String email) throws FitnessException {
		User user = userService.getUser(email);
		return userModelAssembler.toModel(user);
	}

	/**
	 * Updates an existing user.
	 *
	 * @param user The user to be updated.
	 * @return The user updated.
	 * @throws FitnessException If user is null or empty.
	 */
	@PutMapping
	public UserModel updateUser(@RequestBody final UserInput userInput) throws FitnessException {
		User user = userInputDisassembler.toDomainObject(userInput);
		user = userService.updateUser(user);
		return userModelAssembler.toModel(user);
	}

	/**
	 * Retrieves a user based on the given email address.
	 *
	 * @param email The email address.
	 * @return The user that matches the given email address.
	 * @throws FitnessException If email is null or empty.
	 */
	@DeleteMapping(value = "/{email}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable("email") final String email) throws FitnessException {
		userService.deleteUser(email);
	}
}
