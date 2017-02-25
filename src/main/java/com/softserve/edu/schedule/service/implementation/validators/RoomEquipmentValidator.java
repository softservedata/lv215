/* RoomEquipmentValidator 1.0 01/29/2017 */
package com.softserve.edu.schedule.service.implementation.validators;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;

import com.softserve.edu.schedule.dto.RoomEquipmentDTO;
import com.softserve.edu.schedule.service.RoomEquipmentService;

/**
 * A validator class to check whatever room equipment DTO data is correct.
 *
 * @version 1.0 29 January 2017
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
public class RoomEquipmentValidator
        implements ConstraintValidator<Validate, RoomEquipmentDTO> {

    /**
     * Messages source for internationalization purposes.
     */
    @Autowired
    private ResourceBundleMessageSource messageSource;

    /**
     * RoomEquipmentService example to provide search by room equipment name.
     */
    @Autowired
    private RoomEquipmentService roomEquipmentService;

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
     * @param roomEquipmentDTO
     *            object to validate
     * @param context
     *            context in which the constraint is evaluated
     *
     * @return false if value does not pass the constraint
     */
    @Override
    public boolean isValid(final RoomEquipmentDTO roomEquipmentDTO,
            final ConstraintValidatorContext context) {
        boolean isNameValid = isNameValid(roomEquipmentDTO);
        boolean hasNoDuplicates = hasNoDuplicates(roomEquipmentDTO);
        printErrorMessages(isNameValid, hasNoDuplicates, context);
        return isNameValid && hasNoDuplicates;
    }

    /**
     * Checks the room equipments in the database with the same name as given
     * roomEquipmentDTO parameter.
     *
     * @param roomEquipmentDTO
     *            a RoomEquipmentDTO object to check duplicates.
     *
     * @return true if there are no duplicates
     */
    private boolean hasNoDuplicates(final RoomEquipmentDTO roomEquipmentDTO) {
        List<RoomEquipmentDTO> duplicates = roomEquipmentService
                .getByName(roomEquipmentDTO.getName().trim());
        return duplicates.isEmpty() || duplicates.stream()
                .anyMatch(s -> s.getId().equals(roomEquipmentDTO.getId()));
    }

    /**
     * Checks the given roomEquipmentDTO name contains only allowed characters.
     *
     * @param roomEquipmentDTO
     *            a RoomEquipmentDTO object to check name.
     *
     * @return true if name is valid
     */
    private boolean isNameValid(final RoomEquipmentDTO roomEquipmentDTO) {
        return roomEquipmentDTO.getName().trim()
                .matches(ValidationCriteria.PATTERN_FOR_ROOM_NAME);
    }

    /**
     * Method localizes message.
     *
     * @param field
     *            message field
     * @param message
     *            message text
     * @param context
     *            validator context
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
     *            true if room name is valid
     *
     * @param hasNoDuplicates
     *            true if there are no duplicates of room in this locstion
     * @param context
     *            validator context
     */
    private void printErrorMessages(final boolean isNameValid,
            final boolean hasNoDuplicates,
            final ConstraintValidatorContext context) {
        if (!isNameValid) {
            errorMessage(ValidationFields.NAME,
                    ValidationMessages.INVALID_CHARACTERS_OR_EMPTY_FIELD,
                    context);
        }
        if (!hasNoDuplicates) {
            errorMessage(ValidationFields.NAME,
                    ValidationMessages.DUPLICATE_ROOM_EQUIPMENT, context);
        }
    }

}
