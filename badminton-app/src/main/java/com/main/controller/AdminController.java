package com.main.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.model.admin.AdminTournamentActivationModel;
import com.main.repository.admin.AdminTournamentRepository;
import com.main.service.admin.ActivateTournamentService;

import com.main.repository.admin.*;
import com.main.model.admin.*;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@RestController
@RequestMapping("/admin")
public class AdminController {
	
	private final ActivateTournamentService activateTournamentService;
	
	@GetMapping("/ab")
	String index() {
		System.out.println("hello");
		return "hello";
	} 
	
	@PostMapping("/activateTournament")
	String activateTournament(@RequestBody AdminTournamentActivationModel activationModel) {
		return activateTournamentService.handleActivateTournamentRequest(activationModel);
		
	}
	private final AdminTournamentRepository adminTournamentRepository;
	@GetMapping("/get")
	List<AdminTournamentProjection>getAdminTournamentDetails(){
		return adminTournamentRepository.getAdminTournament();
	}
	
}
