/* PastMeetingStatusCorrector 1.0 22/02/2017 */
package com.softserve.edu.schedule.cronotasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.edu.schedule.dao.MeetingDAO;
import com.softserve.edu.schedule.service.MeetingHistoryService;

/**
 * MeetingHistoryCreator task to correct past meetings statuses and them to MeetingHistory table.
 *
 * @version 1.0 22 02 2017.
 *
 * @author Lv-215.
 *
 * @since 1.8
 */
@Component
public class MeetingHistoryCreator {

    /**
     * MeetingDAO example to provide database operations.
     */
    @Autowired
    private MeetingDAO meetingDAO;

    /**
     * MeetingHistoryService example to provide database operations.
     */
    @Autowired
    private MeetingHistoryService meetingHistoryService;
    
    /**
     * Scheduling task to correct past meetings statuses and them to MeetingHistory table.
     *
     */
    @Scheduled(cron = "0 0 1 * * *")
    @Transactional
    public void createMeetingHistory() {
        // TODO to delete
        System.out.println("CreateMeetingHistory working ... ");
        meetingDAO.getPastNotArchivedMeetings()
                .forEach(e -> meetingHistoryService.backup(e));
    }
}
