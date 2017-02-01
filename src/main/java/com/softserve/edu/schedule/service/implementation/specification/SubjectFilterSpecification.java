/*
 * SubjectDTO.java
 * 1.0
 * 15 Jan 2017
 * Copyright (c) Ped'ko Volodymyr
 */
package com.softserve.edu.schedule.service.implementation.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import com.softserve.edu.schedule.dao.UserDAO;
import com.softserve.edu.schedule.dto.filter.SubjectFilter;
import com.softserve.edu.schedule.entity.Subject;
import com.softserve.edu.schedule.entity.Subject_;
import com.softserve.edu.schedule.entity.User;

/**
 * A class to provide predicate based on given SubjectFilter example. This
 * predicate is used to search rooms in database by given filter parameters.
 *
 * @version 1.0 31 January 2017
 *
 * @author Ped'ko Volodymyr
 *
 * @since 1.8
 */
public class SubjectFilterSpecification implements Specification<Subject> {

    /**
     * SubjectFilter example which provides parameters to build predicate.
     */
    private SubjectFilter filter;

    /**
     * UserDAO example to provide database operations.
     */
    private UserDAO userDao;

    /**
     * List of subject specifications based on which the predicate is built.
     */
    private List<Specification<Subject>> list = new ArrayList<>();

    /**
     * Constructor of SubjectFilterSpecification.
     * 
     * @param filter
     *            SubjectFilter example which provides parameters to build
     *            predicate.
     * 
     * @param userDAO
     *            userDAO example to provide database operations.
     */
    public SubjectFilterSpecification(final SubjectFilter filter,
            final UserDAO userDAO) {
        this.filter = filter;
        this.userDao = userDAO;
    }

    /**
     * Add subject name specification to specification list if filter contains
     * subject name parameter.
     */
    private void findByName() {
        if (filter.getName() != null && !filter.getName().trim().equals("")) {
            list.add((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder
                    .like(root.get(Subject_.name),
                            "%" + filter.getName() + "%"));
        }
    }

    /**
     * Add subject description specification to specification list if filter contains
     * subject description parameter.
     */
    private void findByDescription() {
        if (filter.getDescription() != null
                && !filter.getDescription().trim().equals("")) {
            list.add((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder
                    .like(root.get(Subject_.description),
                            "%" + filter.getDescription() + "%"));
        }
    }

    /**
     * Add subject user specification to specification list if filter contains
     * user description parameter.
     */
    private void findByUserId() {
        if (filter.getUserId() != null && filter.getUserId() > 0) {
            User user = userDao.getById(filter.getUserId());
            list.add((root, criteriQuery, criteriaBuilder) -> criteriaBuilder
                    .isMember(user, root.get(Subject_.users)));
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
    private void setSortingParameters(final Root<Subject> root,
            final CriteriaQuery<?> criteriaQuery,
            final CriteriaBuilder criteriaBuilder) {
        if (filter.getSortOrder() == 1) {
            if (filter.getSortByField() == 1) {
                criteriaQuery
                        .orderBy(criteriaBuilder.asc(root.get(Subject_.name)));
            }
            if (filter.getSortByField() == 2) {
                criteriaQuery.orderBy(
                        criteriaBuilder.asc(root.get(Subject_.description)));
            }

        } else if (filter.getSortOrder() == 2) {
            if (filter.getSortByField() == 1) {
                criteriaQuery
                        .orderBy(criteriaBuilder.desc(root.get(Subject_.name)));
            }
            if (filter.getSortByField() == 2) {
                criteriaQuery.orderBy(
                        criteriaBuilder.desc(root.get(Subject_.description)));
            }
        } else {
            criteriaQuery.orderBy(criteriaBuilder.asc(root.get(Subject_.id)));
        }
    }

    /**
     * Build predicate based on given SubjectFilter parameters.
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
     * @return a Predicate example to search subjects in database by given filter
     *         parameters.
     */
    @Override
    public Predicate toPredicate(Root<Subject> root,
            CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        root.join(Subject_.users, JoinType.LEFT);
        setSortingParameters(root, criteriaQuery, criteriaBuilder);
            findByName();
            findByDescription();
            findByUserId();
        if (list.size() == 0) {
            return null;
        }
        Specifications<Subject> spec = Specifications.where(list.get(0));
        for (int i = 1; i < list.size(); i++) {
            spec = spec.and(list.get(i));
        }
        return spec.toPredicate(root, criteriaQuery, criteriaBuilder);
    }

}
