package com.main.service.admin;

import com.main.model.admin.AdminOrganizationProjection;
import org.springframework.stereotype.Service;

import com.main.model.admin.AdminOrganizationActivationModel;
import com.main.repository.admin.AdminOrganizationRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminOrganizationService {
	
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

	public List<AdminOrganizationProjection> handleGetAdminOrganizationRequest() {
		return adminOrganizationRepository.getAdminOrganization();
	}
}