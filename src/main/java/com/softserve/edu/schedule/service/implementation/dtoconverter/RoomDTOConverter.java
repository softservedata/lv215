/* RoomDTOConverter 1.0 01/17/2017 */
package com.softserve.edu.schedule.service.implementation.dtoconverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserve.edu.schedule.dao.LocationDAO;
import com.softserve.edu.schedule.dao.RoomEquipmentDAO;
import com.softserve.edu.schedule.dto.RoomDTO;
import com.softserve.edu.schedule.entity.Room;

/**
 * A class to provide conversion operations between RoomDTO and Room entity.
 *
 * @version 1.0 17 January 2017
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
@Service("roomDTOConverter")
public class RoomDTOConverter {

    /**
     * LocationDAO example to provide database operations.
     */
    @Autowired
    private LocationDAO locationDAO;

    /**
     * RoomEquipmentDAO example to provide database operations.
     */
    @Autowired
    private RoomEquipmentDAO roomEquipmentDAO;

    /**
     * LocationDTOConverter example to provide to DTO and from DTO conversion.
     */
    @Autowired
    private LocationDTOConverter locationDTOConverter;

    /**
     * RoomEquipmentDTOConverter example to provide to DTO and from DTO
     * conversion.
     */
    @Autowired
    private RoomEquipmentDTOConverter roomEquipmentDTOConverter;

    /**
     * Convert given RoomDTO object to Room object
     * 
     * @param roomDTO
     *            a RoomDTO object to convert.
     * 
     * @return a Room object or null if given @param roomDTO is null.
     */
    public Room getEntity(RoomDTO roomDTO) {
        if (roomDTO != null) {
            Room room = new Room();
            if (roomDTO.getId() != null) {
                room.setId(roomDTO.getId());
            }
            if (roomDTO.getLocation() != null) {
                room.setLocation(
                        locationDAO.getById(roomDTO.getLocation().getId()));
            }
            if (roomDTO.getName() != null) {
                room.setName(roomDTO.getName());
            }
            if (roomDTO.getCapacity() != null) {
                room.setCapacity(Integer.parseInt(roomDTO.getCapacity()));
            }
            if (roomDTO.getEquipments() != null) {
                roomDTO.getEquipments().forEach(e -> room.getEquipments()
                        .add(roomEquipmentDAO.getById(e.getId())));
            }
            return room;
        }
        return null;
    }

    /**
     * Convert given Room object to RoomDTO object.
     * 
     * @param room
     *            a Room object to convert.
     * 
     * @return a RoomDTO object or null if given @param room is null.
     */
    public RoomDTO getDTO(Room room) {
        if (room != null) {
            RoomDTO roomDTO = new RoomDTO();
            if (room.getId() != null) {
                roomDTO.setId(room.getId());
            }
            if (room.getName() != null) {
                roomDTO.setName(room.getName());
            }
            if (room.getCapacity() != null) {
                roomDTO.setCapacity(String.valueOf(room.getCapacity()));
            }
            if (room.getLocation() != null) {
                roomDTO.setLocation(
                        locationDTOConverter.getDTO(room.getLocation()));
            }
            if (room.getEquipments() != null) {
                room.getEquipments().forEach(e -> roomDTO.getEquipments()
                        .add(roomEquipmentDTOConverter.getDTO(e)));
            }
            return roomDTO;
        }
        return null;
    }

}
