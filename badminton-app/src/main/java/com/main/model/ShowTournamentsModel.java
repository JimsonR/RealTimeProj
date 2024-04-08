package com.main.model;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.main.entity.Organization;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Component
@Data

@NoArgsConstructor
public class ShowTournamentsModel {

	private String tournamentName;
	private Date startDate;
	private Date endDate;
	private Date bookingsCloseTime;
	private String location;
	private String address;
	private String description;
	private String sponsors;
	private MultipartFile poster;
	private MultipartFile sponsorPoster;
}
