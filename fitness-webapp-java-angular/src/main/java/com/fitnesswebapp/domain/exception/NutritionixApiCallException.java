package com.fitnesswebapp.domain.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

/**
 * Custom unchecked exception used when there is any error making a request to Nutritionix API.
 * 
 * @author Crystiane Meira
 */
@Getter
public class NutritionixApiCallException extends FitnessException {

	private static final long serialVersionUID = -2749871866779980036L;

	public NutritionixApiCallException(final HttpStatus httpStatus, final String errorCode) {
		super(httpStatus, errorCode, null, null);
	}

	public NutritionixApiCallException(final HttpStatus httpStatus, final String errorCode, final String[] errorArguments) {
		super(httpStatus, errorCode, errorArguments, null);
	}

	public NutritionixApiCallException(final HttpStatus httpStatus, final String errorCode, final Throwable cause) {
		super(httpStatus, errorCode, null, cause);
	}

	public NutritionixApiCallException(final HttpStatus httpStatus, final String errorCode, final String[] errorArguments, 
			                final Throwable cause) {
		super(httpStatus, errorCode, errorArguments, cause);
	}
}
