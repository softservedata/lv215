/* MeetingForRoomDTOConverter 1.0 01/22/2017 */
package com.softserve.edu.schedule.service.implementation.dtoconverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserve.edu.schedule.dao.MeetingDAO;
import com.softserve.edu.schedule.dto.MeetingForRoomDTO;
import com.softserve.edu.schedule.entity.Meeting;

/**
 * A class to provide conversion operations between MeetingForRoomDTO and Meeting entity.
 *
 * @version 1.0 22 January 2017
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
@Service
public class MeetingForRoomDTOConverter {

    /**
     * MeetingDAO example to provide database operations.
     */
    @Autowired
    private MeetingDAO meetingDAO;

    /**
     * Convert given MeetingsForRoomDTO object to Meeting object
     * 
     * @param meetingsForRoomDTO
     *            a MeetingsForRoomDTO object to convert.
     * 
     * @return a Meeting object.
     */
    public Meeting getEntity(final MeetingForRoomDTO meetingsForRoomDTO) {
        return meetingDAO.getById(meetingsForRoomDTO.getId());
    }

    /**
     * Convert given Meeting object to MeetingsForRoomDTO object.
     * 
     * @param meeting
     *            a Meeting object to convert.
     * 
     * @return a MeetingsForRoomDTO object or null if given @param room is null.
     */
    public MeetingForRoomDTO getDTO(final Meeting meeting) {
        if (meeting != null) {
            MeetingForRoomDTO meetingsForRoomDTO = new MeetingForRoomDTO();
            if (meeting.getId() != null) {
                meetingsForRoomDTO.setId(meeting.getId());
            }
            if (meeting.getOwner().getFirstName() != null
                    && meeting.getOwner().getLastName() != null) {
                meetingsForRoomDTO
                        .setOwnerFullName(meeting.getOwner().getFirstName()
                                + " " + meeting.getOwner().getLastName());
            } else if (meeting.getOwner().getFirstName() != null) {
                meetingsForRoomDTO
                        .setOwnerFullName(meeting.getOwner().getFirstName());
            } else {
                meetingsForRoomDTO
                        .setOwnerFullName(meeting.getOwner().getLastName());
            }

            if (meeting.getGroups() != null) {
                meeting.getGroups().forEach(e -> meetingsForRoomDTO
                        .getGroupsNames().add(e.getName()));
            }

            if (meeting.getSubject() != null) {
                meetingsForRoomDTO
                        .setSubjectName(meeting.getSubject().getName());
            }

            if (meeting.getDate() != null) {
                meetingsForRoomDTO.setDate(meeting.getDate());
            }
            if (meeting.getStartTime() != null) {
                meetingsForRoomDTO.setStartTime(meeting.getStartTime());
            }
            if (meeting.getEndTime() != null) {
                meetingsForRoomDTO.setEndTime(meeting.getEndTime());
            }
            if (meeting.getStatus() != null) {
                meetingsForRoomDTO.setStatus(meeting.getStatus());
            }
            return meetingsForRoomDTO;
        }
        return null;
    }

}
