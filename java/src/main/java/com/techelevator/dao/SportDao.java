package com.techelevator.dao;

import com.techelevator.model.Sport;


import java.util.List;

public interface SportDao {
    List<Sport> getAllSports();
    Sport getSportsBySportId(int sportId);
    Sport getSportsBySportName(String SportName);
    Sport createSport(Sport sport);
    void updateSport(Sport sport);
    void deleteSport(int sportId);

}
