package com.techelevator.dao;

import com.techelevator.model.UserTeam;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcUserTeamDao implements UserTeamDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcUserTeamDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public UserTeam getUserTeamByUserId(int userId) {
        String sql = "SELECT user_id, team_id FROM user_teams WHERE user_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, userId);
        if (result.next()){
            return mapRowToUserTeam(result);
        }
        return null;
    }

    // ALEX JUST FIXED THIS ONE
    @Override
    public List<UserTeam> getTeamMembersListByTeamId(int teamId) {
        List<UserTeam> teamMembers = new ArrayList<>();
        String sql = "SELECT user_id, team_id FROM user_teams WHERE team_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, teamId);
        while(result.next()){
            teamMembers.add(mapRowToUserTeam(result));
        }
        return teamMembers;
    }

    @Override
    public List<UserTeam> getAllUserTeam() {
        String sql = "SELECT user_id, team_id " +
                "FROM public.user_teams;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        List<UserTeam> allUserTeam = new ArrayList<>();
        while (results.next()) {
            UserTeam userTeam= mapRowToUserTeam(results);
            allUserTeam.add(userTeam);
        }
        return allUserTeam;
    }


    //view total number of the team member by team Id -- not working, may need a new mapping
//    @Override
//    public UserTeam getTeamUserNumByTeamId(int teamId) {
//        String sql = "SELECT COUNT(user_id), team_id " +
//                "FROM public.user_teams " +
//                "GROUP BY team_id " +
//                "ORDER BY team_id ASC;";
//        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, teamId);
//        if (results.next()) {
//            return mapRowToUserTeam(results);
//        }
//        return null;
//    }


    // I don't think we need this method, because they're just two FKs
//    @Override
//    public void createUserTeam(UserTeam userTeam) {
//        String sql = "INSERT INTO public.user_teams (user_id, team_id) " +
//                     "VALUES (?, ?);";
//        jdbcTemplate.update(sql, userTeam.getUserId(), userTeam.getTeamId());
//
//    }


    private UserTeam mapRowToUserTeam (SqlRowSet rowset) {
        UserTeam userTeam = new UserTeam();
        userTeam.setUserId(rowset.getInt("user_id"));
        userTeam.setTeamId(rowset.getInt("team_id"));
        return userTeam;
    }
}
