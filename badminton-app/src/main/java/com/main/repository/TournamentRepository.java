package com.main.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.main.entity.Tournament;
import com.main.model.QueryResponse;
import com.main.model.ShowTournamentsModel;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Integer>{
	@Query(nativeQuery=true,value="select sponsor_poster from tournament where tournament_id=?1")
	byte[] findImageByTournamentId(int id);

	Tournament findByTournamentId(int tournamentId);

//	@Query(nativeQuery = true,value="select * from tournament")
//	List<Tournament> findAll();
	List<Tournament> findAll();
	
	
//	@Query(nativeQuery = true,value="select ShowTournamentModel(t.tournament_id AS TournamentId,t.address as Address,t.bookings_close_time as BookingsCloseTime,t.description as Description,t.end_date as EndDate,t.location as Location,t.sponsors as Sponsors,t.start_date as StartDate,t.tournament_name as TournamentName) from tournament t")
	@Query(nativeQuery = true,value="select t.tournament_name AS TournamentName,t.tournament_id AS TournamentId,t.poster AS Poster,t.start_date AS StartDate,t.end_date AS EndDate, t.address AS Address from tournament t")
	List<QueryResponse> getEvery();
	
}
