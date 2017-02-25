/* RoomEquipmentDAO 1.0 01/02/2017 */
package com.softserve.edu.schedule.dao;

import java.util.List;

import com.softserve.edu.schedule.dto.filter.Paginator;
import com.softserve.edu.schedule.dto.filter.RoomEquipmentFilter;
import com.softserve.edu.schedule.entity.RoomEquipment;

/**
 * An interface to provide DAO operations with RoomEquipment entity.
 *
 * @version 1.0 02 January 2017
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
public interface RoomEquipmentDAO extends CrudDAO<RoomEquipment> {

    /**
     * Delete room equipment from the database by given id.
     *
     * @param id
     *            a room equipment id to delete from the database.
     */
    void deleteById(Long id);

    /**
     * Find all room equipments entities in the database with applied filter.
     *
     * @param filter
     *            a filter to apply.
     *
     * @param paginator
     *            paginator to provide paging information
     *
     * @return List of the room equipment objects.
     */
    List<RoomEquipment> getRoomEquipmentsPageWithFilter(
            RoomEquipmentFilter filter, Paginator paginator);

    /**
     * Count room equipments entities in the database with specified filter.
     *
     * @param filter
     *            a filter to apply.
     *
     * @return Count of the room equipment entities in the database with
     *         specified predicate.
     */
    Long getCountOfRoomEquipmentsWithFilter(RoomEquipmentFilter filter);

    /**
     * Find room equipments entities in the database by given name.
     *
     * @param roomEquipmentName
     *            a name to find in database.
     *
     * @return List of the room equipment objects.
     */
    List<RoomEquipment> getRoomEquipmentsByName(String roomEquipmentName);
}
