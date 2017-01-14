/*
 * Implementation of the Interface for Meetings Data Access Object.
 *
 * Version 03.01.17
 *
 * 03.01.17
 *
 * Create by Melnyk Bohdan
 */

package com.softserve.edu.schedule.dao.implementation;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.softserve.edu.schedule.dao.MeetingDAO;
import com.softserve.edu.schedule.entity.MeetingStatus;
import com.softserve.edu.schedule.entity.Meeting;
import com.softserve.edu.schedule.entity.Meeting_;
import com.softserve.edu.schedule.entity.Room;
import com.softserve.edu.schedule.entity.Room_;
import com.softserve.edu.schedule.entity.User;
import com.softserve.edu.schedule.entity.UserGroup;
import com.softserve.edu.schedule.entity.UserGroup_;
import com.softserve.edu.schedule.entity.User_;

/**
 * This class implements the Meetings DAO. It also implements ReadDAO interface.
 * 
 * @version 1.0 12.12.2016
 * @author IT Academy
 */

public class MeetingDAOImpl extends CrudDAOImpl<Meeting> implements MeetingDAO {

    /**
     * Overridden default constructor to provide entity class for DAO.
     * 
     */
    public MeetingDAOImpl() {
        super(Meeting.class);
    }

    /**
     * Delete existed room entity from the database by id.
     *
     * @param id
     *            a room id to delete from database.
     */
    @Override
    public void deleteById(final Long id) {
        Meeting meeting = getById(id);
        delete(meeting);
    }

    /**
     * Return the List of searched Meetings filtered by Owner.
     *
     * @return List of searched Transfer objects
     */
    @Override
    public List<Meeting> searchByOwner(final String pattern) {
        CriteriaBuilder builder = getEm().getCriteriaBuilder();
        CriteriaQuery<Meeting> cq = builder.createQuery(Meeting.class);
        Root<Meeting> root = cq.from(Meeting.class);

        Join<Meeting, User> joinUser = root.join(Meeting_.owner);
        Predicate predicate = builder.like(joinUser.get(User_.lastName),
                SEARCH_MASK + pattern + SEARCH_MASK);
        cq.where(predicate);
        return getEm().createQuery(cq).getResultList();
    }

    /**
     * Return the List of searched Meetings filtered by Room.
     *
     * @return List of searched Transfer objects
     */
    public List<Meeting> searchByRoom(final String pattern) {
        CriteriaBuilder builder = getEm().getCriteriaBuilder();
        CriteriaQuery<Meeting> cq = builder.createQuery(Meeting.class);
        Root<Meeting> root = cq.from(Meeting.class);

        Join<Meeting, Room> joinRoom = root.join(Meeting_.room);
        Predicate predicate = builder.like(joinRoom.get(Room_.name),
                SEARCH_MASK + pattern + SEARCH_MASK);
        cq.where(predicate);
        return getEm().createQuery(cq).getResultList();
    }

    /**
     * Return the List of searched Meetings filtered by UserGroup.
     *
     * @return List of searched Transfer objects
     */
    public List<Meeting> searchByUserGroup(final String pattern) {
        CriteriaBuilder builder = getEm().getCriteriaBuilder();
        CriteriaQuery<Meeting> cq = builder.createQuery(Meeting.class);
        Root<Meeting> root = cq.from(Meeting.class);

        Join<Meeting, UserGroup> joinUserGroup = root.join(Meeting_.groups);
        Predicate predicate = builder.like(joinUserGroup.get(UserGroup_.name),
                SEARCH_MASK + pattern + SEARCH_MASK);
        cq.where(predicate);
        return getEm().createQuery(cq).getResultList();
    }

    /**
     * Return the List of searched Meetings filtered by Status.
     *
     * @return List of searched Transfer objects
     */
    public List<Meeting> searchByStatus(final String pattern) {
        CriteriaBuilder builder = getEm().getCriteriaBuilder();
        CriteriaQuery<Meeting> cq = builder.createQuery(Meeting.class);
        Root<Meeting> root = cq.from(Meeting.class);

        Join<Meeting, MeetingStatus> joinStatus = root.join(Meeting_.status);
        Predicate predicate = builder.like(
                joinStatus.get(Meeting_.status.toString()),
                SEARCH_MASK + pattern + SEARCH_MASK);
        cq.where(predicate);
        return getEm().createQuery(cq).getResultList();
    }

    /**
     * Return the List of searched Meetings filtered by level.
     *
     * @return List of searched Transfer objects
     */
    public List<Meeting> searchByLevel(final String pattern) {
        CriteriaBuilder builder = getEm().getCriteriaBuilder();
        CriteriaQuery<Meeting> cq = builder.createQuery(Meeting.class);
        Root<Meeting> root = cq.from(Meeting.class);

        Join<Meeting, MeetingStatus> joinLevel = root.join(Meeting_.status);
        Predicate predicate = builder.like(
                joinLevel.get(Meeting_.level.toString()),
                SEARCH_MASK + pattern + SEARCH_MASK);
        cq.where(predicate);
        return getEm().createQuery(cq).getResultList();
    }

    /**
     * Return the List of searched Meetings filtered by Date.
     *
     * @return List of searched Transfer objects
     */
    public List<Meeting> searchByDate(final String pattern) {
        // TODO How to filter by Date in DB ?
        return null;
    }

}
