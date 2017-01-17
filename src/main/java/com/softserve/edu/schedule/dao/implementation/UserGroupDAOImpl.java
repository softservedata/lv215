/*
 * UserGroupDAOImpl.java
 * 1.0
 * 09 Jan 2017
 * Copyright (c) Zhydenko Andrii
 */
package com.softserve.edu.schedule.dao.implementation;

import com.softserve.edu.schedule.dao.UserGroupDAO;
import com.softserve.edu.schedule.entity.UserGroup;

/**
 * A simple class to handle the database operation (CRUD).
 *
 * @version 1.0 09 Jan 2017
 * @author Zhydenko Andrii
 *
 */
public class UserGroupDAOImpl extends CrudDAOImpl<UserGroup> implements UserGroupDAO {

	public UserGroupDAOImpl(Class<UserGroup> entityClass) {
		super(entityClass);
	}
}
