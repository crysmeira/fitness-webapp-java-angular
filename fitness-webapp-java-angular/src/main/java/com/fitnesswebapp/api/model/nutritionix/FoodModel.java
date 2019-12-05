package com.fitnesswebapp.api.model.nutritionix;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 * Food entity that holds the information for food retrieved from Nutritionix API.
 *
 * @author Crystiane Meira
 */
@Data
@JsonInclude(value = Include.NON_NULL)
public class FoodModel {

	private String nixItemId;
	private String foodName;
	private String brandName;

}
