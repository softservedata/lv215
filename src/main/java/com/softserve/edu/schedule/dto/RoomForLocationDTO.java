/*
 * RoomForLocationDTO
 * 1.0
 * 18 Jan 2017
 * Copyright (c) Oleksandr Butyter
 */
package com.softserve.edu.schedule.dto;

/**
 * A DTO class to transport room data.
 *
 * @version 1.0 18 January 2017
 *
 * @author Oleksandr Butyter
 */
public class RoomForLocationDTO {

	private Long id;

	/**
	 * Room name.
	 */
	private String name;

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
	 * @return the room name
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

}
