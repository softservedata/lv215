/*
 * SubjectEntityListner.java
 * 1.0
 * 03 Feb 2017
 * Copyright (c) Ped'ko Volodymyr
 */
package com.softserve.edu.schedule.entitylisteners;

import javax.persistence.PreRemove;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.softserve.edu.schedule.entity.Meeting;
import com.softserve.edu.schedule.entity.MeetingStatus;
import com.softserve.edu.schedule.service.implementation.mailsenders.DeleteMeetingMailService;

/**
 * An entity listener class for Meeting entity.
 *
 * @version 1.0 03 February 2017
 *
 * @author Bodhan Melnyk
 *
 * @since 1.8
 */
public class MeetingEntityListener {

    /**
     * MeetingCanceledMailService example to provide send mail to user
     * operations.
     */
    @Autowired
    private DeleteMeetingMailService deleteMeetingMailService;

    /**
     * Send mail messages to owner of the meeting, to userGroup curator
     * 
     * @param meeting
     *            meeting that will be deleted.
     * 
     */
    @PreRemove
    public void processingBeforeMeetingDeletion(final Meeting meeting) {
        if (meeting.getStatus() == MeetingStatus.APPROVED) {
            SpringBeanAutowiringSupport
                    .processInjectionBasedOnCurrentContext(this);
            deleteMeetingMailService.sendInfoMessageAboutMeetingDeletetion(
                    meeting, LocaleContextHolder.getLocale());
        }
    }
}
