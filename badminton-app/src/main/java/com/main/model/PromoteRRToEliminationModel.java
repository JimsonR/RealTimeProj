package com.main.model;

import com.main.entity.Teams;
import com.main.entity.TeamsNEvent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PromoteRRToEliminationModel {
	int qualifierPosition;
	int teamId;
	int eventId;
}
