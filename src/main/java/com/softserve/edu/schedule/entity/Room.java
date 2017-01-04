/* Room 1.0 12/25/2016 */
package com.softserve.edu.schedule.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * An entity class for rooms.
 *
 * @version 1.0 25 December 2016
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
@Entity
public class Room {

    /**
     * Id for database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Room name.
     */
    private String name;

    /**
     * Room capacity. How many people can accommodate the room.
     */
    private int capacity;

    /**
     * Field for storage location of the room.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    private Location location;

    /**
     * Field for storage list of equipments of the room.
     */
    @ManyToMany(fetch = FetchType.LAZY)
    private List<RoomEquipment> equipments;

    /**
     * Field for storage list of meetings in the room.
     */
    @OneToMany(mappedBy = "room")
    private List<Meeting> meetings = new ArrayList<>();

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
    public int getCapacity() {
        return capacity;
    }

    /**
     * @return the location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * @return the equipments
     */
    public List<RoomEquipment> getEquipments() {
        return equipments;
    }

    /**
     * @return the meetings
     */
    public List<Meeting> getMeetings() {
        return meetings;
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
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * @param location
     *            the location to set
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * @param equipments
     *            the equipments to set
     */
    public void setEquipments(List<RoomEquipment> equipments) {
        this.equipments = equipments;
    }

    /**
     * @param meetings
     *            the meetings to set
     */
    public void setMeetings(List<Meeting> meetings) {
        this.meetings = meetings;
    }

}
