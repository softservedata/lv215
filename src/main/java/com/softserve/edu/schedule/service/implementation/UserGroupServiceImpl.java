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
@Service("userGroupService")
public class UserGroupServiceImpl implements UserGroupService {
	@Autowired
    UserGroupDAO userGroupDAO;

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

}
