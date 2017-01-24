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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
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
 * This class implements the MeetingsDAO. It also implements ReadDAO interface.
 * 
 * @version 1.0 12.12.2016
 * @author IT Academy
 */
@Repository("meetingDAO")
public class MeetingDAOImpl extends CrudDAOImpl<Meeting> implements MeetingDAO {
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
     * com.softserve.edu.schedule.dao.implementation.CrudDAOImpl#getById(java.
     * lang.Long)
     */
    @Override
    public Meeting getById(Long id) {
        CriteriaBuilder builder = getEm().getCriteriaBuilder();
        CriteriaQuery<Meeting> cq = builder.createQuery(Meeting.class);
        Root<Meeting> root = cq.from(Meeting.class);
        root.fetch(Meeting_.subject, JoinType.LEFT);
        root.fetch(Meeting_.owner, JoinType.LEFT);
        root.fetch(Meeting_.room, JoinType.LEFT);
        root.fetch(Meeting_.groups, JoinType.LEFT);
        cq.where(root.get(Meeting_.id).in(id));
        return getEm().createQuery(cq).getSingleResult();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.softserve.edu.schedule.dao.implementation.CrudDAOImpl#getAll()
     */
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

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.softserve.edu.schedule.dao.MeetingDAO#changeStatus(com.softserve.edu.
     * schedule.entity.Meeting, com.softserve.edu.schedule.entity.MeetingStatus)
     */
    public void changeMeetingStatus(final Long id,
            final MeetingStatus meetingStatus) {
        Meeting meeting = this.getById(id);
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
        root.fetch(Meeting_.subject, JoinType.LEFT);
        root.fetch(Meeting_.owner, JoinType.LEFT);
        root.fetch(Meeting_.room, JoinType.LEFT);
        root.fetch(Meeting_.groups, JoinType.LEFT);
        cq.distinct(true);
        cq.where(builder.like(root.get(Meeting_.level.getName()),
                SEARCH_MASK + pattern + SEARCH_MASK));
        return getEm().createQuery(cq).getResultList();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.softserve.edu.schedule.dao.MeetingDAO#searchByDate(java.lang.String)
     */
    public List<Meeting> searchByDate(final LocalDate date) {
                CriteriaBuilder builder = getEm().getCriteriaBuilder();
                CriteriaQuery<Meeting> cq = builder.createQuery(Meeting.class);
                Root<Meeting> root = cq.from(Meeting.class);
                root.fetch(Meeting_.subject, JoinType.LEFT);
                root.fetch(Meeting_.owner, JoinType.LEFT);
                root.fetch(Meeting_.room, JoinType.LEFT);
                root.fetch(Meeting_.groups, JoinType.LEFT);
                cq.distinct(true);
                cq.where(root.get(Meeting_.date).in(date));
                return getEm().createQuery(cq).getResultList();
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
        root.fetch(Meeting_.subject, JoinType.LEFT);
        root.fetch(Meeting_.owner, JoinType.LEFT);
        root.fetch(Meeting_.room, JoinType.LEFT);
        root.fetch(Meeting_.groups, JoinType.LEFT);
        cq.distinct(true);
        cq.where(builder.like(root.get(Meeting_.description),
                SEARCH_MASK + pattern + SEARCH_MASK));
        return getEm().createQuery(cq).getResultList();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.softserve.edu.schedule.dao.MeetingDAO#sortByDescription(com.softserve
     * .edu.schedule.dao.Order)
     */
    @Override
    public List<Meeting> sortByDescription(final Order order) {
        CriteriaBuilder builder = getEm().getCriteriaBuilder();
        CriteriaQuery<Meeting> cq = builder.createQuery(Meeting.class);
        Root<Meeting> root = cq.from(Meeting.class);
        root.fetch(Meeting_.subject, JoinType.LEFT);
        root.fetch(Meeting_.owner, JoinType.LEFT);
        root.fetch(Meeting_.room, JoinType.LEFT);
        root.fetch(Meeting_.groups, JoinType.LEFT);
        cq.distinct(true);
        if (order == Order.ASC) {
            cq.orderBy(builder.asc(root.get(Meeting_.description)));
        } else {
            cq.orderBy(builder.desc(root.get(Meeting_.description)));
        }
        return getEm().createQuery(cq).getResultList();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.softserve.edu.schedule.dao.MeetingDAO#sortBySubject(com.softserve.edu
     * .schedule.dao.Order)
     */
    @Override
    public List<Meeting> sortBySubject(final Order order) {
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
            cq.orderBy(builder.desc(root.get(Meeting_.subject)));
        }
        return getEm().createQuery(cq).getResultList();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.softserve.edu.schedule.dao.MeetingDAO#sortByOwner(com.softserve.edu.
     * schedule.dao.Order)
     */
    @Override
    public List<Meeting> sortByOwner(final Order order) {
        CriteriaBuilder builder = getEm().getCriteriaBuilder();
        CriteriaQuery<Meeting> cq = builder.createQuery(Meeting.class);
        Root<Meeting> root = cq.from(Meeting.class);
        root.fetch(Meeting_.subject, JoinType.LEFT);
        root.fetch(Meeting_.owner, JoinType.LEFT);
        root.fetch(Meeting_.room, JoinType.LEFT);
        root.fetch(Meeting_.groups, JoinType.LEFT);
        cq.distinct(true);
        if (order == Order.ASC) {
            cq.orderBy(builder.asc(root.get(Meeting_.owner)));
        } else {
            cq.orderBy(builder.desc(root.get(Meeting_.owner)));
        }
        return getEm().createQuery(cq).getResultList();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.softserve.edu.schedule.dao.MeetingDAO#sortByRoom(com.softserve.edu.
     * schedule.dao.Order)
     */
    @Override
    public List<Meeting> sortByRoom(final Order order) {
        CriteriaBuilder builder = getEm().getCriteriaBuilder();
        CriteriaQuery<Meeting> cq = builder.createQuery(Meeting.class);
        Root<Meeting> root = cq.from(Meeting.class);
        Join<Meeting, Room> joinUser = root.join(Meeting_.room, JoinType.LEFT);
        if (order == Order.ASC) {
            cq.orderBy(builder.asc(joinUser.get(Room_.name)));
        } else {
            cq.orderBy(builder.desc(joinUser.get(Room_.name)));
        }
        return getEm().createQuery(cq).getResultList();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.softserve.edu.schedule.dao.MeetingDAO#sortByLevel(com.softserve.edu.
     * schedule.dao.Order)
     */
    @Override
    public List<Meeting> sortByLevel(final Order order) {
        CriteriaBuilder builder = getEm().getCriteriaBuilder();
        CriteriaQuery<Meeting> cq = builder.createQuery(Meeting.class);
        Root<Meeting> root = cq.from(Meeting.class);
        root.fetch(Meeting_.subject, JoinType.LEFT);
        root.fetch(Meeting_.owner, JoinType.LEFT);
        root.fetch(Meeting_.room, JoinType.LEFT);
        root.fetch(Meeting_.groups, JoinType.LEFT);
        cq.distinct(true);
        if (order == Order.ASC) {
            cq.orderBy(builder.asc(root.get(Meeting_.level)));
        } else {
            cq.orderBy(builder.desc(root.get(Meeting_.level)));
        }
        return getEm().createQuery(cq).getResultList();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.softserve.edu.schedule.dao.MeetingDAO#sortByStatus(com.softserve.edu.
     * schedule.dao.Order)
     */
    @Override
    public List<Meeting> sortByStatus(final Order order) {
        CriteriaBuilder builder = getEm().getCriteriaBuilder();
        CriteriaQuery<Meeting> cq = builder.createQuery(Meeting.class);
        Root<Meeting> root = cq.from(Meeting.class);
        root.fetch(Meeting_.subject, JoinType.LEFT);
        root.fetch(Meeting_.owner, JoinType.LEFT);
        root.fetch(Meeting_.room, JoinType.LEFT);
        root.fetch(Meeting_.groups, JoinType.LEFT);
        cq.distinct(true);
        if (order == Order.ASC) {
            cq.orderBy(builder.asc(root.get(Meeting_.status)));
        } else {
            cq.orderBy(builder.desc(root.get(Meeting_.status)));
        }
        return getEm().createQuery(cq).getResultList();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.softserve.edu.schedule.dao.implementation.CrudDAOImpl#create(java.
     * lang.Object)
     */
    @Override
    public void create(Meeting meeting) {
        /*
         * New meeting must has status = NOT_APPROVE.
         */
        meeting.setStatus(MeetingStatus.NOT_APPROVED);
        getEm().persist(meeting);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.softserve.edu.schedule.dao.MeetingDAO#getMeetingsByRoomIDAndDate(java
     * .lang.Long, java.time.LocalDate)
     */
    @Override
    public List<Meeting> getMeetingsByRoomIDAndDate(final Long roomId,
            final LocalDate date) {
        try {
            CriteriaBuilder builder = getEm().getCriteriaBuilder();
            CriteriaQuery<Meeting> cq = builder.createQuery(Meeting.class);
            Root<Meeting> root = cq.from(Meeting.class);
            Join<Meeting, Room> roomJoin = root.join(Meeting_.room,
                    JoinType.LEFT);
            root.join(Meeting_.subject, JoinType.LEFT);
            root.join(Meeting_.owner, JoinType.LEFT);
            root.join(Meeting_.groups, JoinType.LEFT);
            Predicate predicate = builder.conjunction();
            predicate = builder.and(predicate,
                    roomJoin.get(Room_.id).in(roomId));
            predicate = builder.and(predicate,
                    root.get(Meeting_.date).in(date));
            cq.where(predicate);
            return getEm().createQuery(cq).getResultList();
        } catch (NoResultException e) {
            return new ArrayList<>();
        }
    }

}
