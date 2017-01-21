/*
 * SubjectService.java
 * 1.0
 * 03 Jan 2017
 * Copyright (c) Ped'ko Volodymyr
 */
package com.softserve.edu.schedule.service;

import java.util.List;

import com.softserve.edu.schedule.dao.Order;
import com.softserve.edu.schedule.dto.SubjectDTO;

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
    void create(final SubjectDTO subject);

    /**
     * Updating Subject in database.
     *
     * @param subject
     *            - Subject object
     */
    void update(final SubjectDTO subject);

    /**
     * Return a Subject object if found.
     *
     * @param id
     *            of Subject transfer object
     * @return Subject transfer object
     */
    SubjectDTO getById(final Long id);
    
    /**
     * Return a List of Subject objects.
     *
     * @return List of Subject objects
     */
    List<SubjectDTO> getAll();
        
    /**
     * Delete existed transfer object from the database by id.
     *
     * @param id
     *            a subject id to delete from database.
     */
    void deleteById(final Long id);
    
    /**
     * Return a List of searched Subject transfer objects.
     *
     * @param pattern
     *            - input string
     * @return List of sorted Subject transfer objects
     */
    List<SubjectDTO> searchByName(final String pattern);

    /**
     * Return a List of searched Subject transfer objects.
     *
     * @param pattern
     *            - input string
     * @return List of sorted Subject transfer objects
     */
    List<SubjectDTO> searchByDescription(final String pattern);
    
    /**
     * Return a List of searched Subject transfer objects containing searched
     * tutor.
     *
     * @param pattern
     *            searched tutor
     * @return List of searched Subject transfer objects containing searched
     *         tutor
     */
    List<SubjectDTO> searchByTutors(final String pattern);
    
    public List<SubjectDTO> sortByName( final Order order);
    
    public List<SubjectDTO> sortByDescription( final Order order);


}
