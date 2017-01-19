/* UserGroup 1.0 12/25/2016 */
package com.softserve.edu.schedule.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 * An entity class for user groups.
 *
 * @version 1.0 25 December 2016
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
@Entity
public class UserGroup {

	/**
	 * Id for database.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * User group name.
	 */
	private String name;

	/**
	 * User group description.
	 */
	@Lob
	private String description;

	/**
	 * Level of the group. The higher the value - the more important group.
	 */
	private Integer level;

	/**
	 * User group manager.
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	private User curator;

	/**
	 * List of users in this group.
	 */
	@ManyToMany(fetch = FetchType.LAZY)
	private List<User> users = new ArrayList<>();

	/**
	 * List of meetings of this group.
	 */
	@ManyToMany(fetch = FetchType.LAZY)
	private List<Meeting> meetings = new ArrayList<>();

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the level
	 */
	public Integer getLevel() {
		return level;
	}

	/**
	 * @return the curator
	 */
	public User getCurator() {
		return curator;
	}

	/**
	 * @return the users
	 */
	public List<User> getUsers() {
		return users;
	}

	/**
	 * @return the meetings
	 */
	public List<Meeting> getMeetings() {
		return meetings;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @param level
	 *            the level to set
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}

	/**
	 * @param curator
	 *            the curator to set
	 */
	public void setCurator(User curator) {
		this.curator = curator;
	}

	/**
	 * @param users
	 *            the users to set
	 */
	public void setUsers(List<User> users) {
		this.users = users;
	}

	/**
	 * @param meetings
	 *            the meetings to set
	 */
	public void setMeetings(List<Meeting> meetings) {
		this.meetings = meetings;
	}

}
