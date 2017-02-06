/*
 * UserGroupService.java
 * 1.0
 * 09 Jan 2017
 * Copyright (c) Zhydenko Andrii
 */
package com.softserve.edu.schedule.service;

import java.util.List;

import com.softserve.edu.schedule.dto.UserDTO;
import com.softserve.edu.schedule.dto.UserGroupDTO;
import com.softserve.edu.schedule.dto.filter.Paginator;
import com.softserve.edu.schedule.dto.filter.UserGroupFilter;

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
	 * Adding user to a list of users in a group
	 * 
	 * @param user
	 *            User to be added in a list
	 * @param group
	 *            Group where we need to add a user
	 */
	public UserGroupDTO addUserToGroup(final UserDTO userDTO, final UserGroupDTO userGroupDTO);

	/**
	 * Get page with filtered groups
	 * 
	 * @param userGroupFilter
	 *            UserGroup filter
	 * @param userGroupPaginator
	 *            Paginator for UserGroup
	 * @return List of a filtered groups
	 */
	public List<UserGroupDTO> getUserGroupPageWithFilter(final UserGroupFilter userGroupFilter,
			final Paginator userGroupPaginator);
}
