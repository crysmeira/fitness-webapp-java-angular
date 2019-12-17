package com.fitnesswebapp.domain.exception;

import org.springframework.validation.BindingResult;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Custom unchecked exception used when there is an error validating input data.
 * 
 * @author Crystiane Meira
 */
@AllArgsConstructor
@Getter
public class ValidationException extends RuntimeException {

	private static final long serialVersionUID = -3503492813351198155L;

	private BindingResult bindingResult;
}
