/*
 * UserGroupDAOImpl.java
 * 1.0
 * 09 Jan 2017
 * Copyright (c) Zhydenko Andrii
 */
package com.softserve.edu.schedule.dao.implementation;

import org.springframework.stereotype.Repository;

import com.softserve.edu.schedule.dao.UserGroupDAO;
import com.softserve.edu.schedule.entity.UserGroup;

/**
 * A simple class to handle the database operation (CRUD).
 *
 * @version 1.0 09 Jan 2017
 * @author Zhydenko Andrii
 *
 */
@Repository("userGroupDAO")
public class UserGroupDAOImpl extends CrudDAOImpl<UserGroup>
        implements UserGroupDAO {


    public UserGroupDAOImpl() {
        super(UserGroup.class);
    }

    @Override
    public void deleteUserFromUserGroup(Long userID, Long userGroupID) {
        if (getById(userGroupID).getCurator().getId() != userID) {
            getById(userGroupID).getUsers()
                    .removeIf(e -> e.getId().equals(userID));
        }
    }

}
