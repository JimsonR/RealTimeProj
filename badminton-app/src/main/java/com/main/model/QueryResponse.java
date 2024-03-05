package com.main.model; 

import java.util.Date;

import com.main.entity.Teams;
import com.main.entity.TeamsNEventId;

public interface QueryResponse {
	String getTournamentName();
	byte[] getPoster();
	String getTournamentId();
	Date getStartDate();
	Date getEndDate();
	String getAddress();

//	int getTeamId();
//	int getEventId();
//	EventMimic getTeamsId();
//	TeamsNEventId getTeamsNEventId();
////	int getTeamId();
//	 String getPlayer1();
//	 String getPlayer2();
//	 String getEmailId();
//	 String getPhoneNumber();
}
