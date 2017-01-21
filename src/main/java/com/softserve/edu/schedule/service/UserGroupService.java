/*
 * UserGroupService.java
 * 1.0
 * 09 Jan 2017
 * Copyright (c) Zhydenko Andrii
 */
package com.softserve.edu.schedule.service;

import java.util.List;

import com.softserve.edu.schedule.dao.Order;
import com.softserve.edu.schedule.entity.UserGroup;

/**
 * A simple service interface to handle the operation required to manipulate a
 * UserGroup menu.
 *
 * @version 1.0 09 Jan 2017
 * @author Zhydenko Andrii
 *
 */
public interface UserGroupService {

	/**
	 * Saving UserGroup in database.
	 *
	 * @param UserGroup
	 *            - UserGroup object
	 */
	void create(final UserGroup userGroup);

	/**
	 * Updating UserGroup in database.
	 *
	 * @param userGroup
	 *            - UserGroup object
	 */
	void update(final UserGroup userGroup);

	/**
	 * Returns a UserGroup object if it exists in table.
	 *
	 * @param id
	 *            UserGroup transfer object id
	 * @return UserGroup transfer object
	 */
	UserGroup getById(final Long id);

	/**
	 * Deleting existed UserGroup entity from the database by id.
	 *
	 * @param id
	 *            gserGroup id to delete from database.
	 */
	void deleteById(final Long id);

	/**
	 * Returns a List of UserGroup objects.
	 *
	 * @return List of UserGroup objects
	 */
	List<UserGroup> getAll();

	/**
	 * Deleting UserGroup in database.
	 *
	 * @param userGroup
	 *            - UserGroup object
	 */
	void delete(final UserGroup userGroup);

	/**
	 * Returns a List of sorted UserGroup transfer objects.
	 *
	 * @param field
	 *            for sort
	 * @param order
	 *            - ASC or DESC
	 * @return List of sorted UserGroup transfer objects
	 */
	List<UserGroup> sort(final String field, final Order order);

	/**
	 * Returns a List of searched UserGroup transfer objects.
	 *
	 * @param field
	 *            for search
	 * @param pattern
	 *            - input string
	 * @return List of sorted UserGroup transfer objects
	 */
	List<UserGroup> search(final String field, final String pattern);

	/**
	 * Method returns list of sorted locations by count of groups.
	 * 
	 * @return list of sorted groups by count of members
	 */
	List<UserGroup> sortByCountMembers(final Order order);

	/**
	 * Method returns list of sorted usergroups.
	 * 
	 * @param field
	 *            field for sort
	 * @param order
	 *            ASC or DESC
	 * @return list of sorted usergroups
	 */
	List<UserGroup> sortByFields(final String field, final Order order);

}
