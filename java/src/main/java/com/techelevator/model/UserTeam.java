package com.techelevator.model;

public class UserTeam {

    private int userId;
    private int teamId;

    public UserTeam() {
    }

    public UserTeam(int userId, int teamId) {
        this.userId = userId;
        this.teamId = teamId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    @Override
    public String toString() {
        return "UserTeam{" +
                "userId=" + userId +
                ", teamId=" + teamId +
                '}';
    }
}
