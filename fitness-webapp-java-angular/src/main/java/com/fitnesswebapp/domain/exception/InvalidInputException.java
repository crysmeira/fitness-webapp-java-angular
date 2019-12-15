package com.fitnesswebapp.domain.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

/**
 * Custom unchecked exception used when there is any error regarding user input.
 * 
 * @author Crystiane Meira
 */
@Getter
public class InvalidInputException extends FitnessException {

	private static final long serialVersionUID = 3902673007096977533L;

	public InvalidInputException(final HttpStatus httpStatus, final String errorCode) {
		super(httpStatus, errorCode, null, null);
	}

	public InvalidInputException(final HttpStatus httpStatus, final String errorCode, final String[] errorArguments) {
		super(httpStatus, errorCode, errorArguments, null);
	}

	public InvalidInputException(final HttpStatus httpStatus, final String errorCode, final Throwable cause) {
		super(httpStatus, errorCode, null, cause);
	}

	public InvalidInputException(final HttpStatus httpStatus, final String errorCode, final String[] errorArguments, 
			                final Throwable cause) {
		super(httpStatus, errorCode, errorArguments, cause);
	}
}
