package com.fitnesswebapp.domain.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.fitnesswebapp.domain.exception.FitnessException;
import com.fitnesswebapp.domain.model.fitness.Profile;
import com.fitnesswebapp.domain.model.fitness.User;
import com.fitnesswebapp.domain.repository.UserRepository;
import com.fitnesswebapp.domain.service.UserService;
import com.fitnesswebapp.utils.ErrorCodes;
import com.fitnesswebapp.utils.FitnessConstants;

/**
 * Implementation of {@link UserService}.
 *
 * @author Crystiane Meira
 */
@Component(FitnessConstants.USER_SERVICE_BEAN)
public class UserServiceImpl implements UserService {

	private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

	private final UserRepository userRepository;

	@Autowired
	public UserServiceImpl(final UserRepository usersRepository) {
		userRepository = usersRepository;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User saveUser(final User user) throws FitnessException {
		if (user == null) {
			throw new FitnessException(HttpStatus.INTERNAL_SERVER_ERROR.value(), ErrorCodes.ERROR_400005);
		}
		if (StringUtils.isEmpty(user.getEmail())) {
			throw new FitnessException(HttpStatus.INTERNAL_SERVER_ERROR.value(), ErrorCodes.ERROR_400004);
		}
		if (userRepository.findUserByEmail(user.getEmail()) != null) {
			throw new FitnessException(HttpStatus.INTERNAL_SERVER_ERROR.value(), ErrorCodes.ERROR_500010, 
					                   new String[] {user.getEmail()});
		}

		user.setPassword(user.getPassword());
		user.setEnabled(true);

		return userRepository.save(user);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User getUser(final String email) throws FitnessException {
		if (StringUtils.isBlank(email)) {
			throw new FitnessException(HttpStatus.INTERNAL_SERVER_ERROR.value(), ErrorCodes.ERROR_400006);
		}

		return userRepository.findUserByEmail(email);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Profile getProfile(final String email) throws FitnessException {
		final User user = getUser(email);
		if (user == null) {
			throw new FitnessException(HttpStatus.INTERNAL_SERVER_ERROR.value(), ErrorCodes.ERROR_500020, 
					                   new String[] {email});
		}

		return new Profile.ProfileBuilder(email).setFirstName(user.getFirstName())
				.setLastName(user.getLastName())
				.setBirthDate(user.getBirthDate())
				.setHeight(user.getHeight())
				.setWeight(user.getWeight())
				.build();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User updateUser(final User user) throws FitnessException {
		if (user == null) {
			throw new FitnessException(HttpStatus.INTERNAL_SERVER_ERROR.value(), ErrorCodes.ERROR_400007);
		}
		if (StringUtils.isBlank(user.getEmail())) {
			throw new FitnessException(HttpStatus.INTERNAL_SERVER_ERROR.value(), ErrorCodes.ERROR_400008);
		}

		final User retrievedUser = userRepository.findUserByEmail(user.getEmail());
		if (retrievedUser == null) {
			throw new FitnessException(HttpStatus.INTERNAL_SERVER_ERROR.value(), ErrorCodes.ERROR_500021);
		}

		retrievedUser.setBirthDate(user.getBirthDate());
		retrievedUser.setFirstName(user.getFirstName());
		retrievedUser.setLastName(user.getLastName());
		retrievedUser.setHeight(user.getHeight());
		retrievedUser.setWeight(user.getWeight());

		return userRepository.save(retrievedUser);
	}

	/**
	 * Deletes an {@link User} based on the given email address.
	 *
	 * @param email The email address.
	 * @throws FitnessException If email is null or empty.
	 */
	@Override
	public void deleteUser(final String email) throws FitnessException {
		if (StringUtils.isBlank(email)) {
			throw new FitnessException(HttpStatus.INTERNAL_SERVER_ERROR.value(), ErrorCodes.ERROR_500011);
		}

		final User user = userRepository.findUserByEmail(email);
		userRepository.delete(user);

		logger.debug("Deleted user for email '{}'.", email);
	}
}
