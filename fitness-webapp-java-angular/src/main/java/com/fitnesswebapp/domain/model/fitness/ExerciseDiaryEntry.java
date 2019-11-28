package com.fitnesswebapp.domain.model.fitness;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * Exercise diary entry entity containing the data for each entry in an exercise diary.
 *
 * @author Crystiane Meira
 */
@Data
@Entity
public class ExerciseDiaryEntry {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "exercise_diary_id")
	private long exerciseDiaryId;

	@JsonProperty("name")
	@Column(name = "exercise")
	private String exercise;

	@JsonProperty("duration_min")
	@Column(name = "duration")
	private int duration;

	@JsonProperty("nf_calories")
	@Column(name = "calories")
	private long calories;

	@Column(name = "date")
	private LocalDate date;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("ExerciseDiaryEntry [exerciseDiaryId=");
		builder.append(exerciseDiaryId);
		builder.append(", exercise=");
		builder.append(exercise);
		builder.append(", duration=");
		builder.append(duration);
		builder.append(", calories=");
		builder.append(calories);
		builder.append(", date=");
		builder.append(date);
		builder.append(", user=");
		builder.append(user);
		builder.append("]");
		return builder.toString();
	}

}
