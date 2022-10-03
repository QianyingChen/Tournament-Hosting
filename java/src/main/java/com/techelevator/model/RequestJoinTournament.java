package com.techelevator.model;

import java.sql.Timestamp;

public class RequestJoinTournament {
    private int requestId;
    private int teamId;
    private int tournamentId;
    private String requestStatus;
    private Timestamp requestTimestamp;

    public RequestJoinTournament() {
    }

    public RequestJoinTournament(int requestId, int teamId, int tournamentId, String requestStatus, Timestamp requestTimestamp) {
        this.requestId = requestId;
        this.teamId = teamId;
        this.tournamentId = tournamentId;
        this.requestStatus = requestStatus;
        this.requestTimestamp = requestTimestamp;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
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

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public Timestamp getRequestTimestamp() {
        return requestTimestamp;
    }

    public void setRequestTimestamp(Timestamp requestTimestamp) {
        this.requestTimestamp = requestTimestamp;
    }

    @Override
    public String toString() {
        return "RequestJoinTournament{" +
                "requestId=" + requestId +
                ", teamId=" + teamId +
                ", tournamentId=" + tournamentId +
                ", requestStatus='" + requestStatus + '\'' +
                ", requestTimestamp=" + requestTimestamp +
                '}';
    }
}
