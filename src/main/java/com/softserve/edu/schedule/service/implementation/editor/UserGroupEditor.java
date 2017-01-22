package com.softserve.edu.schedule.service.implementation.editor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserve.edu.schedule.dao.UserGroupDAO;

// delete after meeting DTO
@Service
public class UserGroupEditor extends PropertyEditorSupport {

	@Autowired
	private UserGroupDAO userGroupDAO;

	@Override
	public void setAsText(final String groupId) throws IllegalArgumentException {
		setValue(userGroupDAO.getById(Long.valueOf(groupId)));
	}
}
