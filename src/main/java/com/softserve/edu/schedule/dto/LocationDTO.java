package com.softserve.edu.schedule.dto;

import java.util.ArrayList;
import java.util.List;

public class LocationDTO {

    private Long id;

    private String name;

    private String address;

    private String coordinates;

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

	public List<RoomForLocationDTO> getRooms() {
		return rooms;
	}

	public void setRooms(List<RoomForLocationDTO> rooms) {
		this.rooms = rooms;
	}
    
}
