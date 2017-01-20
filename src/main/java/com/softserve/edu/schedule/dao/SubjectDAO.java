/*
 * CrudDAO<E>.java
 * 1.0
 * 04 Jan 2017
 * Copyright (c) Ped'ko Volodymyr
 */
package com.softserve.edu.schedule.dao;

import java.util.List;

import com.softserve.edu.schedule.entity.Subject;

/**
 * A simple DAO interface to handle the database operation required to
 * manipulate Tutors in Subject.
 *
 * @version 1.0 04 Jan 2016
 * @author Ped'ko Volodymyr
 *
 */
public interface SubjectDAO extends CrudDAO<Subject> {

    /**
     * Return a List of searched Transfer objects.
     *
     * @return List of searched Transfer objects
     */
    List<Subject> searchTutors(final String pattern);

    /**
     * Return a List of searched Subjects fetching Users.
     *
     * @return List of searched Subject transfer objects
     */
    List<Subject> getAllWithDetails();
    
    /**
     * Return a Subject object if found.
     *
     * @param id
     *            of Subject object
     * @return room with given id
     */
    Subject getByIdWhithDetails(final Long id);

    /**
     * Return a List of searched Subjects fetching Users sorted by name.
     *
     * @return List of searched Subjects fetching Users sorted by name
     */
    List<Subject> sortByName(final Order order);
    
    /**
     * Return a List of searched Subjects fetching Users sorted by description.
     *
     * @return List of searched Subjects fetching Users sorted by description
     */
    List<Subject> sortByDescription(final Order order);

    /**
     * Delete existed transfer object from the database by id.
     *
     * @param id
     *            a subject id to delete from database.
     */
    void deleteById(final Long id);
    
    public List<Subject> sortByField(final String field, final Order order);
}
