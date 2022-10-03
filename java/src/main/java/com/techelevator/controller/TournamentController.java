package com.techelevator.controller;


import com.techelevator.dao.RequestJoinTournamentDao;
import com.techelevator.dao.TournamentDao;
import com.techelevator.dao.TeamTournamentDao;
import com.techelevator.exception.RequestJoinTournamentNotFoundException;
import com.techelevator.dao.TournamentPointDao;
import com.techelevator.model.RequestJoinTournament;
import com.techelevator.model.TeamTournament;
import com.techelevator.model.Tournament;
import com.techelevator.model.TournamentPoint;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tournaments")
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class TournamentController {
    private TournamentDao tournamentDao;
    private TournamentPointDao tournamentPointDao;
    private RequestJoinTournamentDao requestJoinTournamentDao;

    private TeamTournamentDao teamTournamentDao;

    public TournamentController(TournamentDao tournamentDao, TournamentPointDao tournamentPointDao,
                                RequestJoinTournamentDao requestJoinTournamentDao, TeamTournamentDao  teamTournamentDao) {
        this.tournamentDao = tournamentDao;
        this.tournamentPointDao = tournamentPointDao;
        this.requestJoinTournamentDao = requestJoinTournamentDao;
        this.teamTournamentDao = teamTournamentDao;
    }

    @PreAuthorize("permitAll()")
    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Tournament> getAllTournaments(){
        return tournamentDao.getAllTournaments();}



    //ALEX added these to access TeamTournament objects
    @RequestMapping(path = "/getallteamtournament/", method = RequestMethod.GET)
    public List<TeamTournament> getAllTeamTournamentsListByTournamentId(){
        return teamTournamentDao.getAllTeamTournaments();
    }
    @RequestMapping(path = "tournament/teamslist/{tournamentId}", method = RequestMethod.GET)
    public List<TeamTournament> getTeamsListByTournamentId(@PathVariable int tournamentId){
        return teamTournamentDao.getTeamsByTournamentId(tournamentId);
    }
    @RequestMapping(path = "team/tournamentslist/{teamId}", method = RequestMethod.GET)
    public List<TeamTournament> getTournamentsListByTeamId(@PathVariable int teamId){
        return teamTournamentDao.getTeamsByTournamentId(teamId);
    }
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping( path = "/teamtournament", method = RequestMethod.POST)
    public TeamTournament createTeamTournament(@RequestBody TeamTournament teamTournament){
        return teamTournamentDao.createTeamTournament(teamTournament);
    }



    //Get Tournament By Tournament Id
    @PreAuthorize("permitAll()")
    @RequestMapping(path = "/tournament/{tournamentId}", method = RequestMethod.GET)
    public Tournament getTournamentById(@PathVariable int tournamentId) {
        return tournamentDao.getTournamentById(tournamentId);
    }

    //Get Tournament By Tournament Name
    @RequestMapping(path = "/tournamentName/{tournamentName}", method = RequestMethod.GET)
    public Tournament getTournamentByName(@PathVariable String tournamentName) {
        return tournamentDao.getTournamentByName(tournamentName);
    }

    //Create Tournament- tournament host create a new tournament
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping( path = "", method = RequestMethod.POST)
    public Tournament createTournament(@RequestBody Tournament tournament){
        return tournamentDao.createTournament(tournament);
    }

    //Modify Tournament- tournament host can update Tournament
    @RequestMapping(path = "/update/{tournamentId}", method = RequestMethod.PUT)
    public void updateTournament(@RequestBody Tournament tournament) {
        this.tournamentDao.updateTournament(tournament);
    }

    //Modify Tournament- tournament host can delete Tournament
    @RequestMapping(path = "/{tournamentId}", method = RequestMethod.DELETE)
    public void deleteTournament(@PathVariable int tournamentId){
        tournamentDao.deleteTournament(tournamentId);
    }

    //---tournamentPoints---
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    // May need more paths based on what I(Alex) added to the TournamentDao
    @RequestMapping(path = "/tournamentPoints/tournament/{tournamentId}", method = RequestMethod.GET)
    public TournamentPoint getTournamentPointByTournamentId (@PathVariable int tournamentId){
        return tournamentPointDao.getTournamentPointByTournamentId(tournamentId);
    }

    @RequestMapping(path = "/tournamentPoints/team/{teamId}", method = RequestMethod.GET)
    public TournamentPoint getTournamentPointByTeamId (@PathVariable int teamId){
        return tournamentPointDao.getTournamentPointByTeamId(teamId);
    }

    //---JOIN TOURNAMENT---
    //Join Tournament- Captain request join tournament (captain_id match $store.state.user.id to access this)
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping( path = "/joinTournament", method = RequestMethod.POST)
    public RequestJoinTournament createJoinTournamentRequest(@RequestBody RequestJoinTournament requestJoinTournament){
        return requestJoinTournamentDao.createJoinTournamentRequest(requestJoinTournament);
    }

    //Get All Join Tournament Requests
    @RequestMapping(path = "/joinTournament", method = RequestMethod.GET)
    public List<RequestJoinTournament> getAllJoinTournamentRequests() {
        return requestJoinTournamentDao.getAllJoinTournamentRequests();
    }

    //Get Join Tournament Requests by request Id
    @RequestMapping(path = "/joinTournament/requestId/{requestId}", method = RequestMethod.GET)
    public RequestJoinTournament getJoinTournamentRequestByRequestId(@PathVariable int requestId) {
        return requestJoinTournamentDao.getJoinTournamentRequestByRequestId(requestId);
    }

    //Get Join Tournament Requests by team Id
    @RequestMapping(path = "/joinTournament/teamId/{teamId}", method = RequestMethod.GET)
    public RequestJoinTournament getJoinTournamentRequestByTeamId(@PathVariable int teamId) {
        return requestJoinTournamentDao.getJoinTournamentRequestByTeamId(teamId);
    }

    //Get Join Tournament Requests by tournamentId
    @RequestMapping(path = "/joinTournament/tournamentId/{tournamentId}", method = RequestMethod.GET)
    public RequestJoinTournament getJoinTournamentRequestByTournamentId (@PathVariable int tournamentId) {
        return requestJoinTournamentDao.getJoinTournamentRequestByTournamentId(tournamentId);
    }

    //Accept/Reject entries- Host approve/deny join tournament request requestId
    @RequestMapping(path = "/joinTournament/update/{requestId}", method = RequestMethod.PUT)
    public boolean updateJoinTournamentRequest(@RequestBody RequestJoinTournament requestJoinTournament, @PathVariable int requestId) throws RequestJoinTournamentNotFoundException {
        boolean success = false;
        try {
            requestJoinTournamentDao.updateJoinTournamentRequest(requestJoinTournament, requestId);
            success = true;
        } catch ( Exception e){}
        return success;
    }

    //Modify Join Tournament - tournament host can delete join tournament request by requestId
    @RequestMapping(path = "/joinTournament/delete/{requestId}", method = RequestMethod.DELETE)
    public boolean deleteJoinTournamentRequest(@PathVariable int requestId){
        boolean success = false;
        try {
            requestJoinTournamentDao.deleteJoinTournamentRequest( requestId);
            success = true;
        } catch ( Exception e){}
        return success;

    }

    //Get Tournament By Tournament Host Id
    @RequestMapping(path = "/tournament/hostId/{hostId}", method = RequestMethod.GET)
    public Tournament getTournamentByHostId(@PathVariable int hostId) {
        return tournamentDao.getTournamentByHostId(hostId);
    }



}
