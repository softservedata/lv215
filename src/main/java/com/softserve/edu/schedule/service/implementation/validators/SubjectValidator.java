package com.softserve.edu.schedule.service.implementation.validators;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.softserve.edu.schedule.dto.SubjectDTO;
import com.softserve.edu.schedule.service.SubjectService;

@Component
public class SubjectValidator implements Validator {

    /**
     * SubjectService example to provide search DTO operations.
     */
    @Autowired
    private SubjectService subjectService;

    /**
     * Messages source for internationalization purposes.
     */
    @Autowired
    private ResourceBundleMessageSource messageSource;

    /**
     * Check if this validator can validate instances of the supplied class.
     * 
     * @param clazz
     *            a Class to check
     * 
     * @return true if this validator can validate instances of the supplied
     *         class.
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return SubjectDTO.class.isAssignableFrom(clazz);
    }

    /**
     * Validate the supplied object, which must be of a Class for which the
     * support() method typically has (or would) return true. The supplied
     * errors instance can be used to report any resulting validation errors.
     * 
     * @param target
     *            the object that is to be validated.
     * @param errors
     *            contextual state about the validation process
     */
    @Override
    public void validate(Object target, Errors errors) {
        SubjectDTO subjectDTO = (SubjectDTO) target;

        if (!isSubjectNameValid(subjectDTO)) {
            errors.rejectValue(ValidationFields.SUBJECT_NAME,
                    ValidationMessages.EMPTY_FIELD, "Something goes wrong");
        }
        if (!isSubjectDescriptionValid(subjectDTO)) {
            errors.rejectValue(ValidationFields.SUBJECT_DESCRIPTION,
                    ValidationMessages.EMPTY_FIELD, "Something goes wrong");
        }
        if (!isSubjectUsersValid(subjectDTO)) {
            errors.rejectValue(ValidationFields.SUBJECTS_USERS,
                    ValidationMessages.EMPTY_FIELD, "Something goes wrong");
        }
        if (!isSubjectDuplicate(subjectDTO)) {
            errors.rejectValue(ValidationFields.SUBJECT_NAME,
                    ValidationMessages.EMPTY_FIELD, "Duplicate");
        }

    }

    /**
     * Checks the subjects in the database with the same name and location as
     * given subjetDTO parameter.
     * 
     * @param subjectDTO
     *            a SUbjectDTO object to check duplicates.
     * 
     * @return true if there are duplicates
     */
    private boolean isSubjectDuplicate(SubjectDTO subjectDTO) { 
        List<SubjectDTO> duplicateList = subjectService
                .getSubjectByName(subjectDTO.getName());
        if (duplicateList.size() > ValidationCriteria.ZERO) {
            if (duplicateList.stream()
                    .anyMatch(s -> s.getId().equals(subjectDTO.getId()))) {
                return true;
            }
            return false;
        }
        return true;
    }

    /**
     * Checks the given subjectDTO name contains only allowed characters.
     * 
     * @param subjectDTO
     *            a SubjectDTO object to check name.
     * 
     * @return true if name is valid
     */
    private boolean isSubjectUsersValid(SubjectDTO subjectDTO) {
        return subjectDTO.getUsers().size() > ValidationCriteria.ZERO;
    }

    /**
     * Checks the given subjectDTO name contains only allowed characters.
     * 
     * @param subjectDTO
     *            a SubjectDTO object to check name.
     * 
     * @return true if name is valid
     */
    private boolean isSubjectDescriptionValid(SubjectDTO subjectDTO) {
        return subjectDTO.getName()
                .matches(ValidationCriteria.CHARACTERS_FOR_SUBJECT_NAME);
    }

    /**
     * Checks the given subjectDTO name contains only allowed characters.
     * 
     * @param subjectDTO
     *            a SubjectDTO object to check name.
     * 
     * @return true if name is valid
     */
    private boolean isSubjectNameValid(SubjectDTO subjectDTO) {
        return subjectDTO.getDescription()
                .matches(ValidationCriteria.CHARECTERS_FOR_SUBJECT_DESCRIPTION);
    }

}
