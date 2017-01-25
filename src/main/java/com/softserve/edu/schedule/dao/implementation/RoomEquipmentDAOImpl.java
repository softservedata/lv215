package com.softserve.edu.schedule.dao.implementation;

import org.springframework.stereotype.Repository;

import com.softserve.edu.schedule.dao.RoomEquipmentDAO;
import com.softserve.edu.schedule.entity.RoomEquipment;

@Repository("roomEquipmentDAO")
public class RoomEquipmentDAOImpl extends CrudDAOImpl<RoomEquipment>
        implements RoomEquipmentDAO {

    public RoomEquipmentDAOImpl() {
        super(RoomEquipment.class);
    }
    
    @Override
    public  void deleteById(Long id){
		RoomEquipment equipment = getById(id);
		delete(equipment);
    }
}
