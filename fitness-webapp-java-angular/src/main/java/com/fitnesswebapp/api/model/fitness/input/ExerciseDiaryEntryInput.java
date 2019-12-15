package com.fitnesswebapp.api.model.fitness.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

import lombok.Data;

/**
 * Exercise diary entry entity containing the data for each entry in an exercise diary.
 *
 * @author Crystiane Meira
 */
@Data
public class ExerciseDiaryEntryInput {

	@NotBlank
	private String exercise;
	
	@PositiveOrZero
	private Integer duration;
	
	@PositiveOrZero
	private Long calories;

}
