package com.fitnesswebapp.api.model.nutritionix;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 * Nutrient entity that holds the information for nutrient retrieved from Nutritionix API.
 *
 * @author Crystiane Meira
 */
@Data
@JsonInclude(value = Include.NON_NULL)
public class NutrientModel {

	private String nixItemId;
	private String foodName;
	private String servingUnit;
	private String servingQuantity;
	private Long servingWeightGrams;
	private Long calories;
	private Long totalFat;
	private Long totalCarbohydrate;
	private Long totalProtein;
	
}
