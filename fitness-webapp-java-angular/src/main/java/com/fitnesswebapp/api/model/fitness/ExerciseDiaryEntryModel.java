package com.fitnesswebapp.api.model.fitness;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 * Exercise diary entry entity containing the data for each entry in an exercise diary.
 *
 * @author Crystiane Meira
 */
@Data
@JsonInclude(content = Include.NON_NULL)
public class ExerciseDiaryEntryModel {

	private long exerciseDiaryId;
	private String exercise;
	private int duration;
	private long calories;
	private LocalDate date;

}
