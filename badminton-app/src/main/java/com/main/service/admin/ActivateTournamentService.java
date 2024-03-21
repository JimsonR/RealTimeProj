package com.main.service.admin;

import org.springframework.stereotype.Service;

import com.main.model.admin.AdminTournamentActivationModel;
import com.main.repository.admin.AdminTournamentRepository;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Data
public class ActivateTournamentService {
	private final AdminTournamentRepository adminTournamentRepository;
	public String handleActivateTournamentRequest(AdminTournamentActivationModel adminTournamentActivationModel) {
		if(adminTournamentActivationModel.getFeatureId()==1) {
			System.out.println("called");
			 adminTournamentRepository.changeBooleanValue(adminTournamentActivationModel.getValue(),adminTournamentActivationModel.getTournamentId());
			 return "done";
		}
		else
		return "not done: "+adminTournamentActivationModel.getFeatureId();
	}
}
