package com.techelevator.dao;

import com.techelevator.model.Tournament;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTournamentDao implements TournamentDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcTournamentDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


//    @Override
//    public List<Tournament> getAllTournaments() {
//        List<Tournament> allTournaments = new ArrayList<>();
//        String sql = "SELECT tournament_id, host_id, tournament_name, sport_id, approval_to_join, tournament_timestamp, max_teams, format_id, host_email, tournament_city, tournament_state_abbreviation, tournament_prize, tournament_winner " +
//                "FROM public.tournaments;";
//        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
//        while(results.next()) {
//            Tournament tournament = mapRowToTournament(results);
//            allTournaments.add(tournament);
//        }
//        return allTournaments;
//    }

    @Override
    public List<Tournament> getAllTournaments() {
        List<Tournament> allTournaments = new ArrayList<>();
        String sql = "SELECT tournaments.tournament_id, tournaments.host_id, tournaments.tournament_name, tournaments.sport_id, tournaments.approval_to_join, " +
                "tournaments.tournament_timestamp, tournaments.max_teams, tournaments.format_id, tournaments.host_email, " +
                "tournaments.tournament_city, tournaments.tournament_state_abbreviation, tournaments.tournament_prize, " +
                "tournaments.tournament_winner, users.username, " +
                "sports.sport_name, tournament_format.format_type " +
                "FROM public.tournaments " +
                "JOIN public.users " +
                "ON tournaments.host_id = users.user_id " +
                "JOIN public.sports " +
                "ON tournaments.sport_id = sports.sport_id " +
                "JOIN public.tournament_format " +
                "ON tournaments.format_id = tournament_format.format_id;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            Tournament tournament = mapRowToTournament(results);
            allTournaments.add(tournament);
        }
        return allTournaments;
    }

//    @Override
//    public Tournament getTournamentById(int tournamentId) {
//        String sql = "SELECT tournament_id, host_id, tournament_name, sport_id, approval_to_join, tournament_timestamp, max_teams, format_id, host_email, tournament_city, tournament_state_abbreviation, tournament_prize, tournament_winner " +
//                "FROM public.tournaments " +
//                "WHERE tournament_id = ?;";
//        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, tournamentId);
//        if(result.next()){
//            return mapRowToTournament(result);
//        }
//        return null;
//    }

    @Override
    public Tournament getTournamentById(int tournamentId){
        String sql = "SELECT tournaments.tournament_id, tournaments.host_id, tournaments.tournament_name, tournaments.sport_id, tournaments.approval_to_join, " +
                "tournaments.tournament_timestamp, tournaments.max_teams, tournaments.format_id, tournaments.host_email, " +
                "tournaments.tournament_city, tournaments.tournament_state_abbreviation, tournaments.tournament_prize, " +
                "tournaments.tournament_winner, users.username, " +
                "sports.sport_name, tournament_format.format_type " +
                "FROM public.tournaments " +
                "JOIN public.users " +
                "ON tournaments.host_id = users.user_id " +
                "JOIN public.sports " +
                "ON tournaments.sport_id = sports.sport_id " +
                "JOIN public.tournament_format " +
                "ON tournaments.format_id = tournament_format.format_id " +
                "WHERE tournament_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, tournamentId);
        if(result.next()){
            return mapRowToTournament(result);
        }
        return null;
    }

    @Override
    public Tournament getTournamentByName(String tournamentName) {
        String sql = "SELECT tournament_id, host_id, tournament_name, sport_id, approval_to_join, tournament_timestamp, max_teams, format_id, host_email, tournament_city, tournament_state_abbreviation, tournament_prize, tournament_winner " +
                "FROM public.tournaments " +
                "WHERE tournament_name = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, tournamentName);
        if(result.next()){
            return mapRowToTournament(result);
        }
        return null;
    }


    @Override
    public Tournament createTournament(Tournament tournament) {

        String sql = "INSERT INTO tournaments (host_id, tournament_name, sport_id, approval_to_join, tournament_timestamp, max_teams, " +
                "format_id, host_email, tournament_city, tournament_state_abbreviation, tournament_prize, tournament_winner) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING tournament_id;";
        Integer newId = jdbcTemplate.queryForObject(sql, Integer.class,
                tournament.getHostId(), tournament.getTournamentName(),
                tournament.getSportId(),tournament.isApprovalToJoin(),tournament.getTournamentTimestamp(),
                tournament.getMaxTeams(),tournament.getFormatId(),tournament.getHostEmail(),
                tournament.getTournamentCity(),tournament.getTournamentStateAbbreviation(),
                tournament.getTournamentPrize(),tournament.getTournamentWinner());
        return getTournamentById(newId);


    }

    // Need (host_id match $store.state.user.id to access this)
    @Override
    public void updateTournament(Tournament tournament) {
        String sql = "UPDATE tournaments " +
                "SET host_id = ?, tournament_name = ?, sport_id = ?, approval_to_join = ?, tournament_timestamp = ?, max_teams = ?, " +
                "format_id = ?, host_email = ?, tournament_city = ?, tournament_state_abbreviation = ?, tournament_prize = ?, tournament_winner = ? " +
                "WHERE tournament_id = ?;";
        jdbcTemplate.update(sql, tournament.getHostId(), tournament.getTournamentName(),
                tournament.getSportId(),tournament.isApprovalToJoin(),tournament.getTournamentTimestamp(),
                tournament.getMaxTeams(),tournament.getFormatId(),tournament.getHostEmail(),
                tournament.getTournamentCity(),tournament.getTournamentStateAbbreviation(),
                tournament.getTournamentPrize(),tournament.getTournamentWinner(),tournament.getTournamentId());
    }


    // Need (host_id match $store.state.user.id to access this)
    @Override
    public void deleteTournament(int tournamentId) {
        String sql = "DELETE FROM tournaments WHERE tournament_id =?;";
        jdbcTemplate.update(sql, tournamentId);

    }


    @Override
    public Tournament getTournamentByHostId(int hostId) {
        String sql = "SELECT tournament_id, host_id, tournament_name, sport_id, approval_to_join, tournament_timestamp, max_teams, format_id, host_email, tournament_city, tournament_state_abbreviation, tournament_prize, tournament_winner " +
                "FROM public.tournaments " +
                "WHERE host_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, hostId);
        if(result.next()){
            return mapRowToTournament(result);
        }
        return null;
    }







    private Tournament mapRowToTournament(SqlRowSet rowSet){
        Tournament tournament = new Tournament();
        tournament.setTournamentId(rowSet.getInt("tournament_id"));
        tournament.setHostId(rowSet.getInt("host_id"));
        tournament.setTournamentName(rowSet.getString("tournament_name"));
        tournament.setSportId(rowSet.getInt("sport_id"));
        tournament.setApprovalToJoin(rowSet.getBoolean("approval_to_join"));
        tournament.setTournamentTimestamp(rowSet.getTimestamp("tournament_timestamp"));
        tournament.setMaxTeams(rowSet.getInt("max_teams"));
        tournament.setFormatId(rowSet.getInt("format_id"));
        tournament.setHostEmail(rowSet.getString("host_email"));
        tournament.setTournamentCity(rowSet.getString("tournament_city"));
        tournament.setTournamentStateAbbreviation(rowSet.getString("tournament_state_abbreviation"));
        tournament.setTournamentPrize(rowSet.getString("tournament_prize"));
        tournament.setTournamentWinner(rowSet.getInt("tournament_winner"));
        tournament.setUserName(rowSet.getString("username"));
        tournament.setSportName(rowSet.getString("sport_name"));
        tournament.setFormatType(rowSet.getString("format_type"));
        return tournament;
    }

}
