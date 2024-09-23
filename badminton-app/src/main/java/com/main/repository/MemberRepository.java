package com.main.repository;

import com.main.entity.Members;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Members,Integer > {

Members findByEmail(String email);

//@Query(nativeQuery = true, value = "select ")


}
