package com.main.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.main.entity.Event;
import com.main.model.GetEventsForEditTournamentProjection;
import com.main.repository.EventRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventsService {
	private final EventRepository eventRepository;
	public List<GetEventsForEditTournamentProjection> handleGetEvents(int tournamentId) {
		return eventRepository.getByTournamentId(tournamentId);
	}

}
