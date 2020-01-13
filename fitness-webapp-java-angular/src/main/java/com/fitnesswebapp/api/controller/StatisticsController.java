package com.fitnesswebapp.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fitnesswebapp.api.model.fitness.StatisticsModel;
import com.fitnesswebapp.domain.model.fitness.User;
import com.fitnesswebapp.domain.service.StatisticsService;
import com.fitnesswebapp.domain.service.UserService;
import com.fitnesswebapp.utils.BeanNames;

/**
 * Handles the requests to Fitness Webapp for statistics operations.
 *
 * @author Crystiane Meira
 */
@RestController
@RequestMapping("/statistics")
@CrossOrigin(origins = "http://localhost:4200")
public class StatisticsController {

	private final StatisticsService statisticsService;
	private final UserService userService;

	@Autowired
	public StatisticsController(@Qualifier(BeanNames.STATISTICS_SERVICE) final StatisticsService fitnessService, 
			                    @Qualifier(BeanNames.USER_SERVICE) final UserService userService) {
		statisticsService = fitnessService;
		this.userService = userService;
	}

	/**
	 * Generates the food and exercise statistics for the last {@code numDays} days.
	 *
	 * @param userEmail The email address for the user logged in.
	 * @param numDays The number of days to be considered to generate statistics starting from current day.
	 * @return The statistics for the last numDays days.
	 */
	@GetMapping(value = "/{email}/{numDays}")
	public StatisticsModel getStatistics(@PathVariable("email") final String userEmail, 
			                             @PathVariable("numDays") final int numDays) {
		final User user = userService.getUser(userEmail);
		return statisticsService.getStatistics(user, numDays);
	}
}
