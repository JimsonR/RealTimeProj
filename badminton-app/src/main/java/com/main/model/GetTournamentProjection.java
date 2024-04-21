package com.main.model;

import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

public interface GetTournamentProjection {
     String getTournamentName();
     Date getStartDate();
     Date getEndDate();
     Date getBookingsCloseTime();
     String getLocation();
     String getAddress();
     String getDescription();
     String getSponsors();
     byte[] getPoster();
     byte[] getSponsorPoster();
}
