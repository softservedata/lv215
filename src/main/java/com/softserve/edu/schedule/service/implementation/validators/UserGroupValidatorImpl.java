/*
 * UserGroupValidatorImpl.java
 * 1.0
 * 28 Jan 2017
 * Andrii Zhydenko
 */
package com.softserve.edu.schedule.service.implementation.validators;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;

import com.softserve.edu.schedule.dto.UserGroupDTO;
import com.softserve.edu.schedule.service.UserGroupService;

/**
 * Provides all validations for a UserGroup.
 *
 * @version 1.0 28 January 2017
 *
 * @author Andrii Zhydenko
 */
public class UserGroupValidatorImpl implements ConstraintValidator<UserGroupValidator, Object> {

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
	 * Value of a minimum group name length.
	 */
	private int minNameLength;

	/**
	 * Value of a maximum group name length.
	 */
	private int maxNameLength;

	/**
	 * An inputed name
	 */
	private String name;

	/**
	 * An id of a group that is editing
	 */
	private String id;

	/**
	 * Description of a group
	 */
	private String description;

	/**
	 * Level of a group
	 */
	private String level;

	/**
	 * Stores true value if group has correct length of a name
	 */
	private boolean isCorrectLengthName;

	/**
	 * Stores true value if group has no duplicates in db
	 */
	private boolean isUniqueName;

	/**
	 * Stores true value if description is not empty
	 */
	private boolean isNotEmptyDescription;

	/**
	 * Stores true value if level is not empty
	 */
	private boolean isNotEmptyLevel;

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
	public void initialize(UserGroupValidator constraintAnnotation) {
		minNameLength = constraintAnnotation.min();
		maxNameLength = constraintAnnotation.max();
		id = constraintAnnotation.id();
		name = constraintAnnotation.name();
		description = constraintAnnotation.description();
		level = constraintAnnotation.level();

		groups = userGroupService.getAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.validation.ConstraintValidator#isValid(java.lang.Object,
	 * javax.validation.ConstraintValidatorContext)
	 */
	@Override
	public boolean isValid(final Object value, ConstraintValidatorContext context) {
		String groupName = "";
		String groupId = "";
		String groupDescription = "";
		String groupLevel = null;

		try {
			groupName = BeanUtils.getProperty(value, name);
			groupId = BeanUtils.getProperty(value, id);
			groupDescription = BeanUtils.getProperty(value, description);
			groupLevel = BeanUtils.getSimpleProperty(value, level);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			e.printStackTrace();
		}

		isCorrectLengthName = hasCorrectNameLength(groupName);
		isUniqueName = hasUniqueName(groupId, groupName);
		isNotEmptyDescription = hasDescription(groupDescription);
		isNotEmptyLevel = hasLevel(groupLevel);

		showErrors(isUniqueName, isCorrectLengthName, isNotEmptyDescription, isNotEmptyLevel, context);
		return (isCorrectLengthName && isUniqueName && isNotEmptyDescription && isNotEmptyLevel);
	}

	/**
	 * Check if our group level is not empty.
	 * 
	 * @param groupLevel
	 *            Level of a group to check
	 * @return boolean value to figure out if level is empty
	 */
	public boolean hasLevel(final String groupLevel) {
		if (groupLevel == null) {
			return false;
		}
		return true;
	}

	/**
	 * Check if our group description is not empty.
	 * 
	 * @param groupDescription
	 *            Description of a group to check
	 * @return boolean value to figure out if description is empty
	 */
	public boolean hasDescription(final String groupDescription) {
		if (groupDescription.isEmpty()) {
			return false;
		}
		return true;
	}

	/**
	 * Check if our group name has correct length.
	 * 
	 * @param groupName
	 *            Name of a group to check
	 * @return boolean value to figure out if our name has correct length
	 */
	public boolean hasCorrectNameLength(final String groupName) {
		if (groupName.length() < minNameLength || groupName.length() > maxNameLength) {
			return false;
		}
		return true;
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
	public boolean hasUniqueName(final String groupId, final String groupName) {
		if (groupId != null) {
			for (UserGroupDTO group : groups) {
				if (group.getName().equals(groupName) && (group.getId() != Long.parseLong(groupId))) {
					return false;
				}
			}
		} else {
			for (UserGroupDTO group : groups) {
				if (group.getName().equals(groupName)) {
					return false;
				}
			}
		}
		return true;
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
	private void showErrors(boolean isUniqueName, boolean isCorrectLengthName, boolean isNotEmptyDescription,
			boolean isNotEmptyLevel, ConstraintValidatorContext context) {
		if (!isCorrectLengthName) {
			errorMessage(name, ValidationMessages.INVALID_GROUP_NAME_LENGTH, context);
		}
		if (!isUniqueName) {
			errorMessage(name, ValidationMessages.INVALID_GROUP_NAME_UNIQUENESS, context);
		}
		if (!isNotEmptyDescription) {
			errorMessage(description, ValidationMessages.INVALID_GROUP_DESCRIPTION, context);
		}
		if (!isNotEmptyLevel) {
			errorMessage(level, ValidationMessages.EMPTY_GROUP_LEVEL, context);
		}
	}
}
