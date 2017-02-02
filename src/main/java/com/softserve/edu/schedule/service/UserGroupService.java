/*
 * UserGroupService.java
 * 1.0
 * 09 Jan 2017
 * Copyright (c) Zhydenko Andrii
 */
package com.softserve.edu.schedule.service;

import java.util.List;

import com.softserve.edu.schedule.dao.Order;
import com.softserve.edu.schedule.dto.UserDTO;
import com.softserve.edu.schedule.dto.UserGroupDTO;

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
	void create(final UserGroupDTO userGroupDTO);

	/**
	 * Updating UserGroup in database.
	 *
	 * @param userGroup
	 *            - UserGroup object
	 */
	void update(final UserGroupDTO userGroupDTO);

	/**
	 * Returns a UserGroup object if it exists in table.
	 *
	 * @param id
	 *            UserGroup transfer object id
	 * @return UserGroup transfer object
	 */
	UserGroupDTO getById(final Long id);

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
	List<UserGroupDTO> getAll();

	/**
	 * Deleting UserGroup in database.
	 *
	 * @param userGroup
	 *            - UserGroup object
	 */
	void delete(final UserGroupDTO userGroupDTO);

	/**
	 * Returns a List of sorted UserGroup transfer objects.
	 *
	 * @param field
	 *            for sort
	 * @param order
	 *            - ASC or DESC
	 * @return List of sorted UserGroup transfer objects
	 */
	List<UserGroupDTO> sort(final String field, final Order order);

	/**
	 * Returns a List of searched UserGroup transfer objects.
	 *
	 * @param field
	 *            for search
	 * @param pattern
	 *            - input string
	 * @return List of sorted UserGroup transfer objects
	 */
	List<UserGroupDTO> search(final String field, final String pattern);

	/**
	 * Method returns list of sorted locations by count of groups.
	 * 
	 * @return list of sorted groups by count of members
	 */
	List<UserGroupDTO> sortByCountMembers(final Order order);

	/**
	 * Method returns list of sorted usergroups.
	 * 
	 * @param field
	 *            field for sort
	 * @param order
	 *            ASC or DESC
	 * @return list of sorted usergroups
	 */
	List<UserGroupDTO> sortByFields(final String field, final Order order);

	/**
	 * Adding user to a list of users in a group
	 * 
	 * @param user
	 *            User to be added in a list
	 * @param group
	 *            Group where we need to add a user
	 */
	public UserGroupDTO addUserToGroup(final UserDTO userDTO, final UserGroupDTO userGroupDTO);

	/**
	 * Method for searching groups by name pattern
	 * 
	 * @param pattern
	 *            a patter to search
	 * @return List of UserGroups
	 */
	public List<UserGroupDTO> searchByName(final String pattern);

	/**
	 * Method for searching groups by curator pattern
	 * 
	 * @param pattern
	 *            a patter to search
	 * @return List of UserGroups
	 */
	public List<UserGroupDTO> searchGroupsByCurators(final String pattern);

	/**
	 * Get list of groups by specified levelId
	 * 
	 * @param level
	 * @return list of a groups with specified level
	 */
	public List<UserGroupDTO> getGroupsByLevel(final Long levelId);
}
