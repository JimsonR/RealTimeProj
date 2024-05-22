package com.main.service;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.main.entity.Event;
import com.main.entity.Tournament;
import com.main.model.CreateEventModel;
import com.main.repository.EventRepository;
import com.main.repository.TournamentRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@Data
@RequiredArgsConstructor
public class EventService {
	 private final TournamentRepository tournamentRepository;
	 private final EventRepository eventRepository;
	 
	public int handleCreateEventRequest(List<CreateEventModel> createEventModel, int tournamentId) {
		Tournament tournament = tournamentRepository.findByTournamentId(tournamentId);
		for(CreateEventModel event:createEventModel) {
			Event newEvent = Event.builder()
					.EventName(event.getEventName())
					.category(event.getEventType())
					.tournamentId(tournament)
					.matchType(event.getMatchType())
					.entryFee(event.getEntryFee())
					.maxEntries(event.getMaxEntries())
//					.qualifiersPerPool(event.getQualifiersPerPool())
//					.totalPools(event.getTotalPools())
//					.allowBookings(event.getAllowBookings())
					.build();
			
			 eventRepository.save(newEvent);
		}
		
		return 100;
//		Tournament tournamentId;
//		private int category;
//		private int matchType;
//		private int entryFee;
//		private int maxEntries;
//		private boolean allowBookings;
	}

public int handleEditEventRequest(List<CreateEventModel> createEventModel, int tournamentId) {
	Tournament tournament = tournamentRepository.findByTournamentId(tournamentId);
	List<Event> events = eventRepository.findByTournamentId(tournament);
	int i = 0;
	for(Event event:events) {
		event.setEventName(createEventModel.get(i++).getEventName());

		eventRepository.save(event);
	}

	return 100;
//		Tournament tournamentId;
//		private int category;
//		private int matchType;
//		private int entryFee;
//		private int maxEntries;
//		private boolean allowBookings;
}
}
