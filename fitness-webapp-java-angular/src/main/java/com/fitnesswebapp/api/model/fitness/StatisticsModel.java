package com.fitnesswebapp.api.model.fitness;

import java.time.LocalDate;
import java.util.Map;

/**
 * A POJO for statistics containing food and exercise data.
 *
 * @author Crystiane Meira
 */
public class StatisticsModel {

	private Map<LocalDate, Map<String, Double>> foodData;

	private Map<LocalDate, Map<String, Long>> exerciseData;

	public Map<LocalDate, Map<String, Double>> getFoodData() {
		return foodData;
	}

	public void setFoodData(final Map<LocalDate, Map<String, Double>> foodData) {
		this.foodData = foodData;
	}

	public Map<LocalDate, Map<String, Long>> getExerciseData() {
		return exerciseData;
	}

	public void setExerciseData(final Map<LocalDate, Map<String, Long>> exerciseData) {
		this.exerciseData = exerciseData;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("Statistics [foodData=");
		builder.append(foodData);
		builder.append(", exerciseData=");
		builder.append(exerciseData);
		builder.append("]");
		return builder.toString();
	}

}
