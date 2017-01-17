package com.softserve.edu.schedule.dto;

import java.util.ArrayList;
import java.util.List;

public class RoomDTO {

    private Long id;

    private String name;

    private String capacity;

    private LocationDTO location;

    private List<RoomEquipmentDTO> equipments = new ArrayList<>();

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the capacity
     */
    public String getCapacity() {
        return capacity;
    }

    /**
     * @return the location
     */
    public LocationDTO getLocation() {
        return location;
    }

    /**
     * @return the equipments
     */
    public List<RoomEquipmentDTO> getEquipments() {
        return equipments;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param capacity
     *            the capacity to set
     */
    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    /**
     * @param location
     *            the location to set
     */
    public void setLocation(LocationDTO location) {
        this.location = location;
    }

    /**
     * @param equipments
     *            the equipments to set
     */
    public void setEquipments(List<RoomEquipmentDTO> equipments) {
        this.equipments = equipments;
    }
}
