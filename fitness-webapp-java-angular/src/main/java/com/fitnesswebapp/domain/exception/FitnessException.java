package com.fitnesswebapp.domain.exception;

import java.util.Arrays;

/**
 * Custom checked exception used in this application.
 *
 * @author Crystiane Meira
 */
public class FitnessException extends Exception {

	private static final long serialVersionUID = 2434486210831052605L;

	private final int httpStatusCode;
	private final String errorCode;
	private final String[] errorArguments;

	/**
	 * Initializes the exception with httpStatusCode and errorCode.
	 *
	 * @param httpStatusCode The HTTP status code.
	 * @param errorCode The error code.
	 */
	public FitnessException(final int httpStatusCode, final String errorCode) {
		this(httpStatusCode, errorCode, null, null);
	}

	/**
	 * Initializes the exception with httpStatusCode, errorCode and errorArguments.
	 *
	 * @param httpStatusCode The HTTP status code.
	 * @param errorCode The error code.
	 * @param errorArguments An array of strings containing error arguments to be added to the error message.
	 */
	public FitnessException(final int httpStatusCode, final String errorCode, final String[] errorArguments) {
		this(httpStatusCode, errorCode, errorArguments, null);
	}

	/**
	 * Initializes the exception with httpStatusCode, errorCode and cause.
	 *
	 * @param httpStatusCode The HTTP status code.
	 * @param errorCode The error code.
	 * @param cause Nested exception.
	 */
	public FitnessException(final int httpStatusCode, final String errorCode, final Throwable cause) {
		this(httpStatusCode, errorCode, null, cause);
	}

	/**
	 * Initializes the exception with httpStatusCode, errorCode, errorArguments and cause.
	 *
	 * @param httpStatusCode The HTTP status code.
	 * @param errorCode The error code.
	 * @param errorArguments An array of strings containing error arguments to be added to the error message.
	 * @param cause Nested exception.
	 */
	public FitnessException(final int httpStatusCode, final String errorCode, final String[] errorArguments, 
			                final Throwable cause) {
		super(cause);
		this.httpStatusCode = httpStatusCode;
		this.errorCode = errorCode;
		this.errorArguments = errorArguments;
	}

	public int getHttpStatusCode() {
		return httpStatusCode;
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
		builder.append("FitnessException [httpStatusCode=");
		builder.append(httpStatusCode);
		builder.append(", errorCode=");
		builder.append(errorCode);
		builder.append(", errorArguments=");
		builder.append(Arrays.toString(errorArguments));
		builder.append("]");
		return builder.toString();
	}

}
