package com.softserve.edu.schedule.service.implementation.editor;

import java.beans.PropertyEditorSupport;

import com.softserve.edu.schedule.service.LocationService;

public class LocationDTOEditor extends PropertyEditorSupport {

    private LocationService locationService;

    public LocationDTOEditor(LocationService locationService) {
        this.locationService = locationService;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(locationService.getById(Long.valueOf(text)));
    }
}
