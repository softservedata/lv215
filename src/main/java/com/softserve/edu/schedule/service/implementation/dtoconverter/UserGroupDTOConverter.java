package com.softserve.edu.schedule.service.implementation.dtoconverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserve.edu.schedule.dao.UserDAO;
import com.softserve.edu.schedule.dao.UserGroupDAO;
import com.softserve.edu.schedule.dto.UserDTO;
import com.softserve.edu.schedule.dto.UserGroupDTO;
import com.softserve.edu.schedule.entity.UserGroup;

/**
 * Class-converter that is used to provide convertation DTO into Entity and vise
 * versa
 * 
 * @author Andrew
 *
 */
@Service
public class UserGroupDTOConverter {
	/**
	 * UserDAO object to provide database operations.
	 */
	@Autowired
	private UserDAO userDAO;

	/**
	 * UserGroupDAO object to provide database operations.
	 */
	@Autowired
	private UserGroupDAO userGroupDAO;

	/**
	 * Method to convert DTO-object into entity
	 * 
	 * @param userGroupDTO
	 *            DTO object to convert
	 * @return Converted into entity DTO object
	 */
	public UserGroup getEntity(final UserGroupDTO userGroupDTO) {
		if (userGroupDTO != null) {
			UserGroup userGroup = new UserGroup();
			userGroup.setId(userGroupDTO.getId());
			userGroup.setName(userGroupDTO.getName());
			userGroup.setDescription(userGroupDTO.getDescription());
			userGroup.setLevel(userGroupDTO.getLevel());

			if (userGroupDTO.getCurator() != null) {
				userGroup.setCurator(userDAO.getById(userGroupDTO.getCurator().getId()));
			}

			userGroupDTO.getUsers().forEach(e -> userGroup.getUsers().add(userDAO.getById(e.getId())));

			if (userGroupDTO.getId() != null) {
				userGroup.setMeetings(userGroupDAO.getById(userGroupDTO.getId()).getMeetings());
			}
			return userGroup;
		}
		return null;
	}

	/**
	 * Method to convert entity into DTO-object
	 * 
	 * @param userGroup
	 *            Entity object to convert
	 * @return Entity that is converted into a DTO object
	 */
	public UserGroupDTO getDTO(final UserGroup userGroup) {
		if (userGroup != null) {
			UserGroupDTO userGroupDTO = new UserGroupDTO();
			userGroupDTO.setId(userGroup.getId());
			userGroupDTO.setName(userGroup.getName());
			userGroupDTO.setDescription(userGroup.getDescription());
			userGroupDTO.setLevel(userGroup.getLevel());
			if (userGroup.getCurator() != null) {
				UserDTO curator = new UserDTO();
				curator.setId(userGroup.getCurator().getId());
				curator.setFirstName(userGroup.getCurator().getFirstName());
				curator.setLastName(userGroup.getCurator().getLastName());
				userGroupDTO.setCurator(curator);
			}

			userGroup.getUsers().forEach(e -> {
				UserDTO user = new UserDTO();
				user.setId(e.getId());
				user.setFirstName(e.getFirstName());
				user.setLastName(e.getLastName());
				userGroupDTO.getUsers().add(user);
			});
			return userGroupDTO;
		}
		return null;
	}
}
