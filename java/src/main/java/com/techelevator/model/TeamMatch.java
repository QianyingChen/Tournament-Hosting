package com.techelevator.model;

public class TeamMatch {
    private int matchId;
    private int teamId;

    public TeamMatch() {
    }

    public TeamMatch(int matchId, int teamId) {
        this.matchId = matchId;
        this.teamId = teamId;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    @Override
    public String toString() {
        return "TeamMatch{" +
                "matchId=" + matchId +
                ", teamId=" + teamId +
                '}';
    }
}
