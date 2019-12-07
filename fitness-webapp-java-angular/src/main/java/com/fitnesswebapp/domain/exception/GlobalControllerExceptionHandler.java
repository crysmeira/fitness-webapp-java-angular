package com.fitnesswebapp.domain.exception;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fitnesswebapp.utils.BeanNames;

/**
 * The global controller exception handler.
 *
 * @author Crystiane Meira
 */
@ControllerAdvice
public class GlobalControllerExceptionHandler {

	private static final Logger logger = LogManager.getLogger(GlobalControllerExceptionHandler.class);

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
	public ResponseEntity<ExceptionDetails> handleFitnessException(final FitnessException fitnessException) {
		final ExceptionDetails exceptionDetails = new ExceptionDetails(new Date(), fitnessException.getHttpStatusCode(), HttpStatus.valueOf(fitnessException.getHttpStatusCode()).getReasonPhrase(),
				messageLoader.getLocalizedErrorMessage(fitnessException.getErrorCode(), fitnessException.getErrorArguments()));

		logger.debug("Exception details: {}.", exceptionDetails);
		return new ResponseEntity<>(exceptionDetails, HttpStatus.valueOf(fitnessException.getHttpStatusCode()));
	}
}
