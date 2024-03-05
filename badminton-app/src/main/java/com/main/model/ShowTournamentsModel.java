package com.main.model;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.main.entity.Organization;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data

@NoArgsConstructor
public class ShowTournamentsModel {
	public ShowTournamentsModel(int tournamentId) {
//		public ShowTournamentsModel(int tournamentId, String tournamentName, Date startDate, Date endDate,
//				Date bookingsCloseTime, String location, String address, String description, String sponsors) {
		
		this.tournamentId = tournamentId;
//		this.tournamentName = tournamentName;
//		this.startDate = startDate;
//		this.endDate = endDate;
//		this.bookingsCloseTime = bookingsCloseTime;
//		this.location = location;
//		this.address = address;
//		this.description = description;
//		this.sponsors = sponsors;
	}
	private int tournamentId;
	private int organizationId;
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
