package com.softserve.edu.schedule.entitylisteners;

import javax.persistence.PreRemove;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.softserve.edu.schedule.entity.UserGroup;
import com.softserve.edu.schedule.service.MeetingService;
import com.softserve.edu.schedule.service.implementation.mailsenders.UserGroupDeletedMailService;

/**
 * An entity listener class for UserGroups.
 *
 * @version 1.0 04 February 2017
 *
 * @author Andrii Zhydenko
 */
public class UserGroupEntityListener {

    /**
     * UserGroupDeletedMailService example to provide send mail to user
     * operations.
     */
    @Autowired
    private UserGroupDeletedMailService userGroupDeletedMailService;

    /**
     * MeetingService example to provide meeting delete operations.
     */
    @Autowired
    private MeetingService meetingService;

    /**
     * Send mail messages to all users from deleted group
     * 
     * @param userGroup
     *            UserGroup that will be deleted.
     * 
     */
    @PreRemove
    public void processingBeforeGroupDeletion(final UserGroup userGroup) {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        userGroup.getMeetings().forEach(e -> {
            meetingService.deleteById(e.getId());
        });
        userGroupDeletedMailService.sendInfoMessageGroupDelete(userGroup,
                LocaleContextHolder.getLocale());
    }
}
