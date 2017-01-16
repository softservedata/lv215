package com.softserve.edu.schedule.dto.filter;

import java.util.List;

public class RoomFilter {

    private int LocationId;

    private String name;

    private int minCapacity;

    private int maxCapacity;

    private List<Long> equipmentIds;

    /**
     * @return the locationId
     */
    public int getLocationId() {
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
     * @return the equipmentIds
     */
    public List<Long> getEquipmentIds() {
        return equipmentIds;
    }

    /**
     * @param locationId
     *            the locationId to set
     */
    public void setLocationId(int locationId) {
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
    public void setMinCapacity(int minCapacity) {
        this.minCapacity = minCapacity;
    }

    /**
     * @param maxCapacity
     *            the maxCapacity to set
     */
    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    /**
     * @param equipmentIds
     *            the equipmentIds to set
     */
    public void setEquipmentIds(List<Long> equipmentIds) {
        this.equipmentIds = equipmentIds;
    }

}
