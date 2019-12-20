package com.fitnesswebapp.domain.model.fitness;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fitnesswebapp.core.validation.NotUpdatable;

import lombok.Data;

/**
 * Entity for User.
 *
 * @author Crystiane Meira
 */
@Data
@Entity
public class User {

	@NotUpdatable
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long userId;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@NotUpdatable
	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "birth_date")
	private LocalDate birthDate;

	@Column(name = "weight")
	private Double weight;

	@Column(name = "height")
	private Integer height;

	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<ExerciseDiaryEntry> exerciseDiaryEntries;

	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<FoodDiaryEntry> foodDiaryEntries;

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("User [userId=");
		builder.append(userId);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", email=");
		builder.append(email);
		builder.append(", password=******");
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
