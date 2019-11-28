package com.fitnesswebapp.model.nutritionix;

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

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("Exercise [exercise=");
		builder.append(exercise);
		builder.append(", duration=");
		builder.append(duration);
		builder.append(", calories=");
		builder.append(calories);
		builder.append("]");
		return builder.toString();
	}

}
