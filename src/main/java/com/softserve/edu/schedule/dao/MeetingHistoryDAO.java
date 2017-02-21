package com.softserve.edu.schedule.dao;

import java.util.List;

import com.softserve.edu.schedule.entity.MeetingHistory;

public interface MeetingHistoryDAO extends CrudDAO<MeetingHistory> {

    /**
     * Returns meeting from MeetingHistory table by given id.
     * 
     * @param idMeeting
     *            id of the meeting, that will be returned.
     * @return List<MeetingHistory>.
     */
    public List<MeetingHistory> getMeetingHistoryByIdMeeting(
            final String idMeeting);

    /**
     * Returns all meeting from MeetingHistory table.
     * 
     * @param idMeeting
     *            id of the meeting, that will be returned.
     * @return List<MeetingHistory>.
     */
    public List<MeetingHistory> getAllMeetingHistory();
}
