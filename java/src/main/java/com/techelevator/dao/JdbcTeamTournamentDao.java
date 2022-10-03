package com.techelevator.dao;


import com.techelevator.model.TeamTournament;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTeamTournamentDao implements TeamTournamentDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTeamTournamentDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<TeamTournament> getAllTeamTournaments() {
        List<TeamTournament> allTeamTournament = new ArrayList<>();
        String sql = "SELECT team_id, tournament_id " +
                "FROM public.team_tournaments;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
        while(result.next()) {
            allTeamTournament.add(mapRowToTeamTournament(result));
        }
        return allTeamTournament;
    }

    @Override
    public List<TeamTournament> getTournamentsByTeamId(int teamId) {
        List<TeamTournament> tournamentsTeamIsPlayingIn = new ArrayList<>();
        String sql = "SELECT team_id, tournament_id " +
                "FROM public.team_tournaments " +
                "WHERE team_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, teamId);
        while(result.next()) {
            tournamentsTeamIsPlayingIn.add(mapRowToTeamTournament(result));
        }
        return tournamentsTeamIsPlayingIn;
    }

    @Override
    public List<TeamTournament> getTeamsByTournamentId(int tournamentId) {
        List<TeamTournament> teamsInTournament = new ArrayList<>();
        String sql = "SELECT team_id, tournament_id " +
                "FROM public.team_tournaments " +
                "WHERE tournament_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, tournamentId);
        while(result.next()){
            teamsInTournament.add(mapRowToTeamTournament(result));
        }
        return teamsInTournament;
    }

    @Override
    public TeamTournament createTeamTournament(TeamTournament teamTournament) {
        jdbcTemplate.update("INSERT INTO public.team_tournaments( " +
                "team_id, tournament_id) " +
                "VALUES (?, ?);", teamTournament.getTeamId(), teamTournament.getTournamentId());
        return teamTournament;
    }

    private TeamTournament mapRowToTeamTournament(SqlRowSet rowSet) {
        TeamTournament teamTournament = new TeamTournament();
        teamTournament.setTeamId(rowSet.getInt("team_id"));
        teamTournament.setTournamentId(rowSet.getInt("tournament_id"));
        return teamTournament;
    }
}
