package com.softserve.edu.schedule.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.edu.schedule.dao.Order;
import com.softserve.edu.schedule.dao.RoomEquipmentDAO;
import com.softserve.edu.schedule.dto.RoomEquipmentDTO;
import com.softserve.edu.schedule.entity.RoomEquipment_;
import com.softserve.edu.schedule.service.RoomEquipmentService;
import com.softserve.edu.schedule.service.implementation.dtoconverter.RoomEquipmentDTOConverter;

@Transactional
@Service("roomEquipmentService")
public class RoomEquipmentServiceImpl implements RoomEquipmentService {

    @Autowired
    private RoomEquipmentDAO roomEquipmentDAO;
    
    @Autowired
    private RoomEquipmentDTOConverter roomEquipmentDTOConverter;

    @Override
    @Transactional(readOnly = true)
    public List<RoomEquipmentDTO> getAll() {
//        return roomEquipmentDAO.getAll();
		return roomEquipmentDAO.getAll().stream().map(e -> roomEquipmentDTOConverter.getDTO(e)).collect(Collectors.toList());
		
    }

    @Override
    public RoomEquipmentDTO getById(Long id) {
        return roomEquipmentDTOConverter.getDTO(roomEquipmentDAO.getById(id));
    }

	@Override
	public void create(final RoomEquipmentDTO equipmentDTO) {
		roomEquipmentDAO.create(roomEquipmentDTOConverter.getEntity(equipmentDTO));
	}

	@Override
	public void update(RoomEquipmentDTO equipmentDTO) {
		roomEquipmentDAO.update(roomEquipmentDTOConverter.getEntity(equipmentDTO));
		
	}

	@Override
	public void delete(RoomEquipmentDTO equipmentDTO) {
		roomEquipmentDAO.delete(roomEquipmentDTOConverter.getEntity(equipmentDTO));
	}

	@Override
	public void deleteById(Long id) {
		roomEquipmentDAO.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<RoomEquipmentDTO> sortByName(final Order order) {
			return roomEquipmentDAO.sort(RoomEquipment_.name.getName(), order).stream().map(e -> roomEquipmentDTOConverter.getDTO(e))
					.collect(Collectors.toList());
	}
	
 
    

}
