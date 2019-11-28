package com.fitnesswebapp.model.nutritionix;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * Food entity that holds the information for food retrieved from Nutritionix API.
 *
 * @author Crystiane Meira
 */
@Data
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Food {

	@JsonProperty("nix_item_id")
	private String nixItemId;

	@JsonProperty("food_name")
	private String foodName;

	@JsonProperty("brand_name")
	private String brandName;

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("Food [nixItemId=");
		builder.append(nixItemId);
		builder.append(", foodName=");
		builder.append(foodName);
		builder.append(", brandName=");
		builder.append(brandName);
		builder.append("]");
		return builder.toString();
	}

}
