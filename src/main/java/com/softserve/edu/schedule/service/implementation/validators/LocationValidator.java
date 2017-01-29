/*
 * LocationValidator
 * 1.0
 * 28 Jan 2017
 * Copyright (c) Oleksandr Butyter
 */
package com.softserve.edu.schedule.service.implementation.validators;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * The validator of checking location input data.
 * 
 * @version 1.0 28 Jan 2017
 * @author Oleksandr Butyter
 *
 */
@Target(TYPE)
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = LocationValidatorImpl.class)
public @interface LocationValidator {

	String message() default "";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String name();

	String address();
}
