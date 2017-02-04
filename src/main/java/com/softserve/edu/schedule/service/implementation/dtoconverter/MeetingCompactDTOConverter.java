/* MeetingCompactDTOConverter 1.0 01/22/2017 */
package com.softserve.edu.schedule.service.implementation.dtoconverter;

import org.springframework.stereotype.Service;

import com.softserve.edu.schedule.dto.MeetingCompactDTO;
import com.softserve.edu.schedule.entity.Meeting;

/**
 * A class to provide conversion operations between MeetingForMailDTO and
 * Meeting entity.
 *
 * @version 1.0 22 January 2017
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
@Service
public class MeetingCompactDTOConverter {

    /**
     * Convert given Meeting object to MeetingForMailDTO object.
     *
     * @param meeting
     *            a Meeting object to convert.
     *
     * @return a MeetingsForRoomDTO object or null if given meeting is null.
     */
    public MeetingCompactDTO getDTO(final Meeting meeting) {
        if (meeting != null) {
            MeetingCompactDTO meetingForMailDTO = new MeetingCompactDTO();
            if (meeting.getSubject() != null) {
                meetingForMailDTO
                        .setSubjectName(meeting.getSubject().getName());
            }
            if (meeting.getRoom() != null) {
                meetingForMailDTO.setRoomName(meeting.getRoom().getName());
            }
            meetingForMailDTO.setDate(meeting.getDate());
            meetingForMailDTO.setStartTime(meeting.getStartTime());
            meetingForMailDTO.setEndTime(meeting.getEndTime());
            if (meeting.getOwner() != null) {
                meetingForMailDTO
                        .setOwnerFullName(meeting.getOwner().getFirstName()
                                + " " + meeting.getOwner().getLastName());
                meetingForMailDTO.setOwnerMail(meeting.getOwner().getMail());
            }
            meeting.getGroups().forEach(
                    e -> meetingForMailDTO.getGroupsNames().add(e.getName()));
            meetingForMailDTO.setStatus(meeting.getStatus());
            return meetingForMailDTO;
        }
        return null;
    }

}
