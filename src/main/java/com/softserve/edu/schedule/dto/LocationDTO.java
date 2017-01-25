/* LocationDTO 1.0 01/17/2017 */
package com.softserve.edu.schedule.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * A DTO class to transport location data.
 *
 * @version 1.0 17 January 2017
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
public class LocationDTO {

    /**
     * Id for database.
     */
    private Long id;

    /**
     * Location name.
     */
    private String name;

    /**
     * Location address.
     */
    private String address;

    /**
     * Location coordinates.
     */
    private String coordinates;

    /**
     * Field for storage list of rooms of the location.
     */
    private List<RoomForLocationDTO> rooms = new ArrayList<>();

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
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @return the coordinates
     */
    public String getCoordinates() {
        return coordinates;
    }

    /**
     * @return the rooms
     */
    public List<RoomForLocationDTO> getRooms() {
        return rooms;
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
     * @param address
     *            the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @param coordinates
     *            the coordinates to set
     */
    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * @param rooms
     *            the rooms to set
     */
    public void setRooms(List<RoomForLocationDTO> rooms) {
        if (rooms == null) {
            this.rooms = new ArrayList<>();
        } else {
            this.rooms = rooms;
        }
    }

}
