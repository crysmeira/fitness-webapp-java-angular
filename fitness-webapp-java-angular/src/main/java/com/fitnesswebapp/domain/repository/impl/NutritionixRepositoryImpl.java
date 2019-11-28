package com.fitnesswebapp.domain.repository.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fitnesswebapp.core.config.NutritionixConfig;
import com.fitnesswebapp.domain.exception.FitnessException;
import com.fitnesswebapp.domain.model.fitness.User;
import com.fitnesswebapp.domain.repository.NutritionixRepository;
import com.fitnesswebapp.domain.repository.UserRepository;
import com.fitnesswebapp.model.nutritionix.Exercise;
import com.fitnesswebapp.model.nutritionix.Food;
import com.fitnesswebapp.model.nutritionix.Nutrient;
import com.fitnesswebapp.utils.ErrorCodes;
import com.fitnesswebapp.utils.FitnessConstants;
import com.fitnesswebapp.utils.UserHelper;

/** TODO: review the whole class
 * Implementation of {@link NutritionixRepository}.
 *
 * @author Crystiane Meira
 */
@Repository(FitnessConstants.NUTRITIONIX_REPOSITORY_BEAN)
public class NutritionixRepositoryImpl implements NutritionixRepository {

	private static final Logger logger = LogManager.getLogger(NutritionixRepository.class);

	private final NutritionixConfig nutritionixConfig;
	private final UserRepository userRepository;

	@Autowired
	public NutritionixRepositoryImpl(final UserRepository usersRepository,
			                         @Qualifier(FitnessConstants.NUTRITIONIX_CONFIG_BEAN) final NutritionixConfig nutritionixConfig) {
		this.nutritionixConfig = nutritionixConfig;
		userRepository = usersRepository;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Food> searchFood(final String query) throws IOException, JSONException, FitnessException {
		HttpURLConnection httpUrlConnection = null;
		BufferedReader bufferedReader = null;
		final StringBuilder content = new StringBuilder();

		try {
			httpUrlConnection = nutritionixConfig.getConnectionQuery(query);

			if (httpUrlConnection.getResponseCode() != HttpStatus.OK.value()) {
				throw new FitnessException(HttpStatus.INTERNAL_SERVER_ERROR.value(), ErrorCodes.ERROR_500009, 
										   new String[] {String.valueOf(httpUrlConnection.getResponseCode())});
			}

			bufferedReader = new BufferedReader(new InputStreamReader(httpUrlConnection.getInputStream()));
			String inputLine;
			while ((inputLine = bufferedReader.readLine()) != null) {
				content.append(inputLine);
			}

		} finally {
			if (bufferedReader != null) {
				bufferedReader.close();
			}
			if (httpUrlConnection != null) {
				httpUrlConnection.disconnect();
			}
		}

		logger.debug("Content: {}.", content.toString());
		final JSONArray requestJSON = new JSONObject(content.toString())
												.getJSONArray(FitnessConstants.NUTRITIONIX_FOOD_BRANDED_ARRAY);

		final ObjectMapper mapper = new ObjectMapper();
		final String jsonData = requestJSON.toString();
		final Food[] foodArray = mapper.readValue(jsonData , Food[].class);
		return Arrays.asList(foodArray);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Nutrient getNutrient(final String foodId) throws IOException, JSONException, FitnessException {
		HttpURLConnection httpUrlConnection = null;
		BufferedReader bufferedReader = null;
		final StringBuilder content = new StringBuilder();

		try {
			httpUrlConnection = nutritionixConfig.getConnectionNutrients(foodId);

			if (httpUrlConnection.getResponseCode() != HttpStatus.OK.value()) {
				throw new FitnessException(HttpStatus.INTERNAL_SERVER_ERROR.value(), ErrorCodes.ERROR_500009, 
										   new String[] {String.valueOf(httpUrlConnection.getResponseCode())});
			}

			bufferedReader = new BufferedReader(new InputStreamReader(httpUrlConnection.getInputStream()));
			String inputLine;
			while ((inputLine = bufferedReader.readLine()) != null) {
				content.append(inputLine);
			}

		} finally {
			if (bufferedReader != null) {
				bufferedReader.close();
			}
			if (httpUrlConnection != null) {
				httpUrlConnection.disconnect();
			}
		}

		logger.debug("Content: {}.", content.toString());
		final JSONArray requestJSON = new JSONObject(content.toString())
												.getJSONArray(FitnessConstants.NUTRITIONIX_NUTRITION_FOODS_ARRAY);

		final ObjectMapper mapper = new ObjectMapper();
		final String jsonData = requestJSON.toString();
		final Nutrient[] nutrientArray = mapper.readValue(jsonData, Nutrient[].class);

		logger.debug("Nutrient: {}.", nutrientArray[0].toString());
		return nutrientArray[0];
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Exercise getExercise(final String exercise, final int durationInMinutes, final String email) 
			                    throws IOException, JSONException, FitnessException {
		final User user = userRepository.findUserByEmail(email);
		if (user == null) {
			throw new FitnessException(HttpStatus.INTERNAL_SERVER_ERROR.value(), ErrorCodes.ERROR_500022);
		}

		final int userAge = UserHelper.getUserAge(user.getBirthDate());
		final double userWeight = user.getWeight() != null ? user.getWeight() : -1;
		final int userHeight = user.getHeight() != null ? user.getHeight() : -1;
		logger.debug("Getting exercise information for user where: [age: " + userAge + ", weight: "  + userWeight
				     + ", height: " + userHeight + "]");

		final StringBuilder postRequestBody = new StringBuilder();
		postRequestBody.append("{\"query\": \"" + exercise + " " + durationInMinutes + " minutes\"");
		if (userAge > 0) {
			postRequestBody.append(", ");
			postRequestBody.append("\"age\": " + userAge);
		}
		if (userWeight > 0) {
			postRequestBody.append(", ");
			postRequestBody.append("\"weight_kg\": " + userWeight);
		}
		if (userHeight > 0) {
			postRequestBody.append(", ");
			postRequestBody.append("\"height_cm\": " + userHeight);
		}
		postRequestBody.append("}");

		// when add gender:
		//		String postRequestBody = "{\"query\": \"" + exercise + " " + durationInMinutes + " minutes\","
		//				+ "\"age\": " + userAge + ","
		//				+ "\"weight_kg\": " + userWeight + ","
		//				+ "\"height_cm\": " + userHeight + ","
		//				+ "\"gender\": \"female\"}"; // TODO: change to be based on user profile

		HttpURLConnection httpUrlConnection = null;
		BufferedReader bufferedReader = null;
		final StringBuilder content = new StringBuilder();

		try {
			httpUrlConnection = nutritionixConfig.getConnectionExercises(postRequestBody.toString());

			if (httpUrlConnection.getResponseCode() != HttpStatus.OK.value()) {
				throw new FitnessException(HttpStatus.INTERNAL_SERVER_ERROR.value(), ErrorCodes.ERROR_500009, 
										   new String[] {String.valueOf(httpUrlConnection.getResponseCode())});
			}

			bufferedReader = new BufferedReader(new InputStreamReader(httpUrlConnection.getInputStream()));
			String inputLine;
			while ((inputLine = bufferedReader.readLine()) != null) {
				content.append(inputLine);
			}

		} finally {
			if (bufferedReader != null) {
				bufferedReader.close();
			}
			if (httpUrlConnection != null) {
				httpUrlConnection.disconnect();
			}
		}

		logger.debug("Content: {}.", content.toString());
		final JSONArray requestJSON = new JSONObject(content.toString())
												.getJSONArray(FitnessConstants.NUTRITIONIX_EXERCISES_ARRAY);

		final ObjectMapper mapper = new ObjectMapper();
		final String jsonData = requestJSON.toString();
		final Exercise[] exerciseArray = mapper.readValue(jsonData, Exercise[].class);

		logger.debug("Exercise: {}.", exerciseArray[0].toString());
		return exerciseArray[0];
	}

}
