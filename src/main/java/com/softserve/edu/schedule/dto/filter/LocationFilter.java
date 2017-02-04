/*
 * LocationFilter
 * 1.0
 * 1 Feb 2017
 * Copyright (c) Oleksandr Butyter
 */
package com.softserve.edu.schedule.dto.filter;

/**
 * A DTO class to storage and transport filter data.
 *
 * @version 1.0 1 February 2017
 *
 * @author Oleksandr Butyter
 *
 * @since 1.8
 */
public class LocationFilter {

	/**
	 * Location name for location filter
	 */
	private String name;

	/**
	 * Location address for location filter
	 */
	private String address;

	/**
	 * Field id for sorting location filter result.
	 */
	private int sortByField;

	/**
	 * Order id for sorting location filter result.
	 */
	private int sortOrder;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the sortByField
	 */
	public int getSortByField() {
		return sortByField;
	}

	/**
	 * @param sortByField
	 *            to set
	 */
	public void setSortByField(int sortByField) {
		this.sortByField = sortByField;
	}

	/**
	 * @return sortOrder
	 */
	public int getSortOrder() {
		return sortOrder;
	}

	/**
	 * @param sortOrder
	 *            to set
	 */
	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}

}
