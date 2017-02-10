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
import com.softserve.edu.schedule.service.MeetingService;

@RestController
public class MeetingRESTController {

	@Autowired
	MeetingService meetingService;

	// not used at that time. delete before final build
	@RequestMapping(value = "/meetings/rest", method = RequestMethod.GET)
	public ResponseEntity<List<MeetingForCalendarDTO>> getMeetingsInInterval(@RequestParam("start") String start,
			@RequestParam("end") String end) {
		List<MeetingForCalendarDTO> meetings = meetingService.getMeetingsInInterval(start, end);
		if (meetings.isEmpty()) {
			return new ResponseEntity<List<MeetingForCalendarDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<MeetingForCalendarDTO>>(meetings, HttpStatus.OK);
	}

	@RequestMapping(value = "/meetings/restByRoom", method = RequestMethod.GET)
	public ResponseEntity<List<MeetingForCalendarDTO>> getMeetingsInIntervalByRoomId(
			@RequestParam("start") String start, @RequestParam("end") String end, @RequestParam("roomId") Long roomId) {
		List<MeetingForCalendarDTO> meetings = meetingService.getMeetingsInIntervalByRoomId(roomId, start, end);
		if (meetings.isEmpty()) {
			return new ResponseEntity<List<MeetingForCalendarDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<MeetingForCalendarDTO>>(meetings, HttpStatus.OK);
	}

	@RequestMapping(value = "/meetings/restBySubject", method = RequestMethod.GET)
	public ResponseEntity<List<MeetingForCalendarDTO>> getMeetingsInIntervalBySubjectId(
			@RequestParam("start") String start, @RequestParam("end") String end,
			@RequestParam("subjectId") Long subjectId) {
		List<MeetingForCalendarDTO> meetings = meetingService.getMeetingsInIntervalBySubjectId(subjectId, start, end);
		if (meetings.isEmpty()) {
			return new ResponseEntity<List<MeetingForCalendarDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<MeetingForCalendarDTO>>(meetings, HttpStatus.OK);
	}

	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/meetings/restByUser", method = RequestMethod.GET)
	public ResponseEntity<List<MeetingForCalendarDTO>> getMeetingsInIntervalByUserId(
			@RequestParam("start") String start, @RequestParam("end") String end,
			@RequestParam("userId") String userId) {
		List<MeetingForCalendarDTO> meetings = meetingService.getMeetingsInIntervalByUserId(userId, start, end);
		if (meetings.isEmpty()) {
			return new ResponseEntity<List<MeetingForCalendarDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<MeetingForCalendarDTO>>(meetings, HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR', 'ROLE_MODERATOR', 'ROLE_USER')")
	@RequestMapping("/meetings/restByAnyUser")
	public ResponseEntity<List<MeetingForCalendarDTO>> getMeetingsInIntervalByAnyUserId(
			@RequestParam("start") String start, @RequestParam("end") String end,
			@RequestParam("userId") String userId) {
		List<MeetingForCalendarDTO> meetings = meetingService.getMeetingsInIntervalByAnyUserId(userId, start, end);
		if (meetings.isEmpty()) {
			return new ResponseEntity<List<MeetingForCalendarDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<MeetingForCalendarDTO>>(meetings, HttpStatus.OK);
	}

	/**
	 * REST method that returns meetings for a calendar in specified interval
	 * and by group id.
	 * 
	 * @param start
	 *            Meeting start time.
	 * @param end
	 *            Meeting end time.
	 * @param groupId
	 *            Meeting group id.
	 * @return response entity of meetings
	 */
	@RequestMapping(value = "/meetings/restByGroup", method = RequestMethod.GET)
	public ResponseEntity<List<MeetingForCalendarDTO>> getMeetingsInIntervalByGroupId(
			@RequestParam("start") String start, @RequestParam("end") String end,
			@RequestParam("groupId") Long groupId) {
		List<MeetingForCalendarDTO> meetings = meetingService.getMeetingsInIntervalByGroupId(groupId, start, end);
		if (meetings.isEmpty()) {
			return new ResponseEntity<List<MeetingForCalendarDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<MeetingForCalendarDTO>>(meetings, HttpStatus.OK);
	}
}
