package com.fitnesswebapp.api.model.fitness;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 * A POJO for profile containing user's details to be displayed.
 *
 * @author Crystiane Meira
 */
@Data
@JsonInclude(value = Include.NON_NULL)
public class UserModel {

	private String firstName;
	private String lastName;
	private String email;
	private LocalDate birthDate;
	private Double weight;
	private Integer height;

}
