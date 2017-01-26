package com.softserve.edu.schedule.dto.filter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.softserve.edu.schedule.dao.Order;
import com.softserve.edu.schedule.dto.UserGroupDTO;
import com.softserve.edu.schedule.service.implementation.specification.MeetingSortField;

public class MeetingFilter {

    private String showFilter;

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

    private String status;

    private Order sortOrder;

    private MeetingSortField fieldForSorting;

    /**
     * @return the date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * @param date
     *            the date to set
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * @return the startTime
     */
    public LocalTime getStartTime() {
        return startTime;
    }

    /**
     * @param startTime
     *            the startTime to set
     */
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the endTime
     */
    public LocalTime getEndTime() {
        return endTime;
    }

    /**
     * @param endTime
     *            the endTime to set
     */
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    /**
     * @return the fieldForSorting
     */
    public MeetingSortField getFieldForSorting() {
        return fieldForSorting;
    }

    /**
     * @return the maxLevel
     */
    public Integer getMaxLevel() {
        return maxLevel;
    }

    /**
     * @param maxLevel
     *            the maxLevel to set
     */
    public void setMaxLevel(Integer maxLevel) {
        this.maxLevel = maxLevel;
    }

    /**
     * @return the minLevel
     */
    public Integer getMinLevel() {
        return minLevel;
    }

    /**
     * @param minLevel
     *            the minLevel to set
     */
    public void setMinLevel(Integer minLevel) {
        this.minLevel = minLevel;
    }

    /**
     * @param fieldForSorting
     *            the fieldForSorting to set
     */
    public void setFieldForSorting(MeetingSortField fieldForSorting) {
        this.fieldForSorting = fieldForSorting;
    }

    /**
     * @return the showFilter
     */
    public String getShowFilter() {
        return showFilter;
    }

    /**
     * @param showFilter
     *            the showFilter to set
     */
    public void setShowFilter(String showFilter) {
        this.showFilter = showFilter;
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
     * @return the subjectId
     */
    public Long getSubjectId() {
        return subjectId;
    }

    /**
     * @param subjectId
     *            the subjectId to set
     */
    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    /**
     * @return the ownerId
     */
    public Long getOwnerId() {
        return ownerId;
    }

    /**
     * @param ownerId
     *            the ownerId to set
     */
    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    /**
     * @return the roomId
     */
    public Long getRoomId() {
        return roomId;
    }

    /**
     * @param roomId
     *            the roomId to set
     */
    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    /**
     * @return the userGroupDTO
     */
    public List<UserGroupDTO> getGroups() {
        return groups;
    }

    /**
     * @param userGroupDTO
     *            the userGroupDTO to set
     */
    public void setGroups(List<UserGroupDTO> userGroupDTO) {
        this.groups = userGroupDTO;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the sortOrder
     */
    public Order getSortOrder() {
        return sortOrder;
    }

    /**
     * @param sortOrder
     *            the sortOrder to set
     */
    public void setSortOrder(Order sortOrder) {
        this.sortOrder = sortOrder;
    }

}
