package com.softserve.edu.schedule.entitylisteners;

import javax.persistence.PrePersist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.softserve.edu.schedule.entity.User;
import com.softserve.edu.schedule.service.implementation.mailsenders.RegistrationMailServise;

public class UserEntityListener {

    /**
     * MeetingCanceledMailService example to provide send mail to user
     * operations.
     */
    @Autowired
    private RegistrationMailServise registrationMailService;

    /**
     * Send mail messages to new user after registration
     * 
     * @param user
     *            User just registed.
     * 
     */
    @PrePersist
    public void processingBeforUserRegistration(User user) {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        registrationMailService.sendInfoMessageRegistration(
                user, LocaleContextHolder.getLocale());
    }
}
