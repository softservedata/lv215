/*
 * UserGroupServiceImpl.java
 * 1.0
 * 09 Jan 2017
 * Copyright (c) Zhydenko Andrii
 */
package com.softserve.edu.schedule.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.edu.schedule.aspects.PerfomanceLoggable;
import com.softserve.edu.schedule.dao.UserGroupDAO;
import com.softserve.edu.schedule.dto.UserDTO;
import com.softserve.edu.schedule.dto.UserGroupDTO;
import com.softserve.edu.schedule.dto.filter.Paginator;
import com.softserve.edu.schedule.dto.filter.UserGroupFilter;
import com.softserve.edu.schedule.entity.UserGroup;
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
@Service
@PerfomanceLoggable
public class UserGroupServiceImpl implements UserGroupService {
	/**
	 * Object of a dao class that is used to call CRUD operations
	 */
	@Autowired
	private UserGroupDAO userGroupDAO;

	/**
	 * 
	 */
	@Autowired
	private UserGroupDTOConverter userGroupDTOConverter;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.softserve.edu.schedule.service.UserGroupService#create(com.softserve.
	 * edu.schedule.entity.UserGroup)
	 */
	@Override
	public void create(final UserGroupDTO userGroupDTO) {
		userGroupDAO.create(userGroupDTOConverter.getEntity(userGroupDTO));
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
		userGroupDAO.update(userGroupDTOConverter.getEntity(userGroupDTO));
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
		return userGroupDTOConverter.getDTO(userGroupDAO.getById(id));
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
		return userGroupDAO.getAll().stream().map(s -> userGroupDTOConverter.getDTO(s)).collect(Collectors.toList());
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
		userGroupDAO.delete(userGroupDTOConverter.getEntity(userGroupDTO));
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
		if (userGroupDTO.getUsers().contains(userDTO)) {
			return userGroupDTO;
		}

		List<UserDTO> users = userGroupDTO.getUsers();
		users.add(userDTO);
		userGroupDTO.setUsers(users);
		return userGroupDTO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.softserve.edu.schedule.service.UserGroupService#
	 * getUserGroupPageWithFilter(com.softserve.edu.schedule.dto.filter.
	 * UserGroupFilter, com.softserve.edu.schedule.dto.filter.Paginator)
	 */
	@Override
	public List<UserGroupDTO> getUserGroupPageWithFilter(UserGroupFilter userGroupFilter,
			Paginator userGroupPaginator) {
		return userGroupDAO.getUserGroupPageWithFilter(userGroupFilter, userGroupPaginator).stream()
				.map(e -> userGroupDTOConverter.getDTO(e)).collect(Collectors.toList());
	}

}
