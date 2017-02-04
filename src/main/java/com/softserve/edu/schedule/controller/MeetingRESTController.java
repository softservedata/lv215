package com.softserve.edu.schedule.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.softserve.edu.schedule.dto.MeetingForCalendarDTO;
import com.softserve.edu.schedule.service.MeetingService;

@RestController
public class MeetingRESTController {

    @Autowired
    MeetingService meetingService;

    @RequestMapping(value = "/meetings/rest", method = RequestMethod.GET)
    public ResponseEntity<List<MeetingForCalendarDTO>> getMeetingsInInterval(
            @RequestParam("start") String start,
            @RequestParam("end") String end) {
        List<MeetingForCalendarDTO> meetings = meetingService
                .getMeetingsInInterval(start, end);
        if (meetings.isEmpty()) {
            return new ResponseEntity<List<MeetingForCalendarDTO>>(
                    HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<MeetingForCalendarDTO>>(meetings,
                HttpStatus.OK);
    }
}
