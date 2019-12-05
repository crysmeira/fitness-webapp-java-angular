package com.fitnesswebapp.api.model.fitness.input;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

/**
 * Food diary entity that contains a list of food diary entries for a given day.
 *
 * @author Crystiane Meira
 */
@Data
public class FoodDiaryInput {

	private Long foodDiaryId;
	private LocalDate date;
	private List<FoodDiaryEntryInput> foodDiaryEntries;

}
