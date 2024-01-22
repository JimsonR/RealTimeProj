package com.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.main.entity.Teams;
import com.main.entity.TeamsPrimaryKey;

@Repository
public interface TeamsRepository extends JpaRepository<Teams, TeamsPrimaryKey> {

	int countByEventId(int eventId);

	List<Teams> findByEventId(int eventId);
	
}
