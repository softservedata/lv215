/* RoomService 1.0 01/02/2017 */
package com.softserve.edu.schedule.service;

import java.util.List;

import com.softserve.edu.schedule.dto.LocationDTO;
import com.softserve.edu.schedule.dto.RoomDTO;
import com.softserve.edu.schedule.dto.RoomEquipmentDTO;
import com.softserve.edu.schedule.dto.filter.RoomFilter;
import com.softserve.edu.schedule.entity.Room;

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
     *            a new room DTO to storage in database.
     */
    void create(final RoomDTO roomDTO);

    /**
     * Update existed room entity in the database.
     *
     * @param room
     *            a room DTO to update in database.
     */
    void update(final RoomDTO roomDTO);

    /**
     * Find room entity in the database by id.
     *
     * @param id
     *            a room id to find in the database.
     * @return an room object if room with this id exists in the database or
     *         Null if room not found
     */
    RoomDTO getById(final Long id);

    /**
     * Find a room DTO in the database by name and location.
     *
     * @param roomName
     *            a room name to find in the database.
     *            
     * @param location
     *            a location to find room.
     *                    
     * @return a room DTO with given name and location.
     */
    RoomDTO getByNameAndLocation(final String roomName, final LocationDTO location);

    /**
     * Delete existed room entity from the database.
     *
     * @param room
     *            a room DTO object to delete from database.
     */
    void delete(final RoomDTO roomDTO);

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
     * @return List of the room DTO objects.
     */
    List<RoomDTO> getAll();

    /**
     * Find all rooms entities in the database with location and equipment
     * details.
     * 
     * @return List of the room DTO objects.
     */
    List<RoomDTO> getAllWithDetails();

    /**
     * Find all rooms in the database with location and equipment details which
     * has capacity in given interval.
     * 
     * @param minCapacity
     *            a minimum capacity.
     * @param maxCapacity
     *            a minimum capacity.
     * 
     * @return List of the room DTO objects.
     */
    List<RoomDTO> getRoomsByCapacity(final int minCapacity,
            final int maxCapacity);

    /**
     * Find all rooms in the database with location and equipment details by
     * given location.
     * 
     * @param location
     *            a location to find rooms.
     * 
     * @return List of the room DTO objects.
     */
    List<RoomDTO> getRoomsByLocation(final LocationDTO location);

    /**
     * Find all rooms entities in the database with location and equipment
     * details which has given list of equipments.
     * 
     * @param equipments
     *            a list of equipments to find rooms.
     * 
     * @return List of the room DTO objects.
     */
    List<RoomDTO> getRoomsWithEquipments(
            final List<RoomEquipmentDTO> equipments);

    /**
     * Find all rooms entities in the database with applied filter
     * 
     * @param roomFilter
     *            a filter to apply.
     * 
     * @return List of the room DTO objects.
     */
    List<RoomDTO> getRoomsWithFilter(final RoomFilter roomFilter);
    
    public Room getEntityById(final Long id);

}
