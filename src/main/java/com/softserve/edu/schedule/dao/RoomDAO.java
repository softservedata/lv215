/* RoomDAO 1.0 01/02/2017 */
package com.softserve.edu.schedule.dao;

import java.util.List;

import com.softserve.edu.schedule.dto.filter.Paginator;
import com.softserve.edu.schedule.dto.filter.RoomFilter;
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
public interface RoomDAO extends CrudDAO<Room> {

    /**
     * Find rooms in the database by name and location id.
     *
     * @param roomName
     *            a room name to find in the database.
     *
     * @param locationId
     *            a location id to find room.
     *
     * @return List of rooms with given name and location Id.
     */
    List<Room> getByNameAndLocationId(String roomName, Long locationId);

    /**
     * Find all rooms entities in the database with applied filter.
     *
     * @param roomFilter
     *            a filter to apply.
     *
     * @param roomPaginator
     *            paginator to provide paging information
     *
     * @return List of the room objects.
     */
    List<Room> getRoomsPageWithFilter(RoomFilter roomFilter,
            Paginator roomPaginator);

    /**
     * Find all rooms entities in the database with location and equipment
     * details.
     *
     * @return List of the room objects.
     */
    List<Room> getAllWithDetails();

    /**
     * Count rooms entities in the database with specified filter.
     *
     * @param roomFilter
     *            a filter to apply.
     *
     * @return Count of the room entities in the database with specified
     *         predicate.
     */
    Long getCountOfRoomsWithFilter(RoomFilter roomFilter);

}
