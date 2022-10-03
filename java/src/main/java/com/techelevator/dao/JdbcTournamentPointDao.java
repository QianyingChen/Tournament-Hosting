package com.techelevator.dao;

import com.techelevator.model.TournamentPoint;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcTournamentPointDao implements TournamentPointDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTournamentPointDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public TournamentPoint getTournamentPointByTournamentId(int tournamentId) {
        String sql = "SELECT tournament_point_id, tournament_id, team_id, points " +
                "FROM tournament_points WHERE tournament_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, tournamentId);
        if (result.next()) {
            return mapRowToTournamentPoint(result);
        }
        return null;
    }

    @Override
    public TournamentPoint getTournamentPointByTeamId(int teamId) {

        String sql = "SELECT tournament_point_id, tournament_id, team_id, points " +
                "FROM tournament_points WHERE team_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, teamId);
        if (result.next()) {
            return mapRowToTournamentPoint(result);
        }
        return null;
    }

    private TournamentPoint mapRowToTournamentPoint(SqlRowSet rowSet) {
        TournamentPoint tournamentPoint = new TournamentPoint();
        tournamentPoint.setTournamentPointId(rowSet.getInt("tournament_point_id"));
        tournamentPoint.setTournamentId(rowSet.getInt("tournament_id"));
        tournamentPoint.setTeamId(rowSet.getInt("team_id"));
        tournamentPoint.setPoints(rowSet.getInt("points"));
        return tournamentPoint;
    }
}
