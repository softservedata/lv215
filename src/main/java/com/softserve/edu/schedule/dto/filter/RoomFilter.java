/* RoomFilter 1.0 01/17/2017 */
package com.softserve.edu.schedule.dto.filter;

import java.util.ArrayList;
import java.util.List;

import com.softserve.edu.schedule.dto.RoomEquipmentDTO;

/**
 * A DTO class to storage and transport filter data.
 *
 * @version 1.0 17 January 2017
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
public class RoomFilter {

    /**
     * Show filter parameter. If true filter will be shown.
     */
    private String showFilter;

    /**
     * Location id for filter rooms.
     */
    private int locationId;

    /**
     * Room name for filter rooms.
     */
    private String name;

    /**
     * Room minimal capacity for filter rooms.
     */
    private int minCapacity;

    /**
     * Room maximal capacity for filter rooms.
     */
    private int maxCapacity;

    /**
     * Field id for sorting filter result.
     */
    private int sortByField;

    /**
     * Order id for sorting filter result.
     */
    private int sortOrder;

    /**
     * List of RoomEquipmentDTO for filter rooms.
     */
    private List<RoomEquipmentDTO> equipments = new ArrayList<>();

    /**
     * @return the showFilter
     */
    public String getShowFilter() {
        return showFilter;
    }

    /**
     * @return the locationId
     */
    public int getLocationId() {
        return locationId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the minCapacity
     */
    public int getMinCapacity() {
        return minCapacity;
    }

    /**
     * @return the maxCapacity
     */
    public int getMaxCapacity() {
        return maxCapacity;
    }

    /**
     * @return the sortByField
     */
    public int getSortByField() {
        return sortByField;
    }

    /**
     * @return the sortOrder
     */
    public int getSortOrder() {
        return sortOrder;
    }

    /**
     * @return the equipments
     */
    public List<RoomEquipmentDTO> getEquipments() {
        return equipments;
    }

    /**
     * @param showFilter
     *            the showFilter to set
     */
    public void setShowFilter(final String showFilter) {
        this.showFilter = showFilter;
    }

    /**
     * @param locationId
     *            the locationId to set
     */
    public void setLocationId(final int locationId) {
        this.locationId = locationId;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @param minCapacity
     *            the minCapacity to set
     */
    public void setMinCapacity(final int minCapacity) {
        this.minCapacity = minCapacity;
    }

    /**
     * @param maxCapacity
     *            the maxCapacity to set
     */
    public void setMaxCapacity(final int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    /**
     * @param sortByField
     *            the sortByField to set
     */
    public void setSortByField(final int sortByField) {
        this.sortByField = sortByField;
    }

    /**
     * @param sortOrder
     *            the sortOrder to set
     */
    public void setSortOrder(final int sortOrder) {
        this.sortOrder = sortOrder;
    }

    /**
     * @param equipments
     *            the equipments to set
     */
    public void setEquipments(final List<RoomEquipmentDTO> equipments) {
        this.equipments = equipments;
    }

}
