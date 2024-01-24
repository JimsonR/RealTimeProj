package com.main.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
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
	
	@ManyToOne(optional=false,cascade = CascadeType.ALL)
//	@MapsId("teamId")
	 @JoinColumns({
	        @JoinColumn(name = "team1Id", referencedColumnName = "teamId"),
	        @JoinColumn(name = "event1Id", referencedColumnName = "eventId")
	    })
	private Teams team1Id;
	
	@ManyToOne(optional=false,cascade = CascadeType.ALL)
//	@MapsId("teamId")
	@JoinColumns({
        @JoinColumn(name = "team2Id", referencedColumnName = "teamId"),
        @JoinColumn(name = "event2Id", referencedColumnName = "eventId")
    })
	private Teams team2Id;
	
	private int matchNumber;
	
	@ManyToOne(optional=false,cascade = CascadeType.ALL)
//	@MapsId("teamId")
	@JoinColumns({
        @JoinColumn(name = "teamWId", referencedColumnName = "teamId"),
        @JoinColumn(name = "eventWId", referencedColumnName = "eventId")
    })
	private Teams winnerId;
	private int courtNumber;
	private boolean duece;
	private int totalSets;
	private int totalPoints;
	private String remarks;
}
