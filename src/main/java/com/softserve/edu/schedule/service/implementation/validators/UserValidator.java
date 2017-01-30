package com.softserve.edu.schedule.service.implementation.validators;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.softserve.edu.schedule.dto.UserDTO;
import com.softserve.edu.schedule.service.UserService;

/**
 * A validator class to check whatever user DTO data is correct.
 *
 * @version 1.0 25 January 2017
 *
 * @author Serhiy Dudynsky
 *
 * @since 1.8
 */
@Component
public class UserValidator  implements Validator {
    
    /**
     * UserService example to provide search DTO operations.
     */
    @Autowired
    private UserService userService;

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
        return UserDTO.class.isAssignableFrom(clazz);
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
        UserDTO userDTO = (UserDTO) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, ValidationFields.FIRSTNAME,
                ValidationMessages.NO_ERROR_CODE,
                messageSource.getMessage(ValidationMessages.EMPTY_FIELD,
                        new String[0], LocaleContextHolder.getLocale()));
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, ValidationFields.LASTNAME,
                ValidationMessages.NO_ERROR_CODE,
                messageSource.getMessage(ValidationMessages.EMPTY_FIELD,
                        new String[0], LocaleContextHolder.getLocale()));
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, ValidationFields.POSITION,
                ValidationMessages.NO_ERROR_CODE,
                messageSource.getMessage(ValidationMessages.EMPTY_FIELD,
                        new String[0], LocaleContextHolder.getLocale()));
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, ValidationFields.MAIL,
                ValidationMessages.NO_ERROR_CODE,
                messageSource.getMessage(ValidationMessages.EMPTY_FIELD,
                        new String[0], LocaleContextHolder.getLocale()));
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, ValidationFields.PASSWORD,
                ValidationMessages.NO_ERROR_CODE,
                messageSource.getMessage(ValidationMessages.EMPTY_FIELD,
                        new String[0], LocaleContextHolder.getLocale()));
        
        if (!isUserFirstNameValid(userDTO)) {
            errors.rejectValue(ValidationFields.FIRSTNAME,
                    ValidationMessages.NO_ERROR_CODE,
                    messageSource.getMessage(
                            ValidationMessages.INVALID_NAME,
                            new String[0], LocaleContextHolder.getLocale()));
        }
        
        if (!isUserLastNameValid(userDTO)) {
            errors.rejectValue(ValidationFields.LASTNAME,
                    ValidationMessages.NO_ERROR_CODE,
                    messageSource.getMessage(
                            ValidationMessages.INVALID_NAME,
                            new String[0], LocaleContextHolder.getLocale()));
        }
        
        if (!isPositionValid(userDTO)) {
            errors.rejectValue(ValidationFields.POSITION,
                    ValidationMessages.NO_ERROR_CODE,
                    messageSource.getMessage(
                            ValidationMessages.INVALID_NAME,
                            new String[0], LocaleContextHolder.getLocale()));
        }
        
        if (!isMailValid(userDTO)) {
            errors.rejectValue(ValidationFields.MAIL,
                    ValidationMessages.NO_ERROR_CODE,
                    messageSource.getMessage(
                            ValidationMessages.INVALID_MAIL,
                            new String[0], LocaleContextHolder.getLocale()));
        }
        
        if (hasDuplicates(userDTO)) {
            errors.rejectValue(ValidationFields.MAIL,
                    ValidationMessages.NO_ERROR_CODE,
                    messageSource.getMessage(ValidationMessages.DUPLICATE_MAIL,
                            new String[0], LocaleContextHolder.getLocale()));
        }
        
        if (!isPhoneValid(userDTO)) {
            errors.rejectValue(ValidationFields.PHONE,
                    ValidationMessages.NO_ERROR_CODE,
                    messageSource.getMessage(
                            ValidationMessages.WRONG_PHONE_NUMBER,
                            new String[0], LocaleContextHolder.getLocale()));
        }
        
        if (!isPasswordValid(userDTO)) {
            errors.rejectValue(ValidationFields.PASSWORD,
                    ValidationMessages.NO_ERROR_CODE,
                    messageSource.getMessage(
                            ValidationMessages.INCORECT_PASSWORD,
                            new String[0], LocaleContextHolder.getLocale()));
        }
        
    }

    /**
     * Checks the given userDTO firstName contains only allowed characters.
     * 
     * @param userDTO
     *            a UserDTO object to check firstName.
     * 
     * @return true if FirstName is valid
     */
    private boolean isUserFirstNameValid(UserDTO userDTO) {
        return userDTO.getFirstName().matches(ValidationCriteria.CHARACTERS_FOR_USERNAME);
    }
    
    /**
     * Checks the given userDTO lastName contains only allowed characters.
     * 
     * @param userDTO
     *            a UserDTO object to check lastName.
     * 
     * @return true if lastName is valid
     */
    private boolean isUserLastNameValid(UserDTO userDTO) {
        return userDTO.getLastName().matches(ValidationCriteria.CHARACTERS_FOR_USERNAME);
    }
    
    /**
     * Checks the given userDTO position contains only allowed characters.
     * 
     * @param userDTO
     *            a UserDTO object to check position.
     * 
     * @return true if position is valid
     */
    private boolean isPositionValid(UserDTO userDTO) {
        return userDTO.getPosition().matches(ValidationCriteria.CHARACTERS_FOR_USERNAME);
    }
    
    /**
     * Checks the given userDTO phone number contains only allowed characters.
     * 
     * @param userDTO
     *            a UserDTO object to check phone.
     * 
     * @return true if phone is valid
     */
    private boolean isPhoneValid(UserDTO userDTO) {
        return userDTO.getPhone().matches(ValidationCriteria.CHARACTERS_FOR_PHONE);
    }
    
    /**
     * Checks the given userDTO phone number contains only allowed characters.
     * 
     * @param userDTO
     *            a UserDTO object to check phone.
     * 
     * @return true if phone is valid
     */
    private boolean isMailValid(UserDTO userDTO) {
        return userDTO.getMail().matches(ValidationCriteria.CHARACTERS_FOR_MAIL);
//        return userDTO.getMail().matches(ValidationCriteria.CHARACTERS_FOR_NAME);
    }
    
    /**
     * Checks the given userDTO password contains only allowed characters.
     * 
     * @param userDTO
     *            a UserDTO object to check password.
     * 
     * @return true if password is valid
     */
    private boolean isPasswordValid(UserDTO userDTO) {
        return userDTO.getPassword().matches(ValidationCriteria.CHARACTERS_FOR_PASSWORD);
//        return userDTO.getPassword().matches(ValidationCriteria.CHARACTERS_FOR_NAME);
    }

    /**
     * Checks the user in the database with the same mail as given
     * userDTO parameter.
     * 
     * @param userDTO
     *            a UserDTO object to check duplicates.
     * 
     * @return true if there are duplicates
     */
    private boolean hasDuplicates(UserDTO userDTO) {
        List<UserDTO> dublicate = userService.searchByMail(userDTO.getMail());
        if (dublicate.size() == 1) {
            if(dublicate.stream().anyMatch(e -> e.getId().equals(userDTO.getId()))){
                return false;
            }
            return true;
        }
        return false;
    }

}
