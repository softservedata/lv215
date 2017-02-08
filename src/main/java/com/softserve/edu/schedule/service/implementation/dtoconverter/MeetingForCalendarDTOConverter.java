/* MeetingForCalendarDTOConverter 1.0 02/04/2017 */
package com.softserve.edu.schedule.service.implementation.dtoconverter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.stereotype.Service;

import com.softserve.edu.schedule.dto.MeetingForCalendarDTO;
import com.softserve.edu.schedule.entity.Meeting;
import com.softserve.edu.schedule.entity.MeetingStatus;

/**
 * A class to provide conversion operations between MeetingForCalendarDTO and
 * Meeting entity.
 *
 * @version 1.0 04 February 2017
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
@Service
public class MeetingForCalendarDTOConverter {

    /**
     * Color value for approved meetings in calendar.
     */
    private static final String APPROVED_MEETING_COLOR = "blue";

    /**
     * Color value for disapproved meetings in calendar.
     */
    private static final String DISAPPROVED_MEETING_COLOR = "red";

    /**
     * Color value for not approved meetings in calendar.
     */
    private static final String NOTAPPROVED_MEETING_COLOR = "grey";

    /**
     * Color value for finished meetings in calendar.
     */
    private static final String FINISHED_MEETING_COLOR = "yellow";

    /**
     * Empty string with single space character.
     */
    private static final String SPACE = " ";

    /**
     * Empty string with next line character.
     */
    private static final String NEXT_LINE = "\n";

    /**
     * Convert given Meeting object to MeetingForCalendarDTO object.
     *
     * @param meeting
     *            a Meeting object to convert.
     *
     * @return a MeetingForCalendarDTO object or null if given meeting is null.
     */
    public MeetingForCalendarDTO getDTO(final Meeting meeting) {
        if (meeting != null) {
            MeetingForCalendarDTO meetingForCalendarDTO = new MeetingForCalendarDTO();
            meetingForCalendarDTO.setId(meeting.getId().toString());

            meetingForCalendarDTO.setTitle(getMeetingTitle(meeting));

            meetingForCalendarDTO.setStart(
                    getFullTime(meeting.getDate(), meeting.getStartTime()));
            meetingForCalendarDTO.setStart(
                    getFullTime(meeting.getDate(), meeting.getEndTime()));

            meetingForCalendarDTO
                    .setUrl("/schedule/meetings/" + meeting.getId().toString());
            meetingForCalendarDTO
                    .setColor(getMeetingColor(meeting.getStatus()));

            return meetingForCalendarDTO;
        }
        return null;
    }

    /**
     * Determine meeting color in calendar depends on meeting status.
     *
     * @param status
     *            MeetingStatus for determining color.
     *
     * @return color name in String value.
     */
    private String getMeetingColor(MeetingStatus status) {
        if (status.equals(MeetingStatus.APPROVED)) {
            return APPROVED_MEETING_COLOR;
        }
        if (status.equals(MeetingStatus.FINISHED)) {
            return FINISHED_MEETING_COLOR;
        }
        if (status.equals(MeetingStatus.NOT_APPROVED)) {
            return NOTAPPROVED_MEETING_COLOR;
        }
        return DISAPPROVED_MEETING_COLOR;
    }

    /**
     * Convert separate date and time values in datetime string.
     *
     * @param date
     *            date to convert.
     * 
     * @param time
     *            time to convert
     *
     * @return datetime string.
     */
    private String getFullTime(LocalDate date, LocalTime startTime) {
        LocalDateTime time = LocalDateTime.of(date, startTime);
        return time.toString();
    }

    /**
     * Determine meeting title in calendar depends on meeting subject, owner,
     * room and participating groups.
     *
     * @param meeting
     *            Meeting for determining title.
     *
     * @return meeting title in String value.
     */
    private String getMeetingTitle(Meeting meeting) {
        StringBuffer title = new StringBuffer();
        if (meeting.getSubject() != null) {
            title.append(meeting.getSubject().getName());
            title.append(NEXT_LINE);
        }
        if (meeting.getRoom() != null) {
            title.append(meeting.getRoom().getName());
            title.append(NEXT_LINE);
        }
        if (meeting.getOwner() != null) {
            title.append(meeting.getOwner().getFirstName());
            title.append(SPACE);
            title.append(meeting.getOwner().getLastName());
            title.append(NEXT_LINE);
        }
        meeting.getGroups().forEach(e -> {
            title.append(e.getName());
            title.append(NEXT_LINE);
        });
        return title.toString();
    }

}
