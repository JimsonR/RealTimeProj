package com.main.model;

public interface MatchesProjection {
    String getTeam1Player1();
    String getTeam1Player2();
    String getTeam2Player1();
    String getTeam2Player2();
    String getMatchId();
    String getMatchNumber();
    String getTeam1Id();
    String getTeam2Id();
    String getTotalPoints();
    int getWinner();
    String getSummary();

}
