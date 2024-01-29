package com.main.model;

import com.main.entity.Matches;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PromoteWinnerModel {
	int matchId;
	int winnerId;
}
