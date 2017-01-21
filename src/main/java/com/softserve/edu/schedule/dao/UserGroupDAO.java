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

}
