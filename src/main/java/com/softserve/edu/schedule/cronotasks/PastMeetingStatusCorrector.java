/* PastMeetingStatusCorrector 1.0 02/03/2017 */
package com.softserve.edu.schedule.cronotasks;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.edu.schedule.config.IgnoreDuringScan;
import com.softserve.edu.schedule.dao.MeetingDAO;
import com.softserve.edu.schedule.entity.Meeting;
import com.softserve.edu.schedule.entity.MeetingStatus;

/**
 * PastMeetingStatusCorrector task to correct past meetings statuses.
 *
 * @version 1.0 03 February 2017
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
@Component
@IgnoreDuringScan
public class PastMeetingStatusCorrector {

	/**
	 * MeetingDAO example to provide database operations.
	 */
	@Autowired
	private MeetingDAO meetingDAO;

	/**
	 * Scheduling task to correct past meetings statuses.
	 *
	 */
	@Scheduled(cron = "0 0 * * * *")
	@Transactional
	public void correctMeetingStatuses() {
		List<Meeting> pastMeetings = meetingDAO.getUnfinishedPastMeetings();
		pastMeetings.forEach(e -> {
			if (e.getStatus().equals(MeetingStatus.APPROVED)) {
				e.setStatus(MeetingStatus.FINISHED);
			} else {
				meetingDAO.deleteById(e.getId());
			}
		});
	}

}
