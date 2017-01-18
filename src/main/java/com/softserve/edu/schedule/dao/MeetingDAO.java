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

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import com.softserve.edu.schedule.entity.Meeting;
import com.softserve.edu.schedule.entity.MeetingStatus;
import com.softserve.edu.schedule.entity.Subject;
import com.softserve.edu.schedule.entity.Subject_;

/**
 * This interface for managing Meetings DAO implementation. It extends ReadDAO
 * interface.
 * 
 * @version 1.0 12.12.2016
 * @author IT Academy
 */
public interface MeetingDAO extends CrudDAO<Meeting> {

    /**
     * Delete Meeting by id.
     * 
     * @param id
     *            id.
     */
    public void deleteById(Long id);

    /**
     * For the given Meeting changes meeting status for given MeetingStatus.
     * 
     * @param meeting
     * @param meetingStatus
     */
    public void changeStatus(final Meeting meeting,
            final MeetingStatus meetingStatus);

    /**
     * Return the List of searched Meetings filtered by Owner.
     *
     * @return List of searched Transfer objects
     */
    public List<Meeting> searchByOwner(final String pattern);

    /**
     * Return the List of searched Meetings filtered by Description.
     *
     * @return List of searched Transfer objects
     */
    public List<Meeting> searchByDescription(final String pattern);

    /**
     * Return the List of searched Meetings filtered by Subject.
     *
     * @return List of searched Transfer objects
     */
    public List<Meeting> searchBySubject(final String pattern);

    /**
     * Return the List of searched Meetings filtered by Room.
     *
     * @return List of searched Transfer objects
     */
    public List<Meeting> searchByRoom(final String pattern);

    /**
     * Return the List of searched Meetings filtered by UserGroup.
     *
     * @return List of searched Transfer objects
     */
    public List<Meeting> searchByUserGroup(final String pattern);

    /**
     * Return the List of searched Meetings filtered by Status.
     *
     * @return List of searched Transfer objects
     */
    public List<Meeting> searchByDate(final String pattern);

    /**
     * Return the List of searched Meetings filtered by Date.
     *
     * @return List of searched Transfer objects
     */
    public List<Meeting> searchByStatus(final String pattern);

    /**
     * Return the List of searched Meetings filtered by level.
     *
     * @return List of searched Transfer objects
     */
    public List<Meeting> searchByLevel(final String pattern);

    public List<Meeting> sortByField(final String field, final Order order);
    
    public List<Meeting> sortBySubject(final Order order);
}
