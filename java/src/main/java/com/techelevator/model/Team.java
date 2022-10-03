package com.techelevator.model;

public class Team {
    private int teamId;
    private int captainId;
    private String teamName;
    private int sportId;
    private boolean teamOpenToNewMembers;
    private boolean approvalToJoin;
    private int maxMembers;
    private String teamEmail;
    private String teamCity;
    private String teamStateAbbreviation;




    public Team() {}

    public Team(int teamId, int captainId, String teamName, int sportId, boolean teamOpenToNewMembers, boolean approvalToJoin, int maxMembers, String teamEmail, String teamCity, String teamStateAbbreviation) {
        this.teamId = teamId;
        this.captainId = captainId;
        this.teamName = teamName;
        this.sportId = sportId;
        this.teamOpenToNewMembers = teamOpenToNewMembers;
        this.approvalToJoin = approvalToJoin;
        this.maxMembers = maxMembers;
        this.teamEmail = teamEmail;
        this.teamCity = teamCity;
        this.teamStateAbbreviation = teamStateAbbreviation;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public int getCaptainId() {
        return captainId;
    }

    public void setCaptainId(int captainId) {
        this.captainId = captainId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getSportId() {
        return sportId;
    }

    public void setSportId(int sportId) {
        this.sportId = sportId;
    }

    public boolean isTeamOpenToNewMembers() {
        return teamOpenToNewMembers;
    }

    public void setTeamOpenToNewMembers(boolean teamOpenToNewMembers) {
        this.teamOpenToNewMembers = teamOpenToNewMembers;
    }

    public boolean isApprovalToJoin() {
        return approvalToJoin;
    }

    public void setApprovalToJoin(boolean approvalToJoin) {
        this.approvalToJoin = approvalToJoin;
    }

    public int getMaxMembers() {
        return maxMembers;
    }

    public void setMaxMembers(int maxMembers) {
        this.maxMembers = maxMembers;
    }

    public String getTeamEmail() {
        return teamEmail;
    }

    public void setTeamEmail(String teamEmail) {
        this.teamEmail = teamEmail;
    }

    public String getTeamCity() {
        return teamCity;
    }

    public void setTeamCity(String teamCity) {
        this.teamCity = teamCity;
    }

    public String getTeamStateAbbreviation() {
        return teamStateAbbreviation;
    }

    public void setTeamStateAbbreviation(String teamStateAbbreviation) {
        this.teamStateAbbreviation = teamStateAbbreviation;
    }

    @Override
    public String toString() {
        return "Team{" +
                "teamId=" + teamId +
                ", captainId=" + captainId +
                ", teamName='" + teamName + '\'' +
                ", sportId=" + sportId +
                ", teamOpenToNewMembers=" + teamOpenToNewMembers +
                ", approvalToJoin=" + approvalToJoin +
                ", maxMembers=" + maxMembers +
                ", teamEmail='" + teamEmail + '\'' +
                ", teamCity='" + teamCity + '\'' +
                ", teamStateAbbreviation='" + teamStateAbbreviation + '\'' +
                '}';
    }

}
