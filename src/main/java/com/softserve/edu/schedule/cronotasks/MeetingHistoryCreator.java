package com.softserve.edu.schedule.cronotasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.edu.schedule.dao.MeetingDAO;
import com.softserve.edu.schedule.service.MeetingHistoryService;

@Component
public class MeetingHistoryCreator {

    @Autowired
    private MeetingDAO meetingDAO;

    @Autowired
    private MeetingHistoryService meetingHistoryService;

    @Scheduled(cron = "0 0 1 * * *")
    @Transactional
    public void createMeetingHistory() {
        // TODO to delete
        System.out.println("CreateMeetingHistory working ... ");
        meetingDAO.getPastNotArchivedMeetings()
                .forEach(e -> meetingHistoryService.backup(e));
    }
}
