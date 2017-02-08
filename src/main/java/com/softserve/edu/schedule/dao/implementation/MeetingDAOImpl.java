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
import java.time.LocalTime;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.softserve.edu.schedule.dao.MeetingDAO;
import com.softserve.edu.schedule.dao.UserDAO;
import com.softserve.edu.schedule.dao.UserGroupDAO;
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
/**
 * @author Andrew
 *
 */
@Repository
public class MeetingDAOImpl extends CrudDAOImpl<Meeting> implements MeetingDAO {

    /**
     * Overridden default constructor to provide entity class for DAO.
     * 
     */
    public MeetingDAOImpl() {
        super(Meeting.class);
    }

    /**
     * UserGroupDAO example for further business logic.
     */
    @Autowired
    UserGroupDAO userGroupDAO;

    /**
     * UserDAO example for further business logic.
     */
    @Autowired
    UserDAO userDAO;

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.softserve.edu.schedule.dao.MeetingDAO#getMeetingPageWithFilter(com.
     * softserve.edu.schedule.dto.filter.MeetingFilter,
     * com.softserve.edu.schedule.dto.filter.Paginator)
     */
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
        meetingPaginator
                .setPagesCount(getCountOfMeetingsWithFilter(meetingFilter));
        return getEm().createQuery(criteriaQuery)
                .setFirstResult(meetingPaginator.getOffset())
                .setMaxResults(meetingPaginator.getPageSize()).getResultList();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.softserve.edu.schedule.dao.MeetingDAO#getCountOfMeetingsWithFilter(
     * com.softserve.edu.schedule.dto.filter.MeetingFilter)
     */
    @Override
    public Long getCountOfMeetingsWithFilter(
            final MeetingFilter meetingFilter) {
        CriteriaBuilder qb = getEm().getCriteriaBuilder();
        CriteriaQuery<Long> cq = qb.createQuery(Long.class);
        Root<Meeting> root = cq.from(Meeting.class);
        cq.select(qb.countDistinct(root));
        Predicate predicate = new MeetingFilterSpecification(meetingFilter,
                userGroupDAO).toPredicate(root, cq, qb);
        if (predicate != null) {
            cq.where(predicate);
        }
        return getEm().createQuery(cq).getSingleResult();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.softserve.edu.schedule.dao.implementation.CrudDAOImpl#getById(java.
     * lang.Long)
     */
    @Override
    public Meeting getById(final Long id) {
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
        getEm().persist(meeting);
    }

    public MeetingStatus getStatusbyString(final String status) {
        if (status.contains("fin") || status.contains("FIN")) {
            return MeetingStatus.FINISHED;
        }
        if (status.contains("app") || status.contains("APP")) {
            return MeetingStatus.APPROVED;
        }
        if (status.contains("DIS") || status.contains("dis")) {
            return MeetingStatus.DISAPPROVED;
        } else {
            return MeetingStatus.NOT_APPROVED;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.softserve.edu.schedule.dao.MeetingDAO#dublicatesOfGivenFields(java.
     * lang.String, java.lang.String, java.lang.String, java.time.LocalDate,
     * java.time.LocalTime)
     */
    public List<Meeting> dublicatesOfGivenFields(final String subjectName,
            final String OwnerName, final String roomName,
            final LocalDate localDate, final LocalTime localTime) {

        CriteriaBuilder builder = getEm().getCriteriaBuilder();
        CriteriaQuery<Meeting> cq = builder.createQuery(Meeting.class);
        Root<Meeting> root = cq.from(Meeting.class);

        Join<Meeting, Subject> joinSubject = root.join(Meeting_.subject);
        Join<Meeting, User> joinOwner = root.join(Meeting_.owner);
        Join<Meeting, Room> joinRoom = root.join(Meeting_.room);
        root.join(Meeting_.groups);

        Predicate predicateSubject = builder
                .like(joinSubject.get(Subject_.name), subjectName);

        Predicate predicateOwner = builder.like(joinOwner.get(User_.lastName),
                OwnerName);

        Predicate predicateRoom = builder.like(joinRoom.get(Room_.name),
                roomName);

        Predicate predicateDate = root.get(Meeting_.date).in(localDate);

        Predicate predicateStartTime = root.get(Meeting_.startTime)
                .in(localTime);

        Predicate predicateAll = builder.and(predicateSubject, predicateOwner,
                predicateRoom, predicateDate, predicateStartTime);

        cq.where(predicateAll);

        return getEm().createQuery(cq).getResultList();
    }

    /**
     * Find all meetings in the DB which date and time are in past and status
     * not FINISHED.
     *
     * @author Petro Zelyonka
     *
     * @return List of the Meeting objects.
     */
    @Override
    public List<Meeting> getUnfinishedPastMeetings() {
        CriteriaBuilder builder = getEm().getCriteriaBuilder();
        CriteriaQuery<Meeting> cq = builder.createQuery(Meeting.class);
        Root<Meeting> root = cq.from(Meeting.class);
        Predicate predicate = builder.conjunction();
        predicate = builder.and(predicate, builder.lessThan(
                root.get(Meeting_.date), (LocalDate.now().plusDays(1))));
        predicate = builder.and(predicate, builder
                .lessThan(root.get(Meeting_.endTime), (LocalTime.now())));
        predicate = builder.and(predicate, builder
                .not(root.get(Meeting_.status).in(MeetingStatus.FINISHED)));
        cq.where(predicate);
        cq.distinct(true);
        return getEm().createQuery(cq).getResultList();
    }

    /**
     * Find all approved meetings in the DB by given roomId, date, start and end
     * time.
     *
     * @author Petro Zelyonka
     *
     * @param roomId
     *            room id for find meetings
     * @param date
     *            date for find meetings
     * @param startTime
     *            start time for find meetings
     * @param endTime
     *            end time for find meetings
     *
     * @return List of the Meeting objects.
     */
    @Override
    public List<Meeting> getApprovedMeetingsByRoomIdAndTime(final Long roomId,
            final LocalDate date, final LocalTime startTime,
            final LocalTime endTime) {
        CriteriaBuilder builder = getEm().getCriteriaBuilder();
        CriteriaQuery<Meeting> cq = builder.createQuery(Meeting.class);
        Root<Meeting> root = cq.from(Meeting.class);
        Join<Meeting, Room> roomJoin = root.join(Meeting_.room, JoinType.LEFT);
        Predicate basePredicate = builder.conjunction();
        basePredicate = builder.and(basePredicate,
                roomJoin.get(Room_.id).in(roomId));
        basePredicate = builder.and(basePredicate,
                root.get(Meeting_.date).in(date));
        basePredicate = builder.and(basePredicate, builder
                .lessThan(root.get(Meeting_.endTime), (LocalTime.now())));
        basePredicate = builder.and(basePredicate,
                root.get(Meeting_.status).in(MeetingStatus.APPROVED));
        Predicate timePredicate1 = builder.conjunction();
        timePredicate1 = builder.and(basePredicate,
                builder.lessThan(root.get(Meeting_.startTime), endTime));
        timePredicate1 = builder.and(basePredicate,
                builder.greaterThan(root.get(Meeting_.endTime), startTime));
        Predicate timePredicate2 = builder.conjunction();
        timePredicate2 = builder.and(basePredicate, builder
                .between(root.get(Meeting_.startTime), startTime, endTime));
        timePredicate2 = builder.and(basePredicate, builder
                .between(root.get(Meeting_.endTime), startTime, endTime));
        Predicate timePredicate = builder.or(timePredicate1, timePredicate2);
        basePredicate = builder.and(basePredicate, timePredicate);
        cq.where(basePredicate);
        cq.distinct(true);
        return getEm().createQuery(cq).getResultList();
    }

    /**
     * Find all meetings in the DB by given roomId, startDate and endDate.
     *
     * @author Petro Zelyonka
     *
     * @param roomId
     *            room id for find meetings
     * 
     * @param startDate
     *            start date for find meetings
     * 
     * @param endDate
     *            end date for find meetings
     *
     * @return List of the Meeting objects.
     */
    @Override
    public List<Meeting> getMeetingsInIntervalByRoomId(final Long roomId,
            final LocalDate startDate, final LocalDate endDate) {
        CriteriaBuilder builder = getEm().getCriteriaBuilder();
        CriteriaQuery<Meeting> cq = builder.createQuery(Meeting.class);
        Root<Meeting> root = cq.from(Meeting.class);
        root.join(Meeting_.subject);
        root.join(Meeting_.owner);
        Join<Meeting, Room> roomJoin = root.join(Meeting_.room);
        root.join(Meeting_.groups);
        Predicate predicate = builder.conjunction();
        predicate = builder.and(predicate,
                builder.between(root.get(Meeting_.date), startDate, endDate));
        predicate = builder.and(predicate, roomJoin.get(Room_.id).in(roomId));
        cq.where(predicate);
        cq.distinct(true);
        return getEm().createQuery(cq).getResultList();
    }

    /**
     * Find all meetings in the DB by given subjectId, startDate and endDate.
     *
     * @author Volodymyr Ped'ko
     *
     * @param subjectId
     *            subject id for find meetings
     * 
     * @param startDate
     *            start date for find meetings
     * 
     * @param endDate
     *            end date for find meetings
     *
     * @return List of the Meeting objects.
     */
    @Override
    public List<Meeting> getMeetingsInIntervalBySubjectId(final Long subjectId,
            final LocalDate startDate, final LocalDate endDate) {
        CriteriaBuilder builder = getEm().getCriteriaBuilder();
        CriteriaQuery<Meeting> cq = builder.createQuery(Meeting.class);
        Root<Meeting> root = cq.from(Meeting.class);
        Join<Meeting, Subject> subjectJoin = root.join(Meeting_.subject);
        root.join(Meeting_.owner);
        root.join(Meeting_.room);
        root.join(Meeting_.groups);
        Predicate predicate = builder.conjunction();
        predicate = builder.and(predicate,
                builder.between(root.get(Meeting_.date), startDate, endDate));
        predicate = builder.and(predicate,
                subjectJoin.get(Subject_.id).in(subjectId));
        cq.where(predicate);
        cq.distinct(true);
        return getEm().createQuery(cq).getResultList();
    }

    /**
     * Find all meetings in the DB by given userId, startDate and endDate.
     *
     * @author Petro Zelyonka
     *
     * @param userId
     *            user id for find meetings
     * 
     * @param startDate
     *            start date for find meetings
     * 
     * @param endDate
     *            end date for find meetings
     *
     * @return List of the Meeting objects.
     */
    @Override
    public List<Meeting> getMeetingsInIntervalByUserId(final Long userId,
            final LocalDate startDate, final LocalDate endDate) {
        CriteriaBuilder builder = getEm().getCriteriaBuilder();
        CriteriaQuery<Meeting> cq = builder.createQuery(Meeting.class);
        Root<Meeting> root = cq.from(Meeting.class);
        root.join(Meeting_.subject);
        root.join(Meeting_.owner);
        root.join(Meeting_.room);
        Join<Meeting, UserGroup> joinGroups = root.join(Meeting_.groups);
        Predicate basePredicate = builder.conjunction();
        basePredicate = builder.and(basePredicate,
                builder.between(root.get(Meeting_.date), startDate, endDate));
        User user = userDAO.getById(userId);
        Predicate userPredicate = builder.disjunction();
        userPredicate = builder.or(userPredicate,
                root.get(Meeting_.owner).in(userId));
        userPredicate = builder.or(userPredicate,
                builder.isMember(user, joinGroups.get(UserGroup_.users)));
        userPredicate = builder.or(userPredicate,
                joinGroups.get(UserGroup_.curator).in(userId));
        basePredicate = builder.and(basePredicate, userPredicate);
        cq.where(basePredicate);
        cq.distinct(true);
        return getEm().createQuery(cq).getResultList();
    }

    /**
     * Method that's used to get all meetings in specified interval for
     * specified group.
     * 
     * @author Andriy Zhydenko
     * 
     * @param groupId
     *            id of a group to find
     * @param start
     *            start time of meetings
     * @param end
     *            end time of meetings
     * @return list of meetings that will be held for specified group in
     *         specified time limits
     */
    @Override
    public List<Meeting> getMeetingsInIntervalByGroupId(final Long groupId,
            final LocalDate startDate, final LocalDate endDate) {
        CriteriaBuilder builder = getEm().getCriteriaBuilder();
        CriteriaQuery<Meeting> cq = builder.createQuery(Meeting.class);
        Root<Meeting> root = cq.from(Meeting.class);
        Join<Meeting, UserGroup> groupJoin = root.join(Meeting_.groups);
        root.join(Meeting_.owner);
        root.join(Meeting_.room);
        root.join(Meeting_.subject);
        Predicate predicate = builder.conjunction();
        predicate = builder.and(predicate,
                builder.between(root.get(Meeting_.date), startDate, endDate));
        predicate = builder.and(predicate,
                groupJoin.get(UserGroup_.id).in(groupId));
        cq.where(predicate);
        cq.distinct(true);
        return getEm().createQuery(cq).getResultList();
    }
}