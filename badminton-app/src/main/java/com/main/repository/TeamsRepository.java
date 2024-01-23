package com.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.main.entity.Event;
import com.main.entity.Teams;
import com.main.entity.TeamsPrimaryKey;

@Repository
public interface TeamsRepository extends JpaRepository<Teams, TeamsPrimaryKey> {

	@Query(nativeQuery = true,value = "select count(*) from teams where event_id = ?1")
	int countByEventId(int eventId);
//	@Query(nativeQuery = true,value = "select * from teams where event_id = ?1")
//	List<Teams>findByevent_id(int eventId);
	List<Teams>findByPrimaryKeyEventId(Event eventId);
	
}
