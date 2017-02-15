package com.softserve.edu.schedule.entitylisteners;

import javax.persistence.PostPersist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.softserve.edu.schedule.entity.User;
import com.softserve.edu.schedule.service.implementation.mailsenders.RegistrationMailServise;
import com.softserve.edu.schedule.service.implementation.mailsenders.RestorePasswordMailServise;

public class UserEntityListener {

    /**
     * RegistrationMailService example to provide send mail to user
     * operations.
     */
    @Autowired
    private RegistrationMailServise registrationMailService;
    
    /**
     * RestorePasswordMailService example to provide send mail to user
     * operations.
     */
    @Autowired
    private RestorePasswordMailServise restorePasswordMailService;

    /**
     * Send mail messages to new user after registration
     * 
     * @param user
     *            User just registered.
     */
    @PostPersist
    public void processingAfterUserRegistration(User user) {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        registrationMailService.sendInfoMessageRegistration(
                user, LocaleContextHolder.getLocale());
    }
    
    /**
     * Send mail messages to new user after restore password
     * 
     * @param user
     *            User restored password.
     */
    @PostPersist
    public void processingAfterRestorePassword(User user) {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        restorePasswordMailService.sendInfoMessageRegistration(
                user, LocaleContextHolder.getLocale());
    }
}
