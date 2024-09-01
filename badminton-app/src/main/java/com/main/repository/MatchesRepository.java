package com.main.repository;

import com.main.model.MatchesProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.main.entity.Event;
import com.main.entity.Matches;

import java.util.List;

@Repository
public interface MatchesRepository extends JpaRepository<Matches, Integer>{
	Matches findByMatchId(int matchId);

	boolean existsByMatchNumberAndRoundAndEventId(int matchNumber, int round,Event event);

	Matches findByMatchNumberAndRoundAndEventId(int matchNumber, int round,Event event);

	@Query(nativeQuery = true,
			value = "select t1.player1 AS Team1Player1,t1.player2 AS Team1Player2" +
					",t2.player1 AS Team2Player1, t2.player2 AS Team2Player2" +
					",m.winner_id AS Winner, m.remarks AS Summary" +
					",m.match_id AS MatchId" +
					",m.match_number AS MatchNumber" +
					",m.team1id AS Team1Id" +
					",m.team2id As Team2Id" +
					",m.total_points AS TotalPoints" +
					" from matches m join teams t1 on m.team1id = t1.team_id" +
					" join teams t2 on m.team2id = t2.team_id where m.event_id=?1 and m.round=?2 ORDER BY m.match_id")
	List<MatchesProjection> getMatches(int eventId,int roundId);

	@Query(nativeQuery = true,
			value = "select * from matches where match_id = ?1")
	Matches getByMatchId(int match1Id);
}
