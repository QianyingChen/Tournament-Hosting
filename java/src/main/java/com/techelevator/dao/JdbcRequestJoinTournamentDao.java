package com.techelevator.dao;

import com.techelevator.model.RequestJoinTournament;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcRequestJoinTournamentDao implements RequestJoinTournamentDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcRequestJoinTournamentDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //Get All Join Tournament Requests
    @Override
    public List<RequestJoinTournament> getAllJoinTournamentRequests() {
        List<RequestJoinTournament> allTournamentRequests = new ArrayList<>();
        String sql = "SELECT request_id, team_id, tournament_id, request_status, request_timestamp " +
                     "FROM public.request_join_tournament;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            RequestJoinTournament requestJoinTournament = mapRowToRequestJoinTournament(results);
            allTournamentRequests.add(requestJoinTournament);
        }
        return allTournamentRequests;
    }

    //Get Join Tournament Requests by request Id
    @Override
    public RequestJoinTournament getJoinTournamentRequestByRequestId(int requestId) {
        String sql = "SELECT request_id, team_id, tournament_id, request_status, request_timestamp " +
                     "FROM public.request_join_tournament " +
                     "WHERE request_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, requestId);
        if(results.next()){
            return mapRowToRequestJoinTournament(results);
        }
        return null;
    }

    //Get Join Tournament Requests by team Id
    @Override
    public RequestJoinTournament getJoinTournamentRequestByTeamId(int teamId) {
        String sql = "SELECT request_id, team_id, tournament_id, request_status, request_timestamp " +
                "FROM public.request_join_tournament " +
                "WHERE team_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, teamId);
        if(results.next()){
            return mapRowToRequestJoinTournament(results);
        }
        return null;
    }

    //Get Join Tournament Requests by tournamentId
    @Override
    public RequestJoinTournament getJoinTournamentRequestByTournamentId(int tournamentId) {
        String sql = "SELECT request_id, team_id, tournament_id, request_status, request_timestamp " +
                "FROM public.request_join_tournament " +
                "WHERE team_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, tournamentId);
        if(results.next()){
            return mapRowToRequestJoinTournament(results);
        }
        return null;
    }

    // Captain request join tournament (captain_id match $store.state.user.id to access this)
    @Override
    public RequestJoinTournament createJoinTournamentRequest(RequestJoinTournament requestJoinTournament) {
        String sql = "INSERT INTO request_join_tournament (team_id, tournament_id, request_status, request_timestamp) " +
                "VALUES (?, ?, ?, ?) RETURNING request_id;";
        Integer newId = jdbcTemplate.queryForObject(sql, Integer.class, requestJoinTournament.getTeamId(),
                         requestJoinTournament.getTournamentId(), requestJoinTournament.getRequestStatus(),
                         requestJoinTournament.getRequestTimestamp());
        return getJoinTournamentRequestByRequestId(newId);
    }

    //Modify Join Tournament - tournament host can delete join tournament request by requestId
    @Override
    public boolean deleteJoinTournamentRequest(int requestId) {
        String sql = "DELETE FROM request_join_tournament WHERE request_id = ?;";
        int numberOfRows = jdbcTemplate.update(sql, requestId);
        return numberOfRows == 1;
    }


    //Host approve/deny tournament join request (host_id match $store.state.user.id to access this)
    @Override
    public boolean updateJoinTournamentRequest (RequestJoinTournament requestJoinTournament, int requestId) {

        String sql = "UPDATE request_join_tournament " +
                     "SET team_id=?, tournament_id=?, request_status=?, request_timestamp=? " +
                     "WHERE request_id=?;";
        int numberOfRows = jdbcTemplate.update(sql, requestJoinTournament.getTeamId(), requestJoinTournament.getTournamentId(),
                requestJoinTournament.getRequestStatus(), requestJoinTournament.getRequestTimestamp());

        return numberOfRows == 1;
    }


    private RequestJoinTournament mapRowToRequestJoinTournament (SqlRowSet rowSet) {
        RequestJoinTournament requestJoinTournament = new RequestJoinTournament();
        requestJoinTournament.setRequestId(rowSet.getInt("request_id"));
        requestJoinTournament.setTeamId(rowSet.getInt("team_id"));
        requestJoinTournament.setTournamentId(rowSet.getInt("tournament_id"));
        requestJoinTournament.setRequestStatus(rowSet.getString("request_status"));
        requestJoinTournament.setRequestTimestamp(rowSet.getTimestamp("request_timestamp"));
        return requestJoinTournament;
    }
}
