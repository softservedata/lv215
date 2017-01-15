package com.softserve.edu.schedule.service.implementation.editor;

import java.beans.PropertyEditorSupport;

import com.softserve.edu.schedule.dto.RoomEquipmentDTO;
import com.softserve.edu.schedule.entity.RoomEquipment;
import com.softserve.edu.schedule.service.RoomEquipmentService;

public class RoomEquipmentDTOEditor extends PropertyEditorSupport {

    private RoomEquipmentService roomEquipmentService;

    public RoomEquipmentDTOEditor(RoomEquipmentService roomEquipmentService) {
        this.roomEquipmentService = roomEquipmentService;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        RoomEquipment roomEquipment = roomEquipmentService
                .getById(Long.valueOf(text));
        RoomEquipmentDTO roomEquipmentDTO = new RoomEquipmentDTO();
        roomEquipmentDTO.setId(roomEquipment.getId());
        roomEquipmentDTO.setName(roomEquipment.getName());
        setValue(roomEquipmentDTO);
    }
}
