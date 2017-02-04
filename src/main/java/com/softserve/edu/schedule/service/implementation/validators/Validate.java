/* RoomValidator 1.0 01/29/2017 */
package com.softserve.edu.schedule.service.implementation.validators;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

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
@Target({ TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = { RoomValidator.class, SubjectValidator.class, MeetingValidator.class, UserValidator.class,
		UserGroupValidator.class, LocationValidator.class })
public @interface Validate {

	String message() default "";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
