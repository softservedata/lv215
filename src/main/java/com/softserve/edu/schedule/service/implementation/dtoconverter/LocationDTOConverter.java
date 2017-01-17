package com.softserve.edu.schedule.service.implementation.dtoconverter;

import org.springframework.stereotype.Service;

import com.softserve.edu.schedule.dto.LocationDTO;
import com.softserve.edu.schedule.entity.Location;

@Service("locationDTOConverter")
public class LocationDTOConverter {

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
            return locationDTO;
        }
        return null;
    }

}
