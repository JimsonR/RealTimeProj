package com.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.main.entity.Event;
import com.main.model.GetEventsForEditTournamentProjection;
import com.main.model.GetEventsForTournamentProjection;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer>{

	Event findByEventId(int eventId);

	@Query(nativeQuery = true,value="select e.event_name from event e where e.tournament_id = ?1")
	List<String> getEventsByTournamentId(int tournamentId);
	
	@Query(nativeQuery=true,value="select e.event_name as EventName,"
			+ "e.category as Category,"
			+ "e.match_type as MatchType,"
			+ "e.entry_fee as EntryFee,"
			+ "e.discount as Discount,"
			+ "e.max_entries as MaxEntries from  event e where e.tournament_id = ?1")
	List<GetEventsForEditTournamentProjection> getByTournamentId(int tournamentId);
}
