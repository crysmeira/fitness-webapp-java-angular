package com.fitnesswebapp.domain.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.fitnesswebapp.domain.model.fitness.User;

/**
 * Repository for {@link User} object.
 *
 * @author Crystiane Meira
 */
@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, Long> {

	@Query("SELECT u FROM User u WHERE u.email = ?1")
	public User findUserByEmail(String email);

	@Query("SELECT u.userId FROM User u WHERE u.email = ?1")
	public Long findUserIdByEmail(String email);

}
