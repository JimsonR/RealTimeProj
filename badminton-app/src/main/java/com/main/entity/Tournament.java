package com.main.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tournament {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int tournamentId;
	@ManyToOne
	@JoinColumn(referencedColumnName = "organizationId")
	private Organization organizationId;
	private String tournamentName;
	private LocalDate startDate;
	private LocalDate endDate;
	private LocalDate bookingsCloseTime;
	private String location;
	private String address;
	private String description;
	private String sponsors;
	
	private boolean isPublic;
	private boolean isLive;
	private boolean isPro;
	private boolean isActive;
	
	@Lob
	@Column(length=100000)
	private byte[] poster;
	@Lob
	@Column(length=100000)
	private byte[] sponsorPoster;
}
