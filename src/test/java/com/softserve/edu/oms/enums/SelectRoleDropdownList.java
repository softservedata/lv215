package com.softserve.edu.oms.enums;

public enum SelectRoleDropdownList {
    ADMINISTRATOR("Administrator"),
    CUSTOMER("Customer"),
    MERCHANDISER("Merchandiser"),
    SUPERVISOR("Supervisor");
    //
    private String role;

    private SelectRoleDropdownList(String role) {
        this.role= role;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return this.role;
    }
}
