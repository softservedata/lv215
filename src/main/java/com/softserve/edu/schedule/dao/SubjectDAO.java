/*
 * SubjectDAO.java
 * 1.0
 * 04 Jan 2017
 * Copyright (c) Ped'ko Volodymyr
 */
package com.softserve.edu.schedule.dao;

import java.util.List;

import com.softserve.edu.schedule.dto.filter.Paginator;
import com.softserve.edu.schedule.dto.filter.SubjectFilter;
import com.softserve.edu.schedule.entity.Subject;

/**
 * A simple DAO interface to handle the database operation required to
 * manipulate in database.
 *
 * @version 1.0 04 Jan 2016
 * @author Ped'ko Volodymyr
 *
 */
public interface SubjectDAO extends CrudDAO<Subject> {

    /**
     * Return a searched Subjects.
     *
     * @param subjectName
     *
     * @return searched Subjects
     */
    public List<Subject> getSubjectByName(final String subjectName);

    /**
     * Find all subjects entities in the database with applied filter
     * 
     * @param subjectFilter
     *            a filter to apply.
     *            
     * @param subjectPaginator           
     * 
     * @return List of the subject objects.
     */
    public List<Subject> getSubjectsPageWithFilter(final SubjectFilter subjectFilter,
            final Paginator subjectPaginator);

    /**
     * Count subjects entities in the database with specified filter
     *
     * @param subjectFilter
     *            
     * @return Count of the subject entities in the database with filter.
     */
    public Long getCountOfSubjectsWithFilter(final SubjectFilter subjectFilter);

    /**
     * Return a searched Subject.
     *
     *@param id
     *
     * @return searched Subject
     */
    public Subject getSubjectsWithMeetingDetailsById(final Long id);
}
