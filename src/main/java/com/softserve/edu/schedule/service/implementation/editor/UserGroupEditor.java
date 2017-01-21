package com.softserve.edu.schedule.service.implementation.editor;

import java.beans.PropertyEditorSupport;

import com.softserve.edu.schedule.service.UserGroupService;

// delete after meeting DTO
public class UserGroupEditor extends PropertyEditorSupport {

    private UserGroupService userGroupService;

    public UserGroupEditor(final UserGroupService userGroupService) {
        this.userGroupService = userGroupService;
    }

    @Override
    public void setAsText(final String groupId)
            throws IllegalArgumentException {
        setValue(userGroupService.getById(Long.valueOf(groupId)));
    }
}
