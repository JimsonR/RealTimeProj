package com.main.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.main.entity.Event;
import com.main.entity.TeamsNEvent;
import com.main.repository.EventRepository;
import com.main.repository.TeamsNEventRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GenerateTeamsService {
	
	private final TeamsNEventRepository teamsNEventRepository;
	private final EventRepository eventRepository;

	public int handleGenerateTeamsRequest(int eventId) {
		Event event = eventRepository.findByEventId(eventId);
		System.out.println(eventId+" :eventId");
		List<TeamsNEvent> teamsNEvent = teamsNEventRepository.findByTeamsNEventIdEventId(event);
		System.out.println("Entered pool generation");
		generatePools(event,event.getTotalPools(),teamsNEvent,teamsNEvent.size());
		return 0;
	}
	
	void generatePools(Event event,int totalNumberOfPools, List<TeamsNEvent> teams,int numberOfTeams){
		int estTeamsPerPool = numberOfTeams/totalNumberOfPools;
//		System.out.println(estTeamsPerPool);
		Map<Integer,List<TeamsNEvent>> poolMap = new HashMap<>();
		int teamsIterator=0;
		for(int pool = 1;pool <= totalNumberOfPools;pool++) {
			List<TeamsNEvent>currTeams = new ArrayList<>();
			int thisPoolTeams=0;
			while(thisPoolTeams < estTeamsPerPool) {
				currTeams.add(teams.get(teamsIterator));
				teamsIterator++;
				thisPoolTeams++;
			}
			poolMap.put(pool, currTeams);
		}
		
		for(int j = 1; j <= totalNumberOfPools && teamsIterator < teams.size();teamsIterator++,j++) {
			poolMap.get(j).add(teams.get(teamsIterator));		
		}
		
		for(int i = 1; i <= totalNumberOfPools;i++) {
			List<TeamsNEvent>thisPool = poolMap.get(i);
			for(int j = 0;j < thisPool.size();j++) {
					TeamsNEvent thisTeam = thisPool.get(j);
					thisTeam.setPoolId(i);
					teamsNEventRepository.save(thisTeam);
				}
			}
		}
}
