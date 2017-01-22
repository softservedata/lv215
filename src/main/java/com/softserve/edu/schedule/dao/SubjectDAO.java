/*
 * SubjectDAO.java
 * 1.0
 * 04 Jan 2017
 * Copyright (c) Ped'ko Volodymyr
 */
package com.softserve.edu.schedule.dao;

import java.util.List;

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
     * Return a List of searched tutors.
     *
     * @return List of searched tutors
     */
    public List<Subject> searchTutors(final String pattern);

    /**
     * Delete existed subject from the database by id.
     *
     * @param id
     *            a subject id to delete from database.
     */
    public void deleteById(final Long id);
}
