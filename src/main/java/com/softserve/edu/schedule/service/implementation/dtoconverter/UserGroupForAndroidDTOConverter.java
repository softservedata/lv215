/* UserGroupForAndroidDTOConverter 1.0 02/21/2017 */
package com.softserve.edu.schedule.service.implementation.dtoconverter;

import org.springframework.stereotype.Service;

import com.softserve.edu.schedule.dto.UserGroupForAndroidDTO;
import com.softserve.edu.schedule.entity.UserGroup;

/**
 * A class to provide conversion operations between UserGroupForAndroidDTO and
 * UserGroup entity.
 *
 * @version 1.0 21 February 2017
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
@Service
public class UserGroupForAndroidDTOConverter {

    /**
     * Method to convert entity into DTO-object
     * 
     * @param userGroup
     *            Entity object to convert
     * @return UserGroupForAndroidDTO instance
     */
    public UserGroupForAndroidDTO getDTO(final UserGroup userGroup) {
        if (userGroup != null) {
            UserGroupForAndroidDTO userGroupDTO = new UserGroupForAndroidDTO();
            userGroupDTO.setId(userGroup.getId());
            userGroupDTO.setName(userGroup.getName());
            userGroupDTO.setDescription(userGroup.getDescription());
            userGroupDTO.setLevel(userGroup.getLevel());
            if (userGroup.getCurator() != null) {
                userGroupDTO.setCuratorFullName(
                        userGroup.getCurator().getFirstName() + " "
                                + userGroup.getCurator().getLastName());
            }

            userGroup.getUsers().forEach(e -> {
                String userFullName = e.getFirstName() + " " + e.getLastName();
                userGroupDTO.getUsersFullNames().add(userFullName);
            });
            return userGroupDTO;
        }
        return null;
    }
}
