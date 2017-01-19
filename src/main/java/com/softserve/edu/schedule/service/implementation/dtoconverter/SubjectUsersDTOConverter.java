package com.softserve.edu.schedule.service.implementation.dtoconverter;

import org.springframework.stereotype.Service;

import com.softserve.edu.schedule.dto.SubjectUsersDTO;
import com.softserve.edu.schedule.entity.User;

@Service("subjectUsersDTOConverter")
public class SubjectUsersDTOConverter {

    public SubjectUsersDTO getDTO(User user) {
        if (user != null) {
            SubjectUsersDTO subjectUsersDTO = new SubjectUsersDTO();
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
