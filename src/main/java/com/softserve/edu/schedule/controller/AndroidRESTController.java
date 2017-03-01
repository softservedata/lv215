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

import com.softserve.edu.schedule.controller.constants.AndroidRESTContrConst;
import com.softserve.edu.schedule.dto.LocationDTO;
import com.softserve.edu.schedule.dto.MeetingForCalendarDTO;
import com.softserve.edu.schedule.dto.RoomDTO;
import com.softserve.edu.schedule.dto.RoomEquipmentDTO;
import com.softserve.edu.schedule.dto.SubjectDTO;
import com.softserve.edu.schedule.dto.UserForAndroidDTO;
import com.softserve.edu.schedule.dto.UserGroupForAndroidDTO;
import com.softserve.edu.schedule.service.LocationService;
import com.softserve.edu.schedule.service.MeetingService;
import com.softserve.edu.schedule.service.RoomEquipmentService;
import com.softserve.edu.schedule.service.RoomService;
import com.softserve.edu.schedule.service.SubjectService;
import com.softserve.edu.schedule.service.UserGroupService;
import com.softserve.edu.schedule.service.UserService;

/**
 * A controller class to provide verification and data for Android application.
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
     * RoomService example to provide rooms list.
     */
    @Autowired
    private RoomService roomService;

    /**
     * UserGroupService example to provide group list.
     */
    @Autowired
    private UserGroupService userGroupService;

    /**
     * UserGroupService example to provide group list.
     */
    @Autowired
    private SubjectService subjectService;

    /**
     * LocationService example to provide location list.
     */
    @Autowired
    private LocationService locationService;

    /**
     * LocationService example to provide location list.
     */
    @Autowired
    private RoomEquipmentService roomEquipmentService;

    /**
     * Provide ResponseEntity with UserForAndroidDTO instance by given mail and
     * password.
     *
     * @param mail
     *            user Mail
     *
     * @param pass
     *            user password
     *
     * @return ResponseEntity with UserForAndroidDTO instance.
     */
    @RequestMapping(value = AndroidRESTContrConst.USER_VERIFY_URL_MAPPING,
            method = RequestMethod.GET)
    public ResponseEntity<UserForAndroidDTO> verifyUser(
            @RequestParam(AndroidRESTContrConst.USER_MAIL_REQUEST_PARAM) final String mail,
            @RequestParam(AndroidRESTContrConst.USER_PASS_REQUEST_PARAM) final String pass) {
        UserForAndroidDTO user = userService.getVerifiedUser(mail, pass);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
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
    @RequestMapping(
            value = AndroidRESTContrConst.REST_BY_USER_ANDROID_URL_MAPPING,
            method = RequestMethod.GET)
    public ResponseEntity<List<MeetingForCalendarDTO>> getMeetingsInIntervalByUserId(
            @RequestParam(AndroidRESTContrConst.START_REQUEST_PARAM) final String start,
            @RequestParam(AndroidRESTContrConst.END_REQUEST_PARAM) final String end,
            @RequestParam(AndroidRESTContrConst.USER_ID_REQUEST_PARAM) final String userId) {
        List<MeetingForCalendarDTO> meetings = meetingService
                .getMeetingsInIntervalByUserId(userId, start, end);
        if (meetings.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(meetings, HttpStatus.OK);
    }

    /**
     * Provide ResponseEntity with list of RoomDTO instances.
     *
     * @return ResponseEntity with list of RoomDTO instances.
     */
    @RequestMapping(
            value = AndroidRESTContrConst.REST_ROOMS_ANDROID_URL_MAPPING,
            method = RequestMethod.GET)
    public ResponseEntity<List<RoomDTO>> getRooms() {
        List<RoomDTO> rooms = roomService.getAllWithDetails();
        if (rooms.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

    /**
     * Provide ResponseEntity with list of UserGroupDTO instances.
     *
     * @return ResponseEntity with list of UserGroupDTO instances.
     */
    @RequestMapping(
            value = AndroidRESTContrConst.REST_GROUPS_ANDROID_URL_MAPPING,
            method = RequestMethod.GET)
    public ResponseEntity<List<UserGroupForAndroidDTO>> getGroups() {
        List<UserGroupForAndroidDTO> groups = userGroupService
                .getAllForAndroid();
        if (groups.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    /**
     * Provide ResponseEntity with list of SubjectDTO instances.
     *
     * @return ResponseEntity with list of SubjectDTO instances.
     */
    @RequestMapping(
            value = AndroidRESTContrConst.REST_SUBJECTS_ANDROID_URL_MAPPING,
            method = RequestMethod.GET)
    public ResponseEntity<List<SubjectDTO>> getSubjects() {
        List<SubjectDTO> subjects = subjectService.getAll();
        if (subjects.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(subjects, HttpStatus.OK);
    }

    /**
     * Provide ResponseEntity with list of LocationDTO instances.
     *
     * @return ResponseEntity with list of LocationDTO instances.
     */
    @RequestMapping(
            value = AndroidRESTContrConst.REST_LOCATIONS_ANDROID_URL_MAPPING,
            method = RequestMethod.GET)
    public ResponseEntity<List<LocationDTO>> getLocations() {
        List<LocationDTO> locations = locationService.getAll();
        if (locations.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(locations, HttpStatus.OK);
    }

    /**
     * Provide ResponseEntity with list of RoomEquipmentDTO instances.
     *
     * @return ResponseEntity with list of RoomEquipmentDTO instances.
     */
    @RequestMapping(
            value = AndroidRESTContrConst.REST_EQUIPMENTS_ANDROID_URL_MAPPING,
            method = RequestMethod.GET)
    public ResponseEntity<List<RoomEquipmentDTO>> getEquipments() {
        List<RoomEquipmentDTO> equipments = roomEquipmentService.getAll();
        if (equipments.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(equipments, HttpStatus.OK);
    }
}
