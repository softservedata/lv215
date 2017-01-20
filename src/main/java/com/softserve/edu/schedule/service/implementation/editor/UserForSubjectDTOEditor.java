package com.softserve.edu.schedule.service.implementation.editor;

import java.beans.PropertyEditorSupport;

import com.softserve.edu.schedule.dto.UserForSubjectDTO;
import com.softserve.edu.schedule.entity.User;
import com.softserve.edu.schedule.service.UserService;

public class UserForSubjectDTOEditor extends PropertyEditorSupport {

    private UserService userService;

    public UserForSubjectDTOEditor(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void setAsText(String userForSUbjectDTOId)
            throws IllegalArgumentException {
        User user = userService.getById(Long.valueOf(userForSUbjectDTOId));
        UserForSubjectDTO userForSubjectDTO = new UserForSubjectDTO();
        userForSubjectDTO.setId(user.getId());
        userForSubjectDTO.setFirstName(user.getFirstName());
        userForSubjectDTO.setLastName(user.getLastName());
        setValue(userForSubjectDTO);
    }

}
