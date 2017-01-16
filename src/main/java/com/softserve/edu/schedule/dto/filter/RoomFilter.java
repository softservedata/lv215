package com.softserve.edu.schedule.dto.filter;

import java.util.List;

public class RoomFilter {

    private Integer LocationId;

    private String name;

    private Integer minCapacity;

    private Integer maxCapacity;

    private List<Integer> equipmentIds;

    /**
     * @return the locationId
     */
    public Integer getLocationId() {
        return LocationId;
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
    public Integer getMinCapacity() {
        return minCapacity;
    }

    /**
     * @return the maxCapacity
     */
    public Integer getMaxCapacity() {
        return maxCapacity;
    }

    /**
     * @return the equipmentIds
     */
    public List<Integer> getEquipmentIds() {
        return equipmentIds;
    }

    /**
     * @param locationId
     *            the locationId to set
     */
    public void setLocationId(Integer locationId) {
        LocationId = locationId;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param minCapacity
     *            the minCapacity to set
     */
    public void setMinCapacity(Integer minCapacity) {
        this.minCapacity = minCapacity;
    }

    /**
     * @param maxCapacity
     *            the maxCapacity to set
     */
    public void setMaxCapacity(Integer maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    /**
     * @param equipmentIds
     *            the equipmentIds to set
     */
    public void setEquipmentIds(List<Integer> equipmentIds) {
        this.equipmentIds = equipmentIds;
    }

}
