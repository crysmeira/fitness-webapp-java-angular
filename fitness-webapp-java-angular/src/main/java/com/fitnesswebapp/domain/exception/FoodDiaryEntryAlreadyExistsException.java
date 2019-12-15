package com.fitnesswebapp.domain.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

/**
 * Custom unchecked exception used when there is already a food diary entry for a date.
 * 
 * @author Crystiane Meira
 */
@Getter
public class FoodDiaryEntryAlreadyExistsException extends FitnessException {

	private static final long serialVersionUID = -2749871866779980036L;

	public FoodDiaryEntryAlreadyExistsException(final HttpStatus httpStatus, final String errorCode) {
		super(httpStatus, errorCode, null, null);
	}

	public FoodDiaryEntryAlreadyExistsException(final HttpStatus httpStatus, final String errorCode, final String[] errorArguments) {
		super(httpStatus, errorCode, errorArguments, null);
	}

	public FoodDiaryEntryAlreadyExistsException(final HttpStatus httpStatus, final String errorCode, final Throwable cause) {
		super(httpStatus, errorCode, null, cause);
	}

	public FoodDiaryEntryAlreadyExistsException(final HttpStatus httpStatus, final String errorCode, final String[] errorArguments, 
			                final Throwable cause) {
		super(httpStatus, errorCode, errorArguments, cause);
	}
}
