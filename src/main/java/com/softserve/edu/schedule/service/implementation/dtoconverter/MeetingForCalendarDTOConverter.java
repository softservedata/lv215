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

    private String getMeetingColor(MeetingStatus status) {
        if (status.equals(MeetingStatus.APPROVED)) {
            return "blue";
        } else if (status.equals(MeetingStatus.FINISHED)) {
            return "grey";
        }
        return "red";
    }

    private String getFullTime(LocalDate date, LocalTime startTime) {
        LocalDateTime time = LocalDateTime.of(date, startTime);
        return time.toString();
    }

    private String getMeetingTitle(Meeting meeting) {
        StringBuffer title = new StringBuffer();
        if (meeting.getSubject() != null) {
            title.append(meeting.getSubject().getName());
            title.append("\n");
        }
        if (meeting.getRoom() != null) {
            title.append(meeting.getRoom().getName());
            title.append("\n");
        }
        if (meeting.getOwner() != null) {
            title.append(meeting.getOwner().getFirstName());
            title.append(" ");
            title.append(meeting.getOwner().getLastName());
            title.append("\n");
        }
        meeting.getGroups().forEach(e -> {
            title.append(e.getName());
            title.append("\n");
        });

        return title.toString();
    }

}
