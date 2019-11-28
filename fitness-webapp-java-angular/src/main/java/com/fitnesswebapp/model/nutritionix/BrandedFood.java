package com.fitnesswebapp.model.nutritionix;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 * Branded food entity that contains a list of food entities from Nutritionix API.
 *
 * @author Crystiane Meira
 */
@Data
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BrandedFood {

	private List<Food> branded;

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("BrandedFood [branded=");
		builder.append(branded);
		builder.append("]");
		return builder.toString();
	}

}
