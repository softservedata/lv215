/* MeetingRESTController 1.0 02/03/2017 */
package com.softserve.edu.schedule.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.softserve.edu.schedule.controller.constants.GeneralContrConst;
import com.softserve.edu.schedule.controller.constants.MeetingRESTContrConst;
import com.softserve.edu.schedule.dto.MeetingForCalendarDTO;
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
	@RequestMapping(value = MeetingRESTContrConst.REST_BY_ROOM_URL_MAPPING, method = RequestMethod.GET)
	public ResponseEntity<List<MeetingForCalendarDTO>> getMeetingsInIntervalByRoomId(
			@RequestParam(MeetingRESTContrConst.START_REQUEST_PARAM) final String start,
			@RequestParam(MeetingRESTContrConst.END_REQUEST_PARAM) final String end,
			@RequestParam(MeetingRESTContrConst.ROOM_ID_REQUEST_PARAM) final Long roomId) {
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
	@PreAuthorize(GeneralContrConst.CALENDAR_SHOW_PERMISSIONS)
	@RequestMapping(value = MeetingRESTContrConst.REST_BY_USER_URL_MAPPING, method = RequestMethod.GET)
	public ResponseEntity<List<MeetingForCalendarDTO>> getMeetingsInIntervalByUserId(
			@RequestParam(MeetingRESTContrConst.START_REQUEST_PARAM) final String start,
			@RequestParam(MeetingRESTContrConst.END_REQUEST_PARAM) final String end,
			@RequestParam(MeetingRESTContrConst.USER_ID_REQUEST_PARAM) final String userId) {
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
	@RequestMapping(value = MeetingRESTContrConst.REST_BY_SUBJECT_URL_MAPPING, method = RequestMethod.GET)
	public ResponseEntity<List<MeetingForCalendarDTO>> getMeetingsInIntervalBySubjectId(
			@RequestParam(MeetingRESTContrConst.START_REQUEST_PARAM) final String start,
			@RequestParam(MeetingRESTContrConst.END_REQUEST_PARAM) final String end,
			@RequestParam(MeetingRESTContrConst.SUBJECT_ID_REQUEST_PARAM) final Long subjectId) {
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
	@RequestMapping(value = MeetingRESTContrConst.REST_BY_GROUP_URL_MAPPING, method = RequestMethod.GET)
	public ResponseEntity<List<MeetingForCalendarDTO>> getMeetingsInIntervalByGroupId(
			@RequestParam(MeetingRESTContrConst.START_REQUEST_PARAM) final String start,
			@RequestParam(MeetingRESTContrConst.END_REQUEST_PARAM) final String end,
			@RequestParam(MeetingRESTContrConst.GROUP_ID_REQUEST_PARAM) final Long groupId) {
		List<MeetingForCalendarDTO> meetings = meetingService.getMeetingsInIntervalByGroupId(groupId, start, end);
		if (meetings.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(meetings, HttpStatus.OK);
	}

	/**
	 * Method returns data for building charts by given date interval and user
	 * id.
	 *
	 * @author Oleksandr Butyter
	 * @param userId
	 *            user id for find meetings
	 *
	 * @param start
	 *            start date for find meetings
	 *
	 * @param end
	 *            end date for find meetings
	 *
	 * @return ResponseEntity with data for building charts.
	 */
	@RequestMapping(value = MeetingRESTContrConst.REST_FOR_CHARTS_URL_MAPPING, method = RequestMethod.GET)
	public ResponseEntity<Map<String, Long>> getMeetingsForChart(
			@RequestParam(MeetingRESTContrConst.START_REQUEST_PARAM) final String start,
			@RequestParam(MeetingRESTContrConst.END_REQUEST_PARAM) final String end,
			@RequestParam(MeetingRESTContrConst.USER_ID_REQUEST_PARAM) final String userId) {
		Map<String, Long> meetings = meetingService.getMeetingsForChartInIntervalByUserId(userId, start, end);
		return new ResponseEntity<>(meetings, HttpStatus.OK);
	}

}
