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
	
	private void createSingleEvent(CreateEventModel event,Tournament tournament) {
		Event newEvent = Event.builder()
				.EventName(event.getEventName())
				.category(event.getEventType())
				.tournamentId(tournament)
				.matchType(event.getMatchType())
				.entryFee(event.getEntryFee())
				.maxEntries(event.getMaxEntries())
				.discount(event.getDiscount())
//				.qualifiersPerPool(event.getQualifiersPerPool())
//				.totalPools(event.getTotalPools())
//				.allowBookings(event.getAllowBookings())
				.build();
		
		 eventRepository.save(newEvent);
	}

public int handleEditEventRequest(List<CreateEventModel> createEventModel, int tournamentId) {
	Tournament tournament = tournamentRepository.findByTournamentId(tournamentId);
	List<Event> events = eventRepository.findByTournamentId(tournament);
	int i = 0;
	if(createEventModel.size() > events.size()) {
		for(Event event:events) {
			event.setEventName(createEventModel.get(i).getEventName());
			event.setCategory(createEventModel.get(i).getEventType());
			event.setMatchType(createEventModel.get(i).getMatchType());
			event.setEntryFee(createEventModel.get(i).getEntryFee());
			event.setMaxEntries(createEventModel.get(i).getMaxEntries());
			event.setDiscount(createEventModel.get(i).getDiscount());
			eventRepository.save(event);
			i++;
		}
		while(i < createEventModel.size()) {
			this.createSingleEvent(createEventModel.get(i++), tournament);
		}
		return 100;
	}
	else if(createEventModel.size() < events.size()) {
		int diff = events.size() - createEventModel.size();
		
		for(CreateEventModel createEvent:createEventModel ) {
			events.get(i).setEventName(createEvent.getEventName());
			events.get(i).setCategory(createEvent.getEventType());
			events.get(i).setMatchType(createEvent.getMatchType());
			events.get(i).setEntryFee(createEvent.getEntryFee());
			events.get(i).setMaxEntries(createEvent.getMaxEntries());
			events.get(i).setDiscount(createEvent.getDiscount());
			i++;
			eventRepository.save(events.get(i));
		}
		while(i < events.size()) {
			eventRepository.delete(events.get(i++));
		}
		return 100;
	}
	else {
		return 1;
	}
	
//		Tournament tournamentId;
//		private int category;
//		private int matchType;
//		private int entryFee;
//		private int maxEntries;
//		private boolean allowBookings;
}
}
