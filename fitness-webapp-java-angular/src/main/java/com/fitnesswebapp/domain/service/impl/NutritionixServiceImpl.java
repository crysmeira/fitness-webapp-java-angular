package com.fitnesswebapp.domain.service.impl;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fitnesswebapp.domain.exception.FitnessException;
import com.fitnesswebapp.domain.exception.InvalidInputException;
import com.fitnesswebapp.domain.model.nutritionix.Exercise;
import com.fitnesswebapp.domain.model.nutritionix.Food;
import com.fitnesswebapp.domain.model.nutritionix.Nutrient;
import com.fitnesswebapp.domain.repository.NutritionixRepository;
import com.fitnesswebapp.domain.service.NutritionixService;
import com.fitnesswebapp.utils.BeanNames;
import com.fitnesswebapp.utils.ErrorCodes;

/**
 * Implementation of {@link NutritionixService}.
 *
 * @author Crystiane Meira
 */
@Service(BeanNames.NUTRITIONIX_SERVICE)
public class NutritionixServiceImpl implements NutritionixService {

	private final NutritionixRepository nutritionixRepository;

	@Autowired
	public NutritionixServiceImpl(
			@Qualifier(BeanNames.NUTRITIONIX_REPOSITORY) final NutritionixRepository nutritionixRepository) {
		this.nutritionixRepository = nutritionixRepository;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Food> searchFood(final String query) throws FitnessException {
		if (StringUtils.isBlank(query)) {
			throw new InvalidInputException(HttpStatus.BAD_REQUEST, ErrorCodes.ERROR_400009);
		}

		try {
			return nutritionixRepository.searchFood(query);
		} catch (JSONException | IOException e) {
			throw new FitnessException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCodes.ERROR_500001, 
					                   new String[] {query}, e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Nutrient getNutrient(final String foodId) throws FitnessException {
		if (StringUtils.isBlank(foodId)) {
			throw new InvalidInputException(HttpStatus.BAD_REQUEST, ErrorCodes.ERROR_400010);
		}

		try {
			return nutritionixRepository.getNutrient(foodId);
		} catch (IOException | JSONException e) {
			throw new FitnessException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCodes.ERROR_500002, 
					                   new String[] {foodId}, e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Exercise getExercise(final String exercise, final int durationInMinutes, final String email) 
			                    throws FitnessException {
		if (StringUtils.isBlank(exercise)) {
			throw new InvalidInputException(HttpStatus.BAD_REQUEST, ErrorCodes.ERROR_400011);
		}
		if (durationInMinutes < 0) {
			throw new InvalidInputException(HttpStatus.BAD_REQUEST, ErrorCodes.ERROR_400012);
		}
		if (StringUtils.isBlank(email)) {
			throw new InvalidInputException(HttpStatus.BAD_REQUEST, ErrorCodes.ERROR_400013);
		}

		try {
			return nutritionixRepository.getExercise(exercise, durationInMinutes, email);
		} catch (IOException | JSONException e) {
			throw new FitnessException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCodes.ERROR_500003, 
					                   new String[] {exercise, String.valueOf(durationInMinutes)}, e);
		}
	}
}
