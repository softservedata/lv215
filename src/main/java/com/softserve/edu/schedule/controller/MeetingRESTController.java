/* MeetingRESTController 1.0 02/03/2017 */
package com.softserve.edu.schedule.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.softserve.edu.schedule.dto.MeetingForCalendarDTO;
import com.softserve.edu.schedule.dto.MeetingForChartDTO;
import com.softserve.edu.schedule.service.MeetingService;

/**
 * A controller class to provide REST service for calendar.
 *
 * @version 1.0 03 February 2017
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
@RestController
public class MeetingRESTController {

	/**
	 * MeetingService example to provide meetings list.
	 */
	@Autowired
	private MeetingService meetingService;

	/**
	 * Provide ResponseEntity with list of MeetingForCalendarDTO instances by
	 * given date interval and roomId.
	 *
	 * @param roomId
	 *            room id for find meetings
	 *
	 * @param start
	 *            start date for find meetings
	 *
	 * @param end
	 *            end date for find meetings
	 *
	 * @return ResponseEntity with list of MeetingForCalendarDTO instances.
	 */
	@RequestMapping(value = "/meetings/restByRoom", method = RequestMethod.GET)
	public ResponseEntity<List<MeetingForCalendarDTO>> getMeetingsInIntervalByRoomId(
			@RequestParam("start") final String start, @RequestParam("end") final String end,
			@RequestParam("roomId") final Long roomId) {
		List<MeetingForCalendarDTO> meetings = meetingService.getMeetingsInIntervalByRoomId(roomId, start, end);
		if (meetings.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(meetings, HttpStatus.OK);
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
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/meetings/restByUser", method = RequestMethod.GET)
	public ResponseEntity<List<MeetingForCalendarDTO>> getMeetingsInIntervalByUserId(
			@RequestParam("start") final String start, @RequestParam("end") final String end,
			@RequestParam("userId") final String userId) {
		List<MeetingForCalendarDTO> meetings = meetingService.getMeetingsInIntervalByUserId(userId, start, end);
		if (meetings.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(meetings, HttpStatus.OK);
	}

	/**
	 * Provide ResponseEntity with list of MeetingForCalendarDTO instances by
	 * given date interval and subjectId.
	 *
	 * @author Volodymyr Ped'ko
	 *
	 * @param subjectId
	 *            subject id for find meetings
	 *
	 * @param start
	 *            start date for find meetings
	 *
	 * @param end
	 *            end date for find meetings
	 *
	 * @return ResponseEntity with list of MeetingForCalendarDTO instances.
	 */
	@RequestMapping(value = "/meetings/restBySubject", method = RequestMethod.GET)
	public ResponseEntity<List<MeetingForCalendarDTO>> getMeetingsInIntervalBySubjectId(
			@RequestParam("start") final String start, @RequestParam("end") final String end,
			@RequestParam("subjectId") final Long subjectId) {
		List<MeetingForCalendarDTO> meetings = meetingService.getMeetingsInIntervalBySubjectId(subjectId, start, end);
		if (meetings.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(meetings, HttpStatus.OK);
	}

	/**
	 * Provide ResponseEntity with list of MeetingForCalendarDTO instances by
	 * given date interval and groupId.
	 *
	 * @author Andriy Zhydenko
	 *
	 * @param groupId
	 *            group id for find meetings
	 *
	 * @param start
	 *            start date for find meetings
	 *
	 * @param end
	 *            end date for find meetings
	 *
	 * @return ResponseEntity with list of MeetingForCalendarDTO instances.
	 */
	@RequestMapping(value = "/meetings/restByGroup", method = RequestMethod.GET)
	public ResponseEntity<List<MeetingForCalendarDTO>> getMeetingsInIntervalByGroupId(
			@RequestParam("start") final String start, @RequestParam("end") final String end,
			@RequestParam("groupId") final Long groupId) {
		List<MeetingForCalendarDTO> meetings = meetingService.getMeetingsInIntervalByGroupId(groupId, start, end);
		if (meetings.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(meetings, HttpStatus.OK);
	}

	@RequestMapping(value = "/meetings/restForChart", method = RequestMethod.GET)
	public ResponseEntity<List<MeetingForChartDTO>> getMeetingsForChart(@RequestParam("start") final String start,
			@RequestParam("end") final String end, @RequestParam("userId") final String userId) {
		List<MeetingForChartDTO> meetings = meetingService.getMeetingsForChartInIntervalByUserId(userId, start, end);
		if (meetings.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(meetings, HttpStatus.OK);

		/*
		 * @RequestMapping(value = "/meetings/restBy/{id}", method =
		 * RequestMethod.GET) public ResponseEntity<Map<String, Long>>
		 * getSomething(@PathVariable String id) { Map<String, Long> map = new
		 * HashMap<>(); map.put("Sasha", 32L); map.put("Katya", 16L); if
		 * (map.isEmpty()) { return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		 * } return new ResponseEntity<>(map, HttpStatus.OK);
		 */

	}
}
