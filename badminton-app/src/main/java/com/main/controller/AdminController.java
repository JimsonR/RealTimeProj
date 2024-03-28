package com.main.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.repository.admin.AdminTournamentRepository;
import com.main.service.admin.ActivateOrganizationService;
import com.main.service.admin.ActivateTournamentService;

import com.main.repository.admin.*;
import com.main.model.admin.*;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@RestController
@RequestMapping("/admin")
public class AdminController {
	
	private final ActivateOrganizationService activateOrganizationService;
	private final ActivateTournamentService activateTournamentService;
	private final AdminRevenueRepository adminRevenueRepository;
	
	@GetMapping("/ab")
	String index() {
		System.out.println("hello");
		return "hello";
	} 
	
	@PostMapping("/activateTournament")
	int activateTournament(@RequestBody AdminTournamentActivationModel activationModel) {
		return activateTournamentService.handleActivateTournamentRequest(activationModel);
		
	}
	private final AdminTournamentRepository adminTournamentRepository;
	@GetMapping("/getTournaments")
	List<AdminTournamentProjection>getAdminTournamentDetails(){
		return adminTournamentRepository.getAdminTournament();
	}
	
	
	@PostMapping("/activateOrganization")
	int activateOrganization(@RequestBody AdminOrganizationActivationModel activationModel) {
		return activateOrganizationService.handleActivateOrganizationRequest(activationModel);
		
	}
	private final AdminOrganizationRepository adminOrganizationRepository;
	@GetMapping("/getOrganizations")
	List<AdminOrganizationProjection>getAdminOrganizationDetails(){
		return adminOrganizationRepository.getAdminOrganization();
	}
	@GetMapping("/getRevenue")
	List<AdminRevenueProjection>getAdminRevenueDetails(){
		return adminRevenueRepository.getRevenue();
	}
}
