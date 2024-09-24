package com.main.service;

import com.main.entity.Users;
import com.main.model.CreateMemberDTO;
import com.main.repository.MemberRepository;
import com.main.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganiserAccountService {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenValidationService tokenValidationService;

public int addMember(CreateMemberDTO createMemberDTO,HttpServletRequest request){
    Users loggedInUser = userRepository.findByEmailId(tokenValidationService.loadUserEmail(request.getHeader("Authorization").substring(7)));



    return 200;
}

public int removeMember(){




    return 200;
}

public int changePassword(){

    return 200;
}

public int changeNameAndOrganisation(){

    return 200;
}

public int changeMobileNumber(){

    return 200;
}

}
