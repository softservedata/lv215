/*
 * Meeting filter specification class for sorting meetings fields.
 */
package com.softserve.edu.schedule.service.implementation.specification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import com.softserve.edu.schedule.dao.UserGroupDAO;
import com.softserve.edu.schedule.dto.filter.MeetingFilter;
import com.softserve.edu.schedule.entity.Meeting;
import com.softserve.edu.schedule.entity.Meeting_;
import com.softserve.edu.schedule.entity.Room;
import com.softserve.edu.schedule.entity.Room_;
import com.softserve.edu.schedule.entity.Subject;
import com.softserve.edu.schedule.entity.Subject_;
import com.softserve.edu.schedule.entity.User;
import com.softserve.edu.schedule.entity.UserGroup;
import com.softserve.edu.schedule.entity.User_;

/**
 * This class provides the business logic of filtering data on the meeting list
 * page.
 * 
 * @author Bohdan Melnyk
 *
 */
public class MeetingFilterSpecification implements Specification<Meeting> {

    /**
     * Sort order ascending.
     */
    private static final int SORT_ASC = 1;

    /**
     * Sort order descending.
     */
    private static final int SORT_DESC = 2;

    /**
     * MeetingFilter example which provides parameters to build predicate.
     */
    private MeetingFilter meetingFilter;

    /**
     * UserGroupDAO example to provide database operations.
     */
    private UserGroupDAO userGroupDAO;

    /**
     * Subject join field for sorting purposes.
     */
    private Join<Meeting, Subject> subjectJoin;

    /**
     * Room join field for sorting purposes.
     */
    private Join<Meeting, Room> roomJoin;

    /**
     * User join field for sorting purposes.
     */
    private Join<Meeting, User> ownerJoin;

    /**
     * List of meetings specifications based on which the predicate is built.
     */
    private List<Specification<Meeting>> list = new ArrayList<>();

    /**
     * Constructor of MeetingFilterSpecification class.
     * 
     * @param meetingFilter
     * @param userGroupDAO
     */
    public MeetingFilterSpecification(final MeetingFilter meetingFilter,
            final UserGroupDAO userGroupDAO) {
        this.meetingFilter = meetingFilter;
        this.userGroupDAO = userGroupDAO;
    }

    /**
     * Add Meeting id for meeting specifications to specification list if filter
     * contains equipments parameter.
     */
    private void findById() {
        if (meetingFilter.getId() != null) {
            list.add((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder
                    .equal(root.get(Meeting_.id), meetingFilter.getId()));
        }
    }

    /**
     * Add Subjects id for meeting specifications to specification list if
     * filter contains equipments parameter.
     */
    private void findBySubjectId() {
        if (meetingFilter.getSubjectId() != null
                && meetingFilter.getSubjectId() > 0) {
            list.add((root, criteriaQuery, criteriaBuilder) -> root
                    .get(Meeting_.subject).in(meetingFilter.getSubjectId()));
        }
    }

    /**
     * Add Rooms id for meeting specifications to specification list if filter
     * contains equipments parameter.
     */
    private void findByRoomId() {
        if (meetingFilter.getRoomId() != null
                && meetingFilter.getRoomId() > 0) {
            list.add((root, criteriaQuery, criteriaBuilder) -> root
                    .get(Meeting_.room).in(meetingFilter.getRoomId()));
        }
    }

    /**
     * Add Onwer id for meeting specifications to specification list if filter
     * contains equipments parameter.
     */
    private void findByOwnerId() {
        if (meetingFilter.getOwnerId() != null
                && meetingFilter.getOwnerId() > 0) {
            list.add((root, criteriaQuery, criteriaBuilder) -> root
                    .get(Meeting_.owner).in(meetingFilter.getOwnerId()));
        }
    }

    /**
     * Add Date for meeting specifications to specification list if filter
     * contains equipments parameter.
     */
    private void findByDate() {
        if (meetingFilter.getDate() != null) {
            list.add((root, criteriaQuery, criteriaBuilder) -> root
                    .get(Meeting_.date).in(meetingFilter.getDate()));
        }
    }

    /**
     * Add StartTime for meeting specifications to specification list if filter
     * contains equipments parameter.
     */
    private void findByStartTime() {
        if (meetingFilter.getStartTime() != null) {
            list.add((root, criteriaQuery, criteriaBuilder) -> root
                    .get(Meeting_.startTime).in(meetingFilter.getStartTime()));
        }
    }

    /**
     * Add EndTime for meeting specifications to specification list if filter
     * contains equipments parameter.
     */
    private void findByEndTime() {
        if (meetingFilter.getEndTime() != null) {
            list.add((root, criteriaQuery, criteriaBuilder) -> root
                    .get(Meeting_.endTime).in(meetingFilter.getEndTime()));
        }
    }

    /**
     * Add UserGroups for meeting specifications to specification list if filter
     * contains equipments parameter.
     */
    private void findByUserGroupDTOs() {
        if (meetingFilter.getGroups() != null
                && !meetingFilter.getGroups().isEmpty()) {
            List<UserGroup> userGroups = meetingFilter.getGroups().stream()
                    .map(e -> userGroupDAO.getById(e.getId()))
                    .collect(Collectors.toList());
            userGroups.forEach(e -> list.add(
                    (root, criteriQuery, criteriaBuilder) -> criteriaBuilder
                            .isMember(e, root.get(Meeting_.groups))));
        }
    }

    /**
     * Add Meeting level specification to specification list if filter contains
     * equipments parameter.
     */
    private void findBylevel() {
        if (meetingFilter.getLevel() != null && meetingFilter.getLevel() > 0

        ) {
            list.add((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder
                    .equal(root.get(Meeting_.level), meetingFilter.getLevel()));
        }
    }

    /**
     * Add Meeting status specification to specification list if filter contains
     * equipments parameter.
     */
    private void findByStatus() {
        if (meetingFilter.getStatus() >= 0) {
            list.add((root, criteriaQuery, criteriaBuilder) -> root
                    .get(Meeting_.status).in(meetingFilter.getStatus()));
        }
    }

    /**
     * Add sorting order to predicate if filter contains sortByField and
     * sortOrder parameters.
     * 
     * @param root
     *            a root of predicate
     * 
     * @param criteriaQuery
     *            a query to add sorting order.
     * 
     * @param criteriaBuilder
     *            a criteria builder of predicate.
     */
    private void setSortingParameters(final Root<Meeting> root,
            final CriteriaQuery<?> criteriaQuery,
            final CriteriaBuilder criteriaBuilder) {
        if (meetingFilter.getSortOrder() == SORT_ASC) {
            if (meetingFilter
                    .getFieldForSorting() == MeetingSortField.DESCRIPTION
                            .ordinal()) {
                criteriaQuery.orderBy(
                        criteriaBuilder.asc(root.get(Meeting_.description)));
            } else if (meetingFilter
                    .getFieldForSorting() == MeetingSortField.SUBJECT
                            .ordinal()) {
                criteriaQuery.orderBy(
                        criteriaBuilder.asc(subjectJoin.get(Subject_.name)));
            } else if (meetingFilter
                    .getFieldForSorting() == MeetingSortField.OWNER.ordinal()) {
                criteriaQuery.orderBy(
                        criteriaBuilder.asc(ownerJoin.get(User_.lastName)));
            } else if (meetingFilter
                    .getFieldForSorting() == MeetingSortField.ROOM.ordinal()) {
                criteriaQuery
                        .orderBy(criteriaBuilder.asc(roomJoin.get(Room_.name)));
            } else if (meetingFilter
                    .getFieldForSorting() == MeetingSortField.DATE.ordinal()) {
                criteriaQuery
                        .orderBy(criteriaBuilder.asc(root.get(Meeting_.date)));
            } else if (meetingFilter
                    .getFieldForSorting() == MeetingSortField.STARTTIME
                            .ordinal()) {
                criteriaQuery.orderBy(
                        criteriaBuilder.asc(root.get(Meeting_.startTime)));
            } else if (meetingFilter
                    .getFieldForSorting() == MeetingSortField.ENDTIME
                            .ordinal()) {
                criteriaQuery.orderBy(
                        criteriaBuilder.asc(root.get(Meeting_.endTime)));
            } else if (meetingFilter
                    .getFieldForSorting() == MeetingSortField.LEVEL.ordinal()) {
                criteriaQuery
                        .orderBy(criteriaBuilder.asc(root.get(Meeting_.level)));
            } else if (meetingFilter
                    .getFieldForSorting() == MeetingSortField.STATUS
                            .ordinal()) {
                criteriaQuery.orderBy(
                        criteriaBuilder.asc(root.get(Meeting_.status)));
            }
        } else if (meetingFilter.getSortOrder() == SORT_DESC) {
            if (meetingFilter
                    .getFieldForSorting() == MeetingSortField.DESCRIPTION
                            .ordinal()) {
                criteriaQuery.orderBy(
                        criteriaBuilder.desc(root.get(Meeting_.description)));
            } else if (meetingFilter
                    .getFieldForSorting() == MeetingSortField.SUBJECT
                            .ordinal()) {
                criteriaQuery.orderBy(
                        criteriaBuilder.desc(subjectJoin.get(Subject_.name)));
            } else if (meetingFilter
                    .getFieldForSorting() == MeetingSortField.OWNER.ordinal()) {
                criteriaQuery.orderBy(
                        criteriaBuilder.desc(ownerJoin.get(User_.lastName)));
            } else if (meetingFilter
                    .getFieldForSorting() == MeetingSortField.ROOM.ordinal()) {
                criteriaQuery.orderBy(
                        criteriaBuilder.desc(roomJoin.get(Room_.name)));
            } else if (meetingFilter
                    .getFieldForSorting() == MeetingSortField.DATE.ordinal()) {
                criteriaQuery
                        .orderBy(criteriaBuilder.desc(root.get(Meeting_.date)));
            } else if (meetingFilter
                    .getFieldForSorting() == MeetingSortField.STARTTIME
                            .ordinal()) {
                criteriaQuery.orderBy(
                        criteriaBuilder.desc(root.get(Meeting_.startTime)));
            } else if (meetingFilter
                    .getFieldForSorting() == MeetingSortField.ENDTIME
                            .ordinal()) {
                criteriaQuery.orderBy(
                        criteriaBuilder.desc(root.get(Meeting_.endTime)));
            } else if (meetingFilter
                    .getFieldForSorting() == MeetingSortField.LEVEL.ordinal()) {
                criteriaQuery.orderBy(
                        criteriaBuilder.desc(root.get(Meeting_.level)));
            } else if (meetingFilter
                    .getFieldForSorting() == MeetingSortField.STATUS
                            .ordinal()) {
                criteriaQuery.orderBy(
                        criteriaBuilder.desc(root.get(Meeting_.status)));
            }
        } else {
            criteriaQuery.orderBy(criteriaBuilder.desc(root.get(Meeting_.id)));
        }
    }

    /**
     * Build predicate based on given MeetingFilter parameters.
     * 
     * @param root
     *            a root of predicate
     * 
     * @param criteriaQuery
     *            a query of predicate.
     * 
     * @param criteriaBuilder
     *            a criteria builder of predicate.
     * 
     * @return a Predicate example to search rooms in database by given filter
     *         parameters.
     */
    @Override
    public Predicate toPredicate(Root<Meeting> root,
            CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        subjectJoin = root.join(Meeting_.subject, JoinType.LEFT);
        roomJoin = root.join(Meeting_.room, JoinType.LEFT);
        ownerJoin = root.join(Meeting_.owner, JoinType.LEFT);
        root.join(Meeting_.groups, JoinType.LEFT);
        setSortingParameters(root, criteriaQuery, criteriaBuilder);
        if (meetingFilter != null) {
            findById();
            findBySubjectId();
            findByOwnerId();
            findByRoomId();
            findByDate();
            findByStartTime();
            findByEndTime();
            findByUserGroupDTOs();
            findBylevel();
            findByStatus();
        }
        if (list.isEmpty()) {
            return null;
        }
        Specifications<Meeting> spec = Specifications.where(list.get(0));
        for (int i = 1; i < list.size(); i++) {
            spec = spec.and(list.get(i));
        }
        return spec.toPredicate(root, criteriaQuery, criteriaBuilder);
    }
}
