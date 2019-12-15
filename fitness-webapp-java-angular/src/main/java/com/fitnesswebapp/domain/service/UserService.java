package com.fitnesswebapp.domain.service;

import com.fitnesswebapp.domain.exception.FitnessException;
import com.fitnesswebapp.domain.model.fitness.User;

/**
 * Interface for service to operate on user objects.
 *
 * @author Crystiane Meira
 */
public interface UserService {

	/**
	 * Saves the user.
	 *
	 * @param user The user to be saved
	 * @return The {@link User} saved.
	 */
	public User saveUser(User user);

	/**
	 * Retrieves a {@link User} based on the given email address.
	 *
	 * @param email The email address.
	 * @return The {@link User} that matches the given email address.
	 */
	public User getUser(String email);

	/**
	 * Updates an existing {@link User}.
	 *
	 * @param userEmail The email address for user to be updated.
	 * @param user The user to be updated.
	 * @throws FitnessException If {@code user} is null or empty or if there is not a user saved with the same email address.
	 */
	public User updateUser(String userEmail, User user);

	/**
	 * Deletes a {@link User} based on the given email address.
	 *
	 * @param email The email address.
	 */
	public void deleteUser(String email);
}
