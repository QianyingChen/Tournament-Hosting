package com.techelevator.dao;

import com.techelevator.model.Team;
import com.techelevator.model.User;

import java.util.List;

public interface TeamDao {
    List<Team> getAllTeams();
    List<Team> getTeamsByCaptainId(int captainId);
    Team findTeamById(int teamId);
    Team findTeamByName(String teamName);
    Team createTeam(Team team);
    void updateTeam(Team team);
    void deleteTeam(int teamId);
    List<Team> getOpenTeams();
    Team getTeamUserByUserId(int userId);
    Team getTeamUserByCaptainId(int captainId);




}
