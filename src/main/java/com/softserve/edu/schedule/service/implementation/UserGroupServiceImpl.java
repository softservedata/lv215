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
	@Autowired
	private UserGroupDAO userGroupDAO;

	@Override
	public void create(UserGroup userGroup) {
		userGroupDAO.create(userGroup);
	}

	@Override
	public void update(UserGroup userGroup) {
		userGroupDAO.update(userGroup);
	}

	@Override
	public UserGroup getById(Long id) {
		return userGroupDAO.getById(id);
	}

	@Override
	public void deleteById(Long id) {
		UserGroup group = userGroupDAO.getById(id);
		userGroupDAO.delete(group);
	}

	@Override
	public List<UserGroup> getAll() {
		return userGroupDAO.getAll();
	}

	@Override
	public void delete(UserGroup userGroup) {
		userGroupDAO.delete(userGroup);
	}

	@Override
	public List<UserGroup> sort(String field, Order order) {
		return userGroupDAO.sort(field, order);
	}

	@Override
	public List<UserGroup> search(String field, String pattern) {
		return userGroupDAO.search(field, pattern);
	}

	@Override
	public List<UserGroup> sortByFields(String field, Order order) {
		return userGroupDAO.sortByFields(field, order);
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserGroup> sortByCountMembers(Order order) {
		List<UserGroup> groups = userGroupDAO.getAll();
		if (order == Order.ASC) {
			groups.sort((g1, g2) -> g1.getUsers().size() - g2.getUsers().size());
		} else {
			groups.sort((g1, g2) -> g2.getUsers().size() - g1.getUsers().size());
		}
		return groups;
	}

}
