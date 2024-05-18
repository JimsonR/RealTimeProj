package com.main.model;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class EditTournamentModel {
    private int tournamentId;
    private String tournamentName;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate bookingsCloseTime;
    private String location;
    private String address;
    private String description;
    private String sponsors;
//    private MultipartFile poster;
//    private MultipartFile sponsorPoster;
}

