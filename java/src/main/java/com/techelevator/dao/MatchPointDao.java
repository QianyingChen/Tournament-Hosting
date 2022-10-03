package com.techelevator.dao;

import com.techelevator.model.MatchPoint;

public interface MatchPointDao {

    MatchPoint getMatchPointByMatchId(int matchId);

    MatchPoint getMatchPointByTeamId(int teamId);

}
