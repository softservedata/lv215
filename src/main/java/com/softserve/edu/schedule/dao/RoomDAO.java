/* RoomDAO 1.0 01/02/2017 */
package com.softserve.edu.schedule.dao;

import java.util.List;

import com.softserve.edu.schedule.entity.Room;

/**
 * An interface to provide DAO operations with Room entity.
 *
 * @version 1.0 02 January 2017
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
public interface RoomDAO {

    /**
     * Save new room entity into the database.
     *
     * @param room
     *            a new room to storage in database.
     */
    void add(Room room);

    /**
     * Update existed room entity in the database.
     *
     * @param room
     *            a room to update in database.
     */
    void update(Room room);

    /**
     * Find room entity in the database by Id.
     *
     * @param id
     *            a room id to find in the database.
     * @return an room object if room with this id exists in the database or
     *         Null if room not found
     */
    Room findById(Long id);

    /**
     * Delete existed room entity from the database.
     *
     * @param room
     *            a room object to delete from database.
     */
    void delete(Room room);

    /**
     * Delete existed room entity from the database by id.
     *
     * @param id
     *            a room id to delete from database.
     */
    void deleteById(Long id);

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

}
