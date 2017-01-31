/* UserValidator 1.0 01/30/2017 */
package com.softserve.edu.schedule.service.implementation.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;

import com.softserve.edu.schedule.dto.UserDTOForChangePassword;

/**
 * A validator class to check whatever user DTO data is correct.
 *
 * @version 1.0 31 January 2017
 *
 * @author Serhiy Dudynsky
 *
 * @since 1.8
 */

public class UserPasswordValidator
        implements ConstraintValidator<Validate, UserDTOForChangePassword> {

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
     * @param roomDTO
     *            object to validate
     * @param context
     *            context in which the constraint is evaluated
     *
     * @return false if value does not pass the constraint
     */
    @Override
    public boolean isValid(final UserDTOForChangePassword userDTO,
            final ConstraintValidatorContext context) {

        boolean isPasswordValid = isPasswordValid(userDTO);
        boolean isFirstNewPasswordValid = isFirstNewPasswordValid(userDTO);
        printErrorMessages(isPasswordValid, isFirstNewPasswordValid, context);
        return isPasswordValid && isFirstNewPasswordValid;
    }

    /**
     * Checks the given userDTO password contains only allowed characters.
     * 
     * @param userDTO
     *            a UserDTO object to check password.
     * 
     * @return true if password is valid
     */

    private boolean isPasswordValid(UserDTOForChangePassword userDTO) {
        return userDTO.getPassword()
                .matches(ValidationCriteria.CHARACTERS_FOR_PASSWORD);
    }

    /**
     * Checks the given userDTO password contains only allowed characters.
     * 
     * @param userDTO
     *            a UserDTO object to check password.
     * 
     * @return true if password is valid
     */

    private boolean isFirstNewPasswordValid(UserDTOForChangePassword userDTO) {
        return userDTO.getFirstNewPassword()
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
    private void printErrorMessages(final boolean isPasswordValid,
            final boolean isFirstNewPasswordValid,
            final ConstraintValidatorContext context) {

        if (!isPasswordValid) {
            errorMessage(ValidationFields.PASSWORD,
                    ValidationMessages.INCORECT_PASSWORD, context);
        }

        if (!isFirstNewPasswordValid) {
            errorMessage(ValidationFields.FIRST_NEW_PASSWORD,
                    ValidationMessages.INCORECT_PASSWORD, context);
        }

    }
}
