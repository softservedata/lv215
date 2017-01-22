/* LocationDTOConverter 1.0 01/17/2017 */

package com.softserve.edu.schedule.service.implementation.dtoconverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserve.edu.schedule.dao.LocationDAO;
import com.softserve.edu.schedule.dao.MeetingDAO;
import com.softserve.edu.schedule.dao.RoomDAO;
import com.softserve.edu.schedule.dao.SubjectDAO;
import com.softserve.edu.schedule.dao.UserDAO;
import com.softserve.edu.schedule.dao.UserGroupDAO;
import com.softserve.edu.schedule.dto.LocationDTO;
import com.softserve.edu.schedule.dto.MeetingDTO;
import com.softserve.edu.schedule.dto.RoomDTO;
import com.softserve.edu.schedule.entity.Location;
import com.softserve.edu.schedule.entity.Meeting;
import com.softserve.edu.schedule.entity.Room;

public class MeetingDTOConverter {
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private MeetingDAO meetingDAO;

    @Autowired
    private SubjectDAO subjectDAO;

    @Autowired
    private RoomDAO roomDAO;

    @Autowired
    private UserGroupDAO userGoupDAO;

    @Autowired
    UserDTOConverter userDTOConverter;

    @Autowired
    SubjectDTOConverter subjectDTOConverter;

    @Autowired
    RoomDTOConverter roomDTOConverter;

    @Autowired
    UserGroupDTOForMeetingConverter userGroupDTOForMeetingConverter;

    public Meeting getEntity(final MeetingDTO meetingDTO) {
        if (meetingDTO != null) {
            Meeting meeting = new Meeting();
            if (meetingDTO.getId() != null) {
                meeting.setId(meetingDTO.getId());
            }
            if (meetingDTO.getDescription() != null) {
                meeting.setDescription(meetingDTO.getDescription());
            }
            if (meetingDTO.getSubject() != null) {
                meeting.setSubject(
                        subjectDAO.getById(meetingDTO.getSubject().getId()));
            }
            if (meetingDTO.getOwner() != null) {
                meeting.setOwner(
                        userDAO.getById(meetingDTO.getOwner().getId()));
            }

            if (meetingDTO.getRoom() != null) {
                meeting.setRoom(roomDAO.getById(meetingDTO.getRoom().getId()));
            }

            // TODO add converter for date !

            if (meetingDTO.getGroups() != null) {
                meetingDTO.getGroups().forEach(e -> meeting.getGroups()
                        .add(userGoupDAO.getById(e.getId())));
            }

            if (meetingDTO.getLevel() != null) {
                meeting.setLevel(meetingDTO.getLevel());
            }
            if (meetingDTO.getStatus() != null) {
                meeting.setStatus(meetingDTO.getStatus());
            }

            return meeting;
        }
        return null;
    }

    /**
     * Convert given Meeting object to MeetingDTO object.
     * 
     * @param meeting
     *            a Meeting object to convert.
     * 
     * @return a MeetingDTO object or null if given @param meeting is null.
     */
    public MeetingDTO getDTO(final Meeting meeting) {
        if (meeting != null) {
            MeetingDTO meetingDTO = new MeetingDTO();
            if (meeting.getId() != null) {
                meetingDTO.setId(meeting.getId());
            }
            if (meeting.getDescription() != null) {
                meetingDTO.setDescription(meeting.getDescription());
            }
            if (meeting.getOwner() != null) {
                meetingDTO
                        .setOwner(userDTOConverter.getDTO(meeting.getOwner()));
            }
            if (meeting.getSubject() != null) {
                meetingDTO.setSubject(
                        subjectDTOConverter.getDTO(meeting.getSubject()));
            }

            if (meeting.getRoom() != null) {
                meetingDTO.setRoom(roomDTOConverter.getDTO(meeting.getRoom()));
            }

            if (meeting.getGroups() != null) {
                meeting.getGroups().forEach(e -> meetingDTO.getGroups()
                        .add(userGroupDTOForMeetingConverter.getDTO(e)));
            }

            if (meeting.getLevel() != null) {
                meetingDTO.setLevel(meeting.getLevel());
            }
            if (meeting.getStatus() != null) {
                meetingDTO.setStatus(meeting.getStatus());
            }

            return meetingDTO;
        }
        return null;
    }

}
