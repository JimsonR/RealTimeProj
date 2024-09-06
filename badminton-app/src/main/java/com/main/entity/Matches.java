package com.main.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Matches {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int matchId;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="eventId",referencedColumnName = "eventId")
	private Event eventId;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "team1Id", referencedColumnName = "teamId")
	private Teams team1Id;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "team2Id", referencedColumnName = "teamId")
	private Teams team2Id;
	
	private int matchNumber;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "winnerId", referencedColumnName = "teamId")
	private Teams winnerId;
	
	private int round;
	private int courtNumber;
	private boolean duece;
	private int totalSets;
	private int totalPoints;
	private String remarks;
//	private String poolId;


}
