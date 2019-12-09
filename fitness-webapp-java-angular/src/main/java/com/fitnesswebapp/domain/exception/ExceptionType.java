package com.fitnesswebapp.domain.exception;

import lombok.Getter;

/**
 * The exception types for Fitness Webapp.
 *
 * @author Crystiane Meira
 */
@Getter
public enum ExceptionType {

	INCOMPREHENSIBLE_MESSAGE("Incomprehensible message", "/incomprehensible-message"),
	RESOURCE_NOT_FOUND("Resource not found", "/resource-not-found"),
	ENTITY_IN_USE("Entity in use", "/entity-in-use"),
	GENERAL_EXCEPTION("General exception", "/general-exception"),
	SYSTEM_ERROR("System error", "/system-error"),
	INVALID_PARAMETER("Invalid parameter", "/invalid-parameter"),
	INVALID_DATA("Invalid data", "/invalid-data");

	private String title;
	private String uri;

	private ExceptionType(final String title, final String path) {
		this.title = title;
		uri = "https://fitnesswebapp.com" + path;
	}

}
