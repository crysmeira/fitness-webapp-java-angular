package com.fitnesswebapp.api.model.fitness.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

import lombok.Data;

/**
 * Food diary entry entity containing the data for each entry in a food diary.
 *
 * @author Crystiane Meira
 */
@Data
public class FoodDiaryEntryInput {

	@NotBlank
	private String id; 
	
	@NotBlank
	private String foodName;
	
	@PositiveOrZero
	private Double calories;
	
	@PositiveOrZero
	private Double totalCarbohydrate;
	
	@PositiveOrZero
	private Double totalFat;
	
	@PositiveOrZero
	private Double totalProtein;

}
