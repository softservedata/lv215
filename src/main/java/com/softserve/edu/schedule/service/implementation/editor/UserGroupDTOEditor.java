package com.softserve.edu.schedule.service.implementation.editor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserve.edu.schedule.dao.UserGroupDAO;
import com.softserve.edu.schedule.dto.UserGroupDTO;
import com.softserve.edu.schedule.entity.UserGroup;

@Service
public class UserGroupDTOEditor extends PropertyEditorSupport {

    @Autowired
    private UserGroupDAO userGroupDAO;

    @Override
    public void setAsText(final String groupId)
            throws IllegalArgumentException {
        // TODO edit after implements DTO in USERGROUPSERVICE
        UserGroup userGroup = userGroupDAO.getById(Long.valueOf(groupId));
        UserGroupDTO userGroupDTO = new UserGroupDTO();
        userGroupDTO.setId(userGroup.getId());
        userGroupDTO.setName(userGroup.getName());
        setValue(userGroupDTO);
    }
}
