/*
 * UserGroupServiceImpl.java
 * 1.0
 * 09 Jan 2017
 * Copyright (c) Zhydenko Andrii
 */
package com.softserve.edu.schedule.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.edu.schedule.dao.Order;
import com.softserve.edu.schedule.dao.UserGroupDAO;
import com.softserve.edu.schedule.dto.UserDTO;
import com.softserve.edu.schedule.dto.UserGroupDTO;
import com.softserve.edu.schedule.entity.UserGroup;
import com.softserve.edu.schedule.entity.UserGroup_;
import com.softserve.edu.schedule.service.UserGroupService;
import com.softserve.edu.schedule.service.implementation.dtoconverter.UserGroupDTOConverter;

/**
 * A UserGroupService implementation to handle the operation required to
 * manipulate a UserGroup menu.
 *
 * @version 1.0 09 Jan 2017
 * @author Zhydenko Andrii
 *
 */
@Transactional
@Service("userGroupsService")
public class UserGroupServiceImpl implements UserGroupService {
	/**
	 * Object of a dao class that is used to call CRUD operations
	 */
	@Autowired
	private UserGroupDAO userGroupDAO;

	@Autowired
	private UserGroupDTOConverter converter;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.softserve.edu.schedule.service.UserGroupService#create(com.softserve.
	 * edu.schedule.entity.UserGroup)
	 */
	@Override
	public void create(final UserGroupDTO userGroupDTO) {
		userGroupDAO.create(converter.getEntity(userGroupDTO));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.softserve.edu.schedule.service.UserGroupService#update(com.softserve.
	 * edu.schedule.entity.UserGroup)
	 */
	@Override
	public void update(final UserGroupDTO userGroupDTO) {
		userGroupDAO.update(converter.getEntity(userGroupDTO));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.softserve.edu.schedule.service.UserGroupService#getById(java.lang.
	 * Long)
	 */
	@Override
	public UserGroupDTO getById(final Long id) {
		return converter.getDTO(userGroupDAO.getById(id));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.softserve.edu.schedule.service.UserGroupService#deleteById(java.lang.
	 * Long)
	 */
	@Override
	public void deleteById(final Long id) {
		UserGroup group = userGroupDAO.getById(id);
		userGroupDAO.delete(group);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.softserve.edu.schedule.service.UserGroupService#getAll()
	 */
	@Override
	public List<UserGroupDTO> getAll() {
		return userGroupDAO.getAll().stream().map(s -> converter.getDTO(s)).collect(Collectors.toList());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.softserve.edu.schedule.service.UserGroupService#delete(com.softserve.
	 * edu.schedule.entity.UserGroup)
	 */
	@Override
	public void delete(final UserGroupDTO userGroupDTO) {
		userGroupDAO.delete(converter.getEntity(userGroupDTO));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.softserve.edu.schedule.service.UserGroupService#sort(java.lang.
	 * String, com.softserve.edu.schedule.dao.Order)
	 */
	@Override
	public List<UserGroupDTO> sort(final String field, final Order order) {
		return userGroupDAO.sort(field, order).stream().map(e -> converter.getDTO(e)).collect(Collectors.toList());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.softserve.edu.schedule.service.UserGroupService#search(java.lang.
	 * String, java.lang.String)
	 */
	@Override
	public List<UserGroupDTO> search(final String field, final String pattern) {
		return userGroupDAO.search(field, pattern).stream().map(e -> converter.getDTO(e)).collect(Collectors.toList());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.softserve.edu.schedule.service.UserGroupService#sortByFields(java.
	 * lang.String, com.softserve.edu.schedule.dao.Order)
	 */
	@Override
	public List<UserGroupDTO> sortByFields(final String field, final Order order) {
		return userGroupDAO.sortByFields(field, order).stream().map(e -> converter.getDTO(e))
				.collect(Collectors.toList());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.softserve.edu.schedule.service.UserGroupService#sortByCountMembers(
	 * com.softserve.edu.schedule.dao.Order)
	 */
	@Override
	@Transactional(readOnly = true)
	public List<UserGroupDTO> sortByCountMembers(final Order order) {
		List<UserGroup> groups = userGroupDAO.getAll();
		if (order == Order.ASC) {
			groups.sort((g1, g2) -> g1.getUsers().size() - g2.getUsers().size());
		} else {
			groups.sort((g1, g2) -> g2.getUsers().size() - g1.getUsers().size());
		}
		return groups.stream().map(e -> converter.getDTO(e)).collect(Collectors.toList());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.softserve.edu.schedule.service.UserGroupService#addUserToGroup(com.
	 * softserve.edu.schedule.entity.User)
	 */
	@Override
	public UserGroupDTO addUserToGroup(final UserDTO userDTO, final UserGroupDTO userGroupDTO) {
		List<UserDTO> users = userGroupDTO.getUsers();

		// TODO
		// hashCode
		if (users != null) {
			for (UserDTO user : users) {
				if (user.getId() == userDTO.getId()) {
					return userGroupDTO;
				}
			}
			users.add(userDTO);
		} else {
			users = new ArrayList<>();
			users.add(userDTO);
		}

		userGroupDTO.setUsers(users);
		return userGroupDTO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.softserve.edu.schedule.service.UserGroupService#searchByName(java.
	 * lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public List<UserGroupDTO> searchByName(final String pattern) {
		return userGroupDAO.search(UserGroup_.name.getName(), pattern).stream().map(e -> converter.getDTO(e))
				.collect(Collectors.toList());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.softserve.edu.schedule.service.UserGroupService#searchByCurator(java.
	 * lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public List<UserGroupDTO> searchByCurator(final String pattern) {
		return userGroupDAO.search(UserGroup_.curator.getName(), pattern).stream().map(e -> converter.getDTO(e))
				.collect(Collectors.toList());
	}

}
