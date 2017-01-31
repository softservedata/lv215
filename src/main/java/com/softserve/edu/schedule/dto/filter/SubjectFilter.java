/*
 * SubjectFilter.java
 * 1.0
 * 30 Jan 2017
 * Copyright (c) Ped'ko Volodymyr
 */
package com.softserve.edu.schedule.dto.filter;

/**
 * A DTO class to storage and transport filter data.
 *
 * @version 1.0 30 January 2017
 *
 * @author Ped'ko Volodymyr
 *
 * @since 1.8
 */
public class SubjectFilter {

    /**
     * Subject name for filter rooms.
     */
    private String name;

    /**
     * Subject description for filter rooms.
     */
    private String description;

    /**
     * User id for filter rooms.
     */
    private Long userId;

    /**
     * Field id for sorting filter result.
     */
    private int sortByField;

    /**
     * Order id for sorting filter result.
     */
    private int sortOrder;

    /**
     * @return name
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
     * @return description
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
     * @return the userId
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId
     *            the userId to set
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * @return the sortByField
     */
    public int getSortByField() {
        return sortByField;
    }

    /**
     * @param sortByField
     *            the sortByField to set
     */
    public void setSortByField(int sortByField) {
        this.sortByField = sortByField;
    }

    /**
     * @return the sortOrder
     */
    public int getSortOrder() {
        return sortOrder;
    }

    /**
     * @param sortOrder
     *            the sortOrder to set
     */
    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }

}
