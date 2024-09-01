package com.main.repository;

import com.main.model.ByesForMatchesProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import com.main.entity.Byes;
import com.main.entity.ByesId;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ByesRepository extends JpaRepository<Byes, ByesId>{

    @Query(nativeQuery = true,
            value = "select t.player1 AS Player1" +
                    ",t.player2 AS Player2" +
                    ",t.team_id AS TeamId" +
                    " from byes b join teams t on b.team_id = t.team_id" +
                    " where b.event_id = ?1")
        List<ByesForMatchesProjection> getByesByEventId(int eventId);

    @Query(nativeQuery = true,
    value = "select * from byes b where event_id = ?1 and team_id = ?2")
        Byes getByEventIdAndTeamId(int eventId, int teamId);

    @Modifying
    @Query(nativeQuery = true,
    value = "update byes set team_id = ?1 where team_id = ?2 and event_id = ?3")
    void setTeamValue(int teamId,int oldTeamId, int eventId);
}
