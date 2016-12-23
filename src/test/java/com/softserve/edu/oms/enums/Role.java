package com.softserve.edu.oms.enums;


public enum Role {
    ADMINISTRATOR("roleID1"),
    MERCHANDISER("roleID3"),
    SUPERVISOR("roleID4"),
    CUSTOMER("roleID2");

    private String roleId;

    Role(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleId() {
        return roleId;
    }
    
}
