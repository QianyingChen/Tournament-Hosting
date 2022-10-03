package com.techelevator.dao;

import com.techelevator.model.Sport;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcSportDao implements SportDao{
    private final JdbcTemplate jdbcTemplate;

    public JdbcSportDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Sport> getAllSports() {
        List<Sport> allSports = new ArrayList<>();
        String sql = "SELECT sport_id, sport_name " +
                    "FROM public.sports;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            Sport sport = mapRowToSport(results);
            allSports.add(sport);
        }
        return allSports;
    }

    @Override
    public Sport getSportsBySportId(int sportId) {
        String sql = "SELECT sport_id, sport_name " +
                    "FROM public.sports " +
                    "WHERE sport_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, sportId);
        if(results.next()){
            return mapRowToSport(results);
        }
        return null;
    }

    @Override
    public Sport getSportsBySportName(String SportName) {
        String sql = "SELECT sport_id, sport_name " +
                    "FROM public.sports " +
                     "WHERE sport_name = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, SportName);
        if(results.next()){
            return mapRowToSport(results);
        }
        return null;
    }

    @Override
    public Sport createSport(Sport sport) {
        String sql = "INSERT INTO sports (sport_name) " +
                     "VALUES (?) RETURNING sport_id;";
        Integer newId = jdbcTemplate.queryForObject(sql, Integer.class, sport.getSportName());
        return getSportsBySportId(newId);
    }

    @Override
    public void updateSport(Sport sport) {
        String sql = "UPDATE sports " +
                     "SET sport_name = ? " +
                     "WHERE sport_id = ?;";
         jdbcTemplate.update(sql, sport.getSportName());
    }

    @Override
    public void deleteSport(int sportId) {
        String sql = "DELETE FROM sports WHERE sport_id =?;";
        jdbcTemplate.update(sql, sportId);
    }

    private Sport mapRowToSport(SqlRowSet rowSet){
        Sport sport = new Sport();
        sport.setSportId(rowSet.getInt("sport_id"));
        sport.setSportName(rowSet.getString("sport_name"));
        return sport;
    }

}
