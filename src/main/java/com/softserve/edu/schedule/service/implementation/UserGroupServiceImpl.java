/*
 * UserGroupServiceImpl.java
 * 1.0
 * 09 Jan 2017
 * Copyright (c) Zhydenko Andrii
 */
package com.softserve.edu.schedule.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.edu.schedule.dao.Order;
import com.softserve.edu.schedule.dao.UserGroupDAO;
import com.softserve.edu.schedule.entity.UserGroup;
import com.softserve.edu.schedule.service.UserGroupService;

/**
 * A UserGroupService implementation to handle the operation required to
 * manipulate a UserGroup menu.
 *
 * @version 1.0 09 Jan 2017
 * @author Zhydenko Andrii
 *
 */
@Transactional
@Service("userGroupsService")
public class UserGroupServiceImpl implements UserGroupService {
	/**
	 * Object of a dao class that is used to call CRUD operations
	 */
	@Autowired
	private UserGroupDAO userGroupDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.softserve.edu.schedule.service.UserGroupService#create(com.softserve.
	 * edu.schedule.entity.UserGroup)
	 */
	@Override
	public void create(final UserGroup userGroup) {
		userGroupDAO.create(userGroup);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.softserve.edu.schedule.service.UserGroupService#update(com.softserve.
	 * edu.schedule.entity.UserGroup)
	 */
	@Override
	public void update(final UserGroup userGroup) {
		userGroupDAO.update(userGroup);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.softserve.edu.schedule.service.UserGroupService#getById(java.lang.
	 * Long)
	 */
	@Override
	public UserGroup getById(final Long id) {
		return userGroupDAO.getById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.softserve.edu.schedule.service.UserGroupService#deleteById(java.lang.
	 * Long)
	 */
	@Override
	public void deleteById(final Long id) {
		UserGroup group = userGroupDAO.getById(id);
		userGroupDAO.delete(group);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.softserve.edu.schedule.service.UserGroupService#getAll()
	 */
	@Override
	public List<UserGroup> getAll() {
		return userGroupDAO.getAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.softserve.edu.schedule.service.UserGroupService#delete(com.softserve.
	 * edu.schedule.entity.UserGroup)
	 */
	@Override
	public void delete(final UserGroup userGroup) {
		userGroupDAO.delete(userGroup);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.softserve.edu.schedule.service.UserGroupService#sort(java.lang.
	 * String, com.softserve.edu.schedule.dao.Order)
	 */
	@Override
	public List<UserGroup> sort(final String field, final Order order) {
		return userGroupDAO.sort(field, order);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.softserve.edu.schedule.service.UserGroupService#search(java.lang.
	 * String, java.lang.String)
	 */
	@Override
	public List<UserGroup> search(final String field, final String pattern) {
		return userGroupDAO.search(field, pattern);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.softserve.edu.schedule.service.UserGroupService#sortByFields(java.
	 * lang.String, com.softserve.edu.schedule.dao.Order)
	 */
	@Override
	public List<UserGroup> sortByFields(final String field, final Order order) {
		return userGroupDAO.sortByFields(field, order);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.softserve.edu.schedule.service.UserGroupService#sortByCountMembers(
	 * com.softserve.edu.schedule.dao.Order)
	 */
	@Override
	@Transactional(readOnly = true)
	public List<UserGroup> sortByCountMembers(final Order order) {
		List<UserGroup> groups = userGroupDAO.getAll();
		if (order == Order.ASC) {
			groups.sort((g1, g2) -> g1.getUsers().size() - g2.getUsers().size());
		} else {
			groups.sort((g1, g2) -> g2.getUsers().size() - g1.getUsers().size());
		}
		return groups;
	}

}
