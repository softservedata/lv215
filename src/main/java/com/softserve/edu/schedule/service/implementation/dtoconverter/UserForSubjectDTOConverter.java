/*
 * SubjectDTOConverter.java
 * 1.0
 * 23 Jan 2017
 * Copyright (c) Ped'ko Volodymyr
 */
package com.softserve.edu.schedule.service.implementation.dtoconverter;

import org.springframework.stereotype.Service;

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
@Service
public class UserForSubjectDTOConverter {

    /**
     * Convert given User object to UserForSubjectDTO object.
     * 
     * @param room
     *            a User object to convert.
     * 
     * @return a UserForSubjectDTO object or null if given @param room is null.
     */
    public UserForSubjectDTO getDTO(User user) {
        if (user != null) {
            UserForSubjectDTO subjectUsersDTO = new UserForSubjectDTO();
            if (user.getId() != null) {
                subjectUsersDTO.setId(user.getId());
            }
            if (user.getFirstName() != null) {
                subjectUsersDTO.setFirstName(user.getFirstName());
            }
            if (user.getLastName() != null) {
                subjectUsersDTO.setLastName(user.getLastName());
            }
            return subjectUsersDTO;
        }
        return null;
    }
}
