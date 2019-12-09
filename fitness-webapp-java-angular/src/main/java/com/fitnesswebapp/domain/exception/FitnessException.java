package com.fitnesswebapp.domain.exception;

import java.util.Arrays;

import org.springframework.http.HttpStatus;

/**
 * Custom checked exception used in this application.
 *
 * @author Crystiane Meira
 */
public class FitnessException extends Exception {

	private static final long serialVersionUID = 2434486210831052605L;

	private final HttpStatus httpStatus;
	private final String errorCode;
	private final String[] errorArguments;

	/**
	 * Initializes the exception with httpStatus and errorCode.
	 *
	 * @param httpStatus The HTTP status code.
	 * @param errorCode The error code.
	 */
	public FitnessException(final HttpStatus httpStatus, final String errorCode) {
		this(httpStatus, errorCode, null, null);
	}

	/**
	 * Initializes the exception with httpStatus, errorCode and errorArguments.
	 *
	 * @param httpStatus The HTTP status code.
	 * @param errorCode The error code.
	 * @param errorArguments An array of strings containing error arguments to be added to the error message.
	 */
	public FitnessException(final HttpStatus httpStatus, final String errorCode, final String[] errorArguments) {
		this(httpStatus, errorCode, errorArguments, null);
	}

	/**
	 * Initializes the exception with httpStatus, errorCode and cause.
	 *
	 * @param httpStatus The HTTP status code.
	 * @param errorCode The error code.
	 * @param cause Nested exception.
	 */
	public FitnessException(final HttpStatus httpStatus, final String errorCode, final Throwable cause) {
		this(httpStatus, errorCode, null, cause);
	}

	/**
	 * Initializes the exception with httpStatus, errorCode, errorArguments and cause.
	 *
	 * @param httpStatus The HTTP status code.
	 * @param errorCode The error code.
	 * @param errorArguments An array of strings containing error arguments to be added to the error message.
	 * @param cause Nested exception.
	 */
	public FitnessException(final HttpStatus httpStatus, final String errorCode, final String[] errorArguments, 
			                final Throwable cause) {
		super(cause);
		this.httpStatus = httpStatus;
		this.errorCode = errorCode;
		this.errorArguments = errorArguments;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String[] getErrorArguments() {
		return errorArguments;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("FitnessException [httpStatus=");
		builder.append(httpStatus);
		builder.append(", errorCode=");
		builder.append(errorCode);
		builder.append(", errorArguments=");
		builder.append(Arrays.toString(errorArguments));
		builder.append("]");
		return builder.toString();
	}

}
