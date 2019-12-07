package com.fitnesswebapp.domain.exception;

import java.util.Locale;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.CharEncoding;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
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

	@Value("${spring.messages.basename}")
	private String basename;

	@PostConstruct
	public void setUp() {
		final String[] basenameArray = StringUtils.split(basename, ",");

		errorMessageSource = new ReloadableResourceBundleMessageSource();
		errorMessageSource.setBasenames(basenameArray);
		errorMessageSource.setDefaultEncoding(CharEncoding.UTF_8);
	}

	/**
	 * Retrives the error message based on the message key and the arguments provided.
	 *
	 * @param messageKey The message key (Ex.: ERROR_400001)
	 * @param arguments An array containing the values to be inserted in the error message
	 * @return The error message
	 */
	public String getLocalizedErrorMessage(final String messageKey, final String[] arguments) {
		return errorMessageSource.getMessage(messageKey, arguments, "A default message.", Locale.US);
	}
}
