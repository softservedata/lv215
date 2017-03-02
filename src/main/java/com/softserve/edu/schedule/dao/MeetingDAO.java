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
 * @author Bohdan Melnyk
 */
public interface MeetingDAO extends CrudDAO<Meeting> {

    /**
     * Returns List of Meetings by given MeetingFilter and Paginator.
     *
     * @param meetingFilter
     *            a filter to apply.
     * @param meetingPaginator
     *            a paginator to apply.
     * @return List of Meetings
     */
    public List<Meeting> getMeetingPageWithFilter(
            final MeetingFilter meetingFilter,
            final Paginator meetingPaginator);

    /**
     * Count meeting entities in the database with specified predicate.
     *
     * @param meetingFilter
     *            a filter to apply.
     *
     * @return Count of the meeting entities in the database with specified
     *         predicate.
     */
    public Long getCountOfMeetingsWithFilter(final MeetingFilter meetingFilter);

    /**
     * Delete Meeting by id.
     *
     * @param id
     *            id.
     */
    public void deleteById(final Long id);

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

    /**
     * Gives MeetingStatus by given String name.
     *
     * @param status
     *            name of status
     * @return MeetingStatus object.
     */
    public MeetingStatus getStatusbyString(final String status);

    /**
     * Returns the List of MeetingDTO, that duplicates given Meetings fields.
     *
     * @param subjectName
     *            subject of meeting
     *
     * @param ownerName
     *            owner of meeting
     *
     * @param roomName
     *            room of meeting
     *
     * @param localDate
     *            date of meeting
     *
     * @param localTime
     *            start time of meeting
     *
     * @return List of Meeting
     */
    public List<Meeting> dublicatesOfGivenFields(final String subjectName,
            final String ownerName, final String roomName,
            final LocalDate localDate, final LocalTime localTime);

    /**
     * Find all meetings in the DB which date and time are in past and status
     * not FINISHED.
     *
     * @author Petro Zelyonka
     *
     * @return List of the Meeting objects.
     */
    public List<Meeting> getUnfinishedPastMeetings();

    /**
     * Find all meetings in the DB by given roomId, startDate and endDate.
     *
     * @author Petro Zelyonka
     *
     * @param roomId
     *            room id for find meetings
     *
     * @param startDate
     *            start date for find meetings
     *
     * @param endDate
     *            end date for find meetings
     *
     * @return List of the Meeting objects.
     */
    public List<Meeting> getMeetingsInIntervalByRoomId(final Long roomId,
            final LocalDate startDate, final LocalDate endDate);

    /**
     * Find all meetings in the DB by given userId, startDate and endDate.
     *
     * @author Petro Zelyonka
     *
     * @param userId
     *            user id for find meetings
     *
     * @param startDate
     *            start date for find meetings
     *
     * @param endDate
     *            end date for find meetings
     *
     * @return List of the Meeting objects.
     */
    public List<Meeting> getMeetingsInIntervalByUserId(final Long userId,
            final LocalDate startDate, final LocalDate endDate);

    /**
     * Find all meetings in the DB by given subjectId, startDate and endDate.
     *
     * @author Volodymyr Ped'ko
     *
     * @param subjectId
     *            subject id for find meetings
     *
     * @param startDate
     *            start date for find meetings
     *
     * @param endDate
     *            end date for find meetings
     *
     * @return List of the Meeting objects.
     */
    public List<Meeting> getMeetingsInIntervalBySubjectId(final Long subjectId,
            final LocalDate startDate, final LocalDate endDate);

    /**
     * Method that's used to get all meetings in specified interval for
     * specified group.
     *
     * @author Andriy Zhydenko
     *
     * @param groupId
     *            id of a group to find
     * @param startDate
     *            start time of meetings
     * @param endDate
     *            end time of meetings
     * @return list of meetings that will be held for specified group in
     *         specified time limits
     */
    public List<Meeting> getMeetingsInIntervalByGroupId(final Long groupId,
            final LocalDate startDate, final LocalDate endDate);

    /**
     * Returns all past not archived Meetings.
     * 
     * @return List of List<Meeting>
     */
    public List<Meeting> getPastNotArchivedMeetings();
}
