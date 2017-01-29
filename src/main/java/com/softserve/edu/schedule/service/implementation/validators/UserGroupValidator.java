package com.softserve.edu.schedule.service.implementation.validators;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.softserve.edu.schedule.controller.ControllerConst;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = com.softserve.edu.schedule.service.implementation.validators.UserGroupValidatorImpl.class)
public @interface UserGroupValidator {

	String message() default ControllerConst.UserGroupControllerConst.USERGROUP_NAME_CONSTRAINT_MESSAGE;

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	int min() default 5;

	int max() default (20);

	String name();

	String id();

	String description();

	String level();
}
