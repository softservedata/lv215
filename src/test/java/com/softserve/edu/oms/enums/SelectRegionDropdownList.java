package com.softserve.edu.oms.enums;

/**
 * Created by User on 30.11.2016.
 */
public enum SelectRegionDropdownList {
    NORTH("North"),
    SOUTH("South"),
    EAST("East"),
    WEST("West");
    private String region;

    private SelectRegionDropdownList(String region) {
        this.region= region;
    }

    @Override
    public String toString() {
        return this.region;
    }
}
