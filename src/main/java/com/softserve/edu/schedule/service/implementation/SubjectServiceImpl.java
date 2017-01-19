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
    public void create(final Subject subject) {
        subjectDao.create(subject);
    }

    /**
     * Updating Subject in database.
     *
     * @param subject
     *            - Subject object
     */
    @Override
    public void update(final Subject subject) {
        subjectDao.update(subject);
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
    public Subject getByIdWhithDetails(Long id) {
        return subjectDao.getByIdWhithDetails(id);
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
     * Deleting subject in database.
     *
     * @param subject
     *            - Subject object
     */
    @Override
    public void delete(final Subject subject) {
        subjectDao.delete(subject);
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
     * Return a List of sorted Subject transfer objects.
     *
     * @param field
     *            for sort
     * @param order
     *            - ASC or DESC
     * @return List of sorted Subject transfer objects
     */
    @Override
    @Transactional(readOnly = true)
    public List<Subject> sort(final String field, final Order order) {
        return subjectDao.sort(field, order);
    }

    /**
     * Return a List of sorted Subject transfer objects.
     *
     * @param field
     *            for sort
     * @param order
     *            - ASC or DESC
     * @return List of sorted Subject transfer objects
     */
    @Override
    @Transactional(readOnly = true)
    public List<Subject> sortByName(Order order) {
        return subjectDao.sortByName(order);
    }
    
    /**
     * Return a List of sorted Subject transfer objects.
     *
     * @param field
     *            for sort
     * @param order
     *            - ASC or DESC
     * @return List of sorted Subject transfer objects
     */
    @Override
    @Transactional(readOnly = true)
    public List<Subject> sortByDescription(final Order order){
        return subjectDao.sortByDescription(order);
    }
    
    /**
     * Return a List of searched Subject transfer objects.
     *
     * @param field
     *            for search
     * @param pattern
     *            - input string
     * @return List of sorted Subject transfer objects
     */
    @Override
    @Transactional(readOnly = true)
    public List<Subject> search(final String field, final String pattern) {
        return subjectDao.search(field, pattern);
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
    public List<Subject> searchTutors(final String pattern) {
        return subjectDao.searchTutors(pattern);
    }


}
