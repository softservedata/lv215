/*
 * Interface for MeetingsHistory DAO.
 *
 * Version 03.01.17
 *
 * 03.01.17
 *
 * Create by Melnyk Bohdan
 */

package com.softserve.edu.schedule.dao;

import java.util.List;

import com.softserve.edu.schedule.entity.MeetingHistory;

/**
 * This interface for managing MeetingsHistory DAO.
 *
 * @version 1.0 22.02.2017
 * @author Bohdan Melnyk, Oleksandr Butyter
 */
public interface MeetingHistoryDAO extends CrudDAO<MeetingHistory> {

	/**
	 * Returns meeting from MeetingHistory table by given id.
	 * 
	 * @param idMeeting
	 *            id of the meeting, that will be returned.
	 * @return List<MeetingHistory>.
	 */
	public List<MeetingHistory> getMeetingHistoryByIdMeeting(final String idMeeting);

	/**
	 * Returns all meetings from MeetingHistory table.
	 * 
	 * @param idMeeting
	 *            id of the meeting, that will be returned.
	 * @return List<MeetingHistory>.
	 */
	public List<MeetingHistory> getAllMeetingHistory();

	/**
	 * Method returns count of meetings from MeetingHistory table by given location.
	 * 
	 * @param location
	 *            location of the meeting
	 * @return count of meetings from MeetingHistory table.
	 */
	public Long getCountMeetingHistoryByLocation(final String location);
}
