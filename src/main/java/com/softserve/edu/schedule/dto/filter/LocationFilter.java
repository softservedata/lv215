package com.softserve.edu.schedule.dto.filter;

public class LocationFilter {

	private String name;
	
	private String address;
	
	private int sortByField;
	
	private int sortOrder;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getSortByField() {
		return sortByField;
	}

	public void setSortByField(int sortByField) {
		this.sortByField = sortByField;
	}

	public int getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}
	
}
