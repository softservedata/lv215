/*
 * RoomForLocationDTOConverter
 * 1.0
 * 18 Jan 2017
 * Copyright (c) Oleksandr Butyter
 */
package com.softserve.edu.schedule.service.implementation.dtoconverter;

import org.springframework.stereotype.Component;

import com.softserve.edu.schedule.dto.RoomForLocationDTO;
import com.softserve.edu.schedule.entity.Room;

/**
 * A class to provide conversion operations between Room entity and RoomDTO.
 *
 * @version 1.0 18 January 2017
 *
 * @author Oleksandr Butyter
 */
@Component
public class RoomForLocationDTOConverter {

	/**
	 * Method converts given Room entity to RoomDTO.
	 * 
	 * @param room
	 *            Room entity to convert
	 * @return RoomDTO object.
	 */
	public RoomForLocationDTO getDTO(final Room room) {
		if (room != null) {
			RoomForLocationDTO roomDTO = new RoomForLocationDTO();
			roomDTO.setId(room.getId());
			roomDTO.setName(room.getName());
			return roomDTO;
		}
		return null;
	}

}
