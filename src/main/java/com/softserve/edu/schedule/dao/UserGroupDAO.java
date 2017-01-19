/*
 * UserGroupDAO.java
 * 1.0
 * 09 Jan 2017
 * Copyright (c) Zhydenko Andrii
 */
package com.softserve.edu.schedule.dao;

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

    void deleteUserFromUserGroup(Long userId, Long userGroupId);

    void addMeetingtoUserGroup(Long meetingId, Long userGroupId);
    
    public void deleteMeetingFromUserGroup(Long userID, Long userGroupID);

}
