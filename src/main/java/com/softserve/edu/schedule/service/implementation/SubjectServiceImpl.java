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
import com.softserve.edu.schedule.entity.Subject_;
import com.softserve.edu.schedule.service.SubjectService;
import com.softserve.edu.schedule.service.implementation.dtoconverter.SubjectDTOConverter;

/**
 * A SubjectService implementation to handle the operation required to
 * manipulate an Subject object and Subject DTO object.
 *
 * @version 1.0 03 Jan 2016
 * @author Ped'ko Volodymyr
 *
 */
@Service
public class SubjectServiceImpl implements SubjectService {

    /**
     * Field for subjectDAO.
     */
    @Autowired
    private SubjectDAO subjectDao;

    /**
     * Field for subjectDTOConverter.
     */
    @Autowired
    private SubjectDTOConverter subjectDTOConverter;

    /**
     * Saving Subject in database.
     *
     * @param subject
     *            - SubjectDTO object
     */
    @Override
    @Transactional
    public void create(final SubjectDTO subject) {
        subjectDao.create(subjectDTOConverter.getEntity(subject));
    }

    /**
     * Updating Subject in database.
     *
     * @param subject
     *            - SubjectDTO object
     */
    @Override
    @Transactional
    public void update(final SubjectDTO subject) {
        subjectDao.update(subjectDTOConverter.getEntity(subject));
    }

    /**
     * Return a SubjectDTO object if found.
     *
     * @param id
     *            of Subject transfer object
     * @return SubjectDTO object
     */
    @Override
    @Transactional(readOnly = true)
    public SubjectDTO getById(final Long id) {
        return subjectDTOConverter.getDTO(subjectDao.getById(id));
    }

    /**
     * Return a List of SubjectDTO objects.
     *
     * @return List of SubjectDTO objects
     */
    @Override
    @Transactional(readOnly = true)
    public List<SubjectDTO> getAll() {
        return subjectDao.getAll().stream()
                .map(s -> subjectDTOConverter.getDTO(s))
                .collect(Collectors.toList());
    }

    /**
     * Delete existed Subject from the database by id.
     *
     * @param id
     *            a SubjectDTO id to delete from database.
     */
    @Override
    @Transactional
    public void deleteById(Long id) {
        subjectDao.deleteById(id);
    }

    /**
     * Return a List of searched SubjectDTO objects.
     *
     * @param pattern
     *            - input string
     * @return List of searched SubjectDTO transfer objects
     */
    @Override
    @Transactional(readOnly = true)
    public List<SubjectDTO> searchByName(final String pattern) {
        return subjectDao.search(Subject_.name.getName(), pattern).stream()
                .map(s -> subjectDTOConverter.getDTO(s))
                .collect(Collectors.toList());
    }

    /**
     * Return a List of searched SubjectDTO objects.
     *
     * @param pattern
     *            - input string
     * @return List of searched SubjectDTO objects
     */
    @Override
    @Transactional(readOnly = true)
    public List<SubjectDTO> searchByDescription(final String pattern) {
        return subjectDao.search(Subject_.description.getName(), pattern)
                .stream().map(s -> subjectDTOConverter.getDTO(s))
                .collect(Collectors.toList());
    }

    /**
     * Return a List of searched SubjectDTO objects containing some tutor.
     *
     * @param pattern
     *            - input string
     * @return List of searched SubjectDTO objects containing some tutor
     */
    @Override
    @Transactional(readOnly = true)
    public List<SubjectDTO> searchByTutors(final String pattern) {
        return subjectDao.searchTutors(pattern).stream()
                .map(s -> subjectDTOConverter.getDTO(s))
                .collect(Collectors.toList());
    }

    /**
     * Return a sorted by name List of SubjectDTO objects.
     *
     * @param order
     *            - order of sort
     * @return a sorted by name List of SubjectDTO objects
     */
    @Override
    @Transactional(readOnly = true)
    public List<SubjectDTO> sortByName(Order order) {
        return subjectDao.sort(Subject_.name.getName(), order).stream()
                .map(s -> subjectDTOConverter.getDTO(s))
                .collect(Collectors.toList());
    }

    /**
     * Return a sorted by description List of SubjectDTO objects.
     *
     * @param order
     *            - order of sort
     * @return a sorted by description List of SubjectDTO objects
     */
    @Override
    @Transactional(readOnly = true)
    public List<SubjectDTO> sortByDescription(Order order) {
        return subjectDao.sort(Subject_.description.getName(), order).stream()
                .map(s -> subjectDTOConverter.getDTO(s))
                .collect(Collectors.toList());
    }
}
