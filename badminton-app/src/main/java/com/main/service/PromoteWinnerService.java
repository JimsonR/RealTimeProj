package com.main.service;

import org.springframework.stereotype.Service;

import com.main.entity.Event;
import com.main.entity.Matches;
import com.main.entity.Teams;
import com.main.entity.TeamsNEvent;
import com.main.entity.TeamsNEventId;
import com.main.model.PromoteWinnerModel;
import com.main.repository.EventRepository;
import com.main.repository.MatchesRepository;
import com.main.repository.TeamsNEventRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PromoteWinnerService {
	private final MatchesRepository matchesRepository;
	private final EventRepository eventRepository;
	private final TeamsNEventRepository teamsNEventRepository;
	public int handlepromoteWinnerRequest(PromoteWinnerModel promoteWinnerModel) {
		Matches match = matchesRepository.findByMatchId(promoteWinnerModel.getMatchId());
		
		Teams winner;
		if(promoteWinnerModel.getWinnerId() == 1) 
			winner = match.getTeam1Id();
		
		else
			winner = match.getTeam2Id();
		
		match.setWinnerId(winner);
		matchesRepository.save(match);
//		if(match.getRound() == 1) {
//			Event event = eventRepository.findByEventId(match.getEventId().getEventId());
//			if(event.getMatchType()==2) {
//				promoteRoundRobinToElimination(promoteWinnerModel,match,event,winner);
//			}
//			return 123;
//		}
		int nextMatchNumber = getnextMatchNumber(match.getMatchNumber());
		System.out.println(nextMatchNumber);
		boolean matchExists = matchesRepository.existsByMatchNumberAndRoundAndEventId(nextMatchNumber,match.getRound()+1,match.getEventId());
		if(matchExists) {
			Matches newMatch = matchesRepository.findByMatchNumberAndRoundAndEventId(nextMatchNumber,match.getRound()+1,match.getEventId());
			newMatch.setTeam2Id(winner);
			matchesRepository.save(newMatch);
		}
		else {
			Matches newMatch = Matches.builder()
					.team1Id(winner)
					.round(match.getRound() + 1)
					.matchNumber(nextMatchNumber)
					.eventId(match.getEventId())
					.build();
			matchesRepository.save(newMatch);
		}
		
		return 200;
		
		
	}
	private int getnextMatchNumber(int matchNumber) {
		System.out.println(matchNumber);
		double val = (double)matchNumber/2;
		return (int)(Math.ceil(val));
	}
	private void promoteRoundRobinToElimination(PromoteWinnerModel promoteWinnerModel, Matches match, Event event,Teams winner) {
		TeamsNEventId teamsNEventIdd  = TeamsNEventId.builder().team_id(winner).eventId(event).build();
		TeamsNEvent teamsNEvent = teamsNEventRepository.findByTeamsNEventId(teamsNEventIdd);
		int nextMatchNumber,poolId = teamsNEvent.getPoolId();
		int thisMatchNumber = teamsNEvent.getQualify();
		if(poolId % 2 == 1) {
			nextMatchNumber = (int)poolId/2 + thisMatchNumber;
		}
		else {
			nextMatchNumber = (int)poolId/2-1+thisMatchNumber;
		} 
		System.out.println("nextMatch = "+nextMatchNumber+" "+"poolId="+poolId);
		if(matchesRepository.existsByMatchNumberAndRoundAndEventId(nextMatchNumber, 2,match.getEventId())) {
			Matches nextMatch  = matchesRepository.findByMatchNumberAndRoundAndEventId(nextMatchNumber, 2,match.getEventId());
			if(poolId % 2 == 1)
				nextMatch.setTeam1Id(winner);
			else
				nextMatch.setTeam2Id(winner);
			matchesRepository.save(nextMatch);
		}
		else {
			System.out.println("nextMatch = "+nextMatchNumber+" "+"poolId="+poolId);
			Matches nextMatch = Matches.builder()
					.round(2)
					.matchNumber(nextMatchNumber)
					.eventId(match.getEventId())
					.build();
			if(poolId % 2 == 1)
				nextMatch.setTeam1Id(winner);
			else
				nextMatch.setTeam2Id(winner);
			matchesRepository.save(nextMatch);
		}
		
	}
}
