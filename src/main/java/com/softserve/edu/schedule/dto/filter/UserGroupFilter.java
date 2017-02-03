package com.softserve.edu.schedule.dto.filter;

/**
 * A DTO class to store and transport filter data.
 *
 * @version 1.0 01 February 2017
 *
 * @author Andrii Zhydenko
 */
public class UserGroupFilter {

	/**
	 * Field name for group name
	 */
	private String name;

	/**
	 * Field curator for group name
	 */
	private Long curatorId;

	/**
	 * Field id to sort filter result.
	 */
	private int fieldForSorting;

	/**
	 * Order id to sort filter result.
	 */
	private int sortOrder;

	/**
	 * Level id for filter groups.
	 */
	private Long levelId;

	/**
	 * Name getter
	 * 
	 * @return name of a group
	 */
	public String getName() {
		return name;
	}

	/**
	 * Name setter
	 * 
	 * @param name
	 *            of group to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Curator getter
	 * 
	 * @return curator of a group
	 */
	public Long getCuratorId() {
		return curatorId;
	}

	/**
	 * Curator setter
	 */
	public void setCuratorId(Long curator) {
		this.curatorId = curator;
	}

	/**
	 * SortByField getter
	 * 
	 * @return sortByField value
	 */
	public int getFieldForSorting() {
		return fieldForSorting;
	}

	/**
	 * SortByField setter
	 */
	public void setFieldForSorting(int fieldForSorting) {
		this.fieldForSorting = fieldForSorting;
	}

	/**
	 * SortOrder getter
	 * 
	 * @return SortOrder value
	 */
	public int getSortOrder() {
		return sortOrder;
	}

	/**
	 * SortOrder setter
	 */
	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}

	/**
	 * LevelId setter
	 * 
	 * @return LevelId value
	 */
	public Long getLevelId() {
		return levelId;
	}

	/**
	 * LevelId setter
	 */
	public void setLevelId(Long levelId) {
		this.levelId = levelId;
	}

}
