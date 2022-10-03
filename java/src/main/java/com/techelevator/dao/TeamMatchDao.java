package com.techelevator.dao;

import com.techelevator.model.TeamMatch;

import java.util.List;

public interface TeamMatchDao {

    List<TeamMatch> getTeamMatchByMatchId(int matchId);
    TeamMatch getTeamMatchByTeamId(int teamId);

    TeamMatch createTeamMatch(TeamMatch teamMatch);


}
