package com.fitnesswebapp.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;
import javax.validation.Path.Node;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import com.fitnesswebapp.domain.exception.FitnessException;
import com.fitnesswebapp.domain.exception.FoodDiaryEntryAlreadyExistsException;
import com.fitnesswebapp.domain.exception.InvalidInputException;
import com.fitnesswebapp.domain.exception.NutritionixApiCallException;
import com.fitnesswebapp.domain.exception.UserAlreadyExistsException;
import com.fitnesswebapp.domain.exception.UserNotFoundException;
import com.fitnesswebapp.utils.BeanNames;
import com.fitnesswebapp.utils.FitnessConstants;

/**
 * The global controller exception handler.
 *
 * @author Crystiane Meira
 */
@ControllerAdvice
public class GlobalControllerExceptionHandler extends ResponseEntityExceptionHandler {

	private static final String UNEXPECTED_ERROR = "An unexpected error ocurred.";
	
	private final MessageLoader messageLoader;
	private final MessageSource messageSource;

	@Autowired
	public GlobalControllerExceptionHandler(@Qualifier(BeanNames.FITNESS_MESSAGE_LOADER) final MessageLoader messageLoader,
			final MessageSource messageSource) {
		this.messageLoader = messageLoader;
		this.messageSource = messageSource;
	}

	/**
	 * Handles all exceptions of type {@link FitnessException}.
	 *
	 * @param fitnessException The exception.
	 * @return An {@link ExceptionDetails} object containing details for the given exception.
	 */
	@ExceptionHandler(FitnessException.class)
	public ResponseEntity<?> handleFitnessException(final FitnessException ex, WebRequest request) {
		ExceptionType exceptionType = ExceptionType.GENERAL_EXCEPTION;
		if (ex instanceof FoodDiaryEntryAlreadyExistsException) {
			exceptionType = ExceptionType.ALREADY_EXISTENT_RESOURCE;
		} else if (ex instanceof NutritionixApiCallException) {
			exceptionType = ExceptionType.SYSTEM_ERROR;
		} else if (ex instanceof InvalidInputException) {
			 exceptionType = ExceptionType.INVALID_PARAMETER;
		} else if (ex instanceof UserAlreadyExistsException) {
			exceptionType = ExceptionType.ALREADY_EXISTENT_RESOURCE;
		} else if (ex instanceof UserNotFoundException) {
			exceptionType = ExceptionType.RESOURCE_NOT_FOUND;
		}
		
		HttpStatus status = ex.getHttpStatus();
		
		final ExceptionDetails exceptionDetails = ExceptionDetails.builder().timestamp(LocalDateTime.now())
																	.status(status.value())
																	.type(exceptionType.getUri())
																	.title(exceptionType.getTitle())
																	.detail(messageLoader.getLocalizedErrorMessage(ex.getErrorCode(), ex.getErrorArguments()))
																	.userMessage(messageLoader.getLocalizedUserErrorMessage(ex.getErrorCode(), ex.getErrorArguments()))
																	.build();

		return handleExceptionInternal(ex, exceptionDetails, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
		ExceptionType exceptionType = ExceptionType.INVALID_DATA;
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		List<ExceptionDetails.Field> exceptionDetailsFields = ex.getConstraintViolations()
																.stream()
																.map(constraintViolation -> {
																	List<Node> pathList = new ArrayList<>();
																	constraintViolation.getPropertyPath().forEach(path -> {
																		pathList.add(path);
																	});
																	
																	Node last = pathList.get(pathList.size() - 1); // get only the last field name
																	return ExceptionDetails.Field.builder()
																			.name(last.getName())
																			.index(last.getIndex())
																			.userMessage(constraintViolation.getMessage())
																			.build();
																}).collect(Collectors.toList());
		
		final ExceptionDetails exceptionDetails = ExceptionDetails.builder()
																.timestamp(LocalDateTime.now())
																.status(status.value())
																.type(exceptionType.getUri())
																.title(exceptionType.getTitle())
																.fields(exceptionDetailsFields)
																.build();
		
		return handleExceptionInternal(ex, exceptionDetails, new HttpHeaders(), status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		Throwable rootCause = ExceptionUtils.getRootCause(ex);
		
		if (rootCause instanceof InvalidFormatException) {
			return handleInvalidFormatException((InvalidFormatException) rootCause, headers, status, request);
		} else if (rootCause instanceof PropertyBindingException) {
			return handlePropertyBindingException((PropertyBindingException) rootCause, headers, status, request);
		}
		
		ExceptionType exceptionType = ExceptionType.INCOMPREHENSIBLE_MESSAGE;
		String detail = "Request body is invalid. Verify syntax error.";
		
		ExceptionDetails exceptionDetails = ExceptionDetails.builder().timestamp(LocalDateTime.now())
																.status(status.value())
																.type(exceptionType.getUri())
																.title(exceptionType.getTitle())
																.detail(detail)
																.userMessage(detail)
																.build();
		return handleExceptionInternal(ex, exceptionDetails, new HttpHeaders(), status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionType exceptionType = ExceptionType.INVALID_DATA;

		// Generate a list containing the field name and the error message regarding field value
		List<ExceptionDetails.Field> exceptionDetailsFields = ex.getBindingResult()
														.getFieldErrors()
														.stream()
														.map(fieldError -> {
															String message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
															
															return ExceptionDetails.Field.builder()
																	.name(fieldError.getField())
																	.userMessage(message)
																	.build();
														}).collect(Collectors.toList());
		 
		 final ExceptionDetails exceptionDetails = ExceptionDetails.builder().timestamp(LocalDateTime.now())
					.status(status.value())
					.type(exceptionType.getUri())
					.title(exceptionType.getTitle())
					.fields(exceptionDetailsFields)
					.build();
		
		return handleExceptionInternal(ex, exceptionDetails, new HttpHeaders(), status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
		if (body == null) {
			body = ExceptionDetails.builder().timestamp(LocalDateTime.now())
									.status(status.value())
									.title(status.getReasonPhrase())
									.detail(ex.getMessage())
									.userMessage(UNEXPECTED_ERROR)
									.build();
		} else if (body instanceof String) {
			body = ExceptionDetails.builder().timestamp(LocalDateTime.now())
									.status(status.value())
									.title((String) body)
									.detail(ex.getMessage())
									.userMessage(UNEXPECTED_ERROR)
									.build();
		}
		
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
	
	
	private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		String path = ex.getPath().stream()
							.map(ref -> ref.getFieldName())
							.filter(Objects::nonNull)
							.collect(Collectors.joining(FitnessConstants.DOT));
		
		ExceptionType exceptionType = ExceptionType.INCOMPREHENSIBLE_MESSAGE;
		String detail = String.format("The property '%s' received a value '%s' which is an invalid type. Please provide a value compatible with type '%s'.", path, ex.getValue(), ex.getTargetType().getSimpleName());
		
		ExceptionDetails exceptionDetails = ExceptionDetails.builder().timestamp(LocalDateTime.now())
																.status(status.value())
																.type(exceptionType.getUri())
																.title(exceptionType.getTitle())
																.detail(detail)
																.userMessage(detail)
																.build();
		return handleExceptionInternal(ex, exceptionDetails, new HttpHeaders(), status, request);
	}
	
	private ResponseEntity<Object> handlePropertyBindingException(PropertyBindingException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		String path = ex.getPath().stream()
							.map(ref -> ref.getFieldName())
							.filter(Objects::nonNull)
							.collect(Collectors.joining("."));
		
		ExceptionType exceptionType = ExceptionType.INCOMPREHENSIBLE_MESSAGE;
		String detail = String.format("The property '%s' doesn't exist. Please replace or remove this property and try again.", path);
		
		ExceptionDetails exceptionDetails = ExceptionDetails.builder().timestamp(LocalDateTime.now())
																.status(status.value())
																.type(exceptionType.getUri())
																.title(exceptionType.getTitle())
																.detail(detail)
																.userMessage(detail)
																.build();
		return handleExceptionInternal(ex, exceptionDetails, new HttpHeaders(), status, request);
	}
}
