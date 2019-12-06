package com.fitnesswebapp.api.model.fitness;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 * Food diary entity that contains a list of food diary entries for a given day.
 *
 * @author Crystiane Meira
 */
@Data
@JsonInclude(content = Include.NON_NULL)
public class FoodDiaryModel {

	private Long foodDiaryId;
	private LocalDate date;
	private List<FoodDiaryEntryModel> foodDiaryEntries;
	private UserModel user;

}
