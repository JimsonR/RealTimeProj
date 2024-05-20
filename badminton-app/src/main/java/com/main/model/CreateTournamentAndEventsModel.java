package com.main.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@RequiredArgsConstructor
public class CreateTournamentAndEventsModel {

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
   private MultipartFile eventsModel;
}
