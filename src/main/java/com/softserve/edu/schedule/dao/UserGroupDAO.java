/*
 * UserGroupDAO.java
 * 1.0
 * 09 Jan 2017
 * Copyright (c) Zhydenko Andrii
 */
package com.softserve.edu.schedule.dao;

import java.util.List;

import com.softserve.edu.schedule.entity.UserGroup;

/**
 * A simple DAO interface to handle the database operation required to
 * manipulate additional info in UserGroup.
 *
 * @version 1.0 09 Jan 2017
 * @author Zhydenko Andrii
 *
 */

public interface UserGroupDAO extends CrudDAO<UserGroup> {

	/**
	 * Method returns list of sorted usergroups.
	 * 
	 * @param field
	 *            field for sort
	 * @param order
	 *            ASC or DESC
	 * @return list of sorted usergroups
	 */
	public List<UserGroup> sortByFields(final String field, final Order order);

	/**
	 * Deleting user from a UserGroup
	 * 
	 * @param userID
	 *            an id of a user to delete
	 * @param userGroupID
	 *            an id of a user group
	 */
	public void deleteUserFromUserGroup(final Long userID, final Long userGroupID);

	/**
	 * Checks if user is a curator in a group
	 * 
	 * @param userID
	 *            an id of a user to check
	 * @param userGroupID
	 *            an id of a group to check
	 * @return true value if user is a curator in specified group or false if
	 *         not
	 */
	public boolean isUserCurator(final Long userID, final Long userGroupID);

}
