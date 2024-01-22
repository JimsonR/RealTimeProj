package com.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.main.entity.Organization;
@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Integer>{

	@Query(nativeQuery = true,value="select * from organization o where o.user_id in(select u.user_id from users u where u.email_id = ?1)")
	Organization getLoggedInOrganization(String emailId);
	
}
