package com.fitnesswebapp.api.model.fitness;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 * Food diary entry entity containing the data for each entry in a food diary.
 *
 * @author Crystiane Meira
 */
@Data
@JsonInclude(content = Include.NON_NULL)
public class FoodDiaryEntryModel {

	private Long foodDiaryEntryId;

	private String id;
	private String foodName;
	private Double calories;
	private Double totalCarbohydrate;
	private Double totalFat;
	private Double totalProtein;

}
