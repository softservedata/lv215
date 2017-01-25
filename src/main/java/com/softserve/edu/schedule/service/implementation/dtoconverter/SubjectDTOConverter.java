/*
 * SubjectDTOConverter.java
 * 1.0
 * 23 Jan 2017
 * Copyright (c) Ped'ko Volodymyr
 */
package com.softserve.edu.schedule.service.implementation.dtoconverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.softserve.edu.schedule.dao.UserDAO;
import com.softserve.edu.schedule.dto.SubjectDTO;
import com.softserve.edu.schedule.entity.Subject;

/**
 * A class to provide conversion operations between SubjectDTO and Subject
 * entity.
 *
 * @version 1.0 17 January 2017
 *
 * @author Volodymyr Ped'ko
 *
 * @since 1.8
 */
@Component
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
     * @return a Subject object.
     */
    public Subject getEntity(final SubjectDTO subjectDTO) {
        Subject subject = new Subject();
        subject.setId(subjectDTO.getId());
        subject.setName(subjectDTO.getName());
        subject.setDescription(subjectDTO.getDescription());
        subjectDTO.getUsers().forEach(
                u -> subject.getUsers().add(userDAO.getById(u.getId())));
        return subject;
    }

    /**
     * Convert given Subject object to SubjectDTO object.
     * 
     * @param room
     *            a Subject object to convert.
     * 
     * @return a SubjectDTO object.
     */
    public SubjectDTO getDTO(final Subject subject) {
        SubjectDTO subjectDTO = new SubjectDTO();
        subjectDTO.setId(subject.getId());
        subjectDTO.setName(subject.getName());
        subjectDTO.setDescription(subject.getDescription());
        subject.getUsers().forEach(u -> subjectDTO.getUsers()
                .add(userForSubjectDTOConverter.getDTO(u)));
        return subjectDTO;
    }

}
