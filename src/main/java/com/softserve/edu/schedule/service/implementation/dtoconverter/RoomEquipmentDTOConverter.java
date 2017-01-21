/* RoomEquipmentDTOConverter 1.0 01/17/2017 */
package com.softserve.edu.schedule.service.implementation.dtoconverter;

import org.springframework.stereotype.Service;

import com.softserve.edu.schedule.dto.RoomEquipmentDTO;
import com.softserve.edu.schedule.entity.RoomEquipment;

/**
 * A class to provide conversion operations between RoomEquipmentDTO and
 * RoomEquipment entity.
 *
 * @version 1.0 17 January 2017
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
@Service("roomEquipmentDTOConverter")
public class RoomEquipmentDTOConverter {

    /**
     * Convert given RoomEquipmentDTO object to RoomEquipment object
     * 
     * @param roomEquipmentDTO
     *            a RoomEquipmentDTO object to convert.
     * 
     * @return a RoomEquipment object or null if given @param roomEquipmentDTO
     *         is null.
     */
    public RoomEquipment getEntity(RoomEquipmentDTO roomEquipmentDTO) {
        if (roomEquipmentDTO != null) {
            RoomEquipment roomEquipment = new RoomEquipment();
            if (roomEquipmentDTO.getId() != null) {
                roomEquipment.setId(roomEquipmentDTO.getId());
            }
            if (roomEquipmentDTO.getName() != null) {
                roomEquipment.setName(roomEquipmentDTO.getName());
            }
            return roomEquipment;
        }
        return null;
    }

    /**
     * Convert given RoomEquipment object to RoomEquipmentDTO object.
     * 
     * @param roomEquipment
     *            a roomEquipment object to convert.
     * 
     * @return a RoomEquipmentDTO object or null if given @param roomEquipment
     *         is null.
     */
    public RoomEquipmentDTO getDTO(RoomEquipment roomEquipment) {
        if (roomEquipment != null) {
            RoomEquipmentDTO roomEquipmentDTO = new RoomEquipmentDTO();
            if (roomEquipment.getId() != null) {
                roomEquipmentDTO.setId(roomEquipment.getId());
            }
            if (roomEquipment.getName() != null) {
                roomEquipmentDTO.setName(roomEquipment.getName());
            }
            return roomEquipmentDTO;
        }
        return null;
    }
}
