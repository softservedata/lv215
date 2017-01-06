/* RoomService 1.0 01/02/2017 */
package com.softserve.edu.schedule.service;

import java.util.List;

import com.softserve.edu.schedule.entity.Location;
import com.softserve.edu.schedule.entity.Room;
import com.softserve.edu.schedule.entity.RoomEquipment;

/**
 * An interface to provide service operations with Room entity.
 *
 * @version 1.0 02 January 2017
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
public interface RoomService {

    /**
     * Save new room entity into the database.
     *
     * @param room
     *            a new room to storage in database.
     */
    void add(final Room room);

    /**
     * Update existed room entity in the database.
     *
     * @param room
     *            a room to update in database.
     */
    void update(final Room room);

    /**
     * Find room entity in the database by id.
     *
     * @param id
     *            a room id to find in the database.
     * @return an room object if room with this id exists in the database or
     *         Null if room not found
     */
    Room findById(final Long id);

    /**
     * Find room entity in the database by name.
     *
     * @param roomName
     *            a room name to find in the database.
     * @return an room object if room with this name exists in the database or
     *         Null if room not found
     */
    Room findByName(final String roomName);

    /**
     * Delete existed room entity from the database.
     *
     * @param room
     *            a room object to delete from database.
     */
    void delete(final Room room);

    /**
     * Delete existed room entity from the database by id.
     *
     * @param id
     *            a room id to delete from database.
     */
    void deleteById(final Long id);

    /**
     * Find all rooms entities in the database.
     * 
     * @return List of the room objects.
     */
    List<Room> findAll();

    /**
     * Find all rooms entities in the database with location and equipment
     * details.
     * 
     * @return List of the room objects.
     */
    List<Room> findAllWithDetails();

    /**
     * Find all rooms entities in the database with location and equipment
     * details which has capacity in given interval.
     * 
     * @param minCapacity
     *            a minimum capacity.
     * @param maxCapacity
     *            a minimum capacity.
     * 
     * @return List of the room objects.
     */
    List<Room> findRoomsByCapacity(final int minCapacity,
            final int maxCapacity);

    /**
     * Find all rooms entities in the database with location and equipment
     * details by given location.
     * 
     * @param location
     *            a location to find rooms.
     * 
     * @return List of the room objects.
     */
    List<Room> findRoomsByLocation(final Location location);

    /**
     * Find all rooms entities in the database with location and equipment
     * details which has given list of equipments.
     * 
     * @param equipments
     *            a list of equipments to find rooms.
     * 
     * @return List of the room objects.
     */
    List<Room> findRoomsWithEquipments(final List<RoomEquipment> equipments);
}
