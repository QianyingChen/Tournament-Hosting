package com.techelevator.dao;

import com.techelevator.exception.RequestJoinTournamentNotFoundException;
import com.techelevator.model.RequestJoinTournament;

import java.util.List;

public interface RequestJoinTournamentDao {

    List<RequestJoinTournament> getAllJoinTournamentRequests();
    RequestJoinTournament getJoinTournamentRequestByRequestId (int requestId);
    RequestJoinTournament getJoinTournamentRequestByTeamId(int teamId);
    RequestJoinTournament getJoinTournamentRequestByTournamentId (int tournamentId);
    RequestJoinTournament createJoinTournamentRequest(RequestJoinTournament requestJoinTournament);
    boolean deleteJoinTournamentRequest(int requestId);
    boolean updateJoinTournamentRequest (RequestJoinTournament requestJoinTournament, int requestId) throws RequestJoinTournamentNotFoundException;

}
