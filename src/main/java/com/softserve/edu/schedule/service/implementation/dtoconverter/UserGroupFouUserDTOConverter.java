package com.softserve.edu.schedule.service.implementation.dtoconverter;

import org.springframework.stereotype.Service;
import com.softserve.edu.schedule.dto.UserGroupForUserDTO;
import com.softserve.edu.schedule.entity.UserGroup;

/**
 * An class to provide UserGroupForUserDTO from UserGroup entity.
 *
 * @version 1.0 20 January 2017
 *
 * @author Serhiy Dudynsky
 *
 * @since 1.8
 */
@Service("userGroupForUserDTOConverter")
public class UserGroupFouUserDTOConverter {

    /**
     * Save new UserGroupForUserDTO object from UserGroup entity.
     *
     * @param userGroup
     *            a user group from database.
     */
    public UserGroupForUserDTO getDTO(UserGroup userGroup) {
        if (userGroup != null) {
            UserGroupForUserDTO userGroupFUDTO = new UserGroupForUserDTO();
            if (userGroup.getId() != null) {
                userGroupFUDTO.setId(userGroup.getId());
            }
            if (userGroup.getName() != null) {
                userGroupFUDTO.setName(userGroup.getName());
            }
            return userGroupFUDTO;
        }
        return null;
    }
}
