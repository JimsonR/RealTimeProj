package com.main.service;

import org.springframework.stereotype.Service;

import com.main.entity.Event;
import com.main.entity.Matches;
import com.main.entity.Teams;
import com.main.entity.TeamsNEvent;
import com.main.entity.TeamsNEventId;
import com.main.model.PromoteRRToEliminationModel;
import com.main.repository.EventRepository;
import com.main.repository.MatchesRepository;
import com.main.repository.TeamsNEventRepository;
import com.main.repository.TeamsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PromoteRRToEliminationService {
	private final TeamsNEventRepository teamsNEventRepository;
	private final MatchesRepository matchesRepository;
	private final EventRepository eventRepository;
	private final TeamsRepository teamsRepository;

	public void handlePromoteRRToEliminationService(PromoteRRToEliminationModel promoteRRToEliminationModel) {
		Event event = eventRepository.findByEventId(promoteRRToEliminationModel.getEventId());
		Teams team = teamsRepository.findByTeamId(promoteRRToEliminationModel.getTeamId());
		TeamsNEventId teamsNEventIdd  = TeamsNEventId.builder().team_id(team).eventId(event).build();
		TeamsNEvent teamsNEvent = teamsNEventRepository.findByTeamsNEventId(teamsNEventIdd);
			
		int nextMatchNumber;
//		int poolId = teamsNEventRepository.getPoolIdByEventIdAndTeamId(promoteRRToEliminationModel.getEventId(),promoteRRToEliminationModel.getTeamId());
		int poolId = teamsNEvent.getPoolId();
		int thisMatchNumber = promoteRRToEliminationModel.getQualifierPosition();
		if(poolId % 2 == 1) {
			nextMatchNumber = (int)poolId/2 + thisMatchNumber;
		}
		else {
			nextMatchNumber = (int)poolId/2-1+event.getQualifiersPerPool()-thisMatchNumber+1;
		} 
		System.out.println("nextMatch = "+nextMatchNumber+" "+"poolId="+poolId);
		
	//	Event event = eventRepository.findByEventId(promoteRRToEliminationModel.getEventId());
	//	Teams team = teamsRepository.findByTeamId(promoteRRToEliminationModel.getTeamId());
		if(matchesRepository.existsByMatchNumberAndRoundAndEventId(nextMatchNumber, 2,event)){
			Matches nextMatch  = matchesRepository.findByMatchNumberAndRoundAndEventId(nextMatchNumber, 2,event);
			if(poolId % 2 == 1)
				nextMatch.setTeam1Id(team);
			else
				nextMatch.setTeam2Id(team);
			matchesRepository.save(nextMatch);
		}
		else {
			System.out.println("nextMatch = "+nextMatchNumber+" "+"poolId="+poolId);
			Matches nextMatch = Matches.builder()
					.round(2)
					.matchNumber(nextMatchNumber)
					.eventId(event)
					.build();
			if(poolId % 2 == 1)
				nextMatch.setTeam1Id(team);
			else
				nextMatch.setTeam2Id(team);
			matchesRepository.save(nextMatch);
		}
		
	}

	
}
