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
	 * Get page with filtered groups
	 * 
	 * @param userGroupFilter
	 *            UserGroup filter
	 * @param userGroupPaginator
	 *            Paginator for UserGroup
	 * @return List of a filtered groups
	 */
	public List<UserGroup> getUserGroupPageWithFilter(UserGroupFilter userGroupFilter, Paginator userGroupPaginator);

	/**
	 * Returns list with searched by name groups.
	 *
	 * @param groupName
	 *            Name of a group to search
	 * @return list with searched by name groups
	 */
	public List<UserGroup> getGroupsByName(final String groupName);
}
