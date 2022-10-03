package com.techelevator.dao;

import com.techelevator.model.TeamMatch;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTeamMatchDao implements TeamMatchDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTeamMatchDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //ALEX added this to be able to add teams to matches
    @Override
    public TeamMatch createTeamMatch(TeamMatch teamMatch){
        jdbcTemplate.update( "INSERT INTO team_matches" +
                "(match_id, team_id) " +
                "VALUES (?, ?);", teamMatch.getMatchId(), teamMatch.getTeamId());
        return teamMatch;
    }

    @Override
    public List<TeamMatch> getTeamMatchByMatchId(int matchId) {
        List<TeamMatch> teamsInMatch = new ArrayList<>();
        String sql = "SELECT match_id, team_id FROM team_matches WHERE match_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, matchId);
        while(result.next()) {
            teamsInMatch.add(mapRowToTeamMatch(result));
        }
        return teamsInMatch;
    }

    @Override
    public TeamMatch getTeamMatchByTeamId(int teamId) {
        String sql = "SELECT match_id, team_id FROM team_matches WHERE team_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, teamId);
        if (result.next()) {
            return mapRowToTeamMatch(result);
        }
        return null;
    }

    private TeamMatch mapRowToTeamMatch(SqlRowSet rowSet) {
        TeamMatch teamMatch = new TeamMatch();
        teamMatch.setMatchId(rowSet.getInt("match_id"));
        teamMatch.setTeamId(rowSet.getInt("team_id"));
        return teamMatch;
    }
}
