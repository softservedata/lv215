/* LocationDTOConverter 1.0 01/17/2017 */
package com.softserve.edu.schedule.service.implementation.dtoconverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserve.edu.schedule.dao.RoomDAO;
import com.softserve.edu.schedule.dto.LocationDTO;
import com.softserve.edu.schedule.entity.Location;

/**
 * A class to provide conversion operations between LocationDTO and Location
 * entity.
 *
 * @version 1.0 17 January 2017
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
@Service("locationDTOConverter")
public class LocationDTOConverter {

    /**
     * RoomForLocationDTOConverter example to provide to DTO and from DTO room
     * conversion.
     */
    @Autowired
    private RoomForLocationDTOConverter roomForLocationDTOConverter;

    /**
     * RoomDAO example to provide database operations.
     */
    @Autowired
    private RoomDAO roomDAO;

    /**
     * Convert given LocationDTO object to Location object
     * 
     * @param locationDTO
     *            a LocationDTO object to convert.
     * 
     * @return a Location object or null if given @param locationDTO is null.
     */
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

    /**
     * Convert given Location object to LocationDTO object.
     * 
     * @param location
     *            a Location object to convert.
     * 
     * @return a LocationDTO object or null if given @param location is null.
     */
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
                location.getRooms().forEach(e -> locationDTO.getRooms()
                        .add(roomForLocationDTOConverter.getDTO(e)));
            }
            return locationDTO;
        }
        return null;
    }

}
