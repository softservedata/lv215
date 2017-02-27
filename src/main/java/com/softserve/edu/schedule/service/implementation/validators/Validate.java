/* Validate 1.0 01/29/2017 */
package com.softserve.edu.schedule.service.implementation.validators;

import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * The validator annotation for checking input data.
 *
 * @version 1.0 29 January 2017
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {RoomValidator.class, SubjectValidator.class,
        MeetingValidator.class, UserValidator.class, UserGroupValidator.class,
        LocationValidator.class, UserPasswordValidator.class,
        FileForSubjectValidator.class, RoomEquipmentValidator.class})
public @interface Validate {

    /**
     * Validation message.
     *
     * @return message text
     */
    String message() default "";

    /**
     * Validation groups.
     *
     * @return validation groups
     */
    Class<?>[] groups() default {};

    /**
     * Validation payload.
     *
     * @return validation payload
     */
    Class<? extends Payload>[] payload() default {};

}
