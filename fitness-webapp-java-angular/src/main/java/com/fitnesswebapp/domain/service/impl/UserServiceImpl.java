package com.fitnesswebapp.domain.service.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.fitnesswebapp.core.validation.NotUpdatable;
import com.fitnesswebapp.domain.exception.InvalidInputException;
import com.fitnesswebapp.domain.exception.UserAlreadyExistsException;
import com.fitnesswebapp.domain.exception.UserNotFoundException;
import com.fitnesswebapp.domain.model.fitness.User;
import com.fitnesswebapp.domain.repository.UserRepository;
import com.fitnesswebapp.domain.service.UserService;
import com.fitnesswebapp.utils.BeanNames;
import com.fitnesswebapp.utils.ErrorCodes;
import com.fitnesswebapp.utils.FitnessConstants;

/**
 * Implementation of {@link UserService}.
 *
 * @author Crystiane Meira
 */
@Component(BeanNames.USER_SERVICE)
public class UserServiceImpl implements UserService {

	private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

	private final UserRepository userRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	public UserServiceImpl(final UserRepository usersRepository,
			BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userRepository = usersRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
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

		user.setUsername(user.getEmail());
		user.setRole(FitnessConstants.USER_ROLE);
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

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
		
		String[] ignoreFields = getAllIgnoreFields();
		BeanUtils.copyProperties(user, retrievedUser, ignoreFields);

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
	
	private String[] getAllIgnoreFields() {
		List<String> ignore = new ArrayList<>();
		
        Field[] fields = User.class.getDeclaredFields();
        for (Field f : fields) {
        	if (f.isAnnotationPresent(NotUpdatable.class)) {
        		ignore.add(f.getName());
        	}
        }
        
        return ignore.stream().toArray(String[]::new);
	}
}
