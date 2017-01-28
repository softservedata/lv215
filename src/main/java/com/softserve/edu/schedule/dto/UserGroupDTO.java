package com.softserve.edu.schedule.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class UserGroupDTO {

	private Long id;

	/**
	 * User group name.
	 */
	@Size(min = 5, max = 20, message = "Length should be between 5 and 20")
	private String name;

	/**
	 * User group description.
	 */
	@NotEmpty(message = "Please enter group description.")
	private String description;

	/**
	 * Level of the group. The higher the value - the more important group.
	 */
	@NotEmpty(message = "Please enter level.")
	private Integer level;

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
	 * @return the id
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
	public Integer getLevel() {
		return level;
	}

	/**
	 * @param level
	 *            the level to set
	 */
	public void setLevel(Integer level) {
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
