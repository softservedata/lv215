package com.softserve.edu.schedule.service.implementation.dtoconverter;

import org.springframework.stereotype.Service;

import com.softserve.edu.schedule.dto.RoomForLocationDTO;
import com.softserve.edu.schedule.entity.Room;

@Service("roomForLocationDTOConverter")
public class RoomForLocationDTOConverter {

	 public RoomForLocationDTO getDTO(Room room) {
	        if (room != null) {
	        	RoomForLocationDTO roomDTO = new RoomForLocationDTO();
	            if (room.getId() != null) {
	                roomDTO.setId(room.getId());
	            }
	            if (room.getName() != null) {
	                roomDTO.setName(room.getName());
	            }
	           
	            return roomDTO;
	        }
	        return null;
	    }
	
}
