package com.main.service.admin;

import org.springframework.stereotype.Service;

import com.main.model.admin.AdminTournamentActivationModel;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Data
public class ActivateTournamentService {
	public int handleActivateTournamentRequest(AdminTournamentActivationModel adminTournamentActivationModel) {
		if(adminTournamentActivationModel.getFeature_id()==1) {
			
		}
		return 0;
	}
}
