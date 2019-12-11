package com.fitnesswebapp.api.exceptionhandler;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fitnesswebapp.domain.exception.FitnessException;
import com.fitnesswebapp.utils.BeanNames;

/**
 * The global controller exception handler.
 *
 * @author Crystiane Meira
 */
@ControllerAdvice
public class GlobalControllerExceptionHandler extends ResponseEntityExceptionHandler {

	private final MessageLoader messageLoader;

	@Autowired
	public GlobalControllerExceptionHandler(@Qualifier(BeanNames.FITNESS_MESSAGE_LOADER) final MessageLoader messageLoader) {
		this.messageLoader = messageLoader;
	}

	/**
	 * Handles all exceptions of type {@link FitnessException}.
	 *
	 * @param fitnessException The exception.
	 * @return An {@link ExceptionDetails} object containing details for the given exception.
	 */
	@ExceptionHandler(FitnessException.class)
	public ResponseEntity<?> handleFitnessException(final FitnessException ex, WebRequest request) {
		ExceptionType exceptionType = ExceptionType.RESOURCE_NOT_FOUND;
		HttpStatus status = ex.getHttpStatus();
		
		final ExceptionDetails exceptionDetails = ExceptionDetails.builder().timestamp(LocalDateTime.now())
																	.status(status.value())
																	.type(exceptionType.getUri())
																	.title(exceptionType.getTitle())
																	.detail(messageLoader.getLocalizedErrorMessage(ex.getErrorCode(), ex.getErrorArguments()))
																	.userMessage(messageLoader.getLocalizedErrorMessage(ex.getErrorCode(), ex.getErrorArguments()))
																	.build();

		return handleExceptionInternal(ex, exceptionDetails, new HttpHeaders(), status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
		if (body == null) {
			body = ExceptionDetails.builder().timestamp(LocalDateTime.now())
									.status(status.value())
									.title(status.getReasonPhrase())
									.userMessage("TO BE DEFINED")
									.build();
		} else if (body instanceof String) {
			body = ExceptionDetails.builder().timestamp(LocalDateTime.now())
									.status(status.value())
									.title((String) body)
									.userMessage("TO BE DEFINED")
									.build();
		}
		
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
}
