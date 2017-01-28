/* MeetingDTOConverter 1.0 01/17/2017 */

package com.softserve.edu.schedule.service.implementation.dtoconverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserve.edu.schedule.dao.RoomDAO;
import com.softserve.edu.schedule.dao.SubjectDAO;
import com.softserve.edu.schedule.dao.UserDAO;
import com.softserve.edu.schedule.dao.UserGroupDAO;

import com.softserve.edu.schedule.dto.MeetingDTO;
import com.softserve.edu.schedule.dto.RoomDTO;
import com.softserve.edu.schedule.dto.SubjectDTO;
import com.softserve.edu.schedule.dto.UserDTO;
import com.softserve.edu.schedule.dto.UserGroupDTO;
import com.softserve.edu.schedule.entity.Meeting;
import com.softserve.edu.schedule.entity.Room;
import com.softserve.edu.schedule.entity.Subject;
import com.softserve.edu.schedule.entity.User;

@Service
public class MeetingDTOConverter {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserGroupDAO userGroupDAO;

    @Autowired
    private SubjectDAO subjectDAO;

    @Autowired
    private RoomDAO roomDAO;

    public Meeting getEntity(final MeetingDTO meetingDTO) {
        if (meetingDTO != null) {
            Meeting meeting = new Meeting();
            if (meetingDTO.getId() != null) {
                meeting.setId(meetingDTO.getId());
            }
            if (meetingDTO.getSubject() != null) {
                meeting.setSubject(
                        subjectDAO.getById(meetingDTO.getSubject().getId()));
            }
            if (meetingDTO.getRoom() != null) {
                meeting.setRoom(roomDAO.getById(meetingDTO.getRoom().getId()));
            }
            if (meetingDTO.getDate() != null) {
                meeting.setDate(meetingDTO.getDate());
            }
            if (meetingDTO.getStartTime() != null) {
                meeting.setStartTime(meetingDTO.getStartTime());
            }
            if (meetingDTO.getEndTime() != null) {
                meeting.setEndTime(meetingDTO.getEndTime());
            }
            if (meetingDTO.getGroups() != null) {
                meetingDTO.getGroups().forEach(g -> meeting.getGroups()
                        .add(userGroupDAO.getById(g.getId())));
            }
            if (meetingDTO.getOwner() != null) {
                meeting.setOwner(
                        userDAO.getById(meetingDTO.getOwner().getId()));
            }
            if (meetingDTO.getStatus() != null) {
                meeting.setStatus(meetingDTO.getStatus());
            }
            if (meetingDTO.getLevel() != null) {
                meeting.setLevel(meetingDTO.getLevel());
            }
            if (meetingDTO.getDescription() != null) {
                meeting.setDescription(meetingDTO.getDescription());
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
            if (meeting.getSubject() != null) {
                Subject subject = meeting.getSubject();
                SubjectDTO subjectDTO = new SubjectDTO();
                subjectDTO.setId(subject.getId());
                subjectDTO.setName(subject.getName());
                meetingDTO.setSubject(subjectDTO);
            }
            if (meeting.getRoom() != null) {
                Room room = meeting.getRoom();
                RoomDTO roomDTO = new RoomDTO();
                roomDTO.setId(room.getId());
                roomDTO.setName(room.getName());
                meetingDTO.setRoom(roomDTO);
            }
            if (meeting.getDate() != null) {
                meetingDTO.setDate(meeting.getDate());
            }
            if (meeting.getStartTime() != null) {
                meetingDTO.setStartTime(meeting.getStartTime());
            }
            if (meeting.getEndTime() != null) {
                meetingDTO.setEndTime(meeting.getEndTime());
            }
            if (meeting.getGroups() != null) {
                meeting.getGroups().forEach(e -> {
                    UserGroupDTO userGroupDTO = new UserGroupDTO();
                    userGroupDTO.setId(e.getId());
                    userGroupDTO.setName(e.getName());
                    meetingDTO.getGroups().add(userGroupDTO);
                });
            }
            if (meeting.getOwner() != null) {
                User user = meeting.getOwner();
                UserDTO userDTO = new UserDTO();
                userDTO.setId(user.getId());
                userDTO.setFirstName(user.getFirstName());
                userDTO.setLastName(user.getLastName());
                meetingDTO.setOwner(userDTO);
            }
            if (meeting.getStatus() != null) {
                meetingDTO.setStatus(meeting.getStatus());
            }
            if (meeting.getLevel() != null) {
                meetingDTO.setLevel(meeting.getLevel());
            }
            if (meeting.getDescription() != null) {
                meetingDTO.setDescription(meeting.getDescription());
            }
            return meetingDTO;
        }
        return null;
    }

}
