package com.main.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.main.entity.Event;
import com.main.entity.Teams;
import com.main.entity.TeamsNEvent;
import com.main.entity.TeamsNEventId;
import com.main.model.EventMimic;
import com.main.model.QueryResponse;
import com.main.repository.EventRepository;
import com.main.repository.TeamsNEventsRepository;
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
			System.out.println(teams.get(i) + " vs "+teams.get(i+1));
		}
	}
	
	

	private final TeamsNEventsRepository teamsNEventsRepository;
	private final EventRepository eventRepository;
	public void handleGenerateFixtureRequest(int eventId) {
		Event event = eventRepository.findByEventId(eventId);
		int numberOfTeams =  teamsNEventsRepository.countByTeamsNEventIdEventId(event);
		int totalRounds = findTotalRounds(numberOfTeams);
		event.setTotalRounds(totalRounds);
		eventRepository.save(event);
//		EventMimic teams = teamsNEventsRepository.findByTeamsNEventIdEventId(1);
		 List<QueryResponse> teams = teamsNEventsRepository.findByTeamsNEventIdEventId(1);
//		createMatchesForRound1(teams,totalRounds);
//		System.out.println(teams.getTeamId());
		System.out.println(teams.toString());
		for (QueryResponse team : teams) {
		    System.out.println("Team ID: "+ team.getTeamId()+" Email:"+ team.getEmailId() + " player2:"+team.getPlayer2());
		    // Print other relevant information using additional getters if needed
		    // System.out.println("Player 1: " + team.getPlayer1());
		    // System.out.println("Player 2: " + team.getPlayer2());
		}
		
	}
	
	
}
