package com.techelevator.model;


import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

public class Tournament {

    private int tournamentId;
    private int hostId;
    private String tournamentName;
    private int sportId;
    private boolean approvalToJoin;
    private Timestamp tournamentTimestamp;
    private int maxTeams;
    private int formatId;
    private String hostEmail;
    private String tournamentCity;
    private String tournamentStateAbbreviation;
    private String tournamentPrize;
    private int tournamentWinner;
    private String userName;
    private String sportName;
    private String formatType;


    public Tournament() {
    }

    public Tournament(int tournamentId, int hostId, String tournamentName, int sportId, boolean approvalToJoin, Timestamp tournamentTimestamp, int maxTeams, int formatId, String hostEmail, String tournamentCity, String tournamentStateAbbreviation, String tournamentPrize, int tournamentWinner, String userName, String sportName, String formatType) {
        this.tournamentId = tournamentId;
        this.hostId = hostId;
        this.tournamentName = tournamentName;
        this.sportId = sportId;
        this.approvalToJoin = approvalToJoin;
        this.tournamentTimestamp = tournamentTimestamp;
        this.maxTeams = maxTeams;
        this.formatId = formatId;
        this.hostEmail = hostEmail;
        this.tournamentCity = tournamentCity;
        this.tournamentStateAbbreviation = tournamentStateAbbreviation;
        this.tournamentPrize = tournamentPrize;
        this.tournamentWinner = tournamentWinner;
        this.userName = userName;
        this.sportName = sportName;
        this.formatType = formatType;
    }

    public int getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(int tournamentId) {
        this.tournamentId = tournamentId;
    }

    public int getHostId() {
        return hostId;
    }

    public void setHostId(int hostId) {
        this.hostId = hostId;
    }

    public String getTournamentName() {
        return tournamentName;
    }

    public void setTournamentName(String tournamentName) {
        this.tournamentName = tournamentName;
    }

    public int getSportId() {
        return sportId;
    }

    public void setSportId(int sportId) {
        this.sportId = sportId;
    }

    public boolean isApprovalToJoin() {
        return approvalToJoin;
    }

    public void setApprovalToJoin(boolean approvalToJoin) {
        this.approvalToJoin = approvalToJoin;
    }

    public Timestamp getTournamentTimestamp() {
        return tournamentTimestamp;
    }

    public void setTournamentTimestamp(Timestamp tournamentTimestamp) {
        this.tournamentTimestamp = tournamentTimestamp;
    }

    public int getMaxTeams() {
        return maxTeams;
    }

    public void setMaxTeams(int maxTeams) {
        this.maxTeams = maxTeams;
    }

    public int getFormatId() {
        return formatId;
    }

    public void setFormatId(int formatId) {
        this.formatId = formatId;
    }

    public String getHostEmail() {
        return hostEmail;
    }

    public void setHostEmail(String hostEmail) {
        this.hostEmail = hostEmail;
    }

    public String getTournamentCity() {
        return tournamentCity;
    }

    public void setTournamentCity(String tournamentCity) {
        this.tournamentCity = tournamentCity;
    }

    public String getTournamentStateAbbreviation() {
        return tournamentStateAbbreviation;
    }

    public void setTournamentStateAbbreviation(String tournamentStateAbbreviation) {
        this.tournamentStateAbbreviation = tournamentStateAbbreviation;
    }

    public String getTournamentPrize() {
        return tournamentPrize;
    }

    public void setTournamentPrize(String tournamentPrize) {
        this.tournamentPrize = tournamentPrize;
    }

    public int getTournamentWinner() {
        return tournamentWinner;
    }

    public void setTournamentWinner(int tournamentWinner) {
        this.tournamentWinner = tournamentWinner;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSportName() {
        return sportName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }

    public String getFormatType() {
        return formatType;
    }

    public void setFormatType(String formatType) {
        this.formatType = formatType;
    }

    @Override
    public String toString() {
        return "Tournament{" +
                "tournamentId=" + tournamentId +
                ", hostId=" + hostId +
                ", tournamentName='" + tournamentName +
                ", sportId=" + sportId +
                ", approvalToJoin=" + approvalToJoin +
                ", tournamentTimestamp=" + tournamentTimestamp +
                ", maxTeams=" + maxTeams +
                ", formatId=" + formatId +
                ", hostEmail='" + hostEmail +
                ", tournamentCity='" + tournamentCity +
                ", tournamentStateAbbreviation='" + tournamentStateAbbreviation +
                ", tournamentPrize='" + tournamentPrize +
                ", tournamentWinner=" + tournamentWinner +
                ", userName='" + userName +
                ", sportName='" + sportName +
                ", formatType='" + formatType +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tournament)) return false;
        Tournament that = (Tournament) o;
        return tournamentId == that.tournamentId
                && hostId == that.hostId
                && sportId == that.sportId
                && approvalToJoin == that.approvalToJoin
                && maxTeams == that.maxTeams
                && formatId == that.formatId
                && tournamentWinner == that.tournamentWinner
                && Objects.equals(tournamentName, that.tournamentName)
                && Objects.equals(tournamentTimestamp, that.tournamentTimestamp)
                && Objects.equals(hostEmail, that.hostEmail)
                && Objects.equals(tournamentCity, that.tournamentCity)
                && Objects.equals(tournamentStateAbbreviation, that.tournamentStateAbbreviation)
                && Objects.equals(tournamentPrize, that.tournamentPrize)
                && Objects.equals(userName, that.userName)
                && Objects.equals(sportName, that.sportName)
                && Objects.equals(formatType, that.formatType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tournamentId, hostId, tournamentName, sportId, approvalToJoin, tournamentTimestamp, maxTeams, formatId, hostEmail, tournamentCity, tournamentStateAbbreviation, tournamentPrize, tournamentWinner, userName, sportName, formatType);
    }
}
