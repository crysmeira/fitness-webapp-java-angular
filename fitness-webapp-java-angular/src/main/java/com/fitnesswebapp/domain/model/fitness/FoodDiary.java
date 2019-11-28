package com.fitnesswebapp.domain.model.fitness;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

/**
 * Food diary entity that contains a list of food diary entries for a given day.
 *
 * @author Crystiane Meira
 */
@Data
@Entity
public class FoodDiary {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "food_diary_id")
	private Long foodDiaryId;

	@Column(name = "date")
	private LocalDate date;

	@OneToMany(targetEntity=FoodDiaryEntry.class, fetch=FetchType.LAZY, cascade = {CascadeType.ALL})
	private List<FoodDiaryEntry> foodDiaryEntries;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("FoodDiary [foodDiaryId=");
		builder.append(foodDiaryId);
		builder.append(", date=");
		builder.append(date);
		builder.append(", foodDiaryEntries=");
		builder.append(foodDiaryEntries);
		builder.append(", user=");
		builder.append(user);
		builder.append("]");
		return builder.toString();
	}

}
