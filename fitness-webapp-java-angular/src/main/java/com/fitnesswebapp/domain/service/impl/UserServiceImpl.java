package com.fitnesswebapp.domain.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.fitnesswebapp.domain.exception.InvalidInputException;
import com.fitnesswebapp.domain.exception.UserAlreadyExistsException;
import com.fitnesswebapp.domain.exception.UserNotFoundException;
import com.fitnesswebapp.domain.model.fitness.User;
import com.fitnesswebapp.domain.repository.UserRepository;
import com.fitnesswebapp.domain.service.UserService;
import com.fitnesswebapp.utils.BeanNames;
import com.fitnesswebapp.utils.ErrorCodes;

/**
 * Implementation of {@link UserService}.
 *
 * @author Crystiane Meira
 */
@Component(BeanNames.USER_SERVICE)
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
	public User saveUser(final User user) {
		if (user == null) {
			throw new InvalidInputException(HttpStatus.BAD_REQUEST, ErrorCodes.ERROR_400005);
		}
		if (StringUtils.isBlank(user.getEmail())) {
			throw new InvalidInputException(HttpStatus.BAD_REQUEST, ErrorCodes.ERROR_400004);
		}
		if (userRepository.findUserByEmail(user.getEmail()) != null) {
			throw new UserAlreadyExistsException(HttpStatus.CONFLICT, ErrorCodes.ERROR_409002, 
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
	public User getUser(final String email) {
		if (StringUtils.isBlank(email)) {
			throw new InvalidInputException(HttpStatus.BAD_REQUEST, ErrorCodes.ERROR_400006);
		}

		return userRepository.findUserByEmail(email);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User updateUser(String userEmail, final User user) {
		if (user == null) {
			throw new InvalidInputException(HttpStatus.BAD_REQUEST, ErrorCodes.ERROR_400007);
		}
		if (StringUtils.isBlank(userEmail)) {
			throw new InvalidInputException(HttpStatus.BAD_REQUEST, ErrorCodes.ERROR_400008);
		}

		final User retrievedUser = userRepository.findUserByEmail(userEmail);
		if (retrievedUser == null) {
			throw new UserNotFoundException(HttpStatus.NOT_FOUND, ErrorCodes.ERROR_404007);
		}
		
		BeanUtils.copyProperties(user, retrievedUser, "userId", "email");

		return userRepository.save(retrievedUser);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteUser(final String email) {
		if (StringUtils.isBlank(email)) {
			throw new InvalidInputException(HttpStatus.BAD_REQUEST, ErrorCodes.ERROR_400019);
		}

		final User user = userRepository.findUserByEmail(email);
		if (user == null) {
			throw new UserNotFoundException(HttpStatus.BAD_REQUEST, ErrorCodes.ERROR_400020);
		}
		
		userRepository.delete(user);

		logger.debug("Deleted user for email '{}'.", email);
	}
}
