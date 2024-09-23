package com.main.service;

import com.main.entity.Members;
import com.main.model.CreateMemberDTO;
import com.main.model.OrganizerAccountModel;
import com.main.model.SignUpModel;
import com.main.repository.MemberRepository;
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
	private final MemberService memberService;
	private final MemberRepository memberRepository;


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

	public int addMember(CreateMemberDTO createMemberDTO, HttpServletRequest request){
		Users loggedInUser = userRepository.findByEmailId(tokenValidationService.loadUserEmail(request.getHeader("Authorization").substring(7)));

		Organization organization = organizationRepository.getLoggedInOrganization(loggedInUser.getEmailId());

		createMemberDTO.setOrganisationId(organization);


		memberService.createMember(createMemberDTO);

		return 201;

	}

	public int removeMember(CreateMemberDTO createMemberDTO, HttpServletRequest request){
		Users loggedInUser = userRepository.findByEmailId(tokenValidationService.loadUserEmail(request.getHeader("Authorization").substring(7)));



		memberService.removeMember(createMemberDTO);


		return 200;
	}

	public int updateOrganizer(OrganizerAccountModel model , HttpServletRequest request){
		Users loggedInUser = userRepository.findByEmailId(tokenValidationService.loadUserEmail(request.getHeader("Authorization").substring(7)));

		loggedInUser.setEmailId(model.getEmailId());
		loggedInUser.setUserName(model.getUserName());

		Organization organization = organizationRepository.getLoggedInOrganization(loggedInUser.getEmailId());
		organization.setOrganizationName(model.getOrganizationName());

		organizationRepository.save(organization);


		return 200;
	}



}
