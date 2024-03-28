package com.main.model.admin;

import com.main.entity.Teams;

import java.util.Date;

public interface AdminRevenueProjection {

        int getPaymentId();

        int getTeamId();

        int getEventId();
        String getEventName();

        int getTournamentId();
        String getTournamentName();
        String getUniqueId();

        Date getDate();
        int getAmount();
        boolean getPaymentMode();
        int getPaymentStatus();


}
