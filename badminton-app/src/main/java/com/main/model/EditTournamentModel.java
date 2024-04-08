package com.main.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
@Data
@Builder
public class EditTournamentModel {
    private int tournamentId;
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

