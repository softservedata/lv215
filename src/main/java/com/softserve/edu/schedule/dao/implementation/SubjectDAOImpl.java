/*
 * CrudDAO<E>.java
 * 1.0
 * 04 Jan 2017
 * Copyright (c) Ped'ko Volodymyr
 */
package com.softserve.edu.schedule.dao.implementation;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.softserve.edu.schedule.dao.SubjectDAO;
import com.softserve.edu.schedule.entity.Subject;
import com.softserve.edu.schedule.entity.Subject_;
import com.softserve.edu.schedule.entity.User;
import com.softserve.edu.schedule.entity.User_;

/**
 * A simple class to handle the database operation (CRUD).
 *
 * @version 1.0 04 Jan 2016
 * @author Ped'ko Volodymyr
 *
 */
@Repository("subjectDAO")
public class SubjectDAOImpl extends CrudDAOImpl<Subject> implements SubjectDAO {

    /**
     * Overridden default constructor to provide entity class for DAO.
     * 
     */
    public SubjectDAOImpl() {
        super(Subject.class);
    }

    /**
     * Return a List of searched Transfer objects.
     *
     * @return List of searched Transfer objects
     */
    @Override
    public List<Subject> searchTutors(final String pattern) {
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
     * Delete existed transfer object from the database by id.
     *
     * @param id
     *            a subject id to delete from database.
     */
    @Override
    public void deleteById(Long id) {
        delete(getById(id));
    }
}
