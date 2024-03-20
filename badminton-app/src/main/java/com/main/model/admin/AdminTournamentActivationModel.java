package com.main.model.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminTournamentActivationModel {
	private int featureId;
	private int tournamentId;
	
	public boolean getValue() {
		return true;
	}
	
}
