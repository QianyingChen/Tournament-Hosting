package com.techelevator.dao;

import com.techelevator.model.MatchPoint;
import com.techelevator.model.TournamentPoint;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcMatchPointDao implements MatchPointDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcMatchPointDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public MatchPoint getMatchPointByMatchId(int matchId) {
        String sql = "SELECT match_point_id, match_id, team_id, points FROM match_points " +
                "WHERE match_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, matchId);
        if (result.next()) {
            return mapRowToMatchPoint(result);
        }
        return null;
    }

    @Override
    public MatchPoint getMatchPointByTeamId(int teamId) {
        String sql = "SELECT match_point_id, match_id, team_id, points FROM match_points " +
                "WHERE team_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, teamId);
        if (result.next()) {
            return mapRowToMatchPoint(result);
        }
        return null;
    }

    private MatchPoint mapRowToMatchPoint(SqlRowSet rowSet) {
        MatchPoint matchPoint = new MatchPoint();
        matchPoint.setMatchPointId(rowSet.getInt("match_point_id"));
        matchPoint.setMatchId(rowSet.getInt("match_id"));
        matchPoint.setTeamId(rowSet.getInt("team_id"));
        matchPoint.setPoints(rowSet.getInt("points"));
        return matchPoint;
    }
}
