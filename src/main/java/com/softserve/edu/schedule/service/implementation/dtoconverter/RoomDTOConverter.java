/* RoomDTOConverter 1.0 01/17/2017 */
package com.softserve.edu.schedule.service.implementation.dtoconverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserve.edu.schedule.dao.LocationDAO;
import com.softserve.edu.schedule.dao.RoomEquipmentDAO;
import com.softserve.edu.schedule.dto.LocationDTO;
import com.softserve.edu.schedule.dto.RoomDTO;
import com.softserve.edu.schedule.dto.RoomEquipmentDTO;
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
@Service
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
     * Convert given RoomDTO object to Room object.
     *
     * @param roomDTO
     *            a RoomDTO object to convert.
     *
     * @return a Room object or null if given roomDTO is null.
     */
    public Room getEntity(final RoomDTO roomDTO) {
        if (roomDTO != null) {
            Room room = new Room();
            room.setId(roomDTO.getId());
            if (roomDTO.getLocation() != null) {
                room.setLocation(
                        locationDAO.getById(roomDTO.getLocation().getId()));
            }
            room.setName(roomDTO.getName());
            room.setCapacity(roomDTO.getCapacity());
            roomDTO.getEquipments().forEach(e -> room.getEquipments()
                    .add(roomEquipmentDAO.getById(e.getId())));
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
     * @return a RoomDTO object or null if given room is null.
     */
    public RoomDTO getDTO(final Room room) {
        if (room != null) {
            RoomDTO roomDTO = new RoomDTO();
            roomDTO.setId(room.getId());
            roomDTO.setName(room.getName());
            roomDTO.setCapacity(room.getCapacity());
            if (room.getLocation() != null) {
                LocationDTO locationDTO = new LocationDTO();
                locationDTO.setId(room.getLocation().getId());
                locationDTO.setName(room.getLocation().getName());
                locationDTO.setAddress(room.getLocation().getAddress());
                roomDTO.setLocation(locationDTO);
            }
            room.getEquipments().forEach(e -> {
                RoomEquipmentDTO roomEquipmentDTO = new RoomEquipmentDTO();
                roomEquipmentDTO.setId(e.getId());
                roomEquipmentDTO.setName(e.getName());
                roomDTO.getEquipments().add(roomEquipmentDTO);
            });
            return roomDTO;
        }
        return null;
    }

}
