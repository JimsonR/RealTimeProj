package com.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.model.admin.AdminTournamentActivationModel;
import com.main.service.admin.ActivateTournamentService;

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
	String activateTournament(AdminTournamentActivationModel activationModel) {
		return activateTournamentService.handleActivateTournamentRequest(activationModel);
		
	}
	
}
