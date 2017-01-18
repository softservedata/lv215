/* LocationDTOEditor 1.0 01/17/2017 */
package com.softserve.edu.schedule.service.implementation.editor;

import java.beans.PropertyEditorSupport;

import com.softserve.edu.schedule.dto.LocationDTO;
import com.softserve.edu.schedule.entity.Location;
import com.softserve.edu.schedule.service.LocationService;

/**
 * A class to provide conversion operations from form field locationId to
 * LocationDTO.
 *
 * @version 1.0 17 January 2017
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
public class LocationDTOEditor extends PropertyEditorSupport {

    /**
     * LocationService example to provide search DTO operations.
     */
    private LocationService locationService;

    /**
     * Constructor of LocationDTOEditor.
     * 
     * @param locationService
     *            LocationService example
     */
    public LocationDTOEditor(final LocationService locationService) {
        this.locationService = locationService;
    }

    /**
     * Provides a LocationDTO example by given location id in String format.
     * 
     * @param locationId
     *            a location id in String format
     * 
     * @throws IllegalArgumentException
     *             if @param locationId is not String.
     */
    @Override
    public void setAsText(final String locationId)
            throws IllegalArgumentException {
        Location location = locationService.getById(Long.valueOf(locationId));
        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setId(location.getId());
        locationDTO.setName(location.getName());
        locationDTO.setAddress(location.getAddress());
        locationDTO.setCoordinates(location.getCoordinates());
        setValue(locationDTO);
    }
}
