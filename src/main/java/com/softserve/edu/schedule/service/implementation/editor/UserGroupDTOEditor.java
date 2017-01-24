package com.softserve.edu.schedule.service.implementation.editor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserve.edu.schedule.service.UserGroupService;

/**
 * This class provides conversion operations from form field userGroupId to
 * userGroupDTO.
 *
 * @version 1.0 23 January 2017
 *
 * @author Andrii Zhydenko
 *
 */
@Service
public class UserGroupDTOEditor extends PropertyEditorSupport {

	/**
	 * UserGroup Service
	 */
	@Autowired
	private UserGroupService userGroupService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.beans.PropertyEditorSupport#setAsText(java.lang.String)
	 */
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		setValue(userGroupService.getById(Long.valueOf(text)));
	}

}
