/*
 * SubjectValidatorImpl.java
 * 1.0
 * 27 Jan 2017
 * Copyright (c) Ped'ko Volodymyr
 */
package com.softserve.edu.schedule.service.implementation.validators;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;

import com.softserve.edu.schedule.dto.SubjectDTO;
import com.softserve.edu.schedule.service.SubjectService;

/**
 * A class to provide validation logic.
 *
 * @version 1.0 27 January 2017
 *
 * @author Ped'ko Volodymyr
 *
 * @since 1.8
 */
public class SubjectValidatorImpl
        implements ConstraintValidator<SubjectValidator, Object> {

    /**
     * Messages source for internationalization purposes.
     */
    @Autowired
    private ResourceBundleMessageSource messageSource;

    /**
     * SubjectService example to provide search DTO operations.
     */
    @Autowired
    private SubjectService subjectService;

    /**
     * Initializes the validator in preparation for calls. The constraint
     * annotation for a given constraint declaration is passed. This method is
     * guaranteed to be called before any use of this instance for validation.
     *
     * @param constraintAnnotation
     *            annotation instance for a given constraint declaration
     */
    @Override
    public void initialize(final SubjectValidator constraintAnnotation) {
        
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
    public boolean isValid(final Object value,
            ConstraintValidatorContext context) {
        SubjectDTO subjectDTO = (SubjectDTO) value;
        boolean validName = isValidName(subjectDTO);
        boolean validDescription = isValiDescription(subjectDTO);
        boolean validMultiselect = isValidMultiselect(subjectDTO);
        boolean noDuplicate = isOriginName(subjectDTO);
        printErrorMessages(validName, validDescription, validMultiselect,
                noDuplicate, context);
        return (validName && validDescription && validMultiselect
                && noDuplicate);
    }

    /**
     * Check curent subject name for duplicates.
     * 
     * @param SubjectDTO
     * @return true if no duplicate in subjects list
     */
    private boolean isOriginName(SubjectDTO subjectDTO) {
        List<SubjectDTO> duplicates = subjectService
                .getSubjectByName(subjectDTO.getName().trim());
        return duplicates.isEmpty() || duplicates.stream()
                .anyMatch(s -> s.getId().equals(subjectDTO.getId()));
    }

    /**
     * Checks subjectDTO users to be selected.
     * 
     * @param SubjectDTO
     * 
     * @return true if users are selected
     */
    private boolean isValidMultiselect(SubjectDTO subjectDTO) {
        return subjectDTO.getUsers().size() > ValidationCriteria.ZERO;
    }

    /**
     * Checks the given subjectDTO description contains only allowed characters.
     * 
     * @param SubjectDTO
     * 
     * @return true if description is valid
     */
    private boolean isValiDescription(SubjectDTO subjectDTO) {
        return subjectDTO.getDescription()
                .matches(ValidationCriteria.PATTERN_FOR_SUBJECT_NAME);
    }

    /**
     * Checks the given subjectDTO name contains only allowed characters.
     * 
     * @param SubjectDTO
     * 
     * @return true if name is valid
     */
    private boolean isValidName(SubjectDTO subjectDTO) {
        return subjectDTO.getName()
                .matches(ValidationCriteria.PATTERN_FOR_SUBJECT_DESCRIPTION);
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

    /**
     * This method print error messages if some verification fails.
     * 
     * @param validName
     * @param validDescription
     * @param validMultiselect
     * @param noDuplicate
     * @param context
     */
    private void printErrorMessages(boolean validName, boolean validDescription,
            boolean validMultiselect, boolean noDuplicate,
            ConstraintValidatorContext context) {
        if (!validName) {
            errorMessage(ValidationFields.NAME,
                    ValidationMessages.INVALID_CHARACTERS_OR_EMPTY_FIELD,
                    context);
        }
        if (!validDescription) {
            errorMessage(ValidationFields.DESCRIPTION,
                    ValidationMessages.INVALID_CHARACTERS_OR_EMPTY_FIELD,
                    context);
        }
        if (!validMultiselect) {
            errorMessage(ValidationFields.USERS, ValidationMessages.INVALID_SUBJECT_TUTOR_COUNT,
                    context);
        }
        if (!noDuplicate) {
            errorMessage(ValidationFields.NAME,
                    ValidationMessages.DUPLICATE_SUBJECT, context);
        }
    }
}
