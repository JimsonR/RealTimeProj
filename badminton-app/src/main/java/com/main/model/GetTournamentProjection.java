package com.main.model;

import java.time.LocalDate;

public interface GetTournamentProjection {
     String getTournamentName();
     LocalDate getStartDate();
     LocalDate getEndDate();
     LocalDate getBookingsCloseTime();
     String getLocation();
     String getAddress();
     String getDescription();
     String getSponsors();
     byte[] getPoster();
     byte[] getSponsorPoster();
}
