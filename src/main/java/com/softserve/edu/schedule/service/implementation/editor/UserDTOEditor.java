/* LocationDTOEditor 1.0 01/17/2017 */
package com.softserve.edu.schedule.service.implementation.editor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserve.edu.schedule.service.UserService;

@Service
public class UserDTOEditor extends PropertyEditorSupport {

	@Autowired
	private UserService userService;

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		setValue(userService.getById(Long.valueOf(text)));
	}
}
