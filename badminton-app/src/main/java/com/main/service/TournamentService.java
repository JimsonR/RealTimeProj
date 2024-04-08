package com.main.service;


import java.io.IOException;

import com.main.model.EditTournamentDTO;
import com.main.model.EditTournamentModel;
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

    public int handleEditTournamentRequest(EditTournamentDTO editTournamentDTO) {

		EditTournamentModel previous = editTournamentDTO.getEditTournamentModel();
    	CreateTournamentModel current = editTournamentDTO.getCreateTournamentModel();
		try {
			if (!previous.getTournamentName().equals(current.getTournamentName())) {
				tournamentRepository.updateTournamentName(current.getTournamentName(),previous.getTournamentId());
			}
			if (!previous.getLocation().equals(current.getLocation())) {
				tournamentRepository.updateLocation(current.getLocation(),previous.getTournamentId());
			}
			if (!previous.getAddress().equals(current.getAddress())) {
				tournamentRepository.updateAddress(current.getAddress(),previous.getTournamentId());
			}
			if (!previous.getDescription().equals(current.getDescription())) {
				tournamentRepository.updateDescription(current.getDescription(),previous.getTournamentId());
			}
			if (!previous.getSponsors().equals(current.getSponsors())) {
				tournamentRepository.updateSponsors(current.getSponsors(),previous.getTournamentId());
			}
			if (!previous.getStartDate().equals(current.getStartDate())) {
				tournamentRepository.updateStartDate(current.getStartDate(),previous.getTournamentId());
			}
			if (!previous.getEndDate().equals(current.getEndDate())) {
				tournamentRepository.updateEndDate(current.getEndDate(),previous.getTournamentId());
			}
			if (!previous.getBookingsCloseTime().equals(current.getBookingsCloseTime())) {
				tournamentRepository.updateBookingsCloseTime(current.getBookingsCloseTime(),previous.getTournamentId());
			}
			tournamentRepository.updateFiles(current.getPoster(), current.getSponsorPoster(),previous.getTournamentId());
			return 1;
		}
		catch(Exception e){
			System.out.println("exception "+e);
			return 0;
		}
    }
}
