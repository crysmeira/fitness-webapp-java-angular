package com.fitnesswebapp.domain.exception;

import java.util.Date;

/**
 * POJO containing exception details.
 *
 * @author Crystiane Meira
 */
public final class ExceptionDetails {

	private Date timestamp;
	private int statusCode;
	private String statusCodeDescription;
	private String exceptionMessage;

	public ExceptionDetails(final Date timestamp, final int statusCode, final String statusCodeDescription, final String exceptionMessage) {
		this.timestamp = timestamp;
		this.statusCode = statusCode;
		this.statusCodeDescription = statusCodeDescription;
		this.exceptionMessage = exceptionMessage;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(final Date timestamp) {
		this.timestamp = timestamp;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(final int statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusCodeDescription() {
		return statusCodeDescription;
	}

	public void setStatusCodeDescription(final String statusCodeDescription) {
		this.statusCodeDescription = statusCodeDescription;
	}

	public String getExceptionMessage() {
		return exceptionMessage;
	}

	public void setExceptionMessage(final String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("ExceptionDetails [timestamp=");
		builder.append(timestamp);
		builder.append(", statusCode=");
		builder.append(statusCode);
		builder.append(", statusCodeDescription=");
		builder.append(statusCodeDescription);
		builder.append(", exceptionMessage=");
		builder.append(exceptionMessage);
		builder.append("]");
		return builder.toString();
	}

}
