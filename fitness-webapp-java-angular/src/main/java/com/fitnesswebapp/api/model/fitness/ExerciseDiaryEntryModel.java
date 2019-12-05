package com.fitnesswebapp.api.model.fitness;

import java.time.LocalDate;

import lombok.Data;

/**
 * Exercise diary entry entity containing the data for each entry in an exercise diary.
 *
 * @author Crystiane Meira
 */
@Data
public class ExerciseDiaryEntryModel {

	private long exerciseDiaryId;
	private String exercise;
	private int duration;
	private long calories;
	private LocalDate date;

}
