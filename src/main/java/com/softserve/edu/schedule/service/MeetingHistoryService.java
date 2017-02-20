/*
 * Interface for Meetings Service.
 *
 * Version 03.01.17
 *
 * 03.01.17
 *
 * Create by Melnyk Bohdan
 */

package com.softserve.edu.schedule.service;

import java.util.List;

import com.softserve.edu.schedule.entity.MeetingHistory;

/**
 * This interface for managing MeetingsHistory Service.
 *
 * @version 1.0 12.12.2016
 * @author Bohdan Melnyk
 */
public interface MeetingHistoryService {

    /**
     * Returns all meeting from MeetingHistory table.
     * 
     * @param idMeeting
     *            id of the meeting, that will be returned.
     * @return List<MeetingHistory>.
     */
    public List<MeetingHistory> getAll();
    
    /** Returns meeting from MeetingHistory table by given id.
     * @param idMeeting
     *                 id of the meeting, that will be returned.
     * @return
     *         List<MeetingHistory>.
     */
    public List<MeetingHistory> getMeetingHistoryByIdMeeting(
            final String idMeeting);
}
