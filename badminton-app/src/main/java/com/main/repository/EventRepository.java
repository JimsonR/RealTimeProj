package com.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.main.entity.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer>{

	Event findByEventId(int eventId);
	
}
