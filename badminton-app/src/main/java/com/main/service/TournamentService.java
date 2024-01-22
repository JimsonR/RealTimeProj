package com.main.service;


import java.io.IOException;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.main.entity.Organization;
import com.main.entity.Tournament;
import com.main.model.CreateTournamentModel;
import com.main.repository.OrganizationRepository;
import com.main.repository.TournamentRepository;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class TournamentService {

	private final OrganizationRepository organizationRepository;
	private final TournamentRepository  tournamentRepository;
	private final TokenValidationService tokenValidationService;

	public int handleCreateOrginizationRequest(CreateTournamentModel createTournamentModel,
			HttpServletRequest request) throws IOException {
		
		Organization loggedInOrganization = organizationRepository.getLoggedInOrganization(tokenValidationService.loadUserEmail(request.getHeader("Authorization").substring(7)));
		Tournament newTournament =  Tournament.builder()
				.organizationId(loggedInOrganization)
				.tournamentName(createTournamentModel.getTournamentName())
				.startDate(createTournamentModel.getStartDate())
				.endDate(createTournamentModel.getEndDate())
				.bookingsCloseTime(createTournamentModel.getBookingsCloseTime())
				.location(createTournamentModel.getLocation())
				.address(createTournamentModel.getAddress())
				.description(createTournamentModel.getDescription())
				.sponsors(createTournamentModel.getSponsors())
				.poster(createTournamentModel.getPoster().getBytes())
				.sponsorPoster(createTournamentModel.getSponsorPoster().getBytes())
				.build();
		tournamentRepository.save(newTournament);
//		System.out.println(Base64.decodeBase64(createTournamentModel.getPoster()));
		return 100;
	}
	
}
