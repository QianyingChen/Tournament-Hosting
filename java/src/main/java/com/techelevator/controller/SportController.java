package com.techelevator.controller;

import com.techelevator.dao.SportDao;
import com.techelevator.model.Sport;
import com.techelevator.model.Team;
import com.techelevator.model.Tournament;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sports")
@CrossOrigin
@PreAuthorize("isAuthenticated()")

public class SportController {
    private SportDao sportDao;
    // Alex: my idea for sports was that the App(we) would control the sports list
    // I don't think we want any users to be able to delete or update sports
    // maybe we could add a petition form to add more sports to the App if we have time?

    public SportController(SportDao sportDao) {
        this.sportDao = sportDao;
    }

    @PreAuthorize("permitAll()")
    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Sport> getAllSports(){
        return sportDao.getAllSports();}

    @RequestMapping(path = "/sport/{sportId}", method = RequestMethod.GET)
    public Sport getSportsBySportId(@PathVariable int sportId) {
        return sportDao.getSportsBySportId(sportId);
    }

    @RequestMapping(path = "/sportName/{sportName}", method = RequestMethod.GET)
    public Sport getSportsBySportName(@PathVariable String sportName) {
        return sportDao.getSportsBySportName(sportName);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping( path = "", method = RequestMethod.POST)
    public Sport createSport(@RequestBody Sport sport){
        return sportDao.createSport(sport);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path = "/update/{sportId}", method = RequestMethod.PUT)
    public void updateSport(@RequestBody Sport sport) {
        this.sportDao.updateSport(sport);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path = "/{sportId}", method = RequestMethod.DELETE)
    public void deleteSport (@PathVariable int sportId){
        sportDao.deleteSport(sportId);
    }

}
