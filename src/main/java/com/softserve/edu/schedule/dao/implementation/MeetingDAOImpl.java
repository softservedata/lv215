/*
 * Implementation of the Interface for Meetings Data Access Object.
 * 
 * REMARK:
 * This interface is the same as CrudDAO but with additional
 * methods. This methods provide an opportunity to sort dataObjects
 * direct in the DB, not in the application. 
 * 
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
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.softserve.edu.schedule.dao.MeetingDAO;
import com.softserve.edu.schedule.dao.Order;
import com.softserve.edu.schedule.entity.Meeting;
import com.softserve.edu.schedule.entity.MeetingStatus;
import com.softserve.edu.schedule.entity.Meeting_;
import com.softserve.edu.schedule.entity.Room;
import com.softserve.edu.schedule.entity.Room_;
import com.softserve.edu.schedule.entity.Subject;
import com.softserve.edu.schedule.entity.Subject_;
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

@Repository("meetingDAO")
public class MeetingDAOImpl extends CrudDAOImpl<Meeting> implements MeetingDAO {
    
    
    
    @Override
    public List<Meeting> getAll() {
        CriteriaBuilder builder = getEm().getCriteriaBuilder();
        CriteriaQuery<Meeting> cq = builder.createQuery(Meeting.class);
        Root<Meeting> root = cq.from(Meeting.class);
        root.fetch(Meeting_.subject, JoinType.LEFT);
        root.fetch(Meeting_.owner, JoinType.LEFT);
        root.fetch(Meeting_.room, JoinType.LEFT);
        root.fetch(Meeting_.groups, JoinType.LEFT);
        cq.distinct(true);

        return getEm().createQuery(cq).getResultList();
    }

    @Override
    public List<Meeting> sortByField(final String field, final Order order) {
        CriteriaBuilder builder = getEm().getCriteriaBuilder();
        CriteriaQuery<Meeting> cq = builder.createQuery(Meeting.class);
        Root<Meeting> root = cq.from(Meeting.class);
        root.fetch(Meeting_.subject, JoinType.LEFT);
        root.fetch(Meeting_.owner, JoinType.LEFT);
        root.fetch(Meeting_.room, JoinType.LEFT);
        root.fetch(Meeting_.groups, JoinType.LEFT);
        cq.distinct(true);
        if (order == Order.ASC) {
            cq.orderBy(builder.asc(root.get(field)));
        } else {
            cq.orderBy(builder.desc(root.get(field)));
        }
        return getEm().createQuery(cq).getResultList();
    }

    /**
     * Overridden default constructor to provide entity class for DAO.
     * 
     */
    public MeetingDAOImpl() {
        super(Meeting.class);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.softserve.edu.schedule.dao.MeetingDAO#changeStatus(com.softserve.edu.
     * schedule.entity.Meeting, com.softserve.edu.schedule.entity.MeetingStatus)
     */
    public void changeStatus(Meeting meeting,
            final MeetingStatus meetingStatus) {
        this.delete(meeting);
        meeting.setStatus(meetingStatus);
        this.update(meeting);

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.softserve.edu.schedule.dao.MeetingDAO#deleteById(java.lang.Long)
     */
    @Override
    public void deleteById(final Long id) {
        Meeting meeting = getById(id);
        delete(meeting);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.softserve.edu.schedule.dao.MeetingDAO#searchBySubject(java.lang.
     * String)
     */
    @Override
    public List<Meeting> searchBySubject(final String pattern) {
        CriteriaBuilder builder = getEm().getCriteriaBuilder();
        CriteriaQuery<Meeting> cq = builder.createQuery(Meeting.class);
        Root<Meeting> root = cq.from(Meeting.class);

        Join<Meeting, Subject> joinSubject = root.join(Meeting_.subject);
        Predicate predicate = builder.like(joinSubject.get(Subject_.name),
                SEARCH_MASK + pattern + SEARCH_MASK);
        cq.where(predicate);
        return getEm().createQuery(cq).getResultList();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.softserve.edu.schedule.dao.MeetingDAO#searchByOwner(java.lang.String)
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

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.softserve.edu.schedule.dao.MeetingDAO#searchByRoom(java.lang.String)
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

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.softserve.edu.schedule.dao.MeetingDAO#searchByUserGroup(java.lang.
     * String)
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

    /*
     * (non-Javadoc)
     * 
     * @see com.softserve.edu.schedule.dao.MeetingDAO#searchByStatus(java.lang.
     * String)
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

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.softserve.edu.schedule.dao.MeetingDAO#searchByLevel(java.lang.String)
     */
    public List<Meeting> searchByLevel(final String pattern) {
        CriteriaBuilder builder = getEm().getCriteriaBuilder();
        CriteriaQuery<Meeting> cq = builder.createQuery(Meeting.class);
        Root<Meeting> root = cq.from(Meeting.class);

        Join<Meeting, Integer> joinLevel = root.join(Meeting_.level);
        Predicate predicate = builder.like(
                joinLevel.get(Meeting_.level.toString()),
                SEARCH_MASK + pattern + SEARCH_MASK);
        cq.where(predicate);
        return getEm().createQuery(cq).getResultList();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.softserve.edu.schedule.dao.MeetingDAO#searchByDate(java.lang.String)
     */
    public List<Meeting> searchByDate(final String pattern) {
        // TODO How to filter by Date in DB?
        return this.getAll();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.softserve.edu.schedule.dao.MeetingDAO#searchByDescription(java.lang.
     * String)
     */
    @Override
    public List<Meeting> searchByDescription(String pattern) {

        CriteriaBuilder builder = getEm().getCriteriaBuilder();
        CriteriaQuery<Meeting> cq = builder.createQuery(Meeting.class);
        Root<Meeting> root = cq.from(Meeting.class);

        Join<Meeting, String> joinDescription = root.join(Meeting_.description);
        Predicate predicate = builder.like(
                joinDescription.get(Meeting_.description.toString()),
                SEARCH_MASK + pattern + SEARCH_MASK);
        cq.where(predicate);
        return getEm().createQuery(cq).getResultList();
    }

    @Override
    public List<Meeting> sortBySubject(Order order) {
            CriteriaBuilder builder = getEm().getCriteriaBuilder();
            CriteriaQuery<Meeting> cq = builder.createQuery(Meeting.class);
            Root<Meeting> root = cq.from(Meeting.class);
            root.fetch(Meeting_.subject, JoinType.LEFT);
            root.fetch(Meeting_.owner, JoinType.LEFT);
            root.fetch(Meeting_.room, JoinType.LEFT);
            root.fetch(Meeting_.groups, JoinType.LEFT);
            cq.distinct(true);
            if (order == Order.ASC) {
                cq.orderBy(builder.asc(root.get(Meeting_.subject)));
                
               
                
            } else {
                //cq.orderBy(builder.desc(root.get(Subject_.description)));
            }
            return getEm().createQuery(cq).getResultList();
        }

    }

