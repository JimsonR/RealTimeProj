package com.main.repository.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.main.entity.Tournament;

@Repository
public interface AdminTournamentRepository extends JpaRepository<Tournament, Integer>{
	
}
