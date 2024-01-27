package com.main.model;

import com.main.entity.Teams;
import com.main.entity.TeamsNEventId;

public interface QueryResponse {
	int getTeamId();
//	int getEventId();
//	EventMimic getTeamsId();
//	TeamsNEventId getTeamsNEventId();
//	int getTeamId();
	 String getPlayer1();
	 String getPlayer2();
	 String getEmailId();
	 String getPhoneNumber();
}
