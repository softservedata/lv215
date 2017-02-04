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

import java.time.LocalDate;

import java.util.List;

import com.softserve.edu.schedule.dao.Order;

import com.softserve.edu.schedule.dto.MeetingDTO;
import com.softserve.edu.schedule.dto.filter.MeetingFilter;
import com.softserve.edu.schedule.dto.filter.Paginator;
import com.softserve.edu.schedule.dto.MeetingCompactDTO;
import com.softserve.edu.schedule.entity.MeetingStatus;

/**
 * This interface for managing Meetings Service.
 * 
 * @version 1.0 12.12.2016
 * @author IT Academy
 */

public interface MeetingService {

    /**
     * Saving Meeting in database.
     *
     * @param meeting
     *            - Meeting object
     */
    public void create(final MeetingDTO meetingDTO);

    /**
     * Return a Meeting object if found.
     *
     * @param id
     *            of Meeting transfer object
     * @return Meeting transfer object
     */
    public MeetingDTO getById(final Long id);

    public MeetingStatus getStatusbyString(final String status);

    /**
     * Read all meetings from DB.
     * 
     * @return List<Meeting> our meetings.
     */
    public List<MeetingDTO> getAll();

    /**
     * Update (replace) existence meeting in DB. If such meeting no exist in DB,
     * it will add it.
     * 
     * @param meeting
     *            our meeting.
     */
    public void update(final MeetingDTO meetingDTO);

    /**
     * Delete meeting from DB.
     * 
     * @param meeting
     *            our meeting.
     */
    public void delete(final MeetingDTO meetingDTO);

    /**
     * Delete meeting from DB by given id.
     * 
     * @param id
     *            our id of meeting.
     */
    public void deleteById(final Long id);

    public List<MeetingDTO> getMeetingPageWithFilter(
            final MeetingFilter meetingFilter, final Paginator roomPaginator);

    /**
     * Return a List of sorted Meeting transfer objects.
     *
     * @param field
     *            for sort
     * @param order
     *            - ASC or DESC
     * @return List of sorted Subject transfer objects
     */
    public List<MeetingDTO> sort(final String field, final Order order);

    /**
     * Return a List of searched Meeting transfer objects.
     *
     * @param field
     *            for search
     * @param pattern
     *            - input string
     * @return List of sorted Subject transfer objects
     */
    public List<MeetingDTO> search(final String field, final String pattern);

    /**
     * Change existing status of the meeting to given new meeting status.
     * 
     * @param id
     *            Meeting, in which the status will be changed.
     * 
     * @param meetingStatus
     *            New meeting status.
     */
    public void changeMeetingStatus(final Long id,
            final MeetingStatus meetingStatus);

    public List<MeetingDTO> DublicatesOfGivenDTO(final MeetingDTO meetingDTO);

    /**
     * Find all meetings in the DB by given date and roomId.
     *
     * @author Petro Zelyonka
     *
     * @param roomId
     *            room id for find meetings
     *
     * @param date
     *            date for find meetings
     *
     * @return List of the MeetingCompactDTO objects.
     */
    List<MeetingCompactDTO> getMeetingsByRoomIDAndDate(Long roomId,
            LocalDate date);

    /**
     * During creation checks which status can be given to the meeting depends
     * on room availability.
     *
     * @author Petro Zelyonka
     *
     * @param meetingDTO
     *            given meeting DTO
     *
     * @return correct MeetingStatus
     */
    MeetingStatus getMeetingStatusDuringCreation(MeetingDTO meetingDTO);
}
