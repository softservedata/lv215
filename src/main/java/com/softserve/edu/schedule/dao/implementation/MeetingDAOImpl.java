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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.softserve.edu.schedule.dao.MeetingDAO;

import com.softserve.edu.schedule.dao.UserGroupDAO;
import com.softserve.edu.schedule.dto.MeetingDTO;
import com.softserve.edu.schedule.dto.SubjectDTO;
import com.softserve.edu.schedule.dto.filter.MeetingFilter;
import com.softserve.edu.schedule.dto.filter.Paginator;
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
import com.softserve.edu.schedule.service.implementation.specification.MeetingFilterSpecification;

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

    @Autowired
    UserGroupDAO userGroupDAO;

    @Override
    public List<Meeting> getMeetingPageWithFilter(
            final MeetingFilter meetingFilter,
            final Paginator meetingPaginator) {
        CriteriaBuilder builder = getEm().getCriteriaBuilder();
        CriteriaQuery<Meeting> criteriaQuery = builder
                .createQuery(Meeting.class);
        Root<Meeting> root = criteriaQuery.from(Meeting.class);

        Predicate predicate = new MeetingFilterSpecification(meetingFilter,
                userGroupDAO).toPredicate(root, criteriaQuery, builder);
        if (predicate != null) {
            criteriaQuery.where(predicate);
        }
        criteriaQuery.distinct(true);
        meetingPaginator.setPagesCount(
                getEm().createQuery(criteriaQuery).getResultList().size());
        return getEm().createQuery(criteriaQuery)
                .setFirstResult(meetingPaginator.getOffset())
                .setMaxResults(meetingPaginator.getPageSize()).getResultList();
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
            cq.distinct(true);
            return getEm().createQuery(cq).getResultList();
        } catch (NoResultException e) {
            return new ArrayList<>();
        }
    }

    public MeetingStatus getStatusbyString(final String status) {
        if (status.contains("fin") || status.contains("FIN")  ) {
            return MeetingStatus.FINISHED;
        }
        if (status.contains("app") || status.contains("APP") ) {
            return MeetingStatus.APPROVED;
        }
        if (status.contains("DIS") || status.contains("dis")) {
            return MeetingStatus.DISAPPROVED;
        } else {
            return MeetingStatus.NOT_APPROVED;
        }
    }
    
    public List<Meeting> hasDublicate(final String subjectName, final String OwnerName, final String roomName) {

        CriteriaBuilder builder = getEm().getCriteriaBuilder();
        CriteriaQuery<Meeting> cq = builder.createQuery(Meeting.class);
        Root<Meeting> root = cq.from(Meeting.class);
        
        Join<Meeting, Subject> joinSubject = root.join(Meeting_.subject);
        Join<Meeting, User> joinOwner = root.join(Meeting_.owner);
        Join<Meeting, Room> joinRoom = root.join(Meeting_.room);
        Join<Meeting, UserGroup> joinGroup = root.join(Meeting_.groups);

        Predicate predicateSubject = builder.like(joinSubject.get(Subject_.name),
                SEARCH_MASK + subjectName + SEARCH_MASK);
        
        Predicate predicateOwner = builder.like(joinOwner.get(User_.lastName),
                SEARCH_MASK + OwnerName + SEARCH_MASK);
        
        Predicate predicateRoom = builder.like(joinRoom.get(Room_.name),
                SEARCH_MASK + roomName + SEARCH_MASK);
        
       //TODO by date
        
       Predicate predicateAll = builder.and(predicateSubject, predicateOwner, predicateRoom);  
       
        cq.where(predicateAll);
        
        return getEm().createQuery(cq).getResultList();
    }
}