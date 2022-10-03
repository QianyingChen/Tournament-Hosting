package com.techelevator.dao;

import com.techelevator.model.TournamentPoint;

public interface TournamentPointDao {

    TournamentPoint getTournamentPointByTournamentId(int tournamentId);

    TournamentPoint getTournamentPointByTeamId(int teamId);

}
