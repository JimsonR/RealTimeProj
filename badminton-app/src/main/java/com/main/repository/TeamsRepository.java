package com.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.main.entity.Event;
import com.main.entity.Teams;
@Repository
public interface TeamsRepository extends JpaRepository<Teams, Integer> {
	@Query(nativeQuery = true,value="SELECT t.* FROM Teams t join TeamsNEvent tne on t.team_id = tne.team_id where tne.event_id = ?1")
	List<Teams> findByTeamsNEventIdEventId(int i);

}
