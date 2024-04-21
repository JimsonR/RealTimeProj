package com.main.repository;

import java.util.Date;
import java.util.List;

import com.main.model.GetTournamentProjection;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.main.entity.Tournament;
import com.main.model.QueryResponse;
import org.springframework.web.multipart.MultipartFile;

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
	@Query(nativeQuery = true,value="select t.tournament_name AS TournamentName" +
			",t.poster AS Poster" +
			",t.start_date AS StartDate" +
			",t.end_date AS EndDate" +
			",t.sponsor_poster AS SponsorPoster" +
			",t.address AS Address" +
			",t.location AS Location" +
			", t.bookings_close_time AS BookingsCloseTime" +
			",t.description AS Description" +
			", t.sponsors AS Sponsors"+
			" from tournament t where tournament_id = ?1")
	GetTournamentProjection getTournament(int tournamentId);

	@Transactional
	@Modifying
	@Query(nativeQuery = true,value = "update tournament set tournament_name=?1 where tournament_id = ?2")
	void updateTournamentName(String tournamentName, int tournamentId);

	@Transactional
	@Modifying
	@Query(nativeQuery = true,value = "update table tournament set location=?1 where tournament_id = ?2")
	void updateLocation(String location, int tournamentId);

	@Transactional
	@Modifying
	@Query(nativeQuery = true,value = "update table tournament set address=?1 where tournament_id = ?2")
	void updateAddress(String address, int tournamentId);
	@Transactional
	@Modifying
	@Query(nativeQuery = true,value = "update table tournament set description=?1 where tournament_id = ?2")
	void updateDescription(String description, int tournamentId);
	@Transactional
	@Modifying
	@Query(nativeQuery = true,value = "update table tournament set sponsors=?1 where tournament_id = ?2")
	void updateSponsors(String sponsors, int tournamentId);
	@Transactional
	@Modifying
	@Query(nativeQuery = true,value = "update table tournament set start_date=?1 where tournament_id = ?2")
	void updateStartDate(Date startDate, int tournamentId);
	@Transactional
	@Modifying
	@Query(nativeQuery = true,value = "update table tournament set end_date=?1 where tournament_id = ?2")
	void updateEndDate(Date endDate, int tournamentId);
	@Transactional
	@Modifying
	@Query(nativeQuery = true,value = "update table tournament set update_bookings_close_time=?1 where tournament_id = ?2")
	void updateBookingsCloseTime(Date bookingsCloseTime, int tournamentId);
	@Transactional
	@Modifying
	@Query(nativeQuery = true,value = "update table tournament set poster=?1, set sponsor_poster = ?2 where tournament_id = ?3")
	void updateFiles(MultipartFile poster, MultipartFile sponsorPoster, int tournamentId);
}
