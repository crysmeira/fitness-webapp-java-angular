package com.fitnesswebapp.domain.model.nutritionix;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * Exercise entity that holds the information for exercise retrieved from Nutritionix API.
 *
 * @author Crystiane Meira
 */
@Data
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Exercise {

	@JsonProperty("name")
	private String exercise;

	@JsonProperty("duration_min")
	private int duration;

	@JsonProperty("nf_calories")
	private long calories;

}
