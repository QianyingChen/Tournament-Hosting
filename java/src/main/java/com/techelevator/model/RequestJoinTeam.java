package com.techelevator.model;

import java.sql.Timestamp;

public class RequestJoinTeam {
    private int requestId;
    private int teamId;
    private int userId;
    private String requestStatus;
    private Timestamp requestTimestamp;

    public RequestJoinTeam() {
    }

    public RequestJoinTeam(int requestId, int teamId, int userId, String requestStatus, Timestamp requestTimestamp) {
        this.requestId = requestId;
        this.teamId = teamId;
        this.userId = userId;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
        return "RequestJoinTeam{" +
                "requestId=" + requestId +
                ", teamId=" + teamId +
                ", userId=" + userId +
                ", requestStatus='" + requestStatus + '\'' +
                ", requestTimestamp=" + requestTimestamp +
                '}';
    }
}
