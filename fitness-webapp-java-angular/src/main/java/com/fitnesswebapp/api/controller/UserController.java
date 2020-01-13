package com.fitnesswebapp.api.controller;

import java.lang.reflect.Field;
import java.security.Principal;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fitnesswebapp.api.assembler.fitness.UserInputDisassembler;
import com.fitnesswebapp.api.assembler.fitness.UserModelAssembler;
import com.fitnesswebapp.api.model.fitness.UserModel;
import com.fitnesswebapp.api.model.fitness.input.UserInput;
import com.fitnesswebapp.core.validation.NotUpdatable;
import com.fitnesswebapp.domain.exception.ValidationException;
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
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	private final UserService userService;
	private final UserModelAssembler userModelAssembler;
	private final UserInputDisassembler userInputDisassembler;
	private final SmartValidator validator;

	@Autowired
	public UserController(@Qualifier(BeanNames.USER_SERVICE) final UserService userService,
			@Qualifier(BeanNames.USER_MODEL_ASSEMBLER) final UserModelAssembler userModelAssembler,
			@Qualifier(BeanNames.USER_INPUT_DISASSEMBLER) final UserInputDisassembler userInputDisassembler,
			final SmartValidator validator) {
		this.userService = userService;
		this.userModelAssembler = userModelAssembler;
		this.userInputDisassembler = userInputDisassembler;
		this.validator = validator;
	}
	
	@GetMapping(value = "/auth")
	public Principal user(Principal user) {
		return user;
	}
	
	/**
	 * Creates a user.
	 *
	 * @param userInput The user to be saved.
	 * @return The user saved.
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UserModel registerUser(@RequestBody @Valid final UserInput userInput) {
		User user = userInputDisassembler.toDomainObject(userInput);
		user = userService.saveUser(user);
		return userModelAssembler.toModel(user);
	}

	/**
	 * Retrieves a user based on the given email address.
	 *
	 * @param email The email address.
	 * @return The user that matches the given email address.
	 */
	@GetMapping(value = "/{email}")
	public UserModel getUserByEmail(@PathVariable("email") final String email) {
		User user = userService.getUser(email);
		return userModelAssembler.toModel(user);
	}

	/**
	 * Updates an existing user.
	 *
	 * @param user The user to be updated.
	 * @return The user updated.
	 */
	@PutMapping("/{email}")
	public UserModel updateUser(@PathVariable String email, @RequestBody @Valid final UserInput userInput) {
		User user = userInputDisassembler.toDomainObject(userInput);
		user = userService.updateUser(email, user);
		return userModelAssembler.toModel(user);
	}

	/**
	 * Retrieves a user based on the given email address.
	 *
	 * @param email The email address.
	 * @return The user that matches the given email address.
	 */
	@DeleteMapping(value = "/{email}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable("email") final String email) {
		userService.deleteUser(email);
	}
	
	@PatchMapping("/{email}")
	public UserModel partialUpdate(@PathVariable String email, @RequestBody Map<String, Object> fields, HttpServletRequest request) {
		User user = userService.getUser(email);
		
		merge(fields, user, request);
		validate(user, "user");
		User updatedUser = userService.updateUser(email, user);
		
		return userModelAssembler.toModel(updatedUser);
	}
	
	private void validate(User currentUser, String objectName) {
		BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(currentUser, objectName);
		validator.validate(currentUser, bindingResult);
		
		if (bindingResult.hasErrors()) {
			throw new ValidationException(bindingResult);
		}
		
	}
	
	private void merge(Map<String, Object> sourceData, User targetUser, HttpServletRequest request) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.registerModule(new JavaTimeModule());
			objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
	
			User sourceUser = objectMapper.convertValue(sourceData, User.class);
			
			sourceData.forEach((propertyName, propertyValue) -> {
				System.out.println(propertyName);
				Field field = ReflectionUtils.findField(User.class, propertyName);
				if (!field.isAnnotationPresent(NotUpdatable.class)) {
					field.setAccessible(true);
					
					Object newValue = ReflectionUtils.getField(field, sourceUser);
					
					ReflectionUtils.setField(field, targetUser, newValue);
				}
			});
		} catch (IllegalArgumentException e) {
			ServletServerHttpRequest serverHttpRequset = new ServletServerHttpRequest(request);
			
			Throwable rootCause = ExceptionUtils.getRootCause(e);
			throw new HttpMessageNotReadableException(e.getMessage(), rootCause, serverHttpRequset);
		}
	}
}
