package com.main.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.main.entity.Tournament;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Integer>{
	@Query(nativeQuery=true,value="select sponsor_poster from tournament where tournament_id=?1")
	byte[] findImageByTournamentId(int id);

	Tournament findByTournamentId(int tournamentId);
}
