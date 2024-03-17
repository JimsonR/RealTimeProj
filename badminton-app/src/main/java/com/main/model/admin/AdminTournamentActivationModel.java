package com.main.model.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminTournamentActivationModel {
	int feature_id;
	int tournament_id;
	boolean value;
}
