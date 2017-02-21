package com.softserve.edu.schedule.service.implementation.dtoconverter;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.softserve.edu.schedule.entity.Meeting;
import com.softserve.edu.schedule.entity.MeetingHistory;

@Component
public class MeetingToMeetingHistory {
    public MeetingHistory convertMeetingToMeetingHistory(final Meeting meeting) {
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
