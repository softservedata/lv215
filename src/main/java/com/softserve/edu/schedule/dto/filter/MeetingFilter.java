package com.softserve.edu.schedule.dto.filter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.softserve.edu.schedule.dto.UserGroupDTO;

public class MeetingFilter {

    private String showFilter;

    private Long id;

    private String description;

    private Long subjectId;

    private Long ownerId;

    private Long roomId;

    private List<UserGroupDTO> groups = new ArrayList<>();

    private Integer maxLevel;

    private Integer minLevel;

    private LocalDate date;

    private LocalTime startTime;

    private LocalTime endTime;

    private int status = -1;

    private int sortOrder;

    private int fieldForSorting;

    /**
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the showFilter
     */
    public String getShowFilter() {
        return showFilter;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return the subjectId
     */
    public Long getSubjectId() {
        return subjectId;
    }

    /**
     * @return the ownerId
     */
    public Long getOwnerId() {
        return ownerId;
    }

    /**
     * @return the roomId
     */
    public Long getRoomId() {
        return roomId;
    }

    /**
     * @return the groups
     */
    public List<UserGroupDTO> getGroups() {
        return groups;
    }

    /**
     * @return the maxLevel
     */
    public Integer getMaxLevel() {
        return maxLevel;
    }

    /**
     * @return the minLevel
     */
    public Integer getMinLevel() {
        return minLevel;
    }

    /**
     * @return the date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * @return the startTime
     */
    public LocalTime getStartTime() {
        return startTime;
    }

    /**
     * @return the endTime
     */
    public LocalTime getEndTime() {
        return endTime;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @return the sortOrder
     */
    public int getSortOrder() {
        return sortOrder;
    }

    /**
     * @return the fieldForSorting
     */
    public int getFieldForSorting() {
        return fieldForSorting;
    }

    /**
     * @param showFilter
     *            the showFilter to set
     */
    public void setShowFilter(String showFilter) {
        this.showFilter = showFilter;
    }

    /**
     * @param description
     *            the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @param subjectId
     *            the subjectId to set
     */
    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    /**
     * @param ownerId
     *            the ownerId to set
     */
    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    /**
     * @param roomId
     *            the roomId to set
     */
    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    /**
     * @param groups
     *            the groups to set
     */
    public void setGroups(List<UserGroupDTO> groups) {
        this.groups = groups;
    }

    /**
     * @param maxLevel
     *            the maxLevel to set
     */
    public void setMaxLevel(Integer maxLevel) {
        this.maxLevel = maxLevel;
    }

    /**
     * @param minLevel
     *            the minLevel to set
     */
    public void setMinLevel(Integer minLevel) {
        this.minLevel = minLevel;
    }

    /**
     * @param date
     *            the date to set
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * @param startTime
     *            the startTime to set
     */
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    /**
     * @param endTime
     *            the endTime to set
     */
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @param sortOrder
     *            the sortOrder to set
     */
    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }

    /**
     * @param fieldForSorting
     *            the fieldForSorting to set
     */
    public void setFieldForSorting(int fieldForSorting) {
        this.fieldForSorting = fieldForSorting;
    }
}
