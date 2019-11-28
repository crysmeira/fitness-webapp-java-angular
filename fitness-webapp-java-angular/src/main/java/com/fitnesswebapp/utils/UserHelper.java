package com.fitnesswebapp.utils;

import java.time.LocalDate;
import java.time.Period;

/**
 * Helper class to get user's information.
 *
 * @author Crystiane Meira
 */
public class UserHelper {

	/**
	 * Retrieves the age of the user.
	 *
	 * @param birthDate The user's date of birth.
	 * @return The age of the user based on the birth date and the current date.
	 */
	public static int getUserAge(final LocalDate birthDate) {
		if (birthDate == null) return -1;
		return Period.between(birthDate, LocalDate.now()).getYears();
	}

}
