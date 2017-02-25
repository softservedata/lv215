/*
 *
 * DeleteMeetingMailService 23.02.2017.
 * 1.0
 * 03 Feb 2017
 * Copyright (c) Bohdan Melnyk
 */
package com.softserve.edu.schedule.entitylisteners;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.persistence.PreRemove;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.softserve.edu.schedule.entity.Meeting;
import com.softserve.edu.schedule.entity.MeetingStatus;
import com.softserve.edu.schedule.entity.User;
import com.softserve.edu.schedule.service.implementation.dtoconverter.MeetingDTOConverter;
import com.softserve.edu.schedule.service.implementation.dtoconverter.UserDTOConverter;
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
     * UserDTOConverter example to ability to convert form User to UserDTO.
     */
    @Autowired
    private UserDTOConverter userDTOConverter;

    /**
     * MeetingDTOConverter example to ability to convert form Meeting to
     * MeetingDTO.
     */
    @Autowired
    private MeetingDTOConverter meetingDTOConverter;

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
            sendInfoMessageAboutMeetingDeletetion(meeting,
                    LocaleContextHolder.getLocale());
        }
    }

    /**
     * Send mail notifications to all group members when group has been deleted
     *
     * @param Meeting
     *            a Meeting object that contains mail message parameters.
     *
     * @param locale
     *            current locale.
     */
    private void sendInfoMessageAboutMeetingDeletetion(final Meeting meeting,
            final Locale locale) {
        Set<User> curators = new HashSet<User>();
        curators.add(meeting.getOwner());

        meeting.getGroups()
                .forEach(e -> e.getUsers().forEach(u -> curators.add(u)));
        curators.forEach(e -> deleteMeetingMailService.sendmail(
                userDTOConverter.getDTO(e), meetingDTOConverter.getDTO(meeting),
                locale));
    }
}
