package com.softserve.edu.oms.enums;


public enum Region {
    NORTH("North"),
    EAST("East"),
    SOUTH("South"),
    WEST("West");

    private String region;

    Region(String region) {
        this.region = region;
    }

    public String getRegionType() {
        return region;
    }
    public static Region getRegion(String regionString) {
        for (final Region regionEnum : Region.values()) {
            if (regionEnum.getRegionType().trim().equalsIgnoreCase(regionString)) {
                return regionEnum;
            }
        }

        return null;
    }
}
