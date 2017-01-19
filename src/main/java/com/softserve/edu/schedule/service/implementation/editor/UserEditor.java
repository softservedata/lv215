package com.softserve.edu.schedule.service.implementation.editor;

import java.beans.PropertyEditorSupport;

import com.softserve.edu.schedule.service.UserService;

public class UserEditor extends PropertyEditorSupport {

    private UserService userService;

    public UserEditor(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void setAsText(String userId) throws IllegalArgumentException {
        setValue(userService.getById(Long.valueOf(userId)));
        
    }
}
