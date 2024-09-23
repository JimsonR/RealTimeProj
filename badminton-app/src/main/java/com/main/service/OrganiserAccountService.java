package com.main.service;

import com.main.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class OrganiserAccountService {

    @Autowired
    private MemberRepository memberRepository;

public int addMember(){



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
