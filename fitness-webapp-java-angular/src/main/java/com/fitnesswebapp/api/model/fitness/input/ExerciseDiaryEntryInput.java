package com.fitnesswebapp.api.model.fitness.input;

import lombok.Data;

/**
 * Exercise diary entry entity containing the data for each entry in an exercise diary.
 *
 * @author Crystiane Meira
 */
@Data
public class ExerciseDiaryEntryInput {

	private String exercise;
	private int duration;
	private long calories;

}
