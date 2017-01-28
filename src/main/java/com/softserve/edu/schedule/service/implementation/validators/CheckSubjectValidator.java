/*
 * SubjectDTO.java
 * 1.0
 * 27 Jan 2017
 * Copyright (c) Ped'ko Volodymyr
 */
package com.softserve.edu.schedule.service.implementation.validators;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.BeanUtils;
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
public class CheckSubjectValidator
        implements ConstraintValidator<CheckSubject, Object> {

    /**
     * A input parameter name.
     */
    private String name;

    /**
     * A input parameter description.
     */
    private String description;

    /**
     * A input parameter description.
     */
    private String id;

    /**
     * A input parameter users.
     */
    private String users;

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
    public void initialize(final CheckSubject constraintAnnotation) {
        name = constraintAnnotation.name();
        description = constraintAnnotation.description();
        id = constraintAnnotation.id();
        users = constraintAnnotation.users();
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
        boolean validName = false;
        boolean validDescription = false;
        boolean validMultiselect = false;
        boolean noDuplicate = false;
        try {
            final String nameObj = BeanUtils.getProperty(value, name);
            final String descriptionObj = BeanUtils.getProperty(value,
                    description);
            final String idObj = BeanUtils.getProperty(value, id);
            final String[] usersObj = BeanUtils.getArrayProperty(value, users);
            validName = (isValidName(nameObj));
            validDescription = (isValiDescription(descriptionObj));
            validMultiselect = (isValidMultiselect(usersObj));
            noDuplicate = (isOriginName(idObj, nameObj));
        } catch (Exception e) {
        }
        printErrorMessages(validName, validDescription, validMultiselect,
                noDuplicate, context);
        return (validName && validDescription && validMultiselect
                && noDuplicate);
    }

    /**
     * Check curent subject name for duplicates.
     * 
     * @param idObj
     * @param nameObj
     * @return true if no duplicate in subjects list
     */
    private boolean isOriginName(String idObj, String nameObj) {
        List<SubjectDTO> duplicates = subjectService.getSubjectByName(nameObj);
        if (!duplicates.isEmpty()) {
            if (duplicates.stream()
                    .anyMatch(s -> s.getId().equals(Long.parseLong(idObj)))) {
                return true;
            }
            return false;
        }
        return true;
    }

    /**
     * Checks subjectDTO users to be selected.
     * 
     * @param usersObj
     *            an array of users to be checked.
     * 
     * @return true if users are selected
     */
    private boolean isValidMultiselect(String[] usersObj) {
        if (usersObj.length == ValidationCriteria.ZERO) {
            return false;
        }
        return true;
    }

    /**
     * Checks the given subjectDTO description contains only allowed characters.
     * 
     * @param description
     *            a SubjectDTO description to be checked.
     * 
     * @return true if description is valid
     */
    private boolean isValiDescription(String description) {
        if (description
                .matches(ValidationCriteria.CHARACTERS_FOR_SUBJECT_NAME)) {
            return true;
        }
        return false;
    }

    /**
     * Checks the given subjectDTO name contains only allowed characters.
     * 
     * @param name
     *            a SubjectDTO name to be checked.
     * 
     * @return true if name is valid
     */
    private boolean isValidName(String name) {
        if (name.matches(
                ValidationCriteria.CHARECTERS_FOR_SUBJECT_DESCRIPTION)) {
            return true;
        }
        return false;
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
            errorMessage(name,
                    ValidationMessages.INVALID_CHARACTERS_OR_EMPTY_FIELD,
                    context);
        }
        if (!validDescription) {
            errorMessage(description,
                    ValidationMessages.INVALID_CHARACTERS_OR_EMPTY_FIELD,
                    context);
        }
        if (!validMultiselect) {
            errorMessage(users, ValidationMessages.INVALID_SUBJECT_TUTOR_COUNT,
                    context);
        }
        if (!noDuplicate) {
            errorMessage(name, ValidationMessages.DUPLICATE_SUBJECT, context);
        }
    }
}
