/*
 * SubjectDTOConverter.java
 * 1.0
 * 23 Jan 2017
 * Copyright (c) Ped'ko Volodymyr
 */
package com.softserve.edu.schedule.service.implementation.dtoconverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserve.edu.schedule.dao.UserDAO;
import com.softserve.edu.schedule.dto.SubjectDTO;
import com.softserve.edu.schedule.entity.Subject;

/**
 * A class to provide conversion operations between SubjectDTO and Subject entity.
 *
 * @version 1.0 17 January 2017
 *
 * @author Volodymyr Ped'ko
 *
 * @since 1.8
 */
@Service
public class SubjectDTOConverter {

    /**
     * UserDAO example to provide database operations.
     */
    @Autowired
    private UserDAO userDAO;

    /**
     * UserForSubjectDTOConverter to provide convertations.
     */
    @Autowired
    private UserForSubjectDTOConverter userForSubjectDTOConverter;

    /**
     * Convert given SubjectDTO object to Subject object
     * 
     * @param subjectDTO
     *            a SubjectDTO object to convert.
     * 
     * @return a Subject object or null if given @param subjectDTO is null.
     */
    public Subject getEntity(SubjectDTO subjectDTO) {
        if (subjectDTO != null) {
            Subject subject = new Subject();
            if (subjectDTO.getId() != null) {
                subject.setId(subjectDTO.getId());
            }
            if (subjectDTO.getName() != null) {
                subject.setName(subjectDTO.getName());
            }
            if (subjectDTO.getDescription() != null) {
                subject.setDescription(subjectDTO.getDescription());
            }
            if (subjectDTO.getUsers() != null) {
                subjectDTO.getUsers().forEach(u -> subject.getUsers()
                        .add(userDAO.getById(u.getId())));
            }
            return subject;
        }
        return null;
    }

    /**
     * Convert given Subject object to SubjectDTO object.
     * 
     * @param room
     *            a Subject object to convert.
     * 
     * @return a SubjectDTO object or null if given @param room is null.
     */
    public SubjectDTO getDTO(Subject subject) {
        if (subject != null) {
            SubjectDTO subjectDTO = new SubjectDTO();
            if (subject.getId() != null) {
                subjectDTO.setId(subject.getId());
            }
            if (subject.getName() != null) {
                subjectDTO.setName(subject.getName());
            }
            if (subject.getDescription() != null) {
                subjectDTO.setDescription(subject.getDescription());
            }
            subject.getUsers().forEach(u -> subjectDTO.getUsers()
                    .add(userForSubjectDTOConverter.getDTO(u)));
            return subjectDTO;
        }
        return null;
    }

}
