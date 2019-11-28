package com.fitnesswebapp.domain.model.fitness;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

/**
 * Entity for User.
 *
 * @author Crystiane Meira
 */
@Data
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long userId;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

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

	@Column(name = "enabled")
	private Boolean enabled;

//	@Convert(converter = UserRoleConverter.class)
//	@OneToMany(cascade = {CascadeType.ALL})
	@ElementCollection(targetClass = UserRole.class)
	@JoinTable(name = "user_user_role", joinColumns = @JoinColumn(name = "user_id"))
	@Column(name = "role", nullable = false)
	@Enumerated(EnumType.STRING)
	private List<UserRole> roles;

	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<ExerciseDiaryEntry> exerciseDiaryEntries;

	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<FoodDiary> foodDiaries;

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
		builder.append(", enabled=");
		builder.append(enabled);
		builder.append(", roles=");
		builder.append(roles);
		builder.append(", exerciseDiaryEntries=");
		builder.append(exerciseDiaryEntries);
		builder.append(", foodDiaries=");
		builder.append(foodDiaries);
		builder.append("]");
		return builder.toString();
	}

}
