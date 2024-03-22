package com.main.model.admin; 

import java.util.Date;

import com.main.entity.Teams;
import com.main.entity.TeamsNEventId;

public interface AdminTournamentProjection {
	String getTournamentName();
	int getTournamentId();
	String getOrganisationName();
	int getOrganisationId();
	String getAddress();
	boolean getIsPublic();
	boolean getIsLive();
	boolean getIsActive();
	boolean getIsPro();
	Date getStartDate();
	Date getEndDate();
	
	
//	String getTournamentName();
//	byte[] getPoster();
//	String getTournamentId();
//	Date getStartDate();
//	Date getEndDate();
//	String getAddress();

}
