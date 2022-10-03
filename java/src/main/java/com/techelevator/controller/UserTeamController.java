package com.techelevator.controller;


import com.techelevator.dao.TeamDao;
import com.techelevator.dao.UserTeamDao;
import com.techelevator.model.Team;
import com.techelevator.model.UserTeam;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")

public class UserTeamController {
    private UserTeamDao userTeamDao;


    public UserTeamController(UserTeamDao userTeamDao) {
        this.userTeamDao = userTeamDao;

    }
    //--- two more teamUser(/teamMember) methods at Team Dao and Team Controller--

    //view allList Of UserTeam
    @RequestMapping(path = "/userTeam", method = RequestMethod.GET)
    public List<UserTeam> getAllUserTeam() {
        return userTeamDao.getAllUserTeam();
    }


    // ALEX JUST FIXED THIS ONE
    // View team members by team_id
    @RequestMapping(path = "/userTeam/team/{teamId}", method = RequestMethod.GET)
    public List<UserTeam> getTeamMembersListByTeamId(@PathVariable int teamId) {
        return userTeamDao.getTeamMembersListByTeamId(teamId);
    }

    @RequestMapping(path = "/userTeam/user/{userId}", method = RequestMethod.GET)
    public UserTeam getUserTeamByUserId(@PathVariable int userId) {
        return userTeamDao.getUserTeamByUserId(userId);
    }



}
