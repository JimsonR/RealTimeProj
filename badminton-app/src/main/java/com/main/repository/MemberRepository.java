package com.main.repository;

import com.main.entity.Members;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface MemberRepository extends JpaRepository<Members,Integer > {

UserDetails getByEmail();

}
