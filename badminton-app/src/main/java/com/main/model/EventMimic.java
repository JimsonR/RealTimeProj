package com.main.model;

import com.main.entity.TeamsNEvent;
import com.main.entity.TeamsNEventId;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventMimic {
	private int teamId;
	private String player1;
	private String player2;
	private String emailId;
	private String phoneNumber;
}
