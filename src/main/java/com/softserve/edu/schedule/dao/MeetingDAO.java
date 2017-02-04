/*
 * Interface for Meetings Data Access Object.
 *
 * Version 03.01.17
 *
 * 03.01.17
 *
 * Create by Softserve.accademy
 */
package com.softserve.edu.schedule.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.softserve.edu.schedule.dto.filter.MeetingFilter;
import com.softserve.edu.schedule.dto.filter.Paginator;
import com.softserve.edu.schedule.entity.Meeting;
import com.softserve.edu.schedule.entity.MeetingStatus;

/**
 * This interface for managing Meetings DAO implementation. It extends ReadDAO
 * interface.
 * 
 * @version 1.0 12.12.2016
 * @author IT Academy
 */
public interface MeetingDAO extends CrudDAO<Meeting> {

    public List<Meeting> getMeetingPageWithFilter(
            final MeetingFilter meetingFilter,
            final Paginator meetingPaginator);

    /**
     * Delete Meeting by id.
     * 
     * @param id
     *            id.
     */
    public void deleteById(Long id);

    /**
     * For the given Meeting id changes meeting status for given MeetingStatus.
     * 
     * @param id
     *            Id of the meeting.
     * @param meetingStatus
     *            New meeting status.
     */
    public void changeMeetingStatus(final Long id,
            final MeetingStatus meetingStatus);

    public MeetingStatus getStatusbyString(final String status);

    public List<Meeting> dublicatesOfGivenFields(final String subjectName,
            final String OwnerName, final String roomName,
            final LocalDate localDate, final LocalTime localTime);

    /**
     * Find all meetings in the DB by given date and roomId.
     *
     * @author Petro Zelyonka
     *
     * @param roomId
     *            room id for find meetings
     * @param date
     *            date for find meetings
     *
     * @return List of the Meeting objects.
     */
    List<Meeting> getMeetingsByRoomIdAndDate(Long roomId, LocalDate date);

    /**
     * Find all meetings in the DB which date and time are in past and status
     * not FINISHED.
     *
     * @author Petro Zelyonka
     *
     * @return List of the Meeting objects.
     */
    List<Meeting> getUnfinishedPastMeetings();

    /**
     * Find all approved meetings in the DB by given roomId, date, start and end
     * time.
     *
     * @author Petro Zelyonka
     *
     * @param roomId
     *            room id for find meetings
     * @param date
     *            date for find meetings
     * @param startTime
     *            start time for find meetings
     * @param endTime
     *            end time for find meetings
     *
     * @return List of the Meeting objects.
     */
    List<Meeting> getApprovedMeetingsByRoomIdAndTime(Long roomId,
            LocalDate date, LocalTime startTime, LocalTime endTime);

}
