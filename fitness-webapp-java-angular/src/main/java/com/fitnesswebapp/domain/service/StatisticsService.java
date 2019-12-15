package com.fitnesswebapp.domain.service;

import com.fitnesswebapp.api.model.fitness.StatisticsModel;
import com.fitnesswebapp.domain.model.fitness.User;

/**
 * Interface for service to operate on statistics objects.
 *
 * @author Crystiane Meira
 */
public interface StatisticsService {

	/**
	 * Gets the statistics for the last {@code numDays}.
	 *
	 * @param user The user logged in.
	 * @param numDays The number of days to consider for statistics.
	 * @return The statistics for the last numDays.
	 */
	public StatisticsModel getStatistics(User user, int numDays);
}
