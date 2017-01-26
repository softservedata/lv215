/*
 * SubjectDTOConverter.java
 * 1.0
 * 23 Jan 2017
 * Copyright (c) Ped'ko Volodymyr
 */
package com.softserve.edu.schedule.service.implementation.dtoconverter;

import org.springframework.stereotype.Component;

import com.softserve.edu.schedule.dto.UserForSubjectDTO;
import com.softserve.edu.schedule.entity.User;

/**
 * A class to provide conversion for UserForSubjectDTO.
 *
 * @version 1.0 17 January 2017
 *
 * @author Volodymyr Ped'ko
 *
 * @since 1.8
 */
@Component
public class UserForSubjectDTOConverter {

    /**
     * Convert given User object to UserForSubjectDTO object.
     * 
     * @param room
     *            a User object to convert.
     * 
     * @return a UserForSubjectDTO object or null if given @param room is null.
     */
    public UserForSubjectDTO getDTO(final User user) {
        UserForSubjectDTO subjectUsersDTO = new UserForSubjectDTO();
        subjectUsersDTO.setId(user.getId());
        subjectUsersDTO.setFirstName(user.getFirstName());
        subjectUsersDTO.setLastName(user.getLastName());
        return subjectUsersDTO;
    }
}
