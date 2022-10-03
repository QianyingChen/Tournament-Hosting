package com.techelevator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( code = HttpStatus.NOT_FOUND, reason = "Join tournament request not found.")
public class RequestJoinTournamentNotFoundException extends Exception {
    public RequestJoinTournamentNotFoundException() {
        super( "Join tournament request not found.");
    }
}
