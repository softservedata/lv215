package com.softserve.edu.schedule.service.implementation.dtoconverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserve.edu.schedule.dao.UserDAO;
import com.softserve.edu.schedule.dao.UserGroupDAO;
import com.softserve.edu.schedule.dto.UserDTO;
import com.softserve.edu.schedule.dto.UserGroupDTO;
import com.softserve.edu.schedule.entity.UserGroup;

@Service
public class UserGroupDTOConverter {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserGroupDAO userGroupDAO;

    public UserGroup getEntity(final UserGroupDTO userGroupDTO) {
        if (userGroupDTO != null) {
            UserGroup userGroup = new UserGroup();
            if (userGroupDTO.getId() != null) {
                userGroup.setId(userGroupDTO.getId());
            }
            if (userGroupDTO.getName() != null) {
                userGroup.setName(userGroupDTO.getName());
            }
            if (userGroupDTO.getDescription() != null) {
                userGroup.setDescription(userGroupDTO.getDescription());
            }
            if (userGroupDTO.getLevel() != null) {
                userGroup.setLevel(userGroupDTO.getLevel());
            }
            if (userGroupDTO.getCurator() != null) {
                userGroup.setCurator(
                        userDAO.getById(userGroupDTO.getCurator().getId()));
            }
            if (userGroupDTO.getUsers() != null) {
                userGroupDTO.getUsers().forEach(e -> userGroup.getUsers()
                        .add(userDAO.getById(e.getId())));
            }
            if (userGroupDTO.getId() != null) {
                userGroup.setMeetings(userGroupDAO.getById(userGroupDTO.getId())
                        .getMeetings());
            }
            return userGroup;
        }
        return null;
    }

    public UserGroupDTO getDTO(final UserGroup userGroup) {
        if (userGroup != null) {
            UserGroupDTO userGroupDTO = new UserGroupDTO();
            if (userGroup.getId() != null) {
                userGroupDTO.setId(userGroup.getId());
            }
            if (userGroup.getName() != null) {
                userGroupDTO.setName(userGroup.getName());
            }
            if (userGroup.getDescription() != null) {
                userGroupDTO.setName(userGroup.getDescription());
            }
            if (userGroup.getLevel() != null) {
                userGroupDTO.setLevel(userGroup.getLevel());
            }
            if (userGroup.getCurator() != null) {
                UserDTO curator = new UserDTO();
                curator.setId(userGroup.getCurator().getId());
                curator.setFirstName(userGroup.getCurator().getFirstName());
                curator.setLastName(userGroup.getCurator().getLastName());
                userGroupDTO.setCurator(curator);
            }
            if (userGroup.getUsers() != null) {
                userGroup.getUsers().forEach(e -> {
                    UserDTO user = new UserDTO();
                    user.setId(e.getId());
                    user.setFirstName(e.getFirstName());
                    user.setLastName(e.getLastName());
                    userGroupDTO.getUsers().add(user);
                });
            }
            return userGroupDTO;
        }
        return null;
    }

}
