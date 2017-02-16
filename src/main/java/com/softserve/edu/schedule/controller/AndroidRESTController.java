/* AndroidRESTController 1.0 02/16/2017 */
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
import com.softserve.edu.schedule.dto.UserForAndroidDTO;
import com.softserve.edu.schedule.service.MeetingService;
import com.softserve.edu.schedule.service.UserService;

/**
 * A controller class to provide verification and meetings data for Android
 * application.
 *
 * @version 1.0 16 February 2017
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
@RestController
public class AndroidRESTController {

    /**
     * MeetingService example to provide meetings list.
     */
    @Autowired
    private MeetingService meetingService;

    /**
     * UserService example to provide user verification.
     */
    @Autowired
    private UserService userService;

    /**
     * Provide ResponseEntity with UserForAndroidDTO instance by given mail and password.
     *
     * @param mail
     *            user Mail
     *
     * @param pass
     *            user password
     *
     * @return ResponseEntity with UserForAndroidDTO instance.
     */
    @RequestMapping(value = "/userVerify", method = RequestMethod.GET)
    public ResponseEntity<UserForAndroidDTO> verifyUser(
            @RequestParam("userMail") final String mail,
            @RequestParam("userPass") final String pass) {
        UserForAndroidDTO user = userService.getVerifiedUser(mail, pass);
        if (user == null) {
            return new ResponseEntity<UserForAndroidDTO>(
                    HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<UserForAndroidDTO>(user, HttpStatus.OK);
    }

    /**
     * Provide ResponseEntity with list of MeetingForCalendarDTO instances by
     * given date interval and user id.
     *
     * @param userId
     *            user id for find meetings
     *
     * @param start
     *            start date for find meetings
     *
     * @param end
     *            end date for find meetings
     *
     * @return ResponseEntity with list of MeetingForCalendarDTO instances.
     */
    @RequestMapping(value = "/meetings/restByUserAndroid",
            method = RequestMethod.GET)
    public ResponseEntity<List<MeetingForCalendarDTO>> getMeetingsInIntervalByUserId(
            @RequestParam("start") final String start,
            @RequestParam("end") final String end,
            @RequestParam("userId") final String userId) {
        List<MeetingForCalendarDTO> meetings = meetingService
                .getMeetingsInIntervalByUserId(userId, start, end);
        if (meetings.isEmpty()) {
            return new ResponseEntity<List<MeetingForCalendarDTO>>(
                    HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<MeetingForCalendarDTO>>(meetings,
                HttpStatus.OK);
    }

}
