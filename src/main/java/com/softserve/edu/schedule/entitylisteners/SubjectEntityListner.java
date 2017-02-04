/*
 * SubjectEntityListner.java
 * 1.0
 * 03 Feb 2017
 * Copyright (c) Ped'ko Volodymyr
 */
package com.softserve.edu.schedule.entitylisteners;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.PreRemove;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.softserve.edu.schedule.entity.Meeting;
import com.softserve.edu.schedule.entity.MeetingStatus;
import com.softserve.edu.schedule.entity.Subject;
import com.softserve.edu.schedule.service.implementation.mailsenders.MeetingCanceledMailService;

/**
 * An entity listener class for Subject entity.
 *
 * @version 1.0 03 February 2017
 *
 * @author Ped'ko Volodymyr
 *
 * @since 1.8
 */
public class SubjectEntityListner {

    /**
     * MeetingCanceledMailService example to provide send mail to user
     * operations.
     */
    @Autowired
    private MeetingCanceledMailService meetingCanceledMailService;

    /**
     * Set to all meetings for subject null value, change status of all future
     * meetings to NOT_APPROVED and send mail messages to their owners.
     *
     * @param subject
     *            subject to proceed meetings
     *
     */
    @PreRemove
    public void processingRoomBeforeDeletion(final Subject subject) {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        List<Meeting> meetingsToChange = new ArrayList<>();
        subject.getMeetings().forEach(m -> {
            if (m.getDate().isAfter(LocalDate.now().minusDays(1))) {
                m.setStatus(MeetingStatus.NOT_APPROVED);
                meetingsToChange.add(m);
            }
            m.setSubject(null);
            System.out.println("Yo in");
        });
        System.out.println("Yo out");
        meetingsToChange.forEach(
                m -> meetingCanceledMailService.sendInfoMessageSubjectDelete(m,
                        LocaleContextHolder.getLocale()));
    }
}
