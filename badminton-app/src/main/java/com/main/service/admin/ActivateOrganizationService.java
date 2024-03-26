package com.main.service.admin;

import org.springframework.stereotype.Service;

import com.main.model.admin.AdminOrganizationActivationModel;
import com.main.model.admin.AdminTournamentActivationModel;
import com.main.repository.admin.AdminOrganizationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ActivateOrganizationService {
	
	private final AdminOrganizationRepository adminOrganizationRepository;
	
	public int handleActivateOrganizationRequest(AdminOrganizationActivationModel adminOrganizationActivationModel) {
		if(adminOrganizationActivationModel.getFeatureId()==1) {
			System.out.println("val="+adminOrganizationActivationModel.getValue());
			 adminOrganizationRepository.changeIsActive(adminOrganizationActivationModel.getValue(),adminOrganizationActivationModel.getOrganizationId());
			 return 100;
		}
		else {
			return 0;
		}
	}
}