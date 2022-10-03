package com.techelevator.dao;

import com.techelevator.model.Match;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcMatchDao implements MatchDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcMatchDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    //Results- get all the matches
    @Override
    public List<Match> getAllMatches() {
        List<Match> getAllMatches = new ArrayList<>();
        String sql = "SELECT match_id, tournament_id, match_timestamp, match_address, winning_team, results " +
                "FROM public.matches;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            Match match = mapRowToMatch(results);
            getAllMatches.add(match);
        }
        return getAllMatches;
    }

    //Results- get match by match Id
    @Override
    public Match getMatchById(int matchId) {
        String sql = "SELECT match_id, tournament_id, match_timestamp, match_address, winning_team, results " +
                   "FROM public.matches " +
                   "WHERE match_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, matchId);
        if(results.next()){
            return mapRowToMatch(results);
        }
        return null;
    }

    //Results- get match by tournamentId
    @Override
    public List<Match> getMatchesByTournamentId(int tournamentId) {
        List<Match> matchList = new ArrayList<>();
        String sql = "SELECT match_id, tournament_id, match_timestamp, match_address, winning_team, results " +
                "FROM public.matches " +
                "WHERE tournament_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            Match match = mapRowToMatch(results);
            matchList.add(match);
        }
        return matchList;

    }

    //Pairings- tournament host create matches
    @Override
    public Match createMatch(Match match) {
        String sql = "INSERT INTO matches(tournament_id, match_timestamp, match_address, winning_team, results) " +
                "VALUES (?, ?, ?, ?, ?) RETURNING match_id;";
        Integer newId = jdbcTemplate.queryForObject(sql, Integer.class,
                match.getTournamentId(), match.getMatchTimestamp(),
                match.getMatchAddress(), match.getWinningTeam(), match.getResults());

        return getMatchById(newId);
    }

    //Results - tournament host can update match Results
    @Override
    public void updateMatch(Match match) {
        String sql ="UPDATE matches " +
                "SET tournament_id = ?, match_timestamp = ?, match_address = ?, winning_team = ?, results = ? " +
                "WHERE match_id = ?;";
        jdbcTemplate.update(sql, match.getTournamentId(), match.getMatchTimestamp(), match.getMatchAddress(),
                match.getWinningTeam(), match.getResults());
    }

    //Results - tournament host can delete match
    @Override
    public void deleteMatch(int matchId) {
        String sql = "DELETE FROM matches WHERE match_id =?;";
        jdbcTemplate.update(sql, matchId);
    }

    // Host set match teams I(Alex) need to write SQL for this

    // Host update matches with winner + results I(Alex) need to write SQL for this


    private Match mapRowToMatch(SqlRowSet rowSet){
        Match match = new Match();
        match.setMatchId(rowSet.getInt("match_id"));
        match.setTournamentId(rowSet.getInt("tournament_id"));
        match.setMatchTimestamp(rowSet.getTimestamp("match_timestamp"));
        match.setMatchAddress(rowSet.getString("match_address"));
        match.setWinningTeam(rowSet.getInt("winning_team"));
        match.setResults(rowSet.getString("results"));
        return match;
    }
}
