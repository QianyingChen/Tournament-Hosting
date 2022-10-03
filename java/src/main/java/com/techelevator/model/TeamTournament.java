package com.techelevator.model;

public class TeamTournament {
    private int teamId;
    private int tournamentId;

    public TeamTournament() {
    }

    public TeamTournament(int teamId, int tournamentId) {
        this.teamId = teamId;
        this.tournamentId = tournamentId;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public int getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(int tournamentId) {
        this.tournamentId = tournamentId;
    }

    @Override
    public String toString() {
        return "TeamTournament{" +
                "teamId=" + teamId +
                "tournamentId=" + tournamentId + "}";
    }
}
