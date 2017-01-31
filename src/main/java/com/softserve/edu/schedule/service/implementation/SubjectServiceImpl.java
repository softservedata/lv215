/*
 * SubjectServiceImpl.java
 * 1.0
 * 03 Jan 2017
 * Copyright (c) Ped'ko Volodymyr
 */
package com.softserve.edu.schedule.service.implementation;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.edu.schedule.dao.SubjectDAO;
import com.softserve.edu.schedule.dao.UserDAO;
import com.softserve.edu.schedule.dto.SubjectDTO;
import com.softserve.edu.schedule.dto.UserForSubjectDTO;
import com.softserve.edu.schedule.dto.filter.Paginator;
import com.softserve.edu.schedule.dto.filter.SubjectFilter;
import com.softserve.edu.schedule.entity.MeetingStatus;
import com.softserve.edu.schedule.entity.Subject;
import com.softserve.edu.schedule.service.SubjectService;
import com.softserve.edu.schedule.service.implementation.dtoconverter.SubjectDTOConverter;
import com.softserve.edu.schedule.service.implementation.dtoconverter.UserForSubjectDTOConverter;

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

    @Autowired
    private UserDAO userDao;

    /**
     * Field for subjectDTOConverter.
     */
    @Autowired
    private SubjectDTOConverter subjectDTOConverter;

    /**
     * Field for userForSubjectDTOconverter.
     */
    @Autowired
    private UserForSubjectDTOConverter userForSubjectDTOconverter;

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
     * Return a searched SubjectDTO.
     *
     * @return searched SubjectDTO
     */
    @Override
    public List<SubjectDTO> getSubjectByName(final String subjectName) {
        return subjectDao.getSubjectByName(subjectName).stream()
                .map(s -> subjectDTOConverter.getDTO(s))
                .collect(Collectors.toList());
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
     * Get all UserForSubjectDTO.
     *
     * @return List of the UserForSubjectDTO objects for SubjectDTO.
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserForSubjectDTO> getAllUserForSubjectDTO() {
        return userDao.getAll().stream()
                .map(u -> userForSubjectDTOconverter.getDTO(u))
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
        Subject subject = subjectDao.getById(id);
        subject.getMeetings().forEach(m -> {
            if (m.getDate().isAfter(LocalDate.now().minusDays(1))) {
                m.setStatus(MeetingStatus.NOT_APPROVED);
            }
            m.setSubject(null);
        });
        subjectDao.delete(subject);
    }

    /**
     * Find all subjects entities in the database with applied filter
     * 
     * @param subjectFilter
     *            a filter to apply.
     * @param subjectPaginator
     *            the subjectPaginator to set
     * @return List of the subject DTO objects.
     */
    @Override
    @Transactional(readOnly = true)
    public List<SubjectDTO> getSubjectsPageWithFilter(
            SubjectFilter subjectFilter, Paginator subjectPaginator) {
        return subjectDao
                .getSubjectsPageWithFilter(subjectFilter, subjectPaginator)
                .stream().map(s -> subjectDTOConverter.getDTO(s))
                .collect(Collectors.toList());
    }
}
