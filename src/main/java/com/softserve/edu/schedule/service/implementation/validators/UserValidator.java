/* UserValidator 1.0 01/30/2017 */
package com.softserve.edu.schedule.service.implementation.validators;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;

import com.softserve.edu.schedule.dto.UserDTO;
import com.softserve.edu.schedule.service.UserService;

/**
 * A validator class to check whatever user DTO data is correct.
 *
 * @version 1.0 31 January 2017
 *
 * @author Serhiy Dudynsky
 *
 * @since 1.8
 */
public class UserValidator implements ConstraintValidator<Validate, UserDTO> {

    /**
     * Messages source for internationalization purposes.
     */
    @Autowired
    private ResourceBundleMessageSource messageSource;

    @Autowired
    private UserService userService;

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
     * @param roomDTO
     *            object to validate
     * @param context
     *            context in which the constraint is evaluated
     *
     * @return false if value does not pass the constraint
     */
    @Override
    public boolean isValid(final UserDTO userDTO,
            final ConstraintValidatorContext context) {

        boolean isFirstNameValid = isFirstNameValid(userDTO);
        boolean isLastNameValid = isLastNameValid(userDTO);
        boolean isMailValid = isMailValid(userDTO);
        boolean hasNoDuplicates = hasNoDuplicates(userDTO);
        boolean isPhoneValid = isPhoneValid(userDTO);
        boolean isPositionValid = isPositionValid(userDTO);
        boolean isPasswordValid = isPasswordValid(userDTO);
        printErrorMessages(isFirstNameValid, isLastNameValid, isMailValid,
                hasNoDuplicates, isPhoneValid, isPositionValid, isPasswordValid,
                context);
        return isFirstNameValid && isLastNameValid && isMailValid
                && !hasNoDuplicates && isPhoneValid && isPositionValid
                && isPasswordValid;
    }

    /**
     * Checks the given userDTO firstName contains only allowed characters.
     * 
     * @param userDTO
     *            a UserDTO object to check firstName.
     * 
     * @return true if FirstName is valid
     */
    private boolean isFirstNameValid(UserDTO userDTO) {
        return userDTO.getFirstName()
                .matches(ValidationCriteria.CHARACTERS_FOR_USERNAME);
    }

    /**
     * Checks the given userDTO lastName contains only allowed characters.
     * 
     * @param userDTO
     *            a UserDTO object to check lastName.
     * 
     * @return true if lastName is valid
     */

    private boolean isLastNameValid(UserDTO userDTO) {
        return userDTO.getLastName()
                .matches(ValidationCriteria.CHARACTERS_FOR_USERNAME);
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
        return userDTO.getMail()
                .matches(ValidationCriteria.CHARACTERS_FOR_MAIL);
    }

    /**
     * Checks the user in the database with the same mail as given userDTO
     * parameter.
     * 
     * @param userDTO
     *            a UserDTO object to check duplicates.
     * 
     * @return true if there are duplicates
     */
    private boolean hasNoDuplicates(UserDTO userDTO) {
        List<UserDTO> dublicate = userService.searchByMail(userDTO.getMail());
        if (dublicate.size() == 1) {
            if (dublicate.stream()
                    .anyMatch(e -> e.getId().equals(userDTO.getId()))) {
                return false;
            }
            return true;
        }
        return false;
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
        return userDTO.getPhone()
                .matches(ValidationCriteria.CHARACTERS_FOR_PHONE);
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
        return userDTO.getPosition()
                .matches(ValidationCriteria.CHARACTERS_FOR_POSITION);
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
        return userDTO.getPassword()
                .matches(ValidationCriteria.CHARACTERS_FOR_PASSWORD);
    }

    /**
     * Method localizes message.
     * 
     * @param field
     * @param message
     * @param context
     */
    private void errorMessage(final String field, final String message,
            final ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(messageSource.getMessage(
                message, new String[0], LocaleContextHolder.getLocale()))
                .addPropertyNode(field).addConstraintViolation();
    }

    /**
     * Method sets error messages if some verification fails.
     * 
     * @param isNameValid
     * @param isCapacityValid
     * @param hasNoDuplicates
     * @param context
     */
    private void printErrorMessages(final boolean isFirstNameValid,
            final boolean isLastNameValid, final boolean isMailValid,
            final boolean hasNoDuplicates, final boolean isPhoneValid,
            final boolean isPositionValid, final boolean isPasswordValid,
            final ConstraintValidatorContext context) {

        if (!isFirstNameValid) {
            errorMessage(ValidationFields.FIRSTNAME,
                    ValidationMessages.INVALID_CHARACTERS_OR_EMPTY_FIELD,
                    context);
        }

        if (!isLastNameValid) {
            errorMessage(ValidationFields.LASTNAME,
                    ValidationMessages.INVALID_CHARACTERS_OR_EMPTY_FIELD,
                    context);
        }

        if (!isMailValid) {
            errorMessage(ValidationFields.MAIL, ValidationMessages.INVALID_MAIL,
                    context);
        }

        if (!isPhoneValid) {
            errorMessage(ValidationFields.PHONE,
                    ValidationMessages.INVALID_PHONE_NUMBER, context);
        }

        if (!isPositionValid) {
            errorMessage(ValidationFields.POSITION,
                    ValidationMessages.INVALID_CHARACTERS_OR_EMPTY_FIELD,
                    context);
        }

        if (!isPasswordValid) {
            errorMessage(ValidationFields.PASSWORD,
                    ValidationMessages.INCORECT_PASSWORD, context);
        }

        if (hasNoDuplicates) {
            errorMessage(ValidationFields.MAIL,
                    ValidationMessages.DUPLICATE_MAIL, context);
        }
    }
}
