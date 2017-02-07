/* Location 1.0 12/25/2016 */
package com.softserve.edu.schedule.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.softserve.edu.schedule.entitylisteners.LocationEntityListner;

/**
 * An entity class for locations.
 *
 * @version 1.0 25 December 2016
 *
 * @author Oleksandr Butyter
 *
 * @since 1.8
 */
@Entity
@EntityListeners(LocationEntityListner.class)
public class Location {

    /**
     * Id for database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
     * Location coordinates in String format.
     */
    private String coordinates;

    /**
     * Field for storage rooms of location.
     */
    @OneToMany(mappedBy = "location")
    private List<Room> rooms = new ArrayList<>();

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
    public List<Room> getRooms() {
        return rooms;
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
     * @param address
     *            the address to set
     */
    public void setAddress(final String address) {
        this.address = address;
    }

    /**
     * @param coordinates
     *            the coordinates to set
     */
    public void setCoordinates(final String coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * @param rooms
     *            the rooms to set
     */
    public void setRooms(final List<Room> rooms) {
        this.rooms = rooms;
    }
}
