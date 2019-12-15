package com.fitnesswebapp.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.List;

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
	
	public List<Field> fields;
	
	@Getter
	@Builder
	public static class Field {
		
		/*
		 * This field index is in case the field name is inside a collection
		 */
		@JsonInclude(Include.NON_NULL)
		private Integer index;
		
		private String name;
		private String userMessage;
		
	}
	
}
