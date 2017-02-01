/*
 * SubjectValidatorImpl.java
 * 1.0
 * 27 Jan 2017
 * Copyright (c) Bohdan Melnyk
 */
package com.softserve.edu.schedule.service.implementation.validators;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;

import com.softserve.edu.schedule.dto.MeetingDTO;
import com.softserve.edu.schedule.dto.RoomDTO;
import com.softserve.edu.schedule.entity.MeetingStatus;

/**
 * A class to provide validation logic.
 *
 * @version 1.0 27 January 2017
 *
 * @author Melnyk Bohdan
 *
 * @since 1.8
 */
public class MeetingValidator
        implements ConstraintValidator<Validate, MeetingDTO> {

    /**
     * Messages source for internationalization purposes.
     */
    @Autowired
    private ResourceBundleMessageSource messageSource;

    /**
     * Initializes the validator in preparation for calls. The constraint
     * annotation for a given constraint declaration is passed. This method is
     * guaranteed to be called before any use of this instance for validation.
     *
     * @param constraintAnnotation
     *            annotation instance for a given constraint declaration
     */
    @Override
    public void initialize(final Validate constraintAnnotation) {
    }

    /**
     * Implements the validation logic.
     *
     * @param meetingDTO
     *            object to validate
     * @param context
     *            context in which the constraint is evaluated
     *
     * @return false if value does not pass the constraint
     */
    @Override
    public boolean isValid(final MeetingDTO meetingDTO,
            ConstraintValidatorContext context) {

        boolean isValidDescription = isValidDescription(meetingDTO);

        boolean isValidDate = isValidDate(meetingDTO);

        boolean isValidStartTime = isValidStartTime(meetingDTO);
        boolean isValidEndTime = isValidEndTime(meetingDTO);
        boolean isValidEndTimeToCompareStartTime = isValidEndTimeToCompareStartTime(
                meetingDTO);

        boolean isValidMultiselectGroups = isValidMultiselectGroups(meetingDTO);

        boolean isValidLevel = isValidLevel(meetingDTO);

        boolean isOriginMeeting = isOriginMeeting(meetingDTO);

        boolean isValidMeetingStatus = isValidMeetingStatus(meetingDTO);

        printErrorMessages(isValidDescription, isValidDate, isValidStartTime,
                isValidEndTime, isValidEndTimeToCompareStartTime,
                isValidMultiselectGroups, isValidLevel, isValidMeetingStatus,
                isOriginMeeting, context);
        return (isValidDescription && isValidDate && isValidStartTime
                && isValidEndTime && isValidEndTimeToCompareStartTime
                && isValidMultiselectGroups && isValidLevel
                && isValidMeetingStatus && isOriginMeeting);
    }

    /**
     * Checks the given meetingDTO description contains only allowed characters.
     * 
     * @param MeetingDTO
     * 
     * @return true if description is valid
     */

    private boolean isValidDescription(MeetingDTO meetingDTO) {
        return ((meetingDTO.getDescription() != null)
                && (meetingDTO.getDescription().matches(
                        ValidationCriteria.PATTERN_FOR_MEETING_DESCRIPTION)));
    }

    private boolean isValidDate(MeetingDTO meetingDTO) {
        return meetingDTO.getDate() != null
                && meetingDTO.getDate().isAfter(LocalDate.now().minusDays(1));
    }

    private boolean isValidStartTime(MeetingDTO meetingDTO) {
        return meetingDTO.getStartTime() != null;
    }

    private boolean isValidEndTime(MeetingDTO meetingDTO) {
        return meetingDTO.getEndTime() != null;
    }

    private boolean isValidEndTimeToCompareStartTime(MeetingDTO meetingDTO) {
        return isValidStartTime(meetingDTO) && isValidEndTime(meetingDTO)
                && meetingDTO.getEndTime().isAfter(meetingDTO.getStartTime());
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
        return meetingDTO.getLevel().toString()
                .matches(ValidationCriteria.PATTERN_FOR_MEETING_LEVEL);
    }

    private boolean isValidMeetingStatus(MeetingDTO meetingDTO) {
        if (meetingDTO.getDate().isAfter(LocalDate.now())) {
            return (meetingDTO.getStatus() != MeetingStatus.FINISHED);
        }
        if (meetingDTO.getDate().isBefore(LocalDate.now())) {
            return (meetingDTO.getStatus() == MeetingStatus.FINISHED);
        }
        if (meetingDTO.getDate().isEqual(LocalDate.now())) {
            if (meetingDTO.getEndTime().isBefore(LocalTime.now())) {
                return (meetingDTO.getStatus() == MeetingStatus.FINISHED);
            }
            if (meetingDTO.getStartTime().isAfter(LocalTime.now())) {
                return (meetingDTO.getStatus() != MeetingStatus.FINISHED);
            }
        }
        return true;
    }

    private boolean isOriginMeeting(MeetingDTO meetingDTO) {
        // TODO
        // Add to this list meetingDTO's such, that have the same
        //subject, room, owner, date, start time
        /*List<MeetingDTO> duplicates = null;
        List<MeetingDTO> duplicates = meetingService.
                return duplicates.isEmpty() || duplicates.stream()
                        .anyMatch(s -> s.getId().equals(meetingDTO.getId()));*/
        return true;
    }

    /**
     * Method to convert messages for localization.
     * 
     * @param field
     * @param message
     * @param context
     */
    private void errorMessage(String field, String message,
            ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(messageSource.getMessage(
                message, new String[0], LocaleContextHolder.getLocale()))
                .addPropertyNode(field).addConstraintViolation();
    }

    private void printErrorMessages(boolean isValidDescription,
            boolean isValidDate, boolean isValidStartTime,
            boolean isValidEndTime, boolean isValidEndTimeToCompareStartTime,
            boolean isValidMultiselectGroups, boolean isValidLevel,
            boolean isValidMeetingStatus, boolean isOriginMeeting,
            ConstraintValidatorContext context) {
        if (!isValidDescription) {
            errorMessage(ValidationFields.DESCRIPTION,
                    ValidationMessages.INVALID_CHARACTERS, context);
        }
        if (!isValidDate) {
            errorMessage(ValidationFields.DATE, ValidationMessages.INVALID_DATE,
                    context);
        }
        if (!isValidStartTime) {
            errorMessage(ValidationFields.STARTTIME,
                    ValidationMessages.INVALID_TIME, context);
        }
        if (!isValidEndTime) {
            errorMessage(ValidationFields.ENDTIME,
                    ValidationMessages.INVALID_TIME, context);
        }
        if (!isValidEndTimeToCompareStartTime) {
            errorMessage(ValidationFields.ENDTIME,
                    ValidationMessages.INVALID_ENDTIME_COMPARE_STARTTIME,
                    context);
        }
        if (!isValidMultiselectGroups) {
            errorMessage(ValidationFields.GROUPS,
                    ValidationMessages.EMPTY_FIELD, context);
        }
        if (!isValidLevel) {
            errorMessage(ValidationFields.LEVEL,
                    ValidationMessages.INVALID_LEVEL, context);
        }
        if (!isValidMeetingStatus) {
            errorMessage(ValidationFields.STATUS,
                    ValidationMessages.INVALID_STATUS, context);
        }

        if (!isOriginMeeting) {
            errorMessage(ValidationFields.SUBJECT,
                    ValidationMessages.DUPLICATE_MEETING, context);
        }
    }
}
