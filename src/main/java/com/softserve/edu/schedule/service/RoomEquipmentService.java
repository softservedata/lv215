package com.softserve.edu.schedule.service;

import java.util.List;

import com.softserve.edu.schedule.entity.RoomEquipment;

public interface RoomEquipmentService {

    List<RoomEquipment> getAll();

    RoomEquipment getById(Long id);

}
