package com.fitnesswebapp.domain.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.fitnesswebapp.domain.model.fitness.FoodDiaryEntry;

/**
 * Repository for FoodDiary object.
 *
 * @author Crystiane Meira
 */
@RepositoryRestResource
public interface FoodDiaryEntryRepository extends JpaRepository<FoodDiaryEntry, Long> {

	@Query("SELECT f FROM FoodDiaryEntry f WHERE f.user.userId = ?1 AND f.date = ?2")
	public List<FoodDiaryEntry> findFoodDiaryEntryByUserAndDate(Long userId, LocalDate today);

	@Query("SELECT f FROM FoodDiaryEntry f WHERE f.user.userId = ?1 AND f.date >= ?2 AND f.date <= ?3")
	public List<FoodDiaryEntry> findFoodDiaryEntryForLastDDays(Long userId, LocalDate firstDay, LocalDate today);
}
