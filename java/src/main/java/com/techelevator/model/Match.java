package com.techelevator.model;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class Match {
    private int matchId;
    private int tournamentId;
    private Timestamp matchTimestamp;
    private String matchAddress;
    private int winningTeam;
    private String results;

    public Match() {
    }

    public Match(int matchId, int tournamentId, Timestamp matchTimestamp, String matchAddress, int winningTeam, String results) {
        this.matchId = matchId;
        this.tournamentId = tournamentId;
        this.matchTimestamp = matchTimestamp;
        this.matchAddress = matchAddress;
        this.winningTeam = winningTeam;
        this.results = results;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public int getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(int tournamentId) {
        this.tournamentId = tournamentId;
    }

    public Timestamp getMatchTimestamp() {
        return matchTimestamp;
    }

    public void setMatchTimestamp(Timestamp matchTimestamp) {
        this.matchTimestamp = matchTimestamp;
    }

    public String getMatchAddress() {
        return matchAddress;
    }

    public void setMatchAddress(String matchAddress) {
        this.matchAddress = matchAddress;
    }

    public int getWinningTeam() {
        return winningTeam;
    }

    public void setWinningTeam(int winningTeam) {
        this.winningTeam = winningTeam;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "Match{" +
                "matchId=" + matchId +
                ", tournamentId=" + tournamentId +
                ", matchTimestamp=" + matchTimestamp +
                ", matchAddress='" + matchAddress + '\'' +
                ", winningTeam=" + winningTeam +
                ", results='" + results + '\'' +
                '}';
    }
}
