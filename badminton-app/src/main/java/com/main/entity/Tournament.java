package com.main.entity;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
	@ManyToOne(cascade = CascadeType.ALL)
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
	private byte[] poster;
	private byte[] sponsorPoster;
}
