/*
 * SubjectDAOImpl.java
 * 1.0
 * 04 Jan 2017
 * Copyright (c) Ped'ko Volodymyr
 */
package com.softserve.edu.schedule.dao.implementation;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.softserve.edu.schedule.dao.SubjectDAO;
import com.softserve.edu.schedule.dao.UserDAO;
import com.softserve.edu.schedule.dto.filter.Paginator;
import com.softserve.edu.schedule.dto.filter.SubjectFilter;
import com.softserve.edu.schedule.entity.Meeting_;
import com.softserve.edu.schedule.entity.Subject;
import com.softserve.edu.schedule.entity.Subject_;
import com.softserve.edu.schedule.service.implementation.specification.SubjectFilterSpecification;

/**
 * A SubjectDAO implementation to handle the database operation (CRUD).
 *
 * @version 1.0 04 Jan 2016
 * @author Ped'ko Volodymyr
 *
 */
@Repository
public class SubjectDAOImpl extends CrudDAOImpl<Subject> implements SubjectDAO {

    @Autowired
    UserDAO userDao;
    
    /**
     * Overridden default constructor to provide entity class for DAO.
     * 
     */
    public SubjectDAOImpl() {
        super(Subject.class);
    }

    /**
     * Return a searched Subject.
     *
     * @return searched Subject
     */
    @Override
    public List<Subject> getSubjectByName(final String subjectName) {
        CriteriaBuilder builder = getEm().getCriteriaBuilder();
        CriteriaQuery<Subject> cq = builder.createQuery(Subject.class);
        Root<Subject> root = cq.from(Subject.class);
        root.fetch(Subject_.users, JoinType.LEFT);
        cq.where(builder.like(root.get(Subject_.name), subjectName));
        return getEm().createQuery(cq).getResultList();
    }

    /**
     * Return a searched Subject.
     *
     * @return searched Subject
     */
    @Override
    public Subject getSubjectsWithMeetingDetailsById(final Long id) {
        CriteriaBuilder builder = getEm().getCriteriaBuilder();
        CriteriaQuery<Subject> cq = builder.createQuery(Subject.class);
        Root<Subject> root = cq.from(Subject.class);
        root.fetch(Subject_.meetings, JoinType.LEFT).fetch(Meeting_.owner,
                JoinType.LEFT);
        cq.where(builder.equal(root.get(Subject_.id), id));
        return getEm().createQuery(cq).getSingleResult();
    }

    /**
     * Find all subjects entities in the database with applied filter
     * 
     * @param subjectFilter
     *            a filter to apply.
     * 
     * @return List of the subject objects.
     */
    @Override
    public List<Subject> getSubjectsPageWithFilter(
            final SubjectFilter subjectFilter,
            final Paginator subjectPaginator) {
        CriteriaBuilder builder = getEm().getCriteriaBuilder();
        CriteriaQuery<Subject> criteriaQuery = builder
                .createQuery(Subject.class);
        Root<Subject> root = criteriaQuery.from(Subject.class);
        Predicate predicate = new SubjectFilterSpecification(subjectFilter,
                userDao).toPredicate(root, criteriaQuery, builder);
        if (predicate != null) {
            criteriaQuery.where(predicate);
        }
        criteriaQuery.distinct(true);
        subjectPaginator
                .setPagesCount(getCountOfSubjectsWithFilter(subjectFilter));
        return getEm().createQuery(criteriaQuery)
                .setFirstResult(subjectPaginator.getOffset())
                .setMaxResults(subjectPaginator.getPageSize()).getResultList();
    }

    /**
     * Count subjects entities in the database with specified filter
     *
     * @param predicate
     *            a predicate to apply.
     *
     * @return Count of the subject entities in the database with specified
     *         predicate.
     */
    @Override
    public Long getCountOfSubjectsWithFilter(
            final SubjectFilter subjectFilter) {
        CriteriaBuilder cb = getEm().getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Subject> root = cq.from(Subject.class);
        cq.select(cb.countDistinct(root));
        Predicate predicate = new SubjectFilterSpecification(subjectFilter,
                userDao).toPredicate(root, cq, cb);
        if (predicate != null) {
            cq.where(predicate);
        }
        return getEm().createQuery(cq).getSingleResult();
    }
}
