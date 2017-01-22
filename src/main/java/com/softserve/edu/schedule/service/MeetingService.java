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
import java.time.LocalDateTime;
import java.util.List;

import com.softserve.edu.schedule.dao.Order;
import com.softserve.edu.schedule.dto.MeetingsForRoomDTO;
import com.softserve.edu.schedule.entity.Meeting;
import com.softserve.edu.schedule.entity.MeetingStatus;
import com.softserve.edu.schedule.entity.Room;
import com.softserve.edu.schedule.entity.Subject;
import com.softserve.edu.schedule.entity.User;
import com.softserve.edu.schedule.entity.UserGroup;

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
    public void create(final Meeting meeting);

    /**
     * Return a Meeting object if found.
     *
     * @param id
     *            of Meeting transfer object
     * @return Meeting transfer object
     */
    public Meeting getById(final Long id);

    /**
     * Read all meetings from DB.
     * 
     * @return List<Meeting> our meetings.
     */
    public List<Meeting> getAll();

    /**
     * Update (replace) existence meeting in DB. If such meeting no exist in DB,
     * it will add it.
     * 
     * @param meeting
     *            our meeting.
     */
    public void update(final Meeting meeting);

    /**
     * Delete meeting from DB.
     * 
     * @param meeting
     *            our meeting.
     */
    public void delete(final Meeting meeting);

    /**
     * Delete meeting from DB by given id.
     * 
     * @param id
     *            our id of meeting.
     */
    public void deleteById(final Long id);

    /**
     * Delete meeting from DB by given User. It means, that all meetings, which
     * was approved with owner User will be deleted.
     * 
     * @param user
     *            our owner for meeting.
     */
    public void deleteByOwner(final User user);

    /**
     * Delete meetings from DB by given Room. It means, that all meetings, which
     * was approved in this room will be deleted.
     * 
     * @param room
     *            our room for meeting.
     */
    public void deleteByRoom(final Room room);

    /**
     * Delete meetings from DB by given Date. It means, that all meetings, which
     * are planned at this Date will be deleted.
     * 
     * @param userGroup
     *            our userGroup for meeting.
     */
    public void deleteByDate(final LocalDateTime localDateTime);

    /**
     * Delete meetings from DB by given User Group. It means, that all meetings,
     * which consist only this User Group will be deleted.
     * 
     * @param userGroup
     *            our userGroup for meeting.
     */
    public void deleteMeetingByUserGroup(final UserGroup userGroup);

    /**
     * Delete meetings from DB by given Status. It means, that all meetings,
     * which have this Status will be deleted.
     * 
     * @param meetingStatus
     *            our Status for meeting.
     */
    public void deleteMeetingByStatus(final MeetingStatus meetingStatus);

    /**
     * Return a List of sorted Meeting transfer objects.
     *
     * @param field
     *            for sort
     * @param order
     *            - ASC or DESC
     * @return List of sorted Subject transfer objects
     */
    public List<Meeting> sort(final String field, final Order order);

    /**
     * Return a List of searched Meeting transfer objects.
     *
     * @param field
     *            for search
     * @param pattern
     *            - input string
     * @return List of sorted Subject transfer objects
     */
    public List<Meeting> search(final String field, final String pattern);

    /**
     * Change the status in given (existences in DB) meeting.
     * 
     * @param meeting
     *            old meeting, will be changed.
     * @param meetingStatus
     *            new meeting status.
     */
    public void changeStatus(final Meeting meeting,
            final MeetingStatus meetingStatus);

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
    public List<Meeting> searchByDescription(final String description);

    /**
     * Find all meetings in the DB by given subject.
     * 
     * @param subject
     *            our subject for meeting.
     * @return List<Meeting> returns list.
     */
    public List<Meeting> searchBySubject(final Subject subject);

    /**
     * Find all meetings in the DB by given Owner.
     * 
     * @param user
     *            our user for meeting.
     * @return List<Meeting> returns list.
     */
    public List<Meeting> searchByOwner(final User user);

    /**
     * Find all meetings in the DB by given Room.
     * 
     * @param room
     *            our room for meeting.
     * @return List<Meeting> list.
     */
    public List<Meeting> searchByRoom(final Room room);

    /**
     * Find all meetings in the DB by given date.
     * 
     * @param date
     *            our date for meeting.
     * @return List<Meeting> list.
     */
    public List<Meeting> searchByDate(final LocalDateTime date);

    /**
     * Find all meetings in the DB by given userGroup.
     * 
     * @param userGroup
     *            our userGroup for meeting.
     * @return List<Meeting>
     */
    public List<Meeting> searchByUserGroup(final UserGroup userGroup);

    /**
     * Find all meetings in the DB by given userGroups.
     * 
     * @param userGroups
     *            our list of userGroups for meeting.
     * @return List<Meeting>
     */
    public List<Meeting> searchByUserGroups(final List<UserGroup> userGroups);

    /**
     * Find all meetings in the DB by given level.
     * 
     * @param level
     *            of the meeting.
     * @return List<Meeting>
     */
    public List<Meeting> searchByLevel(final Integer level);

    /**
     * Find all meetings in the DB by given meetingStatus.
     * 
     * @param meetingStatus
     *            of the meeting.
     * @return List<Meeting>
     */
    public List<Meeting> searchByStatus(final MeetingStatus meetingStatus);

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
    public List<Meeting> sortBySubject(final Order order);

    /**
     * Sort existent list of meetings by Owner lastname.
     * 
     * @param order
     *            parameter for sorting (ASC DESC)
     * @return List of sorted meetings.
     */
    public List<Meeting> sortByOwner(final Order order);

    /**
     * Sort existent list of meetings by Room name.
     * 
     * @param order
     *            parameter for sorting (ASC DESC)
     * @return List of sorted meetings.
     */
    public List<Meeting> sortByRoom(final Order order);

    /**
     * Sort existent list of meetings by Description.
     * 
     * @param order
     *            parameter for sorting (ASC DESC)
     * @return List of sorted meetings.
     */
    public List<Meeting> sortByDescription(final Order order);

    /**
     * Sort existent list of meetings by level.
     * 
     * @param order
     *            parameter for sorting (ASC DESC)
     * @return List of sorted meetings.
     */
    public List<Meeting> sortByLevel(final Order order);

    /**
     * Sort existent list of meetings by status.
     * 
     * @param order
     *            parameter for sorting (ASC DESC)
     * @return List of sorted meetings.
     */
    public List<Meeting> sortByStatus(final Order order);

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
     * @return List of the MeetingsForRoomDTO objects.
     */
    public List<MeetingsForRoomDTO> getMeetingsByRoomIDAndDate(Long roomId,
            LocalDate date);

}
