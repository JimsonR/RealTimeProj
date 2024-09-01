package com.main.service;

import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import com.main.entity.Byes;
import com.main.entity.ByesId;
import com.main.entity.Matches;
import com.main.entity.Teams;
import com.main.model.MatchesProjection;
import com.main.model.ReplaceTeamsFromByesModel;
import com.main.model.ReplaceTeamsFromMatchesModel;
import com.main.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MatchesService {
    @Autowired
    private MatchesRepository matchesRepository;
    private final EventRepository eventRepository;
    private final ByesRepository byesRepository;
    private final TeamsRepository teamsRepository;


    @Transactional
    public List<List<MatchesProjection>> handleGetMatchesRequest(int eventId){
        int totalRounds = eventRepository.getTotalRoundsByEventId(eventId);
        List<List<MatchesProjection>>listOfMatches = new ArrayList<>();
//        int totalRounds = 3;
        for(int round = 1;round <= totalRounds;round++){
            List<MatchesProjection>ithRoundMatchesList;
            ithRoundMatchesList = matchesRepository.getMatches(eventId,round);
            listOfMatches.add(ithRoundMatchesList);
        }
         return listOfMatches;
    }

    public List<MatchesProjection> handleReplaceTeamsFromMatches(ReplaceTeamsFromMatchesModel replaceTeamsFromMatchesModel) {
       Matches matches1 =  matchesRepository.findByMatchId(replaceTeamsFromMatchesModel.getMatch1Id());
       Matches matches2 =  matchesRepository.findByMatchId(replaceTeamsFromMatchesModel.getMatch2Id());

        if(replaceTeamsFromMatchesModel.getMatch1Position() == 1 && replaceTeamsFromMatchesModel.getMatch2Position()==1){
            Teams match1TeamId = matches1.getTeam1Id();
            matches1.setTeam1Id(matches2.getTeam1Id());
            matches2.setTeam1Id(match1TeamId);
       }
       else if(replaceTeamsFromMatchesModel.getMatch1Position() == 1 && replaceTeamsFromMatchesModel.getMatch2Position()==2){
            Teams match1TeamId = matches1.getTeam1Id();
            matches1.setTeam1Id(matches2.getTeam2Id());
            matches2.setTeam2Id(match1TeamId);
        }
       else if(replaceTeamsFromMatchesModel.getMatch1Position() == 2 && replaceTeamsFromMatchesModel.getMatch2Position()==1){
            Teams match1TeamId = matches1.getTeam2Id();
            matches1.setTeam2Id(matches2.getTeam1Id());
            matches2.setTeam1Id(match1TeamId);
        }
       else{
            Teams match1TeamId = matches1.getTeam2Id();
            matches1.setTeam2Id(matches2.getTeam2Id());
            matches2.setTeam2Id(match1TeamId);
        }

        matchesRepository.save(matches1);
        matchesRepository.save(matches2);
        return matchesRepository.getMatches(matches1.getEventId().getEventId(),matches1.getRound());


    }

    @Transactional
    public List<MatchesProjection> handleReplaceTeamsFromByes(ReplaceTeamsFromByesModel replaceTeamsFromByesModel) {

        Matches match = matchesRepository.findByMatchId(replaceTeamsFromByesModel.getMatchId());
        Byes bye = byesRepository.getByEventIdAndTeamId(match.getEventId().getEventId(),replaceTeamsFromByesModel.getTeamId());
        int teamId;
        if(replaceTeamsFromByesModel.getMatch1PositionId() == 1){
            teamId = match.getTeam1Id().getTeamId();
            match.setTeam1Id(bye.getByesId().getTeamId());

        }
        else{
            teamId = match.getTeam2Id().getTeamId();
            match.setTeam2Id(bye.getByesId().getTeamId());
        }
        byesRepository.setTeamValue(teamId, replaceTeamsFromByesModel.getTeamId(),match.getEventId().getEventId());
//        byesRepository.save(bye);
        matchesRepository.save(match);
        return matchesRepository.getMatches(match.getEventId().getEventId(),match.getRound());
    }
}
