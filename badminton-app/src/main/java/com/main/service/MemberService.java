package com.main.service;

import com.main.entity.Members;
import com.main.model.CreateMemberDTO;
import com.main.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class MemberService {

    private MemberRepository memberRepository;

    public int createMember( CreateMemberDTO createMemberDTO ){

        Members createMember = Members.builder()
                .email(createMemberDTO.getEmail())
                .name(createMemberDTO.getName())
                .organization(createMemberDTO.getOrganisationId())
                .password(createMemberDTO.getPassword())
                .phoneNumber(createMemberDTO.getPhoneNumber())
                .build();

        memberRepository.save(createMember);
        return 201;
    }

    public int removeMember(CreateMemberDTO createMemberDTO){

        Members members = memberRepository.findByEmail(createMemberDTO.getEmail());
       if(members != null){
            memberRepository.deleteById(createMemberDTO.getId());
        }
       else{

           return 404 ;

       }
return 201;
    }

    public int updateMember(CreateMemberDTO createMemberDTO){

        Optional<Members> existingMember = memberRepository.findById(createMemberDTO.getId());

        if(existingMember.isPresent()){
            Members members = existingMember.get();
            members.setOrganization(createMemberDTO.getOrganisationId());
            members.setPhoneNumber(createMemberDTO.getPhoneNumber());
            memberRepository.save(members);
        }else{

            return 404;

        }


        return 200;
    }

    public int  findMember(String email){


        return 200;
    }





}
