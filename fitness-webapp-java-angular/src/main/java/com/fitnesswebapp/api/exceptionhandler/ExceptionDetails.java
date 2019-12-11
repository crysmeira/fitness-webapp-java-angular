package com.fitnesswebapp.api.exceptionhandler;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Getter;

/**
 * POJO containing exception details.
 *
 * @author Crystiane Meira
 */
@Getter
@Builder
@JsonInclude(Include.NON_NULL)
public final class ExceptionDetails {

	private Integer status;
	private String type;
	private String title;
	private String detail;
	
	private String userMessage;
	private LocalDateTime timestamp;
	
}
