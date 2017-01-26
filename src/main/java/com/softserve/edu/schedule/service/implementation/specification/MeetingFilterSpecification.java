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

import com.softserve.edu.schedule.dao.Order;
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

public class MeetingFilterSpecification implements Specification<Meeting> {

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

    public MeetingFilterSpecification(final MeetingFilter meetingFilter,
            final UserGroupDAO userGroupDAO) {
        this.meetingFilter = meetingFilter;
        this.userGroupDAO = userGroupDAO;
    }

    private void findByDescription() {
        if (meetingFilter.getDescription() != null
                && !meetingFilter.getDescription().equals("")) {
            list.add((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder
                    .like(root.get(Meeting_.description),
                            "%" + meetingFilter.getDescription() + "%"));
        }
    }

    private void findBySubjectId() {
        if (meetingFilter.getSubjectId() != null
                && meetingFilter.getSubjectId() > 0) {
            list.add((root, criteriaQuery, criteriaBuilder) -> root
                    .get(Meeting_.subject).in(meetingFilter.getSubjectId()));
        }
    }

    private void findByRoomId() {
        if (meetingFilter.getRoomId() != null
                && meetingFilter.getRoomId() > 0) {
            list.add((root, criteriaQuery, criteriaBuilder) -> root
                    .get(Meeting_.room).in(meetingFilter.getRoomId()));
        }
    }

    private void findByOwnerId() {
        if (meetingFilter.getOwnerId() != null
                && meetingFilter.getOwnerId() > 0) {
            list.add((root, criteriaQuery, criteriaBuilder) -> root
                    .get(Meeting_.owner).in(meetingFilter.getOwnerId()));
        }
    }

    private void findByDate() {
        if (meetingFilter.getDate() != null) {
            list.add((root, criteriaQuery, criteriaBuilder) -> root
                    .get(Meeting_.date).in(meetingFilter.getDate()));
        }
    }

    private void findByStartTime() {
        if (meetingFilter.getStartTime() != null) {
            list.add((root, criteriaQuery, criteriaBuilder) -> root
                    .get(Meeting_.startTime).in(meetingFilter.getStartTime()));
        }
    }

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
     * Add room capacity specification to specification list if filter contains
     * maxCapacity and(or) minCapacity parameters.
     */
    private void findByMaxMinLevel() {
        if (meetingFilter.getMaxLevel() != null
                && meetingFilter.getMinLevel() != null
                && meetingFilter.getMaxLevel() > 0
                && meetingFilter.getMinLevel() > 0

        ) {
            list.add((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder
                    .between(root.get(Meeting_.level),
                            meetingFilter.getMinLevel(),
                            meetingFilter.getMaxLevel()));
        } else if (meetingFilter.getMaxLevel() != null
                && meetingFilter.getMaxLevel() > 0) {
            list.add((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder
                    .lessThan(root.get(Meeting_.level),
                            meetingFilter.getMaxLevel()));
        } else if (meetingFilter.getMinLevel() != null
                && meetingFilter.getMinLevel() > 0) {
            list.add((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder
                    .greaterThan(root.get(Meeting_.level),
                            meetingFilter.getMinLevel()));
        }
    }

    private void findByStatus() {
        if (meetingFilter.getStatus() >= 0) {
            list.add((root, criteriaQuery, criteriaBuilder) -> root
                    .get(Meeting_.status).in(meetingFilter.getStatus()));
        }
    }

    private void setSortingParameters(final Root<Meeting> root,
            final CriteriaQuery<?> criteriaQuery,
            final CriteriaBuilder criteriaBuilder) {
        if (meetingFilter.getSortOrder() == Order.ASC.ordinal() && meetingFilter
                .getFieldForSorting() == MeetingSortField.DESCRIPTION
                        .ordinal()) {
            criteriaQuery.orderBy(
                    criteriaBuilder.asc(root.get(Meeting_.description)));
        }
        if (meetingFilter.getSortOrder() == Order.DESC.ordinal()
                && meetingFilter
                        .getFieldForSorting() == MeetingSortField.DESCRIPTION
                                .ordinal()) {
            criteriaQuery.orderBy(
                    criteriaBuilder.desc(root.get(Meeting_.description)));
        }

        if (meetingFilter.getSortOrder() == Order.ASC.ordinal() && meetingFilter
                .getFieldForSorting() == MeetingSortField.SUBJECT.ordinal()) {
            criteriaQuery.orderBy(
                    criteriaBuilder.asc(subjectJoin.get(Subject_.name)));
        }
        if (meetingFilter.getSortOrder() == Order.DESC.ordinal()
                && meetingFilter
                        .getFieldForSorting() == MeetingSortField.SUBJECT
                                .ordinal()) {
            criteriaQuery.orderBy(
                    criteriaBuilder.desc(subjectJoin.get(Subject_.name)));
        }

        if (meetingFilter.getSortOrder() == Order.ASC.ordinal() && meetingFilter
                .getFieldForSorting() == MeetingSortField.OWNER.ordinal()) {
            criteriaQuery.orderBy(
                    criteriaBuilder.asc(ownerJoin.get(User_.firstName)));
        }
        if (meetingFilter.getSortOrder() == Order.DESC.ordinal()
                && meetingFilter.getFieldForSorting() == MeetingSortField.OWNER
                        .ordinal()) {
            criteriaQuery.orderBy(
                    criteriaBuilder.desc(ownerJoin.get(User_.firstName)));
        }
        if (meetingFilter.getSortOrder() == Order.ASC.ordinal() && meetingFilter
                .getFieldForSorting() == MeetingSortField.ROOM.ordinal()) {
            criteriaQuery
                    .orderBy(criteriaBuilder.asc(roomJoin.get(Room_.name)));
        }
        if (meetingFilter.getSortOrder() == Order.DESC.ordinal()
                && meetingFilter.getFieldForSorting() == MeetingSortField.ROOM
                        .ordinal()) {
            criteriaQuery
                    .orderBy(criteriaBuilder.desc(roomJoin.get(Room_.name)));
        }
        if (meetingFilter.getSortOrder() == Order.ASC.ordinal() && meetingFilter
                .getFieldForSorting() == MeetingSortField.DATE.ordinal()) {
            criteriaQuery.orderBy(criteriaBuilder.asc(root.get(Meeting_.date)));
        }
        if (meetingFilter.getSortOrder() == Order.DESC.ordinal()
                && meetingFilter.getFieldForSorting() == MeetingSortField.DATE
                        .ordinal()) {
            criteriaQuery
                    .orderBy(criteriaBuilder.desc(root.get(Meeting_.date)));

        }
        if (meetingFilter.getSortOrder() == Order.ASC.ordinal() && meetingFilter
                .getFieldForSorting() == MeetingSortField.STARTTIME.ordinal()) {
            criteriaQuery
                    .orderBy(criteriaBuilder.asc(root.get(Meeting_.startTime)));

        }
        if (meetingFilter.getSortOrder() == Order.DESC.ordinal()
                && meetingFilter
                        .getFieldForSorting() == MeetingSortField.STARTTIME
                                .ordinal()) {
            criteriaQuery
                    .orderBy(criteriaBuilder.asc(root.get(Meeting_.endTime)));
        }
        if (meetingFilter.getSortOrder() == Order.ASC.ordinal() && meetingFilter
                .getFieldForSorting() == MeetingSortField.ENDTIME.ordinal()) {
            criteriaQuery
                    .orderBy(criteriaBuilder.asc(root.get(Meeting_.endTime)));
        }
        if (meetingFilter.getSortOrder() == Order.DESC.ordinal()
                && meetingFilter
                        .getFieldForSorting() == MeetingSortField.ENDTIME
                                .ordinal()) {
            criteriaQuery
                    .orderBy(criteriaBuilder.desc(root.get(Meeting_.endTime)));
        }
        if (meetingFilter.getSortOrder() == Order.ASC.ordinal() && meetingFilter
                .getFieldForSorting() == MeetingSortField.LEVEL.ordinal()) {
            criteriaQuery
                    .orderBy(criteriaBuilder.asc(root.get(Meeting_.level)));
        }
        if (meetingFilter.getSortOrder() == Order.DESC.ordinal()
                && meetingFilter.getFieldForSorting() == MeetingSortField.LEVEL
                        .ordinal()) {
            criteriaQuery
                    .orderBy(criteriaBuilder.desc(root.get(Meeting_.level)));
        }
        if (meetingFilter.getSortOrder() == Order.ASC.ordinal() && meetingFilter
                .getFieldForSorting() == MeetingSortField.STATUS.ordinal()) {
            criteriaQuery
                    .orderBy(criteriaBuilder.asc(root.get(Meeting_.status)));
        }
        if (meetingFilter.getSortOrder() == Order.DESC.ordinal()
                && meetingFilter.getFieldForSorting() == MeetingSortField.STATUS
                        .ordinal()) {
            criteriaQuery
                    .orderBy(criteriaBuilder.desc(root.get(Meeting_.status)));
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
            findByDescription();
            findBySubjectId();
            findByOwnerId();
            findByRoomId();
            findByDate();
            findByStartTime();
            findByEndTime();
            findByUserGroupDTOs();
            findByMaxMinLevel();
            findByStatus();
        }
        if (list.size() == 0) {
            return null;
        }
        Specifications<Meeting> spec = Specifications.where(list.get(0));
        for (int i = 1; i < list.size(); i++) {
            spec = spec.and(list.get(i));
        }
        return spec.toPredicate(root, criteriaQuery, criteriaBuilder);
    }

}
