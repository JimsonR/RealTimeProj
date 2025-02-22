package com.main.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.deser.DataFormatReaders.Match;
import com.main.entity.Byes;
import com.main.entity.ByesId;
import com.main.entity.Event;
import com.main.entity.Matches;
import com.main.entity.Teams;
import com.main.entity.TeamsNEvent;
import com.main.entity.TeamsNEventId;
import com.main.model.EventMimic;
import com.main.model.QueryResponse;
import com.main.repository.ByesRepository;
import com.main.repository.EventRepository;
import com.main.repository.MatchesRepository;
import com.main.repository.TeamsNEventRepository;
import com.main.repository.TeamsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GenerateFixtureService {
	

	private final TeamsNEventRepository teamsNEventRepository;
	private final EventRepository eventRepository;
	private final TeamsRepository teamsRepository;
	private final MatchesRepository matchesRepository;
	private final ByesRepository byesRepository;
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
	private void createMatchesForRound1(List<Teams> teams, int totalRounds, Event event) {
		
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
		int count = 1;
		event.setTotalMatches(matches);;
		event.setTotalRounds(totalRounds);
		
		eventRepository.save(event);
		System.out.println(matches+" :"+totalRounds);
		for(int i = 0; i < playersForRound1;i += 2) {
//			System.out.println(teams.get(i) + " vs "+teams.get(i+1));
			Matches newMatch = Matches.builder()
					.team1Id(teams.get(i))
					.team2Id(teams.get(i+1))
					.eventId(event)
					.round(1)
					.matchNumber(count++)
					.build();
			matchesRepository.save(newMatch);
			
		}
		
		for(int i = playersForRound1;i < teams.size();i++) {
			ByesId newByesId = ByesId.builder().eventId(event).teamId(teams.get(i)).build();
			Byes newBye = Byes.builder().byesId(newByesId).build();
			byesRepository.save(newBye);
		}
	}
	
	

	
	public void handleGenerateFixtureRequest(int eventId) { // starts here
		Event event = eventRepository.findByEventId(eventId);
		List<Teams> teams = teamsRepository.findByTeamsNEventIdEventId(eventId);
		int numberOfTeams = teams.size();
		if(event.getMatchType() == 2){
			int qualifiersPerPool = event.getQualifiersPerPool();
			int totalNumberOfPools = event.getTotalPools();
			int totalRounds = 1 + ((int) (Math.log (qualifiersPerPool * totalNumberOfPools)/Math.log(2))) ;
			System.out.println("{totalRounds = "+totalRounds+ "Number Of Pools="+ totalNumberOfPools );
			event.setTotalRounds(totalRounds);
			eventRepository.save(event);
			
			List<TeamsNEvent>teamsNEvent = teamsNEventRepository.findteamsNEventSortByPoolId(eventId);
			generateRoundRobinEliminationFixture(teamsNEvent);
//			generateRoundRobinEliminationFixture(event,totalNumberOfPools,teams,numberOfTeams);
		}
		else if(event.getMatchType() == 1) {
			int totalRounds = findTotalRounds(numberOfTeams);
//		event.setTotalRounds(totalRounds);
//		eventRepository.save(event);
//		EventMimic teams = teamsNEventsRepository.findByTeamsNEventIdEventId(1);
//		List<Integer> teams = teamsNEventsRepository.findByTeamsNEventIdEventId(1);
//			List<Teams> teams = teamsRepository.findByTeamsNEventIdEventId(eventId);
			createMatchesForRound1(teams, totalRounds, event);


//		System.out.println(teams.getTeamId());
////		System.out.println(teams.toString());
//		for (Teams team : teams) {
//		    System.out.println("Team ID: "+ team.getTeamId()+" Email:"+ team.getEmailId() + " player2:"+team.getPlayer2());
//		    // Print other relevant information using additional getters if needed
//		    // System.out.println("Player 1: " + team.getPlayer1());
//		    // System.out.println("Player 2: " + team.getPlayer2());
//		}

		}
		
	}

	void generateRoundRobinEliminationFixture(List<TeamsNEvent>teamsNEvent){
//		int estTeamsPerPool = numberOfTeams/totalNumberOfPools;
////		System.out.println(estTeamsPerPool);
//		Map<Integer,List<Teams>> poolMap = new HashMap<>();
//		int teamsIterator=0;
//		for(int pool = 1;pool <= totalNumberOfPools;pool++) {
//			List<Teams>currTeams = new ArrayList<>();
//			int thisPoolTeams=0;
//			while(thisPoolTeams < estTeamsPerPool) {
//				currTeams.add(teams.get(teamsIterator));
//				teamsIterator++;
//				thisPoolTeams++;
//			}
//			poolMap.put(pool, currTeams);
//		}
//		
//		for(int j = 1; j <= totalNumberOfPools && teamsIterator < teams.size();teamsIterator++,j++) {
//			poolMap.get(j).add(teams.get(teamsIterator));		}
//		
//		int matchNumber = 1;
//		for(int i = 1; i <= totalNumberOfPools;i++) {
//			List<Teams>thisPool = poolMap.get(i);
//			for(int j = 0;j < thisPool.size();j++) {
//				for(int k = j+1;k < thisPool.size();k++) {
//					Matches match = Matches.builder()
//							.team1Id(thisPool.get(j))
//							.team2Id(thisPool.get(k))
//							.eventId(event)
//							.matchNumber(matchNumber++)
//							.round(1)
//							.build();
//							
//					matchesRepository.save(match);
//				}
//			}
//		}
		int maxPool = teamsNEvent.get(teamsNEvent.size()-1).getPoolId();
		int left=0,right=0;
		
			while(right < teamsNEvent.size()) {
				if( teamsNEvent.get(right).getPoolId() == teamsNEvent.get(left).getPoolId()) {
					
					right++;
				}
				else {
					generateMatchesForPool(left,right,teamsNEvent);
					left=right;
				}
			}
			generateMatchesForPool(left, right, teamsNEvent);
			
	
	}
	
	void generateMatchesForPool(int left, int right, List<TeamsNEvent> teamsNEvent){
		int matchNumber=1;
		for(int i = left;i < right;i++) {
			for(int j = i+1;j < right;j++) {
				Matches match = Matches.builder()
						.team1Id(teamsNEvent.get(i).getTeamsNEventId().getTeam_id())
						.team2Id(teamsNEvent.get(j).getTeamsNEventId().getTeam_id())
						.eventId(teamsNEvent.get(i).getTeamsNEventId().getEventId())
						.matchNumber(matchNumber++)
						.round(1)
						.build();
						
				matchesRepository.save(match);
			}
		}
		
	}
}
