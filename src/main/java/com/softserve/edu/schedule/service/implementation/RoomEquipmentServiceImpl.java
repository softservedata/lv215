/* RoomEquipmentServiceImpl 1.0 01/02/2017 */
package com.softserve.edu.schedule.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.edu.schedule.aspects.PerfomanceLoggable;
import com.softserve.edu.schedule.dao.RoomEquipmentDAO;
import com.softserve.edu.schedule.dto.RoomEquipmentDTO;
import com.softserve.edu.schedule.dto.filter.Paginator;
import com.softserve.edu.schedule.dto.filter.RoomEquipmentFilter;
import com.softserve.edu.schedule.service.RoomEquipmentService;
import com.softserve.edu.schedule.service.implementation.dtoconverter.RoomEquipmentDTOConverter;

/**
 * A class to provide service operations with RoomEquipment entity.
 *
 * @version 1.0 02 January 2017
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
@Transactional
@Service
@PerfomanceLoggable
public class RoomEquipmentServiceImpl implements RoomEquipmentService {

    /**
     * RoomEquipmentDAO example to provide database operations.
     */
    @Autowired
    private RoomEquipmentDAO roomEquipmentDAO;

    /**
     * RoomEquipmentDTOConverter example to provide to DTO and from DTO
     * conversion.
     */
    @Autowired
    private RoomEquipmentDTOConverter roomEquipmentDTOConverter;

    /**
     * Find all room equipments entities in the database.
     *
     * @return List of the room equipments DTO objects.
     */
    @Override
    @Transactional(readOnly = true)
    public List<RoomEquipmentDTO> getAll() {
        return roomEquipmentDAO.getAll().stream()
                .map(e -> roomEquipmentDTOConverter.getDTO(e))
                .collect(Collectors.toList());

    }

    /**
     * Find room equipment entity in the database by id.
     *
     * @param id
     *            a room equipment id to find in the database.
     * @return an room equipment DTO object if room equipment with this id
     *         exists in the database or Null if room equipment not found
     */
    @Override
    @Transactional(readOnly = true)
    public RoomEquipmentDTO getById(final Long id) {
        return roomEquipmentDTOConverter.getDTO(roomEquipmentDAO.getById(id));
    }

    /**
     * Save new room equipment entity into the database.
     *
     * @param equipmentDTO
     *            a new room equipment DTO to storage in database.
     */
    @Override
    public void create(final RoomEquipmentDTO equipmentDTO) {
        roomEquipmentDAO
                .create(roomEquipmentDTOConverter.getEntity(equipmentDTO));
    }

    /**
     * Update existed room equipment entity in the database.
     *
     * @param equipmentDTO
     *            a room equipment DTO to update in database.
     */
    @Override
    public void update(final RoomEquipmentDTO equipmentDTO) {
        roomEquipmentDAO
                .update(roomEquipmentDTOConverter.getEntity(equipmentDTO));

    }

    /**
     * Delete given room equipment entity from the database.
     *
     * @param equipmentDTO
     *            a room equipment DTO to delete from database.
     */
    @Override
    public void delete(final RoomEquipmentDTO equipmentDTO) {
        roomEquipmentDAO
                .delete(roomEquipmentDTOConverter.getEntity(equipmentDTO));
    }

    /**
     * Delete existed room equipment entity from the database by id.
     *
     * @param id
     *            a room equipment id to delete from database.
     */
    @Override
    public void deleteById(final Long id) {
        roomEquipmentDAO.deleteById(id);
    }

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
    @Override
    public List<RoomEquipmentDTO> getRoomEquipmentsPageWithFilter(
            final RoomEquipmentFilter filter, final Paginator paginator) {
        return roomEquipmentDAO
                .getRoomEquipmentsPageWithFilter(filter, paginator).stream()
                .map(e -> roomEquipmentDTOConverter.getDTO(e))
                .collect(Collectors.toList());
    }

    /**
     * Find room equipments DTO in the database by name.
     *
     * @param roomEquipmentName
     *            a room equipment name to find in the database.
     *
     * @return list of room equipments DTO with given name.
     */
    @Override
    public List<RoomEquipmentDTO> getByName(final String roomEquipmentName) {
        return roomEquipmentDAO.getRoomEquipmentsByName(roomEquipmentName)
                .stream().map(e -> roomEquipmentDTOConverter.getDTO(e))
                .collect(Collectors.toList());
    }

}
