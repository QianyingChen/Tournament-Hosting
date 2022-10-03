package com.techelevator.model;

public class MatchPoint {

    private int matchPointId;
    private int matchId;
    private int teamId;
    private int points;

    public MatchPoint() {
    }

    public MatchPoint(int matchPointId, int matchId, int teamId, int points) {
        this.matchPointId = matchPointId;
        this.matchId = matchId;
        this.teamId = teamId;
        this.points = points;
    }

    public int getMatchPointId() {
        return matchPointId;
    }

    public void setMatchPointId(int matchPointId) {
        this.matchPointId = matchPointId;
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

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "MatchPoint{" +
                "matchPointId=" + matchPointId +
                ", matchId=" + matchId +
                ", teamId=" + teamId +
                ", points=" + points +
                '}';
    }
}
