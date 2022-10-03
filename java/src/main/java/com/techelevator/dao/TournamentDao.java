package com.techelevator.dao;

import com.techelevator.model.RequestJoinTeam;
import com.techelevator.model.RequestJoinTournament;
import com.techelevator.model.Tournament;

import java.util.List;

public interface TournamentDao {

    List<Tournament> getAllTournaments();
    Tournament getTournamentById(int tournamentId);
    Tournament getTournamentByName(String tournamentName);
    Tournament createTournament(Tournament tournament);
    void updateTournament(Tournament tournament);
    void deleteTournament(int tournamentId);
    Tournament getTournamentByHostId(int hostId);


}
