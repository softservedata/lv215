/* RoomDTO 1.0 01/17/2017 */
package com.softserve.edu.schedule.dto;

import java.util.ArrayList;
import java.util.List;

import com.softserve.edu.schedule.service.implementation.validators.Validate;

/**
 * A DTO class to transport room data.
 *
 * @version 1.0 17 January 2017
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
@Validate
public class RoomDTO {

    /**
     * Id for database.
     */
    private Long id;

    /**
     * Room name.
     */
    private String name;

    /**
     * Room capacity. How many people can accommodate the room.
     */
    private Integer capacity;

    /**
     * Field for storage location of the room.
     */
    private LocationDTO location;

    /**
     * Field for storage list of equipments of the room.
     */
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
    public Integer getCapacity() {
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
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @param capacity
     *            the capacity to set
     */
    public void setCapacity(final Integer capacity) {
        this.capacity = capacity;
    }

    /**
     * @param location
     *            the location to set
     */
    public void setLocation(final LocationDTO location) {
        this.location = location;
    }

    /**
     * @param equipments
     *            the equipments to set
     */
    public void setEquipments(final List<RoomEquipmentDTO> equipments) {
        if (equipments == null) {
            this.equipments = new ArrayList<>();
        } else {
            this.equipments = equipments;
        }
    }
}
