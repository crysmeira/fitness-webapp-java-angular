package com.fitnesswebapp.domain.service;

import com.fitnesswebapp.domain.exception.FitnessException;
import com.fitnesswebapp.domain.model.fitness.Profile;
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
	 * @return The User saved.
	 * @throws FitnessException If there is already an user saved with the same username.
	 */
	public User saveUser(User user) throws FitnessException;

	/**
	 * Retrieves an {@link User} based on the given email address.
	 *
	 * @param email The email address.
	 * @return The {@link User} that matches the given email address.
	 * @throws FitnessException If email is null or empty.
	 */
	public User getUser(String email) throws FitnessException;

	/**
	 * Retrieves a {@link Profile} created based on a {@link User} for the given email address.
	 *
	 * @param email The email address.
	 * @return The {@link Profile} with user information for user that matches the given email address.
	 * @throws FitnessException If email is null or empty or if there is no user for the given email.
	 */
	public Profile getProfile(String email) throws FitnessException;

	/**
	 * Updates an existing {@link User}.
	 *
	 * @param user The user to be updated.
	 * @return The {@link User} updated.
	 * @throws FitnessException If user is null or empty or if there is not a user saved with the same email address.
	 */
	public User updateUser(User user) throws FitnessException;

	/**
	 * Deletes an {@link User} based on the given email address.
	 *
	 * @param email The email address.
	 * @throws FitnessException If email is null or empty.
	 */
	public void deleteUser(String email) throws FitnessException;
}
