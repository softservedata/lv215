/* MeetingToMeetingHistoryConverter 1.0 01/17/2017 */
package com.softserve.edu.schedule.service.implementation.dtoconverter;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.softserve.edu.schedule.entity.Meeting;
import com.softserve.edu.schedule.entity.MeetingHistory;

/**
 * A class to provide conversion operations between Meeting entity and
 * MeetingHistory entity.
 *
 * @version 1.0 22 02 2017
 *
 * @author Bohdan Melnyk
 *
 * @since 1.8
 */
@Component
public class MeetingToMeetingHistoryConverter {
    
    /** Converts given Meeting entity to the MeetingHistory entity.
     * @param meeting
     * @return
     */
    public MeetingHistory convertMeetingToMeetingHistory(
            final Meeting meeting) {
        if (meeting != null) {
            MeetingHistory meetingHistory = new MeetingHistory();
            meetingHistory.setIdMeeting(Long.toString(meeting.getId()));
            meetingHistory.setSubject(meeting.getSubject().getName());
            meetingHistory.setRoom(meeting.getRoom().getName());
            meetingHistory
                    .setLocation(meeting.getRoom().getLocation().getName());
            meetingHistory
                    .setAddress(meeting.getRoom().getLocation().getAddress());
            meetingHistory.setDate(meeting.getDate());
            meetingHistory.setStartTime(meeting.getStartTime());
            meetingHistory.setEndTime(meeting.getEndTime());
            meetingHistory.setGroups(String.join(", ",
                    meeting.getGroups().stream().map(arg0 -> arg0.getName())
                            .collect(Collectors.toList())));
            meetingHistory.setOwner(meeting.getOwner().getFirstName() + " "
                    + meeting.getOwner().getLastName());
            meetingHistory.setDescription(meeting.getDescription());
            return meetingHistory;
        }
        return null;
    }
}
