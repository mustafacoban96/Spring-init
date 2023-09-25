package com.mvcexp.web.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mvcexp.web.models.Club;

public interface ClubRepository extends JpaRepository<Club, Long>{
	Optional<Club> findByTitle(String url);
	
	@Query("SELECT c FROM Club c WHERE c.title LIKE CONCAT('%', :query, '%')")
	List<Club> searchClubs(String query);
}
