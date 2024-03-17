package com.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.model.admin.AdminTournamentActivationModel;

@RestController
@RequestMapping("/admin")
public class AdminController {
	@GetMapping("/ab")
	String index() {
		System.out.println("hello");
		return "hello";
	} 
	
	@PostMapping("/activateTournament")
	int activateTournament(AdminTournamentActivationModel activationModel) {
		
		return 1;
	}
	
}
