package com.fitnesswebapp.api.model.fitness;

import lombok.Data;

/**
 * Food diary entry entity containing the data for each entry in a food diary.
 *
 * @author Crystiane Meira
 */
@Data
public class FoodDiaryEntryModel {

	private Long foodDiaryEntryId;

	private String id;
	private String foodName;
	private Double calories;
	private Double totalCarbohydrate;
	private Double totalFat;
	private Double totalProtein;

}
