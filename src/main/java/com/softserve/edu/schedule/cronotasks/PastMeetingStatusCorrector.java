package com.softserve.edu.schedule.cronotasks;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.edu.schedule.dao.MeetingDAO;
import com.softserve.edu.schedule.entity.Meeting;

@Component
public class PastMeetingStatusCorrector {

    @Autowired
    MeetingDAO meetingDAO;

    @Scheduled(cron = "0 0 * * * *")
    @Transactional
    public void correctMeetingStatuses() {
        List<Meeting> pastMeetings = meetingDAO.getUnfinishedPastMeetings();
    }
}
