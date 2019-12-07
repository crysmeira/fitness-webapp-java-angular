package com.fitnesswebapp.core.config;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import com.fitnesswebapp.core.config.props.NutritionixConfigProps;
import com.fitnesswebapp.utils.BeanNames;
import com.fitnesswebapp.utils.FitnessConstants;
import com.google.common.base.Charsets;

/**
 * A Spring configuration class which provides HTTP connections to Nutritionix API.
 *
 * @author Crystiane Meira
 */
@Configuration(BeanNames.NUTRITIONIX_CONFIG)
public class NutritionixConfig {

	private final NutritionixConfigProps nutritionixConfig;

	@Autowired
	public NutritionixConfig(
			@Qualifier(BeanNames.NUTRITIONIX_CONFIG_PROPS) final NutritionixConfigProps nutritionixConfig) {
		this.nutritionixConfig = nutritionixConfig;
	}
	
	/**
	 * Creates an HTTP request to get information about the food provided in the given query from Nutritionix API.
	 *
	 * @param query The food query.
	 * @return An HttpURLConnection to perform the desired request.
	 * @throws IOException If an exception occurs when trying to set the connection.
	 */
	public HttpURLConnection getConnectionQuery(final String query) throws IOException {
		final String requestString = new StringBuilder().append(FitnessConstants.NUTRITIONIX_BASE_URL)
													.append(FitnessConstants.SLASH)
													.append(FitnessConstants.NUTRITIONIX_FOOD_QUERY)
													.append(query)
													.toString();
		final URL url = new URL(requestString);
		final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod(HttpMethod.GET.toString());
		setBasicConnectionDetails(connection);
		connection.connect();

		return connection;
	}

	/**
	 * Creates an HTTP request to get information for a specific food id from Nutritionix API.
	 *
	 * @param nixItemId The food id.
	 * @return An HttpURLConnection to perform the desired request.
	 * @throws IOException If an exception occurs when trying to set the connection.
	 */
	public HttpURLConnection getConnectionNutrients(final String nixItemId) throws IOException {
		final String requestString = new StringBuilder().append(FitnessConstants.NUTRITIONIX_BASE_URL)
													.append(FitnessConstants.SLASH)
													.append(FitnessConstants.NUTRITIONIX_NUTRIENT_QUERY)
													.append(nixItemId)
													.toString();
		final URL url = new URL(requestString);
		final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod(HttpMethod.GET.toString());
		setBasicConnectionDetails(connection);
		connection.connect();

		return connection;
	}

	/**
	 * Creates an HTTP request to get information for some exercise stats from Nutritionix API.
	 *
	 * @param query The query containing the information for the exercise.
	 * @return An HttpURLConnection to perform the desired request.
	 * @throws IOException If an exception occurs when trying to set the connection.
	 */
	public HttpURLConnection getConnectionExercises(final String query) throws IOException {
		final String requestString = new StringBuilder().append(FitnessConstants.NUTRITIONIX_BASE_URL)
													.append(FitnessConstants.SLASH)
													.append(FitnessConstants.NUTRITIONIX_EXERCISE_QUERY)
													.toString();
		final URL url = new URL(requestString);
		final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod(HttpMethod.POST.toString());
		setBasicConnectionDetails(connection);
		connection.setRequestProperty(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		connection.setDoOutput(true);

		final OutputStream outputStream = connection.getOutputStream();
		final OutputStreamWriter writer = new OutputStreamWriter(outputStream, Charsets.UTF_8);
		writer.write(query);
		writer.flush();
		writer.close();

		connection.connect();

		return connection;
	}

	private void setBasicConnectionDetails(final HttpURLConnection connection) {
		connection.setRequestProperty(FitnessConstants.NUTRITIONIX_X_APP_ID_HEADER, nutritionixConfig.getAppId());
		connection.setRequestProperty(FitnessConstants.NUTRITIONIX_X_APP_KEY_HEADER, nutritionixConfig.getAppKey());
		connection.setRequestProperty(FitnessConstants.NUTRITIONIX_X_REMOTE_USER_ID_HEADER,
				nutritionixConfig.getRemoteUserId());
	}
}
