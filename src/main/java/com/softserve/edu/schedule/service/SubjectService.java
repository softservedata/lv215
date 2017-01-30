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
import com.softserve.edu.schedule.dto.UserForSubjectDTO;
import com.softserve.edu.schedule.dto.filter.Paginator;
import com.softserve.edu.schedule.dto.filter.SubjectFilter;

/**
 * A simple service interface to handle the operation required to manipulate an
 * Subject object and SubjectDTO object.
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
     *            - SubjectDTO object
     */
    public void create(final SubjectDTO subject);

    /**
     * Updating Subject in database.
     *
     * @param subject
     *            - SubjectDTO object
     */
    public void update(final SubjectDTO subject);

    /**
     * Return a SubjectDTO object if found.
     *
     * @param id
     *            of Subject transfer object
     * @return SubjectDTO object
     */
    public SubjectDTO getById(final Long id);

    /**
     * Return a List of SubjectDTO objects.
     *
     * @return List of SubjectDTO objects
     */
    public List<SubjectDTO> getAll();

    /**
     * Get all UserForSubjectDTO.
     *
     * @return List of the UserForSubjectDTO objects for SubjectDTO.
     */
    public List<UserForSubjectDTO> getAllUserForSubjectDTO();

    /**
     * Delete existed Subject from the database by id.
     *
     * @param id
     *            a SubjectDTO id to delete from database.
     */
    public void deleteById(final Long id);

    /**
     * Return a List of searched SubjectDTO objects.
     *
     * @param pattern
     *            - input string
     * @return List of searched SubjectDTO transfer objects
     */
    public List<SubjectDTO> searchByName(final String pattern);

    /**
     * Return a searched SubjectDTO.
     *
     * @return searched SubjectDTO
     */
    public List<SubjectDTO> getSubjectByName(final String subjectName);

    /**
     * Return a List of searched SubjectDTO objects.
     *
     * @param pattern
     *            - input string
     * @return List of searched SubjectDTO objects
     */
    public List<SubjectDTO> searchByDescription(final String pattern);

    /**
     * Return a List of searched SubjectDTO objects containing some tutor.
     *
     * @param pattern
     *            - input string
     * @return List of searched SubjectDTO objects containing some tutor
     */
    public List<SubjectDTO> searchByTutors(final String pattern);

    /**
     * Return a sorted by name List of SubjectDTO objects.
     *
     * @param order
     *            - order of sort
     * @return a sorted by name List of SubjectDTO objects
     */
    public List<SubjectDTO> sortByName(final Order order);

    /**
     * Return a sorted by description List of SubjectDTO objects.
     *
     * @param order
     *            - order of sort
     * @return a sorted by description List of SubjectDTO objects
     */
    public List<SubjectDTO> sortByDescription(final Order order);

    List<SubjectDTO> getSubjectsPageWithFilter(
            final SubjectFilter subjectFilter,
            final Paginator subjectPaginator);

}
