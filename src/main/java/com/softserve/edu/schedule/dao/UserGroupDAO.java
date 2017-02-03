/*
 * UserGroupDAO.java
 * 1.0
 * 09 Jan 2017
 * Copyright (c) Zhydenko Andrii
 */
package com.softserve.edu.schedule.dao;

import java.util.List;

import com.softserve.edu.schedule.dto.filter.Paginator;
import com.softserve.edu.schedule.dto.filter.UserGroupFilter;
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
	 * Searching groups by curator's last name
	 * 
	 * @param pattern
	 *            Substring that is a pattern for searching
	 * @return List of sorted groups
	 */
	public List<UserGroup> searchGroupsByCurators(final String pattern);

	/**
	 * Get list of groups by specified levelId
	 * 
	 * @param level
	 * @return list of a groups with specified level
	 */
	public List<UserGroup> getGroupsByLevel(final Long levelId);

	/**
	 * Get page with filtered groups
	 * 
	 * @param userGroupFilter
	 *            UserGroup filter
	 * @param userGroupPaginator
	 *            Paginator for UserGroup
	 * @return List of a filtered groups
	 */
	public List<UserGroup> getUserGroupPageWithFilter(UserGroupFilter userGroupFilter, Paginator userGroupPaginator);
}
