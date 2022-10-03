package com.techelevator.dao;

import com.techelevator.model.Team;
import com.techelevator.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTeamDao implements TeamDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcTeamDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Team> getAllTeams() {
        String sql = "SELECT team_id, captain_id, team_name, sport_id, team_open_to_new_members, approval_to_join, max_members, team_email, team_city, team_state_abbreviation " +
                "FROM public.teams;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        List<Team> allTeams = new ArrayList<>();
        while (results.next()) {
            Team team = mapRowToTeam(results);
            allTeams.add(team);
        }
        return allTeams;
    }

    @Override
    public List<Team> getTeamsByCaptainId(int captainId) {
        String sql = "SELECT team_id, captain_id, team_name, sport_id, team_open_to_new_members, approval_to_join, max_members, team_email, team_city, team_state_abbreviation " +
                "FROM public.teams " +
                "WHERE captain_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, captainId);
        List<Team> teamList = new ArrayList<>();
        while (results.next()) {
            Team team = mapRowToTeam(results);
            teamList.add(team);
        }
        return teamList;
    }

    @Override
    public Team findTeamById(int teamId) {
        String sql = "SELECT team_id, captain_id, team_name, sport_id, team_open_to_new_members, approval_to_join, max_members, team_email, team_city, team_state_abbreviation " +
                "FROM public.teams " +
                "WHERE team_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, teamId);
        if (results.next()) {
            return mapRowToTeam(results);
        }
        return null;
    }

    @Override
    public Team findTeamByName(String teamName) {
        String sql = "SELECT team_id, captain_id, team_name, sport_id, team_open_to_new_members, approval_to_join, max_members, team_email, team_city, team_state_abbreviation " +
                "FROM public.teams " +
                "WHERE team_name = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, teamName);
        if (results.next()) {
            return mapRowToTeam(results);
        }
        return null;
    }


    @Override
    public Team createTeam(Team team) {
        String sql = "INSERT INTO teams (captain_id, team_name, sport_id, team_open_to_new_members, approval_to_join, max_members, team_email, team_city, team_state_abbreviation) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING team_id ";
        Integer newId = jdbcTemplate.queryForObject(sql, Integer.class,
                team.getCaptainId(), team.getTeamName(), team.getSportId(), team.isTeamOpenToNewMembers(), team.isApprovalToJoin(), team.getMaxMembers(), team.getTeamEmail(), team.getTeamCity(), team.getTeamStateAbbreviation());
        team.setTeamId(newId);
        return team;
    }

    // Captain modify team (captain_id match $store.state.user.id to access this)
    @Override
    public void updateTeam(Team team) {
        String sql = "UPDATE teams " +
                "SET captain_id = ?, team_name = ?, sport_id = ?, team_open_to_new_members = ?, " +
                "approval_to_join = ?, max_members = ?, team_email = ?, team_city = ?, team_state_abbreviation = ? " +
                "WHERE team_id = ?;";
        jdbcTemplate.update(sql, team.getCaptainId(), team.getTeamName(), team.getSportId(), team.isTeamOpenToNewMembers(),
                team.isApprovalToJoin(), team.getMaxMembers(), team.getTeamEmail(), team.getTeamCity(),
                team.getTeamStateAbbreviation(), team.getTeamId());
    }

    @Override
    public void deleteTeam(int teamId) {
        String sql = "DELETE FROM teams WHERE team_id =?;";
        jdbcTemplate.update(sql, teamId);
    }


    @Override
    public List<Team> getOpenTeams() {
        String sql = "SELECT teams.team_id, teams.captain_id, teams.team_name, teams.sport_id, teams.team_open_to_new_members, teams.approval_to_join, " +
                      "teams.max_members, teams.team_email, teams.team_city, teams.team_state_abbreviation " +
                      "FROM public.teams WHERE teams.team_open_to_new_members = true;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        List<Team> teamsOpenList = new ArrayList<>();
        while (results.next()) {
            Team team = mapRowToTeam(results);
            teamsOpenList.add(team);
        }
        return teamsOpenList;
    }

    // List of teams open to new members (must have Auth on this path)
    // COUNT gives number of rows(user_id) associated with each team_id
    //    SELECT COUNT(user_id), team_id
    //    FROM public.user_teams
    //    GROUP BY team_id;
    // Must test teams.max_members against COUNT(user_id) for each team_id
    // Also only return teams with team_open = true
    //    SELECT teams.team_id, teams.captain_id, teams.team_name, teams.sport_id, teams.team_open_to_new_members, teams.approval_to_join,
    //    teams.max_members, teams.team_email, teams.team_city, teams.team_state_abbreviation
    //    FROM public.teams
    //    WHERE teams.team_open_to_new_members = true;


    // List of teams user is member of (user_id from $store.state.user.id)
    @Override
    public Team getTeamUserByUserId(int userId) {
        String sql = "SELECT teams.team_id, teams.captain_id, teams.team_name, teams.sport_id, teams.team_open_to_new_members, teams.approval_to_join, teams.max_members, teams.team_email, teams.team_city, teams.team_state_abbreviation " +
                "FROM public.teams " +
                "INNER JOIN user_teams ON teams.team_id = user_teams.team_id " +
                "WHERE user_teams.user_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        if (results.next()) {
            return mapRowToTeam(results);
        }
        return null;
    }


    // List of teams user is captain of (captain_id = $store.state.user.id)
    @Override
    public Team getTeamUserByCaptainId(int captainId) {
        String sql = "SELECT teams.team_id, teams.captain_id, teams.team_name, teams.sport_id, teams.team_open_to_new_members, teams.approval_to_join, " +
                     "teams.max_members, teams.team_email, teams.team_city, teams.team_state_abbreviation " +
                     "FROM public.teams " +
                     "WHERE teams.captain_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, captainId);
        if (results.next()) {
            return mapRowToTeam(results);
        }
        return null;
    }



    private Team mapRowToTeam(SqlRowSet rowSet) {
        Team team = new Team();
        team.setTeamId(rowSet.getInt("team_id"));
        team.setCaptainId(rowSet.getInt("captain_id"));
        team.setTeamName(rowSet.getString("team_name"));
        team.setSportId(rowSet.getInt("sport_id"));
        team.setTeamOpenToNewMembers(rowSet.getBoolean("team_open_to_new_members"));
        team.setApprovalToJoin(rowSet.getBoolean("approval_to_join"));
        team.setMaxMembers(rowSet.getInt("max_members"));
        team.setTeamEmail(rowSet.getString("team_email"));
        team.setTeamCity(rowSet.getString("team_city"));
        team.setTeamStateAbbreviation(rowSet.getString("team_state_abbreviation"));
        return team;
    }


}
