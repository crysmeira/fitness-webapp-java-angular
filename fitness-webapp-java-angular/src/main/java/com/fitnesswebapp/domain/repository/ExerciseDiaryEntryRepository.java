package com.fitnesswebapp.domain.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.fitnesswebapp.domain.model.fitness.ExerciseDiaryEntry;

/**
 * Repository for ExerciseDiaryEntry object.
 *
 * @author Crystiane Meira
 */
@RepositoryRestResource
public interface ExerciseDiaryEntryRepository extends JpaRepository<ExerciseDiaryEntry, Long> {

	@Query("SELECT e FROM ExerciseDiaryEntry e WHERE e.user.userId = ?1 AND e.date = ?2")
	public List<ExerciseDiaryEntry> findExerciseDiaryEntryByUserAndDate(Long userId, LocalDate today);

	@Query("SELECT e FROM ExerciseDiaryEntry e WHERE e.user.userId = ?1 AND e.date >= ?2 AND e.date <= ?3")
	public List<ExerciseDiaryEntry> findExerciseDiaryEntryForLastDDays(Long userId, LocalDate firstDay, LocalDate today);
}
