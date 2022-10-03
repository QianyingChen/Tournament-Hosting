package com.techelevator.model;

public class TournamentPoint {

    private int tournamentPointId;
    private int tournamentId;
    private int teamId;
    private int points;

    public TournamentPoint() {
    }

    public TournamentPoint(int tournamentPointId, int tournamentId, int teamId, int points) {
        this.tournamentPointId = tournamentPointId;
        this.tournamentId = tournamentId;
        this.teamId = teamId;
        this.points = points;
    }

    public int getTournamentPointId() {
        return tournamentPointId;
    }

    public void setTournamentPointId(int tournamentPointId) {
        this.tournamentPointId = tournamentPointId;
    }

    public int getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(int tournamentId) {
        this.tournamentId = tournamentId;
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
        return "TournamentPoint{" +
                "tournamentPointId=" + tournamentPointId +
                ", tournamentId=" + tournamentId +
                ", teamId=" + teamId +
                ", points=" + points +
                '}';
    }
}