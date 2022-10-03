package com.techelevator.dao;

import com.techelevator.model.RequestJoinTeam;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcRequestJoinTeamDao implements RequestJoinTeamDao{
    private final JdbcTemplate jdbcTemplate;

    public JdbcRequestJoinTeamDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // view all Join Team Requests
    @Override
    public List<RequestJoinTeam> getAllJoinTeamRequests() {
        List<RequestJoinTeam> allJoinTeamRequests = new ArrayList<>();
        String sql = "SELECT request_id, team_id, user_id, request_status, request_timestamp " +
                "FROM public.request_join_team;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            RequestJoinTeam requestJoinTeam = mapRowToRequestJoinTeam(results);
            allJoinTeamRequests.add(requestJoinTeam);
        }
        return allJoinTeamRequests;
    }

    //Join Team - registered user can send a request to join a team, and will get a request_id
    @Override
    public RequestJoinTeam createRequestJoinTeam(RequestJoinTeam requestJoinTeam) {
        String sql = "INSERT INTO public.request_join_team " +
                "(team_id, user_id, request_status, request_timestamp) " +
                "VALUES (?, ?, ?, ?) RETURNING request_id;";
        Integer newId = jdbcTemplate.queryForObject(sql, Integer.class,
                requestJoinTeam.getTeamId(), requestJoinTeam.getUserId(),
                requestJoinTeam.getRequestStatus(), requestJoinTeam.getRequestTimestamp());
        return getRequestJoinTeamByRequestId(newId);
    }


    //registered user can view Join team requests by request Id
    @Override
    public RequestJoinTeam getRequestJoinTeamByRequestId(int requestId) {
        String sql = "SELECT request_id, team_id, user_id, request_status, request_timestamp " +
                "FROM public.request_join_team " +
                "WHERE request_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, requestId);
        if(results.next()){
            return mapRowToRequestJoinTeam(results);
        }
        return null;
    }

    //View join team requests (captain_id match $store.state.user.id to access this, team_id = selected team)
    @Override
    public RequestJoinTeam getRequestJoinTeamByTeamId(int teamId) {
        String sql = "SELECT request_id, team_id, user_id, request_status, request_timestamp " +
                     "FROM public.request_join_team " +
                     "WHERE team_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, teamId);
        if(results.next()){
            return mapRowToRequestJoinTeam(results);
        }
        return null;
    }

    // If want to filter more specific, each team captain view join team requests by Team Id, with status = 'pending'
    @Override
    public RequestJoinTeam getRequestJoinTeamByTeamIdWithPendingStatus(int teamId) {
        String sql = "SELECT request_id, team_id, user_id, request_status, request_timestamp " +
                     "FROM public.request_join_team " +
                     "WHERE team_id = ? AND request_status = 'pending';";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, teamId);
        if(results.next()){
            return mapRowToRequestJoinTeam(results);
        }
        return null;
    }


    //should we need this for all captains?
    @Override
    public List<RequestJoinTeam> getAllJoinTeamPendingRequests() {
        List<RequestJoinTeam> allJoinTeamPendingRequests = new ArrayList<>();
        String sql = "SELECT request_id, team_id, user_id, request_status, request_timestamp " +
                    "FROM public.request_join_team " +
                     "WHERE request_status = 'Pending';";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            RequestJoinTeam requestJoinTeam = mapRowToRequestJoinTeam(results);
            allJoinTeamPendingRequests.add(requestJoinTeam);
        }
        return allJoinTeamPendingRequests;
    }

    //change request_status to 'approved' / 'Denied'
    @Override
    public void updateRequestJoinTeam(RequestJoinTeam requestJoinTeam) {
        String sql = " UPDATE public.request_join_team " +
                "SET team_id=?, user_id=?, request_status=?, request_timestamp=? " +
                "WHERE request_id =?;";
        jdbcTemplate.update(sql, requestJoinTeam.getTeamId(), requestJoinTeam.getUserId(),
                requestJoinTeam.getRequestStatus(), requestJoinTeam.getRequestTimestamp(), requestJoinTeam.getRequestId());
    }

    //Delete Join Team Request by requestId
    @Override
    public void deleteRequestJoinTeam(int requestId) {
        String sql = "DELETE FROM public.request_join_team WHERE request_id =?;";
        jdbcTemplate.update(sql, requestId);
    }

    //Done-- line 72-83 -- public RequestJoinTeam getRequestJoinTeamByTeamIdWithPendingStatus (int teamId, String requestStatus)
    // (table without join, which table is still keeping user_id instead of userName, feel free to let me know if wanna change userId to username )

    // Captain approve join team request
    // Use view join team requests list with status = 'pending'
    //    SELECT request_join_team.request_id, request_join_team.team_id, users.username, request_join_team.request_status, request_join_team.request_timestamp
    //    FROM public.request_join_team
    //    INNER JOIN public.users ON request_join_team.user_id = users.user_id
    //    WHERE request_join_team.team_id = 1 AND request_join_team.request_status = 'pending';


    //Done--Method at JdbcTeamUserDAO (Lines 55-60),but I comment it out.
    // I don't think we need this method, because they're two FKs

    // Step 2 insert user_id into user_teams table, user_id + team_id selected on frontend
    //    INSERT INTO public.user_teams(
    //            user_id, team_id)
    //    VALUES (?, ?);

    //Done -- lines 102-110 -- updateRequestJoinTeam
    // Step 3 change request_status to 'approved'
    //    UPDATE public.request_join_team
    //    SET request_id=?, team_id=?, user_id=?, request_status=?, request_timestamp=?
    //    WHERE user_id = ?;

    //Done-- method is on lines 59-79 -- RequestJoinTeam getRequestJoinTeamByTeamId(int teamId)
    // View join team requests (captain_id match $store.state.user.id to access this, team_id = selected team)
    //    SELECT request_join_team.request_id, request_join_team.team_id, users.username, request_join_team.request_status, request_join_team.request_timestamp
    //    FROM public.request_join_team
    //    INNER JOIN public.users ON request_join_team.joining_user = users.user_id
    //    WHERE request_join_team.team_id = ?;


    private RequestJoinTeam mapRowToRequestJoinTeam(SqlRowSet rowSet) {
        RequestJoinTeam requestJoinTeam = new RequestJoinTeam();
        requestJoinTeam.setRequestId(rowSet.getInt("request_id"));
        requestJoinTeam.setTeamId(rowSet.getInt("team_id"));
        requestJoinTeam.setUserId(rowSet.getInt("user_id"));
        requestJoinTeam.setRequestStatus(rowSet.getString("request_status"));
        requestJoinTeam.setRequestTimestamp(rowSet.getTimestamp("request_timestamp"));
        return requestJoinTeam;
    }
}
