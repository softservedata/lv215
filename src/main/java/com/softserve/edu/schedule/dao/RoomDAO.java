/* RoomDAO 1.0 01/02/2017 */
package com.softserve.edu.schedule.dao;

import java.util.List;

import com.softserve.edu.schedule.dto.filter.RoomFilter;
import com.softserve.edu.schedule.entity.Room;
import com.softserve.edu.schedule.entity.RoomEquipment;

/**
 * An interface to provide DAO operations with Room entity.
 *
 * @version 1.0 02 January 2017
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
public interface RoomDAO extends CrudDAO<Room> {

    /**
     * Delete existed room entity from the database by id.
     *
     * @param id
     *            a room id to delete from database.
     */
    void deleteById(final Long id);

    /**
     * Find a room in the database by name and location id.
     *
     * @param roomName
     *            a room name to find in the database.
     * 
     * @param locationId
     *            a location id to find room.
     * 
     * @return a room with given name and location Id.
     */
    Room getByNameAndLocationId(final String roomName, final Long locationId);

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
    List<Room> getRoomsByCapacity(final int minCapacity, final int maxCapacity);

    /**
     * Find all rooms entities in the database with location and equipment
     * details by given location id.
     * 
     * @param locationId
     *            a location id to find rooms.
     * 
     * @return List of the room objects.
     */
    List<Room> getRoomsByLocationId(final long locationId);

    /**
     * Find all rooms entities in the database with location and equipment
     * details by given equipments list.
     * 
     * @param equipments
     *            an equipments list to find rooms.
     * 
     * @return List of the room objects.
     */
    List<Room> getRoomsWithEquipments(final List<RoomEquipment> equipments);

    /**
     * Find all rooms entities in the database with applied filter
     * 
     * @param roomFilter
     *            a filter to apply.
     * 
     * @return List of the room objects.
     */
    List<Room> getRoomsWithFilter(final RoomFilter roomFilter);

    /**
     * Find all rooms entities in the database with location and equipment
     * details.
     * 
     * @return List of the room objects.
     */
    List<Room> getAllWithDetails();

}
