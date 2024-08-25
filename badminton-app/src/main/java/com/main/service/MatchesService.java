package com.main.service;

import com.main.model.MatchesProjection;
import com.main.repository.EventRepository;
import com.main.repository.MatchesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MatchesService {
    @Autowired
    private MatchesRepository matchesRepository;
    private final EventRepository eventRepository;

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
}
