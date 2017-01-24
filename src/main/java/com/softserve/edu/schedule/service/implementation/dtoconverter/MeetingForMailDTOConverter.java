/* MeetingForMailDTOConverter 1.0 01/22/2017 */
package com.softserve.edu.schedule.service.implementation.dtoconverter;

import org.springframework.stereotype.Service;

import com.softserve.edu.schedule.dto.MeetingForMailDTO;
import com.softserve.edu.schedule.entity.Meeting;

/**
 * A class to provide conversion operations between MeetingForMailDTO and Meeting entity.
 *
 * @version 1.0 22 January 2017
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
@Service
public class MeetingForMailDTOConverter {

    /**
     * Convert given Meeting object to MeetingForMailDTO object.
     * 
     * @param meeting
     *            a Meeting object to convert.
     * 
     * @return a MeetingsForRoomDTO object or null if given @param room is null.
     */
    public MeetingForMailDTO getDTO(final Meeting meeting) {
        if (meeting != null) {
            MeetingForMailDTO meetingForMailDTO = new MeetingForMailDTO();
            if (meeting.getSubject() != null
                    && meeting.getSubject().getName() != null) {
                meetingForMailDTO
                        .setSubjectName(meeting.getSubject().getName());
            }
            if (meeting.getRoom() != null
                    && meeting.getRoom().getName() != null) {
                meetingForMailDTO.setRoomName(meeting.getRoom().getName());
            }
            if (meeting.getDate() != null) {
                meetingForMailDTO.setDate(meeting.getDate());
            }
            if (meeting.getStartTime() != null) {
                meetingForMailDTO.setStartTime(meeting.getStartTime());
            }
            if (meeting.getEndTime() != null) {
                meetingForMailDTO.setEndTime(meeting.getEndTime());
            }

            if (meeting.getOwner() != null) {
                if (meeting.getOwner().getFirstName() != null
                        && meeting.getOwner().getLastName() != null) {
                    meetingForMailDTO
                            .setOwnerFullName(meeting.getOwner().getFirstName()
                                    + " " + meeting.getOwner().getLastName());
                } else if (meeting.getOwner().getFirstName() != null) {
                    meetingForMailDTO.setOwnerFullName(
                            meeting.getOwner().getFirstName());
                } else {
                    meetingForMailDTO
                            .setOwnerFullName(meeting.getOwner().getLastName());
                }
                if (meeting.getOwner().getMail() != null) {
                    meetingForMailDTO
                            .setOwnerMail(meeting.getOwner().getMail());
                }
            }

            return meetingForMailDTO;
        }
        return null;
    }

}
