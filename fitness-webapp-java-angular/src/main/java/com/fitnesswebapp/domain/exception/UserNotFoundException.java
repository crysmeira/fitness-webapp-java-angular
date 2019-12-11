package com.fitnesswebapp.domain.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

/**
 * Custom unchecked exception used when a user does not exist.
 * 
 * @author Crystiane Meira
 */
@Getter
public class UserNotFoundException extends FitnessException {

	private static final long serialVersionUID = -7424850910081846377L;

	public UserNotFoundException(final HttpStatus httpStatus, final String errorCode) {
		super(httpStatus, errorCode, null, null);
	}

	public UserNotFoundException(final HttpStatus httpStatus, final String errorCode, final String[] errorArguments) {
		super(httpStatus, errorCode, errorArguments, null);
	}

	public UserNotFoundException(final HttpStatus httpStatus, final String errorCode, final Throwable cause) {
		super(httpStatus, errorCode, null, cause);
	}

	public UserNotFoundException(final HttpStatus httpStatus, final String errorCode, final String[] errorArguments, 
			                final Throwable cause) {
		super(httpStatus, errorCode, errorArguments, cause);
	}
	
}
