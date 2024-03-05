package com.main.service;

import com.main.repository.TournamentRepository;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.entity.Tournament;
import com.main.model.*;

@Service
@RequiredArgsConstructor
@Data
public class ShowTournamentsService {
	private final TournamentRepository tournamentRepository;
	public List<Tournament> handleShowTournamentsRequest(){
		List<Tournament>tournaments = tournamentRepository.findAll();		
		if(tournaments != null)
			return tournaments;
		else return null;
	}
	public List<QueryResponse> handleShowTournamentRequest(){
		 List<QueryResponse> tournaments = tournamentRepository.getEvery();		
		if(tournaments != null)
			return tournaments;
		else return null;
	}
}
