package com.softserve.edu.schedule.entity;

/**
 * An enumeration of usergroup level.
 *
 * @version 1.0 01 February 2017
 *
 * @author Andrii Zhydenko
 *
 */
public enum UserGroupLevel {
	STUDENTS("stCode"), TEACHERS("tchCode"), UNKNOWN("unkCode");

	private String code;

	private UserGroupLevel(final String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
