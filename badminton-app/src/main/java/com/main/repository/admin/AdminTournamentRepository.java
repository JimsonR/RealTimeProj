package com.main.repository.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.main.entity.Tournament;

import jakarta.transaction.Transactional;

@Repository
public interface AdminTournamentRepository extends JpaRepository<Tournament, Integer>{
	@Transactional
    @Modifying
	@Query(nativeQuery = true,value="update tournament set is_active = ?1 where tournament_id = ?2")
	void changeBooleanValue(boolean newValue,int tournament_id);
	
}
