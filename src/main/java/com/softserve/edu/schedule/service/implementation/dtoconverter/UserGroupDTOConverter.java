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
		UserGroup userGroup = new UserGroup();

		userGroup.setId(userGroupDTO.getId());
		userGroup.setName(userGroupDTO.getName());
		userGroup.setDescription(userGroupDTO.getDescription());
		userGroup.setLevel(userGroupDTO.getLevel());
		userGroup.setCurator(userDAO.getById(userGroupDTO.getCurator().getId()));
		userGroupDTO.getUsers().forEach(e -> userGroup.getUsers().add(userDAO.getById(e.getId())));
		userGroup.setMeetings(userGroupDAO.getById(userGroupDTO.getId()).getMeetings());
		return userGroup;
	}

	public UserGroupDTO getDTO(final UserGroup userGroup) {
		UserGroupDTO userGroupDTO = new UserGroupDTO();
		userGroupDTO.setId(userGroup.getId());
		userGroupDTO.setName(userGroup.getName());
		userGroupDTO.setDescription(userGroup.getDescription());
		userGroupDTO.setLevel(userGroup.getLevel());

		UserDTO curator = new UserDTO();
		curator.setId(userGroup.getCurator().getId());
		curator.setFirstName(userGroup.getCurator().getFirstName());
		curator.setLastName(userGroup.getCurator().getLastName());
		userGroupDTO.setCurator(curator);

		userGroup.getUsers().forEach(e -> {
			UserDTO user = new UserDTO();
			user.setId(e.getId());
			user.setFirstName(e.getFirstName());
			user.setLastName(e.getLastName());
			userGroupDTO.getUsers().add(user);
		});
		return userGroupDTO;
	}
}
