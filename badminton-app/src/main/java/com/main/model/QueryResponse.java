package com.main.model; 

import java.time.LocalDate;

public interface QueryResponse {
	String getTournamentName();
	byte[] getPoster();
	String getTournamentId();
	LocalDate getStartDate();
	LocalDate getEndDate();
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
