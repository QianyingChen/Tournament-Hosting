package com.techelevator.controller;

import com.techelevator.dao.MatchDao;
import com.techelevator.dao.MatchPointDao;
import com.techelevator.dao.TeamMatchDao;
import com.techelevator.model.Match;
import com.techelevator.model.MatchPoint;
import com.techelevator.model.TeamMatch;
import com.techelevator.model.TournamentPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matches")
@CrossOrigin
@PreAuthorize("isAuthenticated()")

public class MatchController {
    private MatchDao matchDao;
    private MatchPointDao matchPointDao;
    private TeamMatchDao teamMatchDao;


    public MatchController(MatchDao matchDao, MatchPointDao matchPointDao, TeamMatchDao teamMatchDao) {
        this.matchDao = matchDao;
        this.matchPointDao = matchPointDao;
        this.teamMatchDao = teamMatchDao;
    }
    //ALEX added this to have a path for adding teams to matches
    @RequestMapping(path = "/addTeam", method = RequestMethod.POST)
    public TeamMatch addTeamToMatch(@RequestBody TeamMatch teamMatch){
        return teamMatchDao.createTeamMatch(teamMatch);
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Match> getAllMatches(){
        return matchDao.getAllMatches();
    }

    @RequestMapping(path = "/match/{matchId}", method = RequestMethod.GET)
    public  Match getMatchById(@PathVariable int matchId) {
        return matchDao.getMatchById(matchId);
    }

    @RequestMapping(path = "/{tournamentId}", method = RequestMethod.GET)
    public List<Match> getMatchesByTournamentId(@PathVariable int tournamentId) {
        return matchDao.getMatchesByTournamentId(tournamentId);
    }
    //Pairings- tournament host create matches
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "", method = RequestMethod.POST)
    public Match createMatch(@RequestBody Match match){
        return matchDao.createMatch(match);
    }


    @RequestMapping(path = "/update/{matchId}", method = RequestMethod.PUT)
    public void updateMatch(@RequestBody Match match) {
        this.matchDao.updateMatch(match);
    }

    @RequestMapping(path = "/{matchId}", method = RequestMethod.DELETE)
    public void deleteMatch (@PathVariable int matchId){
        matchDao.deleteMatch(matchId);
    }

    @RequestMapping(path = "/matchPoints/match/{matchId}", method = RequestMethod.GET)
    public MatchPoint getMatchPointByMatchId (@PathVariable int matchId){
        return matchPointDao.getMatchPointByMatchId(matchId);
    }

    @RequestMapping(path = "/matchPoints/team/{teamId}", method = RequestMethod.GET)
    public MatchPoint getMatchPointByTeamId (@PathVariable int teamId){
        return matchPointDao.getMatchPointByTeamId(teamId);
    }

    //ALEX FIXED THIS method chain. Should have been returning a List<TeamMatch> not just a single TeamMatch object
    @RequestMapping(path = "/teamMatch/match/{matchId}", method = RequestMethod.GET)
    public List<TeamMatch> getTeamMatchByMatchId (@PathVariable int matchId){
        return teamMatchDao.getTeamMatchByMatchId(matchId);
    }

    @RequestMapping(path = "/teamMatch/team/{teamId}", method = RequestMethod.GET)
    public TeamMatch getTeamMatchByTeamId (@PathVariable int teamId){
        return teamMatchDao.getTeamMatchByTeamId(teamId);
    }
}
