/*
 * SubjectServiceImpl.java
 * 1.0
 * 03 Jan 2017
 * Copyright (c) Ped'ko Volodymyr
 */
package com.softserve.edu.schedule.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.edu.schedule.dao.Order;
import com.softserve.edu.schedule.dao.SubjectDAO;
import com.softserve.edu.schedule.dto.SubjectDTO;
import com.softserve.edu.schedule.entity.Subject;
import com.softserve.edu.schedule.entity.Subject_;
import com.softserve.edu.schedule.service.SubjectService;
import com.softserve.edu.schedule.service.implementation.dtoconverter.SubjectDTOConverter;

/**
 * A SubjectService implementation to handle the operation required to
 * manipulate an Subject menu.
 *
 * @version 1.0 03 Jan 2016
 * @author Ped'ko Volodymyr
 *
 */
@Transactional
@Service("subjectService")
public class SubjectServiceImpl implements SubjectService {

    /**
     * Field for subjectDAO.
     */
    @Autowired
    private SubjectDAO subjectDao;

    @Autowired
    private SubjectDTOConverter subjectDTOConverter;

    /**
     * Saving Subject in database.
     *
     * @param subject
     *            - Subject object
     */
    @Override
    public void create(final SubjectDTO subject) {
        subjectDao.create(subjectDTOConverter.getEntity(subject));
    }

    /**
     * Updating Subject in database.
     *
     * @param subject
     *            - Subject object
     */
    @Override
    public void update(final SubjectDTO subject) {
        subjectDao.update(subjectDTOConverter.getEntity(subject));
    }

    /**
     * Return a Subject object if found.
     *
     * @param id
     *            of Subject transfer object
     * @return Subject transfer object
     */
    @Override
    @Transactional(readOnly = true)
    public Subject getById(final Long id) {
        return subjectDao.getById(id);
    }

    /**
     * Return a Subject object if found.
     *
     * @param id
     *            of Subject object
     * @return room with given id
     */
    @Override
    public SubjectDTO getByIdWhithDetails(final Long id) {
        return subjectDTOConverter.getDTO(subjectDao.getByIdWhithDetails(id));
    }

    /**
     * Return a List of Subject objects.
     *
     * @return List of Subject objects
     */
    @Override
    @Transactional(readOnly = true)
    public List<Subject> getAll() {
        return subjectDao.getAll();
    }

    /**
     * Return a List of searched Subjects fetching Users.
     *
     * @return List of searched Subject transfer objects
     */
    @Override
    @Transactional(readOnly = true)
    public List<SubjectDTO> getAllWithDetails() {
        return subjectDao.getAllWithDetails().stream()
                .map(s -> subjectDTOConverter.getDTO(s))
                .collect(Collectors.toList());
    }

    /**
     * Delete existed transfer object from the database by id.
     *
     * @param id
     *            a Subject id to delete from database.
     */
    @Override
    public void deleteById(Long id) {
        subjectDao.deleteById(id);
    }

    /**
     * Return a List of searched Subject transfer objects.
     *
     * @param pattern
     *            - input string
     * @return List of sorted Subject transfer objects
     */
    @Override
    @Transactional(readOnly = true)
    public List<SubjectDTO> searchByName(final String pattern) {
        return subjectDao.search(Subject_.name.getName(), pattern).stream()
                .map(s -> subjectDTOConverter.getDTO(s))
                .collect(Collectors.toList());
    }
    
    /**
     * Return a List of searched Subject transfer objects.
     *
     * @param pattern
     *            - input string
     * @return List of sorted Subject transfer objects
     */
    @Override
    @Transactional(readOnly = true)
    public List<SubjectDTO> searchByDescription(final String pattern) {
        return subjectDao.search(Subject_.description.getName(), pattern).stream()
                .map(s -> subjectDTOConverter.getDTO(s))
                .collect(Collectors.toList());
    }

    /**
     * Return a List of searched Subject transfer objects containing searched
     * tutor.
     *
     * @param pattern
     *            searched tutor
     * @return List of searched Subject transfer objects containing searched
     *         tutor
     */
    @Override
    @Transactional(readOnly = true)
    public List<SubjectDTO> searchByTutors(final String pattern) {
        return subjectDao.searchTutors(pattern).stream()
                .map(s -> subjectDTOConverter.getDTO(s))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<SubjectDTO> sortByName(Order order) {
        return subjectDao.sort(Subject_.name.getName(), order).stream()
                .map(s -> subjectDTOConverter.getDTO(s))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<SubjectDTO> sortByDescription(Order order) {
        return subjectDao.sort(Subject_.description.getName(), order)
                .stream().map(s -> subjectDTOConverter.getDTO(s))
                .collect(Collectors.toList());
    }
}
