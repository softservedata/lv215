package com.softserve.edu.schedule.service.implementation.dtoconverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserve.edu.schedule.dao.RoomDAO;
import com.softserve.edu.schedule.dto.LocationDTO;
import com.softserve.edu.schedule.entity.Location;

@Service("locationDTOConverter")
public class LocationDTOConverter {
	
	@Autowired
	private RoomForLocationDTOConverter roomForLocationDTOConverter;
	
	@Autowired
	private RoomDAO roomDAO;

    public Location getEntity(LocationDTO locationDTO) {
        if (locationDTO != null) {
            Location location = new Location();
            if (locationDTO.getId() != null) {
                location.setId(locationDTO.getId());
            }
            if (locationDTO.getName() != null) {
                location.setName(locationDTO.getName());
            }
            if (locationDTO.getAddress() != null) {
                location.setAddress(locationDTO.getAddress());
            }
            if (locationDTO.getCoordinates() != null) {
                location.setCoordinates(locationDTO.getCoordinates());
            }
            if (!locationDTO.getRooms().isEmpty()) {
            	locationDTO.getRooms().forEach(e -> location.getRooms()
                        .add(roomDAO.getById(e.getId())));
            }
            return location;
        }
        return null;
    }

    public LocationDTO getDTO(Location location) {
        if (location != null) {
            LocationDTO locationDTO = new LocationDTO();
            if (location.getId() != null) {
                locationDTO.setId(location.getId());
            }
            if (location.getName() != null) {
                locationDTO.setName(location.getName());
            }
            if (location.getAddress() != null) {
                locationDTO.setAddress(location.getAddress());
            }
            if (location.getCoordinates() != null) {
                locationDTO.setCoordinates(location.getCoordinates());
            }
            if (!location.getRooms().isEmpty()) {
            	location.getRooms().forEach(e->locationDTO.getRooms().add(roomForLocationDTOConverter.getDTO(e)));
            }
            return locationDTO;
        }
        return null;
    }

}
