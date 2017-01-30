/*
 * SubjectValidatorImpl.java
 * 1.0
 * 27 Jan 2017
 * Copyright (c) Bohdan Melnyk
 */
package com.softserve.edu.schedule.service.implementation.validators;

import java.time.LocalDate;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;

import com.softserve.edu.schedule.dto.MeetingDTO;
import com.softserve.edu.schedule.service.MeetingService;

/**
 * A class to provide validation logic.
 *
 * @version 1.0 27 January 2017
 *
 * @author Melnyk Bohdan
 *
 * @since 1.8
 */
public class MeetingValidatorImpl implements ConstraintValidator<MeetingValidator, Object> {

	/**
	 * Messages source for internationalization purposes.
	 */
	@Autowired
	private ResourceBundleMessageSource messageSource;

	/**
	 * SubjectService example to provide search DTO operations.
	 */
	@Autowired
	private MeetingService meetingService;

	/**
	 * Initializes the validator in preparation for calls. The constraint
	 * annotation for a given constraint declaration is passed. This method is
	 * guaranteed to be called before any use of this instance for validation.
	 *
	 * @param constraintAnnotation
	 *            annotation instance for a given constraint declaration
	 */
	@Override
	public void initialize(final MeetingValidator constraintAnnotation) {
	}

	/**
	 * Implements the validation logic.
	 *
	 * @param value
	 *            object to validate
	 * @param context
	 *            context in which the constraint is evaluated
	 *
	 * @return false if value does not pass the constraint
	 */
	@Override
	public boolean isValid(final Object value, ConstraintValidatorContext context) {
		MeetingDTO meetingDTO = (MeetingDTO) value;

		boolean isValidDescription = isValidDescription(meetingDTO);

		boolean isValidDate = isValidDate(meetingDTO);
		boolean isValidDateToCompareCurrentDate = isValidDateToCompareCurrentDate(meetingDTO);

		boolean isValidStartTime = isValidStartTime(meetingDTO);
		boolean isValidEndTime = isValidEndTime(meetingDTO);
		boolean isValidEndTimeToCompareStartTime = isValidEndTimeToCompareStartTime(meetingDTO);

		boolean isValidMultiselectGroups = isValidMultiselectGroups(meetingDTO);
		boolean isValidLevel = isValidLevel(meetingDTO);

		 boolean isOriginMeeting = isOriginMeeting(meetingDTO);
		 
		printErrorMessages(isValidDescription, isValidDate, isValidDateToCompareCurrentDate, isValidStartTime,
				isValidEndTime, isValidEndTimeToCompareStartTime, isValidMultiselectGroups,
				isValidLevel, isOriginMeeting, context);
		return (isValidDescription);/* && isValidDate && isValidDateToCompareCurrentDate && isValidStartTime &&
				isValidEndTime && isValidEndTimeToCompareStartTime && isValidMultiselectGroups && isValidLevel
				&& isOriginMeeting*/
	}

	/**
	 * Checks the given meetingDTO description contains only allowed characters.
	 * 
	 * @param MeetingDTO
	 * 
	 * @return true if description is valid
	 */
	private boolean isValidDescription(MeetingDTO meetingDTO) {
		return !meetingDTO.getDescription().matches(ValidationCriteria.PATTERN_FOR_MEETING_DESCRIPTION);
	}

	private boolean isValidDate(MeetingDTO meetingDTO) {
		return (meetingDTO.getDate() != null);
	}

	private boolean isValidDateToCompareCurrentDate(MeetingDTO meetingDTO) {
		if (isValidDate(meetingDTO)) {
			return meetingDTO.getDate().isAfter(LocalDate.now());
		}
		return false;
	}

	private boolean isValidStartTime(MeetingDTO meetingDTO) {
		return (meetingDTO.getStartTime() != null);
	}

	private boolean isValidEndTime(MeetingDTO meetingDTO) {
		return (meetingDTO.getEndTime() != null);
	}

	private boolean isValidEndTimeToCompareStartTime(MeetingDTO meetingDTO) {
		if ((isValidStartTime(meetingDTO)) && (isValidEndTime(meetingDTO))) {
			return meetingDTO.getEndTime().isAfter(meetingDTO.getStartTime());
		}
		return false;
	}

	/**
	 * Checks meetingDTO userGroups to be selected (not to be Empty).
	 * 
	 * @param MeetingDTO
	 * 
	 * @return true if userGroups are selected
	 */
	private boolean isValidMultiselectGroups(MeetingDTO meetingDTO) {
		return meetingDTO.getGroups().size() > ValidationCriteria.ZERO;
	}

	private boolean isValidLevel(MeetingDTO meetingDTO) {
		if (meetingDTO.getLevel() == null) {
			return false;
		}
		return ((meetingDTO.getLevel() > 0) && (meetingDTO.getLevel() < 6));
	}

	private boolean isOriginMeeting(MeetingDTO meetingDTO) {
		// TODO

		return true;
	}

	/**
	 * Method to convert messages for localization.
	 * 
	 * @param field
	 * @param message
	 * @param context
	 */
	private void errorMessage(String field, String message, ConstraintValidatorContext context) {
		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate(
				messageSource.getMessage(message, new String[0], LocaleContextHolder.getLocale()))
				.addPropertyNode(field).addConstraintViolation();
	}



	private void printErrorMessages(boolean isValidDescription, boolean isValidDate, boolean isValidDateToCompareCurrentDate,
			boolean isValidStartTime, boolean isValidEndTime, boolean isValidEndTimeToCompareStartTime,
			boolean isValidMultiselectGroups, boolean isValidLevel, boolean isOriginMeeting,
			ConstraintValidatorContext context) {
		if (!isValidDescription) {
			errorMessage(ValidationFields.DESCRIPTION, ValidationMessages.INVALID_CHARACTERS_OR_EMPTY_FIELD, context);
		}
		if (!isValidDate) {
			errorMessage(ValidationFields.DATE, ValidationMessages.INVALID_CHARACTERS_OR_EMPTY_FIELD, context);	
		}
		if (!isValidDateToCompareCurrentDate) {
			errorMessage(ValidationFields.DATE, ValidationMessages.INVALID_DATE, context);	
		}
		if (!isValidStartTime) {
			errorMessage(ValidationFields.STARTTIME, ValidationMessages.INVALID_CHARACTERS_OR_EMPTY_FIELD, context);	
		}
		if (!isValidEndTime) {
			errorMessage(ValidationFields.ENDTIME, ValidationMessages.INVALID_CHARACTERS_OR_EMPTY_FIELD, context);	
		}
		if (!isValidEndTimeToCompareStartTime) {
			errorMessage(ValidationFields.ENDTIME, ValidationMessages.INVALID_CHARACTERS_OR_EMPTY_FIELD, context);	
		}
		
		if (!isValidMultiselectGroups) {
			errorMessage(ValidationFields.GROUPS, ValidationMessages.INVALID_SUBJECT_TUTOR_COUNT, context);
		}
		if (!isOriginMeeting) {
			errorMessage(ValidationFields.NAME, ValidationMessages.DUPLICATE_SUBJECT, context);
		}
	}
}
