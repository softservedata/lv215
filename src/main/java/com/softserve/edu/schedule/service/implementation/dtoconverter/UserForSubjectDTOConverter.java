package com.softserve.edu.schedule.service.implementation.dtoconverter;

import org.springframework.stereotype.Service;

import com.softserve.edu.schedule.dto.UserForSubjectDTO;
import com.softserve.edu.schedule.entity.User;

@Service("userForSubjectDTOConverter")
public class UserForSubjectDTOConverter {

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
