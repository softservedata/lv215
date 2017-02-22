/*
 * Interface for MeetingsHistory Service.
 *
 * Version 03.01.17
 *
 * 03.01.17
 *
 * Create by Melnyk Bohdan
 */

package com.softserve.edu.schedule.service;

import java.util.List;

import com.softserve.edu.schedule.dto.MeetingHistoryDTO;
import com.softserve.edu.schedule.entity.Meeting;
import com.softserve.edu.schedule.entity.MeetingHistory;

/**
 * This interface for managing MeetingsHistory Service.
 *
 * @version 1.0 22.02.2017
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
    public List<MeetingHistoryDTO> getAll();

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
     * Creates new MeetingHistory in the MeetingHistory table by given
     * MeetingHostory.
     * 
     * @param meetingHistory
     *            meetingHistory to be created.
     */
    public void create(final MeetingHistory meetingHistory);

    /**
     * Backup given meeting to MeetinHistory table.
     * 
     * @param meeting
     *            meeting to backup.
     */
    public void backup(final Meeting meeting);
}
