package com.main.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.main.model.*;
import com.main.service.*;
import jakarta.transaction.Transactional;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.main.entity.Event;
import com.main.entity.Tournament;
import com.main.repository.TournamentRepository;
//import com.main.service.GenerateFixtureService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class RequestController {
	private final ServiceImpl serviceImpl;
	private final OrganizationService organizationService;
	private final TournamentService tournamentService;
	private final EventService eventService;
	private final GenerateFixtureService generateFixtureService;
	private final PromoteWinnerService promoteWinnerService;
	private final GenerateTeamsService	generateTeamsService;
	private final PromoteRRToEliminationService promoteRRToEliminationService;
	private final ShowTournamentsService showTournamentService;
	private final GetEventsForTournamentService getEventsForTournamentService;
	TournamentRepository tournamentRepository;
	private final EventsService eventsService;
	private final MatchesService matchesService;

	@GetMapping("/ab")
	String index() {
		System.out.println("hello");	
		return "hello";
	} 

	@PostMapping("/signup")
	int signUpRequest(@RequestBody SignUpModel signUpModel, HttpServletRequest request) {
		return serviceImpl.handleSignUpRequest(signUpModel, request);
//		100: success | 101: account exists | other:something is wrong
//		if(responseCode == 100) {
//			return ;
//		}
//		else if(responseCode == 101) {
//			return "account already exists";
//		}
//		else {
//			return "oops! something's wrong on our end try again later";
//		}
	}

	@GetMapping("/verificationLink") 
	String emailVerificationRequest(@RequestParam("token") String token) {
		int responseCode = serviceImpl.handleEmailVerificationRequest(token);
		if (responseCode == 100) {
			return "verified successfully";
		} else if (responseCode == 101) {
			return "invalid";
		} else {
			return "oops! something's wrong on our end try again later";
		}

	}

	@PostMapping("/login")
	public LoginResponseModel loginRequest(@RequestBody LoginModel loginModel) {
		return serviceImpl.handleLoginRequest(loginModel);
	}

	@PostMapping("/forgotPassword")
	public int forgotPassword(@RequestBody ForgotPasswordModel forgotPasswordModel, HttpServletRequest request) {
		return serviceImpl.handleForgotPasswordRequest(forgotPasswordModel, request);
//		if(responseCode == 100)
//			return "check your email";
//		else if(responseCode == 101) {
//			return "account doesn't exists";
//		}
//		else {
//			return "oops! something's wrong on our end try again later";
//		}
	}

//	@GetMapping("/passwordResetLink")
//	String passwordResetRequest(@RequestParam("token") String passwordResetToken) {
//		int responseCode = serviceImpl.handlePassowordResetRequest(passwordResetToken);
//		if(responseCode == 100){
//			return "verified, set new password";
//		}
//		else if(responseCode == 101) {
//			return "account doesn't exists";
//		}
//		else {
//			return "oops! something's wrong on our end try again later";
//		}
//	}

	@PostMapping("/resetPassword")
	int resetPasswordRequest(@RequestBody PasswordResetModel passwordResetModel) {
		return serviceImpl.handlePasswordResetRequest(passwordResetModel);
//		if(responseCode == 100){
//			return "new password is set";
//		}
//		else if(responseCode == 101) {
//			return "Invalid otp";
//		}
//		else {
//			return "oops! something's wrong on our end try again later";
//		}
	}

	@PostMapping("/createOrganization")
	int createOrginizationRequest(@RequestBody CreateOrganizationModel createOrganizationModel,
			HttpServletRequest request) {
		return organizationService.handleCreateOrginizationRequest(createOrganizationModel, request);
	}

	@GetMapping("/showTournaments")
	public List<Tournament>showTournamentsRequest(){
		List<Tournament>ret = showTournamentService.handleShowTournamentsRequest();
//		System.out.println(ret.get(1));
		return ret;
	}
	@GetMapping("/showTournament")
	public List<QueryResponse> showTournamentRequest(){
		return showTournamentService.handleShowTournamentRequest();
	}

	@GetMapping("/getTournament")
	public GetTournamentProjection getTournamentRequest(@RequestParam("tournamentId") int tournamentId){
		System.out.println("method called");
		return showTournamentService.handleGetTournamentRequest(tournamentId);
	}

//	@PostMapping(path = "/createTournament")
//	int createTournamentRequest(@ModelAttribute CreateTournamentModel createTournamentModel, HttpServletRequest request)
//			throws IOException {
//		System.out.println("tournament Name: "+createTournamentModel.getTournamentName());
//		return tournamentService.handleCreateTournamentRequest(createTournamentModel, request);
//	}

	@PostMapping(path = "/editTournament")
	int editTournament(@RequestBody EditTournamentDTO editTournamentDTO){
		return tournamentService.handleEditTournamentRequest(editTournamentDTO);
	}
	@GetMapping("/getEventsForTournament")
	public List<GetEventsForTournamentProjection> getEventsForTournament(@RequestParam("tournamentId")int tournamentId){
		return getEventsForTournamentService.getEvents(tournamentId);
	}
	@GetMapping("/getEventsForEditTournament")
	public List<GetEventsForEditTournamentProjection> getEventsForEditTournament(@RequestParam("tournamentId")int tournamentId) {
		return eventsService.handleGetEvents(tournamentId);
	}


	@GetMapping("/showImage") // this is a temporary method 
	ResponseEntity<byte[]> showImage() {
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG)
				.body(tournamentRepository.findImageByTournamentId(852));
//		return tournamentRepository.findImageByTournamentId(402)organizationService;
	}

	@PostMapping("/createTournamentAndEvents")
	@Transactional
	int createTournamentAndEvents(@ModelAttribute CreateTournamentAndEventsModel createTournamentAndEventsModel,HttpServletRequest request) throws IOException {
		try{
			System.out.println("reached createTournamentandEvents");
			int tournamentId = tournamentService.handleCreateTournamentRequest(createTournamentAndEventsModel, request);
			System.out.println("tournament created successfully: "+ createTournamentAndEventsModel.getEventsModel().toString());
			ObjectMapper objectMapper = new ObjectMapper();
			List<CreateEventModel> eventModel =objectMapper.readValue(createTournamentAndEventsModel.getEventsModel().getBytes(), new TypeReference<List<CreateEventModel>>() {});

			int eve = eventService.handleCreateEventRequest(eventModel,tournamentId);
			return 100;
		}
		catch (Exception e){
			System.out.println(e.getMessage());
			return -1;
		}
	}
	
	@PostMapping("/createEvent")
	int createEventRequest(@RequestBody List<CreateEventModel> createEventModel,HttpServletRequest request) {
		return eventService.handleCreateEventRequest(createEventModel,Integer.valueOf(request.getHeader("tournamentId")));
	}

	@PostMapping("/editEvents")
	int EditEventsRequest(@RequestBody List<CreateEventModel> createEventModel,HttpServletRequest request) {
		return eventService.handleCreateEventRequest(createEventModel,Integer.valueOf(request.getHeader("tournamentId")));
	}
//	
	@GetMapping("/generateTeams")
	int generateTeams() {
		return generateTeamsService.handleGenerateTeamsRequest(152);
		
	}
	
	@PostMapping("/generateFixtures")
	int generateFixtureRequest(HttpServletRequest request) {
		generateFixtureService.handleGenerateFixtureRequest(Integer.valueOf(request.getHeader("eventId")));
		return 2;
	}
	
	@PostMapping("/promoteWinner")
	int promoteWinner(@RequestBody PromoteWinnerModel promoteWinnerModel ) {
		return promoteWinnerService.handlepromoteWinnerRequest(promoteWinnerModel);
	}

	@PostMapping("/promoteRRToElimination")
	int promoteRRToElimination(@RequestBody PromoteRRToEliminationModel promoteRRToEliminationModel) {
		promoteRRToEliminationService.handlePromoteRRToEliminationService(promoteRRToEliminationModel);
		return 200;
	}

	@GetMapping("/getMatches")
	int getMatches(@RequestParam("eventId")int eventId,@RequestParam("tournamentId")int tournamentId){
		return matchesService.handleGetMatchesRequest(eventId,tournamentId);

	}

	
	
}
