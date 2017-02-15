/*
 * SubjectService.java
 * 1.0
 * 03 Jan 2017
 * Copyright (c) Ped'ko Volodymyr
 */
package com.softserve.edu.schedule.service;

import java.util.List;

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
    public void create(SubjectDTO subject);

    /**
     * Updating Subject in database.
     *
     * @param subject
     *            - SubjectDTO object
     */
    public void update(SubjectDTO subject);

    /**
     * Return a SubjectDTO object if found.
     *
     * @param id
     *            of Subject transfer object
     * @return SubjectDTO object
     */
    public SubjectDTO getById(Long id);

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
    public void deleteById(Long id);

    /**
     * Return a searched SubjectDTO.
     *
     * @return searched SubjectDTO
     */
    public List<SubjectDTO> getSubjectByName(String subjectName);

    /**
     * Find all subjects entities in the database with applied filter
     * 
     * @param subjectFilter
     *            a filter to apply.
     * @param subjectPaginator
     *            the subjectPaginator to set
     * @return List of the subject DTO objects.
     */
    List<SubjectDTO> getSubjectsPageWithFilter(SubjectFilter subjectFilter,
            Paginator subjectPaginator);

}
