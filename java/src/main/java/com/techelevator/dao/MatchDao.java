package com.techelevator.dao;

import com.techelevator.model.Match;


import java.util.List;

public interface MatchDao {
    List<Match> getAllMatches();
    Match getMatchById(int matchId);
    Match createMatch(Match match);
    List<Match> getMatchesByTournamentId(int tournamentId);
    void updateMatch(Match match);

    void deleteMatch(int matchId);
}
