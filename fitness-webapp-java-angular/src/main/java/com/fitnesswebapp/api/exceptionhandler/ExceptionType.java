package com.fitnesswebapp.api.exceptionhandler;

import lombok.Getter;

/**
 * The exception types for Fitness Webapp.
 *
 * @author Crystiane Meira
 */
@Getter
public enum ExceptionType {

	RESOURCE_NOT_FOUND("Resource not found", "/resource-not-found"),
	ALREADY_EXISTENT_RESOURCE("Already existent resource", "/already-existent-resource"),
	GENERAL_EXCEPTION("General exception", "/general-exception"),
	SYSTEM_ERROR("System error", "/system-error"),
	INCOMPREHENSIBLE_MESSAGE("Incomprehensible message", "/incomprehensible-message"),
	INVALID_METHOD("Invalid method", "/invalid-method"),
	INVALID_DATA("Invalid data", "/invalid-data"),
	INVALID_PARAMETER("Invalid parameter", "/invalid-parameter");

	private String title;
	private String uri;

	private ExceptionType(final String title, final String path) {
		this.title = title;
		uri = "https://fitnesswebapp.com" + path;
	}

}
