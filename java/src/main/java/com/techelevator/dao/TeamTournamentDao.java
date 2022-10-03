package com.techelevator.dao;

import com.techelevator.model.TeamTournament;

import java.util.List;

public interface TeamTournamentDao {

    List<TeamTournament> getAllTeamTournaments();

    List<TeamTournament> getTournamentsByTeamId(int teamId);

    List<TeamTournament> getTeamsByTournamentId(int tournamentId);

    TeamTournament createTeamTournament(TeamTournament teamTournament);
}
