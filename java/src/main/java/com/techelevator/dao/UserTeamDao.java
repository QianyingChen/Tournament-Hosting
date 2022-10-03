package com.techelevator.dao;

import com.techelevator.model.Tournament;
import com.techelevator.model.UserTeam;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.List;

public interface UserTeamDao {

    UserTeam getUserTeamByUserId (int userId);

    List<UserTeam> getAllUserTeam();

    List<UserTeam> getTeamMembersListByTeamId (int teamId);

    //    void createUserTeam(UserTeam UserTeam);

//    UserTeam getTeamUserNumByTeamId(int teamId);
}
