package com.main.repository.admin;

import com.main.entity.Payments;
import com.main.model.admin.AdminRevenueProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRevenueRepository extends JpaRepository<Payments,Integer> {
    @Query(nativeQuery = true,value = "select " +
            "p.payment_id as PaymentId," +
            "p.team_id as TeamId," +
            "te.event_id as EventId," +
            "e.event_name as EventName," +
            "e.tournament_id as TournamentId," +
            "tr.tournament_name as TournamentName," +
            "p.unique_id as UniqueId," +
            "p.date as Date," +
            " p.amount as Amount," +
            "p.payment_mode as PaymentMode," +
            "p.payment_status as PaymentStatus" +
            " from payments p join teamsnevent te on p.team_id = te.team_id" +
            " join event e on e.event_id = te.event_id " +
            "join tournament tr on e.tournament_id = tr.tournament_id")
    List<AdminRevenueProjection>getRevenue();

}
