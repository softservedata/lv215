package com.softserve.edu.schedule.dto;

import java.util.ArrayList;
import java.util.List;

import com.softserve.edu.schedule.entity.UserGroupLevel;
import com.softserve.edu.schedule.service.implementation.validators.Validate;

//@UserGroupValidator(min = 5, max = 20, id = ValidationFields.ID, name = ValidationFields.NAME, description = ValidationFields.DESCRIPTION, level = ValidationFields.LEVEL)
@Validate
public class UserGroupDTO {

	/**
	 * User group id.
	 */
	private Long id;

	/**
	 * User group name.
	 */
	private String name;

	/**
	 * User group description.
	 */
	private String description;

	/**
	 * Level of the group. The higher the value - the more important group.
	 */
	private UserGroupLevel level;

	/**
	 * User group manager.
	 */
	private UserDTO curator;

	/**
	 * List of users in this group.
	 */
	private List<UserDTO> users = new ArrayList<>();

	/**
	 * List of meetings of this group.
	 */
	private List<MeetingDTO> meetings = new ArrayList<>();

	/**
	 * @return the id.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the level
	 */
	public UserGroupLevel getLevel() {
		return level;
	}

	/**
	 * @param level
	 *            the level to set
	 */
	public void setLevel(UserGroupLevel level) {
		this.level = level;
	}

	/**
	 * @return the curator
	 */
	public UserDTO getCurator() {
		return curator;
	}

	/**
	 * @param curator
	 *            the curator to set
	 */
	public void setCurator(UserDTO curator) {
		this.curator = curator;
	}

	/**
	 * @return the users
	 */
	public List<UserDTO> getUsers() {
		if (users == null) {
			return new ArrayList<>();
		}
		return users;
	}

	/**
	 * @param users
	 *            the users to set
	 */
	public void setUsers(List<UserDTO> users) {
		if (users == null) {
			this.users = new ArrayList<>();
		} else {
			this.users = users;
		}
	}

	/**
	 * @return the meetings
	 */
	public List<MeetingDTO> getMeetings() {
		if (meetings == null) {
			return new ArrayList<>();
		}
		return meetings;
	}

	/**
	 * @param meetings
	 *            the meetings to set
	 */
	public void setMeetings(List<MeetingDTO> meetings) {
		this.meetings = meetings;
	}

}
