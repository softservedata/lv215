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
    List<Meeting> getMeetingPageWithFilter(MeetingFilter meetingFilter,
            Paginator meetingPaginator);

    /**
     * Count meeting entities in the database with specified predicate.
     *
     * @param meetingFilter
     *            a filter to apply.
     *
     * @return Count of the meeting entities in the database with specified
     *         predicate.
     */
    Long getCountOfMeetingsWithFilter(MeetingFilter meetingFilter);

    /**
     * Delete Meeting by id.
     *
     * @param id
     *            id.
     */
    void deleteById(Long id);

    /**
     * For the given Meeting id changes meeting status for given MeetingStatus.
     *
     * @param id
     *            Id of the meeting.
     * @param meetingStatus
     *            New meeting status.
     */
    void changeMeetingStatus(Long id, MeetingStatus meetingStatus);

    /**
     * Gives MeetingStatus by given String name.
     *
     * @param status
     *            name of status
     * @return MeetingStatus object.
     */
    MeetingStatus getStatusbyString(String status);

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
    List<Meeting> dublicatesOfGivenFields(String subjectName, String ownerName,
            String roomName, LocalDate localDate, LocalTime localTime);

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
    List<Meeting> getMeetingsInIntervalByRoomId(Long roomId,
            LocalDate startDate, LocalDate endDate);

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
    List<Meeting> getMeetingsInIntervalByUserId(Long userId,
            LocalDate startDate, LocalDate endDate);

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
    List<Meeting> getMeetingsInIntervalBySubjectId(Long subjectId,
            LocalDate startDate, LocalDate endDate);

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
    List<Meeting> getMeetingsInIntervalByGroupId(Long groupId,
            LocalDate startDate, LocalDate endDate);
}
