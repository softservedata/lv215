package com.softserve.edu.schedule.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.edu.schedule.dao.RoomEquipmentDAO;
import com.softserve.edu.schedule.entity.RoomEquipment;
import com.softserve.edu.schedule.service.RoomEquipmentService;

@Transactional
@Service
public class RoomEquipmentServiceImpl implements RoomEquipmentService {

    @Autowired
    private RoomEquipmentDAO roomEquipmentDAO;

    @Override
    @Transactional(readOnly = true)
    public List<RoomEquipment> getAll() {
        return roomEquipmentDAO.getAll();
    }

    @Override
    public RoomEquipment getById(Long id) {
        return roomEquipmentDAO.getById(id);
    }

}
