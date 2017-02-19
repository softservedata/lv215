package com.softserve.edu.schedule.cronotasks;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.edu.schedule.dao.MeetingDAO;
import com.softserve.edu.schedule.dao.MeetingHistoryDAO;
import com.softserve.edu.schedule.entity.Meeting;
import com.softserve.edu.schedule.entity.MeetingHistory;

@Component
public class MeetingHistoryCreator {
	@Autowired
	private MeetingDAO meetingDAO;

	@Autowired
	private MeetingHistoryDAO meetingHistoryDAO;

	@Scheduled(cron = "0 0/5 * 1/1 * ?")
	@Transactional
	public void createMeetingHistory() {
		meetingDAO.getFinishedMeetings().stream()
				.filter(m -> meetingHistoryDAO.getMeetingHistoryByIdMeeting(Long.toString(m.getId())).isEmpty())
				.collect(Collectors.toList()).forEach(e -> meetingHistoryDAO.create(convertMeetingToMeetingHistory(e)));
	}

	public MeetingHistory convertMeetingToMeetingHistory(Meeting meeting) {
		if (meeting != null) {
			MeetingHistory meetingHistory = new MeetingHistory();
			meetingHistory.setIdMeeting(Long.toString(meeting.getId()));
			meetingHistory.setSubject(meeting.getSubject().getName());
			meetingHistory.setRoom(meeting.getRoom().getName());
			meetingHistory.setLocation(meeting.getRoom().getLocation().getName());
			meetingHistory.setAddress(meeting.getRoom().getLocation().getAddress());
			meetingHistory.setDate(meeting.getDate());
			meetingHistory.setStartTime(meeting.getStartTime());
			meetingHistory.setEndTime(meeting.getEndTime());
			meetingHistory.setGroups(String.join(", ",
					meeting.getGroups().stream().map(arg0 -> arg0.getName()).collect(Collectors.toList())));
			meetingHistory.setOwner(meeting.getOwner().getFirstName() + " " + meeting.getOwner().getLastName());
			meetingHistory.setDescription(meeting.getDescription());
			return meetingHistory;
		}
		return null;
	}
}
