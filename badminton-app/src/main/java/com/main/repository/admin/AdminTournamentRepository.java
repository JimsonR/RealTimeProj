package com.main.repository.admin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.main.entity.Tournament;
import com.main.model.admin.AdminTournamentProjection;

import jakarta.transaction.Transactional;

@Repository
public interface AdminTournamentRepository extends JpaRepository<Tournament, Integer>{
	@Transactional
    @Modifying
	@Query(nativeQuery = true,value="update tournament set is_active = ?1 where tournament_id = ?2")
	void changeBooleanValue(boolean newValue,int tournament_id);
	
	@Query(nativeQuery = true,value=
			"select t.tournament_id AS TournamentId, "
			+ "t.organization_id_organization_id AS OrganisationId,"
			+ "t.tournament_name AS TournamentName,"
			+ "t.start_date AS StartDate,"
			+ "t.end_date AS EndDate,"
			+ " t.address AS Address,"
			+ " t.is_active as IsActive,"
			+ "t.is_pro as IsPro,"
			+ "t.is_live as IsLive,"
			+ "t.is_public as IsPublic from tournament t")
	List<AdminTournamentProjection>getAdminTournament();
}
