/*
* UserGroupValidatorImpl.java
 * 1.0
 * 28 Jan 2017
 * Andrii Zhydenko
 */
package com.softserve.edu.schedule.service.implementation.validators;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;

import com.softserve.edu.schedule.dto.SubjectDTO;
import com.softserve.edu.schedule.dto.UserGroupDTO;
import com.softserve.edu.schedule.service.UserGroupService;

/**
 * Provides all validations for a UserGroup.
 *
 * @version 1.0 28 January 2017
 *
 * @author Andrii Zhydenko
 */
public class UserGroupValidator implements ConstraintValidator<Validate, UserGroupDTO> {

	/**
	 * Service that allows operations with db.
	 */
	@Autowired
	private UserGroupService userGroupService;

	/**
	 * List of all groups
	 */
	private List<UserGroupDTO> groups;

	/**
	 * Stores true value if group has no duplicates in db
	 */
	private boolean isUniqueName;

	/**
	 * Message source to internationalize error messages.
	 */
	@Autowired
	private ResourceBundleMessageSource messageSource;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.validation.ConstraintValidator#initialize(java.lang.annotation.
	 * Annotation)
	 */
	@Override
	public void initialize(final Validate constraintAnnotation) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.validation.ConstraintValidator#isValid(java.lang.Object,
	 * javax.validation.ConstraintValidatorContext)
	 */
	@Override
	public boolean isValid(final UserGroupDTO userGroupDTO, ConstraintValidatorContext context) {
		isUniqueName = hasUniqueName(userGroupDTO.getId(), userGroupDTO.getName());

		showErrors(isUniqueName, context);
		return (isUniqueName);
	}

	/**
	 * Check if our group name is unique
	 * 
	 * @param groupId
	 *            Id of a group to check
	 * @param groupName
	 *            Name of a group to check
	 * @return boolean value to figure out if our name is unique
	 */
	public boolean hasUniqueName(final Long groupId, final String groupName) {
        groups = userGroupService
                .getUserGroupsByName(groupName.trim());
        return groups.isEmpty() || groups.stream()
                .anyMatch(s -> s.getId().equals(groupId));
	}

	/**
	 * Changes error message, depending on localization
	 * 
	 * @param field
	 *            field where to display an error
	 * @param message
	 *            error message
	 * @param context
	 *            instance of ConstraintValidatorContext
	 */
	private void errorMessage(String field, String message, ConstraintValidatorContext context) {
		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate(
				messageSource.getMessage(message, new String[0], LocaleContextHolder.getLocale()))
				.addPropertyNode(field).addConstraintViolation();
	}

	/**
	 * Show all errors on a form
	 * 
	 * @param isUniqueName
	 *            Logical variable to check if group has a unique name
	 * @param isCorrectLengthName
	 *            Logical variable to check if group has a correct name length
	 * @param isNotEmptyDescription
	 *            Logical variable to check if group has not empty description
	 * @param isNotEmptyLevel
	 *            Logical variable to check if group has not empty level
	 * @param context
	 *            ConstraintValidatorContext instance
	 */
	private void showErrors(boolean isUniqueName, ConstraintValidatorContext context) {
		if (!isUniqueName) {
			errorMessage(ValidationFields.NAME, ValidationMessages.INVALID_GROUP_NAME_UNIQUENESS, context);
		}
	}
}
