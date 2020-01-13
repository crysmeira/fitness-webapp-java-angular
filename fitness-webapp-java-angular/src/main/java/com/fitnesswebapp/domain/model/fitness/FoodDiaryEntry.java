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

import lombok.Data;

/**
 * Food diary entry entity containing the data for each entry in a food diary.
 *
 * @author Crystiane Meira
 */
@Data
@Entity
public class FoodDiaryEntry {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "food_diary_entry_id")
	private Long foodDiaryEntryId;

	@Column(name = "food_name")
	private String foodName;

	@Column(name = "calories")
	private Double calories;

	@Column(name = "total_carbohydrate")
	private Double totalCarbohydrate;

	@Column(name = "total_fat")
	private Double totalFat;

	@Column(name = "total_protein")
	private Double totalProtein;
	
	@Column(name = "date")
	private LocalDate date;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("FoodDiaryEntry [foodDiaryEntryId=");
		builder.append(foodDiaryEntryId);
		builder.append(", foodName=");
		builder.append(foodName);
		builder.append(", calories=");
		builder.append(calories);
		builder.append(", totalCarbohydrate=");
		builder.append(totalCarbohydrate);
		builder.append(", totalFat=");
		builder.append(totalFat);
		builder.append(", totalProtein=");
		builder.append(totalProtein);
		builder.append(", date=");
		builder.append(date);
		builder.append("]");
		return builder.toString();
	}

}
