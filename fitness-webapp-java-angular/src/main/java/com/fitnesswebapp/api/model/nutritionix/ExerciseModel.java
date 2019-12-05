package com.fitnesswebapp.api.model.nutritionix;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 * Exercise entity that holds the information for exercise retrieved from Nutritionix API.
 *
 * @author Crystiane Meira
 */
@Data
@JsonInclude(value = Include.NON_NULL)
public class ExerciseModel {

	private String exercise;
	private int duration;
	private long calories;

}
