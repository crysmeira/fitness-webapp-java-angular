package com.fitnesswebapp.api.exceptionhandler;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

import javax.annotation.PostConstruct;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import com.fitnesswebapp.utils.BeanNames;

/**
 * The loader class for error messages.
 *
 * @author Crystiane Meira
 */
@Component(BeanNames.FITNESS_MESSAGE_LOADER)
public class MessageLoader {

	private ReloadableResourceBundleMessageSource errorMessageSource;
	private ReloadableResourceBundleMessageSource userErrorMessageSource;

	@PostConstruct
	public void setUp() {
		errorMessageSource = new ReloadableResourceBundleMessageSource();
		errorMessageSource.setBasenames("messages/error-messages");
		errorMessageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());
		
		userErrorMessageSource = new ReloadableResourceBundleMessageSource();
		userErrorMessageSource.setBasenames("messages/user-error-messages");
		userErrorMessageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());
	}

	/**
	 * Retrives the error message based on the message key and the arguments provided.
	 *
	 * @param messageKey The message key (Ex.: ERROR_400001)
	 * @param arguments An array containing the values to be inserted in the error message
	 * @return The error message
	 */
	public String getLocalizedErrorMessage(final String messageKey, final String[] arguments) {
		return errorMessageSource.getMessage(messageKey, arguments, "An unexpected error ocurred.", Locale.US);
	}
	
	/**
	 * Retrives a user friendly error message based on the message key and the arguments provided.
	 *
	 * @param messageKey The message key (Ex.: ERROR_400001)
	 * @param arguments An array containing the values to be inserted in the error message
	 * @return The error message
	 */
	public String getLocalizedUserErrorMessage(final String messageKey, final String[] arguments) {
		return userErrorMessageSource.getMessage(messageKey, arguments, "An unexpected error ocurred.", Locale.US);
	}
}
