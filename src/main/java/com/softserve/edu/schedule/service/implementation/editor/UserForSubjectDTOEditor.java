/*
 * UserForSubjectDTOEditor.java
 * 1.0
 * 23 Jan 2017
 * Copyright (c) Ped'ko Volodymyr
 */
package com.softserve.edu.schedule.service.implementation.editor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.softserve.edu.schedule.dto.UserDTO;
import com.softserve.edu.schedule.dto.UserForSubjectDTO;
import com.softserve.edu.schedule.service.UserService;

/**
 * A class to provide conversion operations from form field userId to
 * UserForSubjectDTO.
 *
 * @version 1.0 17 January 2017
 *
 * @author Ped'ko Volodymyr
 *
 * @since 1.8
 */
@Component
public class UserForSubjectDTOEditor extends PropertyEditorSupport {

	/**
	 * UserService example to provide search DTO operations.
	 */
	@Autowired
	private UserService userService;

	/**
	 * Provides a UserForSubjectDTO example by given user id in String format.
	 * 
	 * @param text
	 *            a user id in String format
	 * 
	 * @throws IllegalArgumentException
	 *             if @param text is not String.
	 */
	@Override
	public void setAsText(final String userForSUbjectDTOId)
	        throws IllegalArgumentException {
		UserDTO user = userService.getById(Long.valueOf(userForSUbjectDTOId));
		UserForSubjectDTO userForSubjectDTO = new UserForSubjectDTO();
		userForSubjectDTO.setId(user.getId());
		userForSubjectDTO.setFirstName(user.getFirstName());
		userForSubjectDTO.setLastName(user.getLastName());
		setValue(userForSubjectDTO);
	}

}
