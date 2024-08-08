package com.main.repository;

import com.main.model.MatchesProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.main.entity.Event;
import com.main.entity.Matches;

@Repository
public interface MatchesRepository extends JpaRepository<Matches, Integer>{
	Matches findByMatchId(int matchId);

	boolean existsByMatchNumberAndRoundAndEventId(int matchNumber, int round,Event event);

	Matches findByMatchNumberAndRoundAndEventId(int matchNumber, int round,Event event);

	@Query(nativeQuery = true,
			value = "select t.player1,t.player2, form matches m join teamsnevent tne" +
					" on m.event_id = tne.event_id and (m.team1id = tne.team_id or m.team2id = tne.team_id)" +
					" join teams t on tne.team_id = t.team_id")
	MatchesProjection getMataches(int eventId);
}
