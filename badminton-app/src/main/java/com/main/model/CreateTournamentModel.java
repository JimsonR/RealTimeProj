package com.main.model;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateTournamentModel {
	private String tournamentName;
	private String startDate;
	private String endDate;
	private String bookingsCloseTime;
	private String location;
	private String address;
	private String description;
	private String sponsors;
	private MultipartFile poster;
	private MultipartFile sponsorPoster;
//	private byte[] poster;
//	private byte[] sponsorPoster;
}
