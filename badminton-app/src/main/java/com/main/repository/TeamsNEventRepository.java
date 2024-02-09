package com.main.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.main.entity.Event;
import com.main.entity.Teams;
import com.main.entity.TeamsNEvent;
import com.main.entity.TeamsNEventId;
import com.main.model.EventMimic;
import com.main.model.QueryResponse;

@Repository
public interface TeamsNEventRepository extends JpaRepository<TeamsNEvent, TeamsNEventId>{
	int countByTeamsNEventIdEventId(Event event);

	
//	@Query(nativeQuery=true,value="select tms.* from teams tms join teamsnevent tmsnevnt on tms.team_id = tmsnevnt.team_id where tmsnevnt.event_id = ?1")
//	<T> List<T> findByTeamsNEventIdEventId(int eventId);
//	 @Query(
//		        nativeQuery = true,
//		        value = "SELECT t.team_id AS TeamId "+
//		                "FROM teams t " +
//		                "JOIN teamsnevent te ON t.team_id = te.team_id " +
//		                "WHERE te.event_id = ?1"
//		    )
//	 List<Integer> findByTeamsNEventIdEventId(int eventId);
//		List<QueryResponse> findByTeamsNEventIdEventId(int eventId);
List<TeamsNEvent>findByTeamsNEventIdEventId(Event event);
//	@Query(nativeQuery=true,value="select tms.* from teams tms join teamsnevent tmsnevnt on tms.team_id = tmsnevnt.team_id where tmsnevnt.event_id = ?1")
//	List<Teams> findByTeamsNEventIdEventId(int eventId);
	
	
}
