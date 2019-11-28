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

import com.fitnesswebapp.domain.exception.FitnessException;
import com.fitnesswebapp.domain.model.fitness.Profile;
import com.fitnesswebapp.domain.model.fitness.User;
import com.fitnesswebapp.domain.service.UserService;
import com.fitnesswebapp.utils.FitnessConstants;

/**
 * Handles the requests to Fitness Webapp for user operations.
 *
 * @author Crystiane Meira
 */
@RestController
@RequestMapping("/users")
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(@Qualifier(FitnessConstants.USER_SERVICE_BEAN) final UserService userService) {
		this.userService = userService;
	}

	/**
	 * Creates a {@link User} by using the given user.
	 *
	 * @param user The user to be saved.
	 * @return The {@link User} saved.
	 * @throws FitnessException If user is null or empty or if there is already a user saved with the same 
	 * email address.
	 */
	@PostMapping(value = "/registration")
	@ResponseStatus(HttpStatus.CREATED)
	public User registerUser(@RequestBody final User user) throws FitnessException {
		return userService.saveUser(user);
	}

	/**
	 * Retrieves a {@link Profile} based on the given email address.
	 *
	 * @param email The email address.
	 * @return The {@link Profile} for the user that matches the given email address.
	 * @throws FitnessException If email is null or empty.
	 */
	@GetMapping(value = "/profile/{email}")
	public Profile getProfile(@PathVariable("email") final String email) throws FitnessException {
		return userService.getProfile(email);
	}

	/**
	 * Updates an existing {@link User}.
	 *
	 * @param user The user to be updated.
	 * @return The {@link User} updated.
	 * @throws FitnessException If user is null or empty.
	 */
	@PutMapping
	public User updateUser(@RequestBody final User user) throws FitnessException {
		return userService.updateUser(user);
	}

	/**
	 * Retrieves a {@link User} based on the given email address.
	 *
	 * @param email The email address.
	 * @return The {@link User} that matches the given email address.
	 * @throws FitnessException If email is null or empty.
	 */
	@DeleteMapping(value = "/{email}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable("email") final String email) throws FitnessException {
		userService.deleteUser(email);
	}
}
