package com.fitnesswebapp.api.model.fitness.input;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

/**
 * A POJO for profile containing user's details to be displayed.
 *
 * @author Crystiane Meira
 */
@Data
@Builder
public class UserInput {

	private final String firstName;
	private final String lastName;
	private final String email;
	private final LocalDate birthDate;
	private final Double weight;
	private final Integer height;

}
