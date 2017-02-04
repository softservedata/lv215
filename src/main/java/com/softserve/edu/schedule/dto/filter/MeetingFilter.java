/* Meeting filter class. */
package com.softserve.edu.schedule.dto.filter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.softserve.edu.schedule.dto.UserGroupDTO;

/**
 * A DTO class to storage and transport filter data.
 *
 * @version 1.0 17 January 2017
 *
 * @author Bohdan Melnyk.
 *
 * @since 1.8
 */
public class MeetingFilter {

    /**
     * Meeting id for filter meetings.
     */
    private Long id;

    /**
     * Meeting description for filter meetings.
     */
    private String description;

    /**
     * Meeting subjectId for filter meetings.
     */
    private Long subjectId;

    /**
     * Meeting ownerId for filter meetings.
     */
    private Long ownerId;

    /**
     * Meeting roomId for filter meetings.
     */
    private Long roomId;

    /**
     * Meeting List<UserGroupDTO> groups for filter meetings.
     */
    private List<UserGroupDTO> groups = new ArrayList<>();

    /**
     * Meeting level for filter meetings.
     */
    private Integer level;

    /**
     * Meeting date for filter meetings.
     */
    private LocalDate date;

    /**
     * Meeting startTime for filter meetings.
     */
    private LocalTime startTime;

    /**
     * Meeting endTime for filter meetings.
     */
    private LocalTime endTime;

    /**
     * Meeting status for filter meetings. The value must be -1, to show empty
     * field in filter menu.
     */
    private int status = -1;

    /**
     * Sort Order for filter meetings.
     */
    private int sortOrder;

    /**
     * Field for Sorting for filter meetings.
     */
    private int fieldForSorting;

    /**
     * @return level
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * Set Level.
     * 
     * @param level
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

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
     * 
     * 
     * /**
     * 
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
