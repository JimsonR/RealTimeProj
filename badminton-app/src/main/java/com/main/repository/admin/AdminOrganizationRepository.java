package com.main.repository.admin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.main.entity.Organization;
import com.main.model.admin.AdminOrganizationProjection;
import com.main.model.admin.AdminTournamentProjection;

import jakarta.transaction.Transactional;

@Repository
public interface AdminOrganizationRepository extends JpaRepository<Organization,Integer>{
	
	@Transactional
    @Modifying
	@Query(nativeQuery = true,value="update organization set is_active = ?1 where organization_id = ?2")
	void changeIsActive(boolean newValue,int organizationId);
	
	@Query(nativeQuery = true,value=
			"select o.organization_id AS OrganizationId, "
			+ "o.organization_name AS OrganizationName, "
			+ "o.is_active AS IsActive, "
			+ "u.user_name AS UserName, "
			+ "u.user_id AS UserId "
			+ "from organization o join users u on o.user_id = u.user_id")
	List<AdminOrganizationProjection>getAdminOrganization();  
}
