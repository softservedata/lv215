package com.softserve.edu.schedule.service;

import java.util.List;

import com.softserve.edu.schedule.dao.Order;
import com.softserve.edu.schedule.dto.RoomEquipmentDTO;

public interface RoomEquipmentService {

    List<RoomEquipmentDTO> getAll();

    RoomEquipmentDTO getById(Long id);
    
    void create(final RoomEquipmentDTO equipmentDTO);
    
    void update(final RoomEquipmentDTO equipmentDTO);

    void delete(final RoomEquipmentDTO equipmentDTO);
    
    void deleteById(final Long id);
    
    List<RoomEquipmentDTO> sortByName(final Order order);
}
