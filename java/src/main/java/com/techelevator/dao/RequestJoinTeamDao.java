package com.techelevator.dao;


import com.techelevator.model.RequestJoinTeam;
import com.techelevator.model.Tournament;

import java.util.List;

public interface RequestJoinTeamDao {
    List<RequestJoinTeam> getAllJoinTeamRequests();
    RequestJoinTeam createRequestJoinTeam(RequestJoinTeam requestJoinTeam);
    RequestJoinTeam getRequestJoinTeamByRequestId(int requestId);


    //view Join team requests by Team Id -  for team Captain
    RequestJoinTeam getRequestJoinTeamByTeamId(int teamId);

    // If want to filter more specific, each team captain view join team requests by Team Id, with status = 'pending'
    RequestJoinTeam getRequestJoinTeamByTeamIdWithPendingStatus(int teamId);

    //    void updateRequestJoinTeam(RequestJoinTeam requestJoinTeam);
    List<RequestJoinTeam> getAllJoinTeamPendingRequests();

    //change request_status to 'approved' / 'Denied' by requestId
    void updateRequestJoinTeam(RequestJoinTeam requestJoinTeam);

    //Delete Join team by requestId
    void deleteRequestJoinTeam(int requestId);

}
