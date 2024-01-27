package com.main.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Event {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int eventId;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tournamentId", referencedColumnName = "tournamentId")
	Tournament tournamentId;
	private int category;
	private int matchType;
	private int entryFee;
	private int maxEntries;
//	private boolean allowBookings=true;
	
	//will fill during fixture generation
	private int totalRounds;
	private int totalMatches;
	private int totalPools;
	private int teamsPerPool;
	private int qualifiersPerPool;
	

}
