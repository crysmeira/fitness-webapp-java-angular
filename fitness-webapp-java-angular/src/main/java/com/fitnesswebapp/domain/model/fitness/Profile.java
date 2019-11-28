package com.fitnesswebapp.domain.model.fitness;

import java.time.LocalDate;

/**
 * A POJO for profile containing user's details to be displayed.
 *
 * @author Crystiane Meira
 */
public class Profile {

	private final String firstName;
	private final String lastName;
	private final String email;
	private final LocalDate birthDate;
	private final Double weight;
	private final Integer height;

	private Profile(final ProfileBuilder profileBuilder) {
		firstName = profileBuilder.firstName;
		lastName = profileBuilder.lastName;
		email = profileBuilder.email;
		birthDate = profileBuilder.birthDate;
		weight = profileBuilder.weight;
		height = profileBuilder.height;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public Double getWeight() {
		return weight;
	}

	public Integer getHeight() {
		return height;
	}


	public static class ProfileBuilder {
		private String firstName;
		private String lastName;
		private final String email;
		private LocalDate birthDate;
		private Double weight;
		private Integer height;

		public ProfileBuilder(final String email) {
			this.email = email;
		}

		public ProfileBuilder setFirstName(final String firstName) {
			this.firstName = firstName;
			return this;
		}

		public ProfileBuilder setLastName(final String lastName) {
			this.lastName = lastName;
			return this;
		}

		public ProfileBuilder setBirthDate(final LocalDate birthDate) {
			this.birthDate = birthDate;
			return this;
		}

		public ProfileBuilder setWeight(final Double weight) {
			this.weight = weight;
			return this;
		}

		public ProfileBuilder setHeight(final Integer height) {
			this.height = height;
			return this;
		}

		public Profile build() {
			return new Profile(this);
		}
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("Profile [firstName=");
		builder.append(firstName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", email=");
		builder.append(email);
		builder.append(", birthDate=");
		builder.append(birthDate);
		builder.append(", weight=");
		builder.append(weight);
		builder.append(", height=");
		builder.append(height);
		builder.append("]");
		return builder.toString();
	}

}
