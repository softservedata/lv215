package com.softserve.edu.schedule.dao;

import java.time.LocalDate;
import java.util.List;

import com.softserve.edu.schedule.entity.MeetingHistory;

public interface MeetingHistoryDAO extends CrudDAO<MeetingHistory> {

	/** Returns meeting from MeetingHistory table by given id.
	 * @param idMeeting
	 *                 id of the meeting, that will be returned.
	 * @return
	 *         List<MeetingHistory>.
	 */
	public List<MeetingHistory> getMeetingHistoryByIdMeeting(final String idMeeting);

	/** Returns meeting from MeetingHistory table by given date.
     * @param idMeeting
     *                 id of the meeting, that will be returned.
     * @return
     *         List<MeetingHistory>.
     */
	public List<MeetingHistory> getMeetingHistoryByStartAndEndDate(final LocalDate startDate, final LocalDate endDate);
	
	   /** Returns all meeting from MeetingHistory table.
     * @param idMeeting
     *                 id of the meeting, that will be returned.
     * @return
     *         List<MeetingHistory>.
     */
    public List<MeetingHistory> getAllMeetingHistory();
}
