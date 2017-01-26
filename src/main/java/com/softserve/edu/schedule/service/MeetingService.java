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
import com.softserve.edu.schedule.dto.RoomDTO;
import com.softserve.edu.schedule.dto.SubjectDTO;
import com.softserve.edu.schedule.dto.UserDTO;
import com.softserve.edu.schedule.dto.UserGroupDTO;
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

    public List<MeetingDTO> getMeetingPageWithFilter(final MeetingFilter meetingFilter,
            final Paginator roomPaginator);
    
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

    /*
     * Additional methods for doing the SEARCH operation but direct in DB.
     */

    /**
     * Find all meetings in the DB by given description.
     * 
     * @param description
     *            our description for meeting.
     * @return List<Meeting> returns list.
     */
    public List<MeetingDTO> searchByDescription(final MeetingDTO meetingDTO);

    /**
     * Find all meetings in the DB by given subject.
     * 
     * @param subject
     *            our subject for meeting.
     * @return List<Meeting> returns list.
     */
    public List<MeetingDTO> searchBySubject(final SubjectDTO subjectDTO);

    /**
     * Find all meetings in the DB by given Owner.
     * 
     * @param user
     *            our user for meeting.
     * @return List<Meeting> returns list.
     */
    public List<MeetingDTO> searchByOwner(final UserDTO userDTO);

    /**
     * Find all meetings in the DB by given Room.
     * 
     * @param room
     *            our room for meeting.
     * @return List<Meeting> list.
     */
    public List<MeetingDTO> searchByRoom(final RoomDTO roomDTO);

    /**
     * Find all meetings in the DB by given date.
     * 
     * @param date
     *            our date for meeting.
     * @return List<Meeting> list.
     */
    public List<MeetingDTO> searchByDate(final LocalDate date);

    /**
     * Find all meetings in the DB by given userGroup.
     * 
     * @param userGroup
     *            our userGroup for meeting.
     * @return List<Meeting>
     */
    public List<MeetingDTO> searchByUserGroup(final UserGroupDTO userGroupDTO);

    /**
     * Find all meetings in the DB by given userGroups.
     * 
     * @param userGroups
     *            our list of userGroups for meeting.
     * @return List<Meeting>
     */
    public List<MeetingDTO> searchByUserGroups(
            final List<UserGroupDTO> userGroups);

    /**
     * Find all meetings in the DB by given level.
     * 
     * @param level
     *            of the meeting.
     * @return List<Meeting>
     */
    public List<MeetingDTO> searchByLevel(final MeetingDTO meetingDTO);

    /**
     * Find all meetings in the DB by given meetingStatus.
     * 
     * @param meetingStatus
     *            of the meeting.
     * @return List<Meeting>
     */
    public List<MeetingDTO> searchByStatus(final MeetingDTO meetingDTO);

    /*
     * Additional methods for doing the SEARCH operation but direct in DB.
     */

    /**
     * Sort existent list of meetings by Subject name.
     * 
     * @param order
     *            parameter for sorting (ASC DESC)
     * @return List of sorted meetings.
     */
    public List<MeetingDTO> sortBySubject(final Order order);

    /**
     * Sort existent list of meetings by Owner last name.
     * 
     * @param order
     *            parameter for sorting (ASC DESC)
     * @return List of sorted meetings.
     */
    public List<MeetingDTO> sortByOwner(final Order order);

    /**
     * Sort existent list of meetings by Room name.
     * 
     * @param order
     *            parameter for sorting (ASC DESC)
     * @return List of sorted meetings.
     */
    public List<MeetingDTO> sortByRoom(final Order order);

    /**
     * Sort existent list of meetings by Description.
     * 
     * @param order
     *            parameter for sorting (ASC DESC)
     * @return List of sorted meetings.
     */
    public List<MeetingDTO> sortByDescription(final Order order);

    /**
     * Sort existent list of meetings by level.
     * 
     * @param order
     *            parameter for sorting (ASC DESC)
     * @return List of sorted meetings.
     */
    public List<MeetingDTO> sortByLevel(final Order order);

    /**
     * Sort existent list of meetings by status.
     * 
     * @param order
     *            parameter for sorting (ASC DESC)
     * @return List of sorted meetings.
     */
    public List<MeetingDTO> sortByStatus(final Order order);

    /**
     * Change existing status of the meeting to given new meeting status.
     * 
     * @param id Meeting, in which the status will be changed.
     * 
     * @param meetingStatus New meeting status.
     */
    public void changeMeetingStatus(final Long id,
            final MeetingStatus meetingStatus);


    /**
     * 
     * Find all meetings in the DB by given date and roomId.
=======
    /*
     * Find all meetings in the DB by given date and roomId.
     * 
     * @author Petro Zelyonka
>>>>>>> 16056cdee5ab399e1bbba4433adbcce1ccc387bc
     * 
     * @param roomId room id for find meetings
     * 
     * @param date date for find meetings
     * 
     * @return List of the MeetingCompactDTO objects.
     */

    public List<MeetingCompactDTO> getMeetingsByRoomIDAndDate(Long roomId,
            LocalDate date);

}
