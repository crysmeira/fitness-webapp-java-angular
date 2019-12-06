package com.fitnesswebapp.domain.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.fitnesswebapp.domain.model.fitness.FoodDiary;

/**
 * Repository for FoodDiary object.
 *
 * @author Crystiane Meira
 */
@RepositoryRestResource
public interface FoodDiaryRepository extends JpaRepository<FoodDiary, Long> {

	@Query("SELECT f FROM FoodDiary f WHERE f.user.userId = ?1 AND f.date = ?2")
	public FoodDiary findFoodDiaryByUserAndDate(Long userId, LocalDate today);

	@Query("SELECT f FROM FoodDiary f WHERE f.user.userId = ?1 AND f.date >= ?2 AND f.date <= ?3")
	public List<FoodDiary> findFoodDiaryForLastDDays(Long userId, LocalDate firstDay, LocalDate today);
}
