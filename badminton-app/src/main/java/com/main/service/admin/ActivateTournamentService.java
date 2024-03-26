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
	public int handleActivateTournamentRequest(AdminTournamentActivationModel adminTournamentActivationModel) {
		if(adminTournamentActivationModel.getFeatureId()==1) {
			System.out.println("val="+adminTournamentActivationModel.getValue());
			 adminTournamentRepository.changeIsActive(adminTournamentActivationModel.getValue(),adminTournamentActivationModel.getTournamentId());
			 return 1;
		}
		else if(adminTournamentActivationModel.getFeatureId()==2) {
			System.out.println("val="+adminTournamentActivationModel.getValue());
			 adminTournamentRepository.changeIsPublic(adminTournamentActivationModel.getValue(),adminTournamentActivationModel.getTournamentId());
			 return 1;
		}
		else if(adminTournamentActivationModel.getFeatureId()==3) {
			System.out.println("val="+adminTournamentActivationModel.getValue());
			 adminTournamentRepository.changeIsLive(adminTournamentActivationModel.getValue(),adminTournamentActivationModel.getTournamentId());
			 return 1;
		}
		else if(adminTournamentActivationModel.getFeatureId()==4) {
			System.out.println("val="+adminTournamentActivationModel.getValue());
			 adminTournamentRepository.changeIsPro(adminTournamentActivationModel.getValue(),adminTournamentActivationModel.getTournamentId());
			 return 1;
		}
		else
		return 2;
	}
}
