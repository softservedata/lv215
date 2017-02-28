/*
 * LocationEntityListner
 * 1.0
 * 06 Feb 2017
 * Copyright (c) Oleksandr Butyter
 */
package com.softserve.edu.schedule.entitylisteners;

import javax.persistence.PreRemove;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.softserve.edu.schedule.dao.MeetingHistoryDAO;
import com.softserve.edu.schedule.dao.UserDAO;
import com.softserve.edu.schedule.entity.Location;
import com.softserve.edu.schedule.service.implementation.mailsenders.DeleteLocationMailService;

/**
 * An entity listener class for Location entity.
 *
 * @version 1.0 06 February 2017
 *
 * @author Oleksandr Butyter
 */
public class LocationEntityListner {

	/**
	 * DeleteLocationMailService example to provide send mail to moderators.
	 */
	@Autowired
	private DeleteLocationMailService deleteLocationMailService;

	/**
	 * UserDAO example to provide list of moderators.
	 */
	@Autowired
	private UserDAO userDAO;

	/**
	 * MeetingHistoryDAO example to provide count of past meetings.
	 */
	@Autowired
	private MeetingHistoryDAO meetingHistoryDAO;

	/**
	 * Method sends mail to moderators in order to inform them about deleting
	 * location.
	 * 
	 * @param location
	 *            location to delete
	 */
	@PreRemove
	public void processingLocationBeforeDeletion(final Location location) {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		if (meetingHistoryDAO.getCountMeetingHistoryByLocation(location.getName()) != 0) {
			userDAO.getModerators().forEach(u -> deleteLocationMailService.sendInfoMessageLocationDelete(u, location,
					LocaleContextHolder.getLocale()));
		}
	}

}
