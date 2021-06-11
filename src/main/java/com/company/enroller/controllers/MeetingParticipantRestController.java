package com.company.enroller.controllers;

import com.company.enroller.model.MeetingParticipant;
import com.company.enroller.persistence.MeetingParticipantService;
import com.company.enroller.persistence.MeetingService;
import com.company.enroller.persistence.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/meetingParticipant")
public class MeetingParticipantRestController {

    @Autowired
    MeetingParticipantService meetingParticipantService;
    @Autowired
    MeetingService meetingService;
    @Autowired
    ParticipantService participantService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> getMeetingsParticipant (){
        Collection<MeetingParticipant> meetings = meetingParticipantService.getAll();
        return new ResponseEntity<Collection<MeetingParticipant>>(meetings,HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getMeeting(@PathVariable("id")int id){
        Collection<MeetingParticipant> meetingParticipant = meetingParticipantService.findByMeetingId(id);
        if (meetingParticipant == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Collection<MeetingParticipant>>(meetingParticipant, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> registerMeetingParticipant(@RequestBody MeetingParticipant meetingParticipant) {
        // sprawdzamy czy uzytkownik istnieje
        if (participantService.findByLogin(meetingParticipant.getParticipantLogin()) == null) {
            return new ResponseEntity<String>(
                    "Unable to add. Participant with login '" + meetingParticipant.getParticipantLogin() + "' not exists",
                    HttpStatus.CONFLICT);
        }
        // czy spotkanie istieje
        if (meetingService.findById(meetingParticipant.getMeetingId()) == null) {
            return new ResponseEntity<String>(
                    "Unable to add. Meeting with id '" + meetingParticipant.getMeetingId() + "' not exists",
                    HttpStatus.CONFLICT);
        }

        // czy jest już dodany
        if (meetingParticipantService.findById(meetingParticipant.getId()) != null){
            return new ResponseEntity<String>(
                    "Unable to add. MeetingParticipant with id '" + meetingParticipant.getId() + "' is already exists",
                    HttpStatus.CONFLICT);
        }

        meetingParticipantService.add(meetingParticipant);
        return new ResponseEntity<MeetingParticipant>(meetingParticipant, HttpStatus.CREATED);
    }



}
