package com.softserve.edu.schedule.service.implementation.validators;

/*
 * SubjectValidator.java
 * 1.0
 * 27 Jan 2017
 * Copyright (c) Bohdan Melnyk.
 */

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * A validator to check SubjectDTO input param.
 *
 * @version 1.0 27 January 2017
 *
 * @author Bohdan Melnyk
 *
 * @since 1.8
 */
@Target(TYPE)
@Retention(RUNTIME)
@Constraint(validatedBy = MeetingValidatorImpl.class)
public @interface MeetingValidator {

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
