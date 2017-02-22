/*
 * MeetingValidator.java
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
import com.softserve.edu.schedule.entity.MeetingStatus;
import com.softserve.edu.schedule.service.MeetingService;

/**
 * A class to provide validation business logic for meeting page validation.
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
     * MeetingService variable.
     * 
     */
    @Autowired
    MeetingService meetingService;

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

    /**
     * Checks the given meetingDTO date field contains only allowed characters
     * and correct inputed.
     * 
     * @param MeetingDTO
     * 
     * @return true if date field is valid
     */
    private boolean isValidDate(MeetingDTO meetingDTO) {
        return meetingDTO.getDate() != null
                && meetingDTO.getDate().isAfter(LocalDate.now().minusDays(1));
    }

    /**
     * Checks the given meetingDTO startTime Field contains only allowed
     * characters and correct inputed.
     * 
     * @param MeetingDTO
     * 
     * @return true if startTime is valid
     */
    private boolean isValidStartTime(MeetingDTO meetingDTO) {
        return meetingDTO.getStartTime() != null;
    }

    /**
     * Checks the given meetingDTO endTime Field contains only allowed
     * characters and correct inputed.
     * 
     * @param MeetingDTO
     * 
     * @return true if endTime is valid
     */
    private boolean isValidEndTime(MeetingDTO meetingDTO) {
        return meetingDTO.getEndTime() != null;
    }

    /**
     * Checks the given meetingDTO endTime compares to endTime.
     * 
     * @param MeetingDTO
     * 
     * @return true if endTime is after startTime.
     */
    private boolean isValidEndTimeToCompareStartTime(MeetingDTO meetingDTO) {
        return isValidStartTime(meetingDTO) && isValidEndTime(meetingDTO)
                && meetingDTO.getEndTime().isAfter(meetingDTO.getStartTime());
    }

    /**
     * Checks meetingDTO userGroups to be selected (not empty).
     * 
     * @param MeetingDTO
     * 
     * @return True if userGroups are selected.
     */
    private boolean isValidMultiselectGroups(MeetingDTO meetingDTO) {
        return meetingDTO.getGroups().size() > ValidationCriteria.ZERO;
    }

    /**
     * Checks the given meetingDTO level field contains only allowed characters
     * and between 1 and 5.
     * 
     * @param MeetingDTO
     * 
     * @return true if level inputed correctly.
     */
    private boolean isValidLevel(MeetingDTO meetingDTO) {
        if (meetingDTO.getLevel() == null) {
            return false;
        }
        return meetingDTO.getLevel().toString()
                .matches(ValidationCriteria.PATTERN_FOR_MEETING_LEVEL);
    }

    /**
     * Checks the given meetingDTO status field. The business logic is: Status
     * of future meeting could not be finished. The status of the past meeting
     * should be finished.
     * 
     * @param MeetingDTO
     * 
     * @return true if status inputed correctly.
     */
    private boolean isValidMeetingStatus(MeetingDTO meetingDTO) {
        if (meetingDTO.getDate() != null) {
            if (meetingDTO.getDate().isAfter(LocalDate.now())) {
                return ((meetingDTO.getStatus() != MeetingStatus.FINISHED)
                        || (meetingDTO.getStatus() != MeetingStatus.APPROVED));
            }
            if (meetingDTO.getDate().isBefore(LocalDate.now())) {
                return ((meetingDTO.getStatus() == MeetingStatus.FINISHED)
                        || (meetingDTO.getStatus() == MeetingStatus.ARCHIVED));
            }
            if (meetingDTO.getDate().isEqual(LocalDate.now())) {
                if (meetingDTO.getEndTime().isBefore(LocalTime.now())) {
                    return ((meetingDTO.getStatus() == MeetingStatus.FINISHED)
                            || (meetingDTO
                                    .getStatus() == MeetingStatus.ARCHIVED));
                }
                if (meetingDTO.getStartTime().isAfter(LocalTime.now())) {
                    return ((meetingDTO.getStatus() != MeetingStatus.FINISHED)
                            || (meetingDTO
                                    .getStatus() != MeetingStatus.APPROVED));
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Checks if the inputed meetingDTO has not duplicates in date base.
     * 
     * @param meetingDTO
     * @return true, if it is original.
     */
    private boolean isOriginMeeting(MeetingDTO meetingDTO) {
        List<MeetingDTO> listOfDuplicates = meetingService
                .duplicatesOfGivenDTO(meetingDTO);
        return ((listOfDuplicates.isEmpty()) || listOfDuplicates.stream()
                .anyMatch(s -> s.getId().equals(meetingDTO.getId())));

    }

    /**
     * Method to convert messages for localization.
     * 
     * @param field
     *            field which will be localized.
     * @param message
     *            which will be sent to application.
     * @param context
     *            context of sent message.
     */
    private void errorMessage(String field, String message,
            ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(messageSource.getMessage(
                message, new String[0], LocaleContextHolder.getLocale()))
                .addPropertyNode(field).addConstraintViolation();
    }

    /**
     * Implements the validation business logic.
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
     * Prints error messages depend which what kind of error occurs.
     * 
     * @param isValidDescription
     * @param isValidDate
     * @param isValidStartTime
     * @param isValidEndTime
     * @param isValidEndTimeToCompareStartTime
     * @param isValidMultiselectGroups
     * @param isValidLevel
     * @param isValidMeetingStatus
     * @param isOriginMeeting
     * @param context
     */
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
