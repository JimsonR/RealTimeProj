package com.main.controller;

import java.util.List;

import com.main.service.admin.AdminRevenueService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.service.admin.AdminOrganizationService;
import com.main.service.admin.AdminTournamentService;

import com.main.repository.admin.*;
import com.main.model.admin.*;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@RestController
@RequestMapping("/admin")
public class AdminController {
	
	private final AdminOrganizationService adminOrganizationService;
	private final AdminTournamentService adminTournamentService;
	private final AdminRevenueService adminRevenueService;


	@GetMapping("/ab")
	String index() {
		System.out.println("hello");
		return "hello";
	} 
	
	@PostMapping("/activateTournament")
	int activateTournament(@RequestBody AdminTournamentActivationModel activationModel) {
		return adminTournamentService.handleActivateTournamentRequest(activationModel);
		
	}

	@GetMapping("/getTournaments")
	List<AdminTournamentProjection>getAdminTournamentDetails(){
		return adminTournamentService.handleGetAdminTournamentDetailsRequest();
	}
	
	
	@PostMapping("/activateOrganization")
	int activateOrganization(@RequestBody AdminOrganizationActivationModel activationModel) {
		return adminOrganizationService.handleActivateOrganizationRequest(activationModel);
		
	}

	@GetMapping("/getOrganizations")
	List<AdminOrganizationProjection>getAdminOrganizationDetails(){
		return adminOrganizationService.handleGetAdminOrganizationRequest();
	}
	@GetMapping("/getRevenue")
	List<AdminRevenueProjection>getAdminRevenueDetails(){
		return adminRevenueService.handleGetRevenueRequest();
	}
//	@GetMapping("/createTournament")

}
