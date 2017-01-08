/*
 * SubjectService.java
 * 1.0
 * 03 Jan 2017
 * Copyright (c) Ped'ko Volodymyr
 */
package com.softserve.edu.schedule.service;

import java.util.List;

import com.softserve.edu.schedule.dao.Order;
import com.softserve.edu.schedule.entity.Subject;

/**
 * A simple service interface to handle the operation required to manipulate an
 * Subject menu.
 *
 * @version 1.0 04 Jan 2016
 * @author Ped'ko Volodymyr
 *
 */
public interface SubjectService {

    /**
     * Saving Subject in database.
     *
     * @param subject
     *            - Subject object
     */
    void create(final Subject subject);

    /**
     * Updating Subject in database.
     *
     * @param subject
     *            - Subject object
     */
    void update(final Subject subject);

    /**
     * Return a Subject object if found.
     *
     * @param id
     *            of Subject transfer object
     * @return Subject transfer object
     */
    Subject getById(final Long id);

    /**
     * Return a List of Subject objects.
     *
     * @return List of Subject objects
     */
    List<Subject> getAll();

    /**
     * Deleting subject in database.
     *
     * @param subject
     *            - Subject object
     */
    void delete(final Subject subject);

    /**
     * Return a List of sorted Subject transfer objects.
     *
     * @param field
     *            for sort
     * @param order
     *            - ASC or DESC
     * @return List of sorted Subject transfer objects
     */
    List<Subject> sort(final String field, final Order order);

    /**
     * Return a List of searched Subject transfer objects.
     *
     * @param field
     *            for search
     * @param pattern
     *            - input string
     * @return List of sorted Subject transfer objects
     */
    List<Subject> search(final String field, final String pattern);

    /**
     * Return a List of searched Subject transfer objects containing searched
     * tutor.
     *
     * @param pattern
     *            searched tutor
     * @return List of searched Subject transfer objects containing searched
     *         tutor
     */
    List<Subject> searchTutors(final String pattern);

}
