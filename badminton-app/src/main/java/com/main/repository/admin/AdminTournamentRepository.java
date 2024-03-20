package com.main.repository.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.main.entity.Tournament;

@Repository
public interface AdminTournamentRepository extends JpaRepository<Tournament, Integer>{

	@Query(nativeQuery = true,value="update Tournament set is_active = true where tournament_id = ?2")
	String changeBooleanValue(boolean newValue,int tournament_id);
	
}
