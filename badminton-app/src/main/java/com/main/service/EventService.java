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

@Service
@Data
@RequiredArgsConstructor
public class EventService {
	 private final TournamentRepository tournamentRepository;
	 private final EventRepository eventRepository;
	 
	public int handleCreateEventRequest(List<CreateEventModel> createEventModel,int tournamentId) {
		Tournament tournament = tournamentRepository.findByTournamentId(tournamentId);
		for(CreateEventModel event:createEventModel) {
			Event newEvent = Event.builder()
					.category(event.getCategory())
					.tournamentId(tournament)
					.matchType(event.getMatchType())
					.entryFee(event.getEntryFee())
					.allowBookings(event.getAllowBookings())
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
}
