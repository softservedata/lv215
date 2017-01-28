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

public class CheckSubjectValidator
        implements ConstraintValidator<CheckSubject, Object> {

    private String name;

    private String description;

    private String id;

    private String users;

    @Autowired
    private ResourceBundleMessageSource messageSource;

    @Autowired
    private SubjectService subjectService;

    @Override
    public void initialize(final CheckSubject constraintAnnotation) {
        name = constraintAnnotation.name();
        description = constraintAnnotation.description();
        id = constraintAnnotation.id();
        users = constraintAnnotation.users();
    }

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
        if (!validName) {
            errorMessage(name, ValidationMessages.EMPTY_FIELD, context);
        }
        if (!validDescription) {
            errorMessage(description, ValidationMessages.EMPTY_FIELD, context);
        }
        if (!validMultiselect) {
            errorMessage(users, ValidationMessages.EMPTY_FIELD, context);
        }
        if (!noDuplicate) {
            errorMessage(name, ValidationMessages.DUPLICATE_ROOM, context);
        }
        return (validName && validDescription && validMultiselect
                && noDuplicate);
    }

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

    private boolean isValidMultiselect(String[] usersObj) {
        if (usersObj.length == ValidationCriteria.ZERO) {
            return false;
        }
        return true;
    }

    private boolean isValiDescription(String description) {
        if (description
                .matches(ValidationCriteria.CHARACTERS_FOR_SUBJECT_NAME)) {
            return true;
        }
        return false;
    }

    private boolean isValidName(String name) {
        if (name.matches(
                ValidationCriteria.CHARECTERS_FOR_SUBJECT_DESCRIPTION)) {
            return true;
        }
        return false;
    }

    private void errorMessage(String field, String message,
            ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(messageSource.getMessage(
                message, new String[0], LocaleContextHolder.getLocale()))
                .addPropertyNode(field).addConstraintViolation();
    }
}
