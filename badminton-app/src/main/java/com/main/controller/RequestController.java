package com.main.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.main.model.CreateEventModel;
import com.main.model.CreateOrganizationModel;
import com.main.model.CreateTournamentModel;
import com.main.model.ForgotPasswordModel;
import com.main.model.LoginModel;
import com.main.model.LoginResponseModel;
import com.main.model.PasswordResetModel;
import com.main.model.SignUpModel;
import com.main.repository.TournamentRepository;
import com.main.service.EventService;
import com.main.service.GenerateFixtureService;
import com.main.service.OrganizationService;
import com.main.service.ServiceImpl;
import com.main.service.TournamentService;

import jakarta.servlet.http.HttpServlet;
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

	@PostMapping(path = "/createTournament")
	int createTournamentRequest(@ModelAttribute CreateTournamentModel createTournamentModel, HttpServletRequest request)
			throws IOException {
		return tournamentService.handleCreateOrginizationRequest(createTournamentModel, request);
	}

	@Autowired
	TournamentRepository tournamentRepository;


	@GetMapping("/showImage") // this is a temporary method 
	ResponseEntity<byte[]> showImage() {
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG)
				.body(tournamentRepository.findImageByTournamentId(852));
//		return tournamentRepository.findImageByTournamentId(402)organizationService;
	}
	
	
	@PostMapping("/createEvent")
	int createEventRequest(@RequestBody List<CreateEventModel> createEventModel,HttpServletRequest request) {
		return eventService.handleCreateEventRequest(createEventModel,Integer.valueOf(request.getHeader("tournamentId")));
	}
	
	@GetMapping("/generateFixtures")
	int generateFixtureRequest(HttpServletRequest request) {
		generateFixtureService.handleGenerateFixtureRequest(Integer.valueOf(request.getHeader("eventId")));
		return 2;
	}

}
