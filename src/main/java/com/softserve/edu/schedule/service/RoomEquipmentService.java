/* RoomEquipmentService 1.0 01/02/2017 */
package com.softserve.edu.schedule.service;

import java.util.List;

import com.softserve.edu.schedule.dto.RoomEquipmentDTO;
import com.softserve.edu.schedule.dto.filter.Paginator;
import com.softserve.edu.schedule.dto.filter.RoomEquipmentFilter;

/**
 * An interface to provide service operations with RoomEquipment entity.
 *
 * @version 1.0 02 January 2017
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
public interface RoomEquipmentService {

    /**
     * Find all room equipments entities in the database.
     *
     * @return List of the room equipments DTO objects.
     */
    List<RoomEquipmentDTO> getAll();

    /**
     * Find room equipment entity in the database by id.
     *
     * @param id
     *            a room equipment id to find in the database.
     * @return an room equipment DTO object if room equipment with this id
     *         exists in the database or Null if room equipment not found
     */
    RoomEquipmentDTO getById(Long id);

    /**
     * Save new room equipment entity into the database.
     *
     * @param equipmentDTO
     *            a new room equipment DTO to storage in database.
     */
    void create(RoomEquipmentDTO equipmentDTO);

    /**
     * Update existed room equipment entity in the database.
     *
     * @param equipmentDTO
     *            a room equipment DTO to update in database.
     */
    void update(RoomEquipmentDTO equipmentDTO);

    /**
     * Delete given room equipment entity from the database.
     *
     * @param equipmentDTO
     *            a room equipment DTO to delete from database.
     */
    void delete(RoomEquipmentDTO equipmentDTO);

    /**
     * Delete existed room equipment entity from the database by id.
     *
     * @param id
     *            a room equipment id to delete from database.
     */
    void deleteById(Long id);

    /**
     * Find all room equipments entities in the database with applied filter.
     *
     * @param filter
     *            a filter to apply.
     *
     * @param paginator
     *            paginator object.
     *
     * @return List of the room equipment DTO objects.
     */
    List<RoomEquipmentDTO> getRoomEquipmentsPageWithFilter(
            RoomEquipmentFilter filter, Paginator paginator);

    /**
     * Find room equipments DTO in the database by name.
     *
     * @param roomEquipmentName
     *            a room equipment name to find in the database.
     *
     * @return list of room equipments DTO with given name.
     */
    List<RoomEquipmentDTO> getByName(String roomEquipmentName);
}
