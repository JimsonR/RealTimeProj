package com.main.entity;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(referencedColumnName = "organizationId")
	private Organization organizationId;
	private String tournamentName;
	private Date startDate;
	private Date endDate;
	private Date bookingsCloseTime;
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
