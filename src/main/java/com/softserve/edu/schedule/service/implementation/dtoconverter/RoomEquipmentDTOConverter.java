package com.softserve.edu.schedule.service.implementation.dtoconverter;

import org.springframework.stereotype.Service;

import com.softserve.edu.schedule.dto.RoomEquipmentDTO;
import com.softserve.edu.schedule.entity.RoomEquipment;

@Service("roomEquipmentDTOConverter")
public class RoomEquipmentDTOConverter {

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
