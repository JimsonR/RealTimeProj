package com.main.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.main.entity.Event;
import com.main.entity.Teams;
import com.main.repository.EventRepository;
import com.main.repository.TeamsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GenerateFixtureService {
	
	//finds total rounds for participants
	private int findTotalRounds(int numberOfTeams) {
		int rounds = 1;
		int factor = 2;
		while(factor < numberOfTeams) {
			factor *= 2;
			rounds++;
		}
		return rounds;
	}
	
	private int getPossiblePlayersForRound1(int players,int totalRounds) {
		int limit = (int)Math.pow(2, totalRounds);
		int byes = limit - players;
		return players - byes;
	}
	
	//to create matches for round1
	private void createMatchesForRound1(List<Teams> teams, int totalRounds) {
		
		//shuffle the teams using Fisher Yates Method
		for(int i = teams.size()-1;i >= 0;i--) {
			int randomIndex = (int)Math.random()*i;
			Teams temp = teams.get(i);
			teams.set(i,teams.get(randomIndex));
			teams.set(randomIndex, temp);
		}
		
		int playersForRound1 = getPossiblePlayersForRound1(teams.size(),totalRounds);
		int byesForRound1 = teams.size() - playersForRound1;
		int matches = playersForRound1/2;
		for(int i = 0; i < playersForRound1;i += 2) {
			
		}
	}
	
	

	private final TeamsRepository teamsRepository;
	private final EventRepository eventRepository;
	public void handleGenerateFixtureRequest(int eventId) {
		int numberOfTeams =  teamsRepository.countByEventId(eventId);
		int totalRounds = findTotalRounds(numberOfTeams);
		Event event = eventRepository.findByEventId(eventId);
		event.setTotalRounds(totalRounds);
		eventRepository.save(event);
		List<Teams>teams = teamsRepository.findByEventId(eventId);
		createMatchesForRound1(teams,totalRounds);
		
	}
	
	
}
