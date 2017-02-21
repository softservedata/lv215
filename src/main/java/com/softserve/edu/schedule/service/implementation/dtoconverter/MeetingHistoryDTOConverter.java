/* MeetingHistoryDTOConverter 1.0 01/17/2017 */
package com.softserve.edu.schedule.service.implementation.dtoconverter;

import org.springframework.stereotype.Service;

import com.softserve.edu.schedule.dto.MeetingHistoryDTO;
import com.softserve.edu.schedule.entity.MeetingHistory;

/**
 * A class to provide conversion operations between MeetingHistoryDTO and
 * MeetingHistory. entity.
 *
 * @version 1.0 21 February 2017
 *
 * @author Bohdan Melnyk
 *
 * @since 1.8
 */
@Service
public class MeetingHistoryDTOConverter {

    /**
     * Convert given MeetingHistoryDTO object to MeetingHistory entity.
     * 
     * @param meetingHistoryDTO
     *            a MeetingHistoryDTO object to convert.
     * 
     * @return a MeetingHistory object or null if given @param meetingHistoryDTO
     *         is null.
     */
    public MeetingHistory getEntity(final MeetingHistoryDTO meetingHistoryDTO) {
        if (meetingHistoryDTO != null) {
            MeetingHistory meetingHistory = new MeetingHistory();
            meetingHistory.setId(meetingHistoryDTO.getId());
            meetingHistory.setIdMeeting(meetingHistoryDTO.getIdMeeting());
            meetingHistory.setSubject(meetingHistoryDTO.getSubject());
            meetingHistory.setRoom(meetingHistoryDTO.getRoom());
            meetingHistory.setLocation(meetingHistoryDTO.getLocation());
            meetingHistory.setAddress(meetingHistoryDTO.getAddress());
            meetingHistory.setDate(meetingHistoryDTO.getDate());
            meetingHistory.setStartTime(meetingHistoryDTO.getStartTime());
            meetingHistory.setEndTime(meetingHistoryDTO.getEndTime());
            meetingHistory.setGroups(meetingHistoryDTO.getGroups());
            meetingHistory.setOwner(meetingHistoryDTO.getOwner());
            meetingHistory.setDescription(meetingHistoryDTO.getDescription());
            return meetingHistory;
        }
        return null;
    }

    /**
     * Convert given MeetingHistory object to MeetingHistoryDTO object.
     * 
     * @param meetingHistory
     *            a MeetingHistory object to convert.
     * 
     * @return a MeetingHistoryDTO object or null if given @param meetingHistory
     *         is null.
     */
    public MeetingHistoryDTO getDTO(final MeetingHistory meetingHistory) {
        if (meetingHistory != null) {
            MeetingHistoryDTO meetingHistoryDTO = new MeetingHistoryDTO();
            meetingHistoryDTO.setId(meetingHistory.getId());
            meetingHistoryDTO.setIdMeeting(meetingHistory.getIdMeeting());
            meetingHistoryDTO.setSubject(meetingHistory.getSubject());
            meetingHistoryDTO.setRoom(meetingHistory.getRoom());
            meetingHistoryDTO.setLocation(meetingHistory.getLocation());
            meetingHistoryDTO.setAddress(meetingHistory.getAddress());
            meetingHistoryDTO.setDate(meetingHistory.getDate());
            meetingHistoryDTO.setStartTime(meetingHistory.getStartTime());
            meetingHistoryDTO.setEndTime(meetingHistory.getEndTime());
            meetingHistoryDTO.setGroups(meetingHistory.getGroups());
            meetingHistoryDTO.setOwner(meetingHistory.getOwner());
            meetingHistoryDTO.setDescription(meetingHistory.getDescription());
            return meetingHistoryDTO;
        }
        return null;
    }
}
