package com.main.model;

import java.time.LocalDate;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data

@NoArgsConstructor
public class ShowTournamentsModel {

	private String tournamentName;
	private LocalDate startDate;
	private LocalDate endDate;
	private LocalDate bookingsCloseTime;
	private String location;
	private String address;
	private String description;
	private String sponsors;
	private MultipartFile poster;
	private MultipartFile sponsorPoster;
}
