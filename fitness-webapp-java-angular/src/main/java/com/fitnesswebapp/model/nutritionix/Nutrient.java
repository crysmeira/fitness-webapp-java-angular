package com.fitnesswebapp.model.nutritionix;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * Nutrient entity that holds the information for nutrient retrieved from Nutritionix API.
 *
 * @author Crystiane Meira
 */
@Data
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Nutrient {

	@JsonProperty("nix_item_id")
	private String nixItemId;

	@JsonProperty("food_name")
	private String foodName;

	@JsonProperty("serving_unit")
	private String servingUnit;

	@JsonProperty("serving_qty")
	private String servingQuantity;

	@JsonProperty("serving_weight_grams")
	private Long servingWeightGrams;

	@JsonProperty("nf_calories")
	private Long calories;

	@JsonProperty("nf_total_fat")
	private Long totalFat;

	@JsonProperty("nf_total_carbohydrate")
	private Long totalCarbohydrate;

	@JsonProperty("nf_protein")
	private Long totalProtein;

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("Nutrient [nixItemId=");
		builder.append(nixItemId);
		builder.append(", foodName=");
		builder.append(foodName);
		builder.append(", servingUnit=");
		builder.append(servingUnit);
		builder.append(", servingQuantity=");
		builder.append(servingQuantity);
		builder.append(", servingWeightGrams=");
		builder.append(servingWeightGrams);
		builder.append(", calories=");
		builder.append(calories);
		builder.append(", totalFat=");
		builder.append(totalFat);
		builder.append(", totalCarbohydrate=");
		builder.append(totalCarbohydrate);
		builder.append(", totalProtein=");
		builder.append(totalProtein);
		builder.append("]");
		return builder.toString();
	}

}
