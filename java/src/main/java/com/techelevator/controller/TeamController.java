package com.techelevator.controller;

import com.techelevator.dao.RequestJoinTeamDao;
import com.techelevator.dao.TeamDao;
import com.techelevator.dao.UserDao;
import com.techelevator.dao.UserTeamDao;
import com.techelevator.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/teams")
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class TeamController {

    private TeamDao teamDao;
    private UserTeamDao userTeamDao;
    private RequestJoinTeamDao requestJoinTeamDao;
    private UserDao userDao;

    public TeamController(TeamDao teamDao, UserTeamDao userTeamDao, RequestJoinTeamDao requestJoinTeamDao, UserDao userDao) {
        this.teamDao = teamDao;
        this.userTeamDao = userTeamDao;
        this.requestJoinTeamDao = requestJoinTeamDao;
        this.userDao = userDao;
    }

    @PreAuthorize("permitAll()")
    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Team> getAllTeams() {
        return teamDao.getAllTeams();
    }

    @RequestMapping(path = "/captain/{captainId}", method = RequestMethod.GET)
    public List<Team> getTeamsByCaptainId(@PathVariable int captainId) {
        return teamDao.getTeamsByCaptainId(captainId);
    }

    @RequestMapping(path = "/team/{teamId}", method = RequestMethod.GET)
    public Team findTeamById(@PathVariable int teamId) {
        return teamDao.findTeamById(teamId);
    }

    @RequestMapping(path = "/teamName/{teamName}", method = RequestMethod.GET)
    public Team findTeamByName(@PathVariable String teamName) {
        return teamDao.findTeamByName(teamName);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "", method = RequestMethod.POST)
    public Team createTeam(@RequestBody Team team) {
        return teamDao.createTeam(team);
    }

    @RequestMapping(path = "/update/{teamId}", method = RequestMethod.PUT)
    public void updateTeam(@RequestBody Team team) {
        this.teamDao.updateTeam(team);
    }

    @RequestMapping(path = "/{teamId}", method = RequestMethod.DELETE)
    public void deleteTeam(@PathVariable int teamId) {
        teamDao.deleteTeam(teamId);
    }

    @RequestMapping(path = "/openTeams", method = RequestMethod.GET)
    public List<Team> getOpenTeams() {
        return teamDao.getOpenTeams();
    }

    //---Join Team---

    // ALEX has this sending all team join requests to the JoinTeamRequestList component
    // getAllJoinTeamRequests
    @RequestMapping(path = "/joinTeam", method = RequestMethod.GET)
    public List<RequestJoinTeam> getAllJoinTeamRequests() {
        return requestJoinTeamDao.getAllJoinTeamRequests();
    }

    //Request to join team
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/joinTeam", method = RequestMethod.POST)
    public RequestJoinTeam createRequestJoinTeam(@RequestBody RequestJoinTeam requestJoinTeam) {
        return requestJoinTeamDao.createRequestJoinTeam(requestJoinTeam);
    }

    // If want to filter more specific, each team captain view join team requests by Team Id, with status = 'pending'
    @RequestMapping(path = "/joinTeam/status/{teamId}/pending", method = RequestMethod.GET)
    public RequestJoinTeam getRequestJoinTeamByTeamIdWithPendingStatus(@PathVariable int teamId) {
        return requestJoinTeamDao.getRequestJoinTeamByTeamIdWithPendingStatus(teamId);
    }

    //should we need this for all captains? If not,we can ignore it
    @RequestMapping(path = "/joinTeam/status/pending", method = RequestMethod.GET)
    public List<RequestJoinTeam> getAllJoinTeamPendingRequests() {
        return requestJoinTeamDao.getAllJoinTeamPendingRequests();
    }

    //change request_status to 'approved' / 'Denied' by requestId
    @RequestMapping(path = "/joinTeam/status/update/{requestId}", method = RequestMethod.PUT)
    public void updateRequestJoinTeam(@RequestBody RequestJoinTeam requestJoinTeam) {
        this.requestJoinTeamDao.updateRequestJoinTeam(requestJoinTeam);
    }

    //Delete Join Team Request by requestId
    @RequestMapping(path = "/joinTeam/status/delete/{requestId}", method = RequestMethod.DELETE)
    public void deleteRequestJoinTeam(@PathVariable int requestId) {
        requestJoinTeamDao.deleteRequestJoinTeam(requestId);
    }


    //---teamUser(/teamMember)part---
    // List of teams user is member of (user_id from $store.state.user.id)
    @RequestMapping(path = "/teamMember/user/{userId}", method = RequestMethod.GET)
    public Team getTeamUserByUserId(@PathVariable int userId) {
        return teamDao.getTeamUserByUserId(userId);
    }

    // List of teams user is captain of (captain_id = $store.state.user.id)
    @RequestMapping(path = "/teamMember/captain/{captainId}", method = RequestMethod.GET)
    public Team getTeamUserByCaptainId(@PathVariable int captainId) {
        return teamDao.getTeamUserByCaptainId(captainId);
    }



    //view total number of the team member by team Id -- not working, may need a new mapping
//    @RequestMapping(path = "/teamMember/total/{teamId}", method = RequestMethod.GET)
//    UserTeam getTeamUserNumByTeamId(@PathVariable int teamId) {
//        return userTeamDao.getTeamUserNumByTeamId(teamId);
//    }
}