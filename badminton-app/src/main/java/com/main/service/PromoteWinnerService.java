package com.main.service;

import org.springframework.stereotype.Service;

import com.main.entity.Matches;
import com.main.entity.Teams;
import com.main.model.PromoteWinnerModel;
import com.main.repository.MatchesRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PromoteWinnerService {
	private final MatchesRepository matchesRepository;
	public int handlepromoteWinnerRequest(PromoteWinnerModel promoteWinnerModel) {
		Matches match = matchesRepository.findByMatchId(promoteWinnerModel.getMatchId());
		Teams winner;
		if(promoteWinnerModel.getWinnerId() == 1) 
			winner = match.getTeam1Id();
		
		else
			winner = match.getTeam2Id();
		
		match.setWinnerId(winner);
		matchesRepository.save(match);
		
		int nextMatchNumber = getnextMatchNumber(match.getMatchNumber());
		System.out.println(nextMatchNumber);
		boolean matchExists = matchesRepository.existsByMatchNumberAndRound(nextMatchNumber,match.getRound()+1);
		if(matchExists) {
			Matches newMatch = matchesRepository.findByMatchNumberAndRound(nextMatchNumber,match.getRound()+1);
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
}
