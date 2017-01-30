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
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.softserve.edu.schedule.dao.SubjectDAO;
import com.softserve.edu.schedule.dao.UserDAO;
import com.softserve.edu.schedule.dto.filter.Paginator;
import com.softserve.edu.schedule.dto.filter.SubjectFilter;
import com.softserve.edu.schedule.entity.Subject;
import com.softserve.edu.schedule.entity.Subject_;
import com.softserve.edu.schedule.entity.User;
import com.softserve.edu.schedule.entity.User_;
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
     * Return a List of searched tutors.
     *
     * @return List of searched tutors
     */
    @Override
    public List<Subject> searchSubjectsByTutor(final String pattern) {
        CriteriaBuilder builder = getEm().getCriteriaBuilder();
        CriteriaQuery<Subject> cq = builder.createQuery(Subject.class);
        Root<Subject> root = cq.from(Subject.class);
        Join<Subject, User> joinUser = root.join(Subject_.users);
        Predicate predicate = builder.like(joinUser.get(User_.lastName),
                SEARCH_MASK + pattern + SEARCH_MASK);
        cq.where(predicate);
        cq.distinct(true);
        return getEm().createQuery(cq).getResultList();
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

    @Override
    public List<Subject> getSubjectsPageWithFilter(SubjectFilter subjectFilter,
            Paginator subjectPaginator) {
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
        subjectPaginator.setPagesCount(
                getEm().createQuery(criteriaQuery).getResultList().size());
        return getEm().createQuery(criteriaQuery)
                .setFirstResult(subjectPaginator.getOffset())
                .setMaxResults(subjectPaginator.getPageSize()).getResultList();
    }
}
