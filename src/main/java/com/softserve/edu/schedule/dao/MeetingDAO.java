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
import java.util.List;

import com.softserve.edu.schedule.dto.MeetingDTO;
import com.softserve.edu.schedule.dto.RoomDTO;
import com.softserve.edu.schedule.dto.SubjectDTO;
import com.softserve.edu.schedule.dto.UserDTO;
import com.softserve.edu.schedule.dto.UserGroupDTO;
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

	public List<Meeting> getMeetingPageWithFilter(final MeetingFilter meetingFilter, final Paginator meetingPaginator);

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
	public void changeMeetingStatus(final Long id, final MeetingStatus meetingStatus);

	/**
	 * Return the List of searched Meetings filtered by Owner.
	 *
	 * @return List of searched Transfer objects
	 */
	public List<Meeting> searchByOwner(final UserDTO userDTO);

	/**
	 * Return the List of searched Meetings filtered by Description.
	 *
	 * @return List of searched Transfer objects
	 */
	public List<Meeting> searchByDescription(final MeetingDTO meetingDTO);

	/**
	 * Return the List of searched Meetings filtered by Subject.
	 *
	 * @return List of searched Transfer objects
	 */
	public List<Meeting> searchBySubject(final SubjectDTO subjectDTO);

	/**
	 * Return the List of searched Meetings filtered by Room.
	 *
	 * @return List of searched Transfer objects
	 */
	public List<Meeting> searchByRoom(final RoomDTO roomDTO);

	/**
	 * Return the List of searched Meetings filtered by UserGroup.
	 *
	 * @return List of searched Transfer objects
	 */
	public List<Meeting> searchByUserGroup(final UserGroupDTO userGroupDTO);

	/**
	 * Return the List of searched Meetings filtered by Status.
	 *
	 * @return List of searched Transfer objects
	 */
	public List<Meeting> searchByDate(final LocalDate date);

	/**
	 * Return the List of searched Meetings filtered by Date.
	 *
	 * @return List of searched Transfer objects
	 */
	public List<Meeting> searchByStatus(final MeetingDTO meetingDTO);

	/**
	 * Return the List of searched Meetings filtered by level.
	 *
	 * @return List of searched Transfer objects
	 */
	public List<Meeting> searchByLevel(final MeetingDTO meetingDTO);

	/**
	 * Sort existent list of meetings by Description.
	 * 
	 * @param order
	 *            parameter for sorting (ASC DESC)
	 * @return List of sorted meetings.
	 */
	public List<Meeting> sortByDescription(final Order order);

	/**
	 * Sort existent list of meetings by Subject name.
	 * 
	 * @param order
	 *            parameter for sorting (ASC DESC)
	 * @return List of sorted meetings.
	 */
	public List<Meeting> sortBySubject(final Order order);

	/**
	 * Sort existent list of meetings by Owner LastName.
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
	 * Sort existent list of meetings by level.
	 * 
	 * @param order
	 *            parameter for sorting (ASC DESC)
	 * @return List of sorted meetings.
	 */
	public List<Meeting> sortByLevel(final Order order);

	/**
	 * Sort existent list of meetings by Status.
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
	 * @return List of the Meeting objects.
	 */
	public List<Meeting> getMeetingsByRoomIDAndDate(final Long roomId, final LocalDate date);


	public MeetingStatus getStatusbyString(final String status);
	
}
