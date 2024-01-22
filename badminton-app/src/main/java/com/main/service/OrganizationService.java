package com.main.service;

import org.springframework.stereotype.Service;

import com.main.entity.Organization;
import com.main.entity.Users;
import com.main.model.CreateOrganizationModel;
import com.main.repository.OrganizationRepository;
import com.main.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrganizationService {
	private final OrganizationRepository organizationRepository;
	private final TokenValidationService tokenValidationService;
	private final UserRepository userRepository;
	public int  handleCreateOrginizationRequest(CreateOrganizationModel createOrganizationModel,HttpServletRequest request) {
		Users loggedInUser = userRepository.findByEmailId(tokenValidationService.loadUserEmail(request.getHeader("Authorization").substring(7)));
		Organization newOrganization = Organization.builder()
				.emailId(createOrganizationModel.getEmailId())
				.organizationName(createOrganizationModel.getOrganizationName())
				.mobileNumber(createOrganizationModel.getMobileNumber())
				.userId(loggedInUser)
				.build();
		organizationRepository.save(newOrganization);
		return 100;
	}
}
