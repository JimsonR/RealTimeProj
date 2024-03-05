package com.main.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.main.model.GetEventsForTournamentProjection;
import com.main.repository.EventRepository;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Service
@Data
@RequiredArgsConstructor
@Builder
public class GetEventsForTournamentService {
	private final EventRepository eventRepository;
	public List<GetEventsForTournamentProjection> getEvents(int tournamentId) {
		return eventRepository.getEventsByTournamentId(tournamentId);
	}
}
