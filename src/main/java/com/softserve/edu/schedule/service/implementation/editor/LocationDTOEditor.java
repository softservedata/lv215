package com.softserve.edu.schedule.service.implementation.editor;

import java.beans.PropertyEditorSupport;

import com.softserve.edu.schedule.dto.LocationDTO;
import com.softserve.edu.schedule.entity.Location;
import com.softserve.edu.schedule.service.LocationService;

public class LocationDTOEditor extends PropertyEditorSupport {

    private LocationService locationService;

    public LocationDTOEditor(LocationService locationService) {
        this.locationService = locationService;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        Location location = locationService.getById(Long.valueOf(text));
        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setId(location.getId());
        locationDTO.setName(location.getName());
        locationDTO.setAddress(location.getAddress());
        locationDTO.setCoordinates(location.getCoordinates());
        setValue(locationDTO);
    }
}
