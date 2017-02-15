package com.softserve.edu.schedule.dao;

import com.softserve.edu.schedule.entity.RoomEquipment;

public interface RoomEquipmentDAO extends CrudDAO<RoomEquipment> {
	   void deleteById(final Long id);
}
