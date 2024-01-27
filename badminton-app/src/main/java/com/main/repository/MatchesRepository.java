package com.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.main.entity.Matches;

@Repository
public interface MatchesRepository extends JpaRepository<Matches, Integer>{
	
}
