package com.softserve.edu.oms.enums;

public enum FieldFilterDropdownList {
    ALL_COLUMNS("All columns"),
    FIRST_NAME("First Name"),
    LAST_NAME("Last Name"),
    ROLE("Role"),
    LOGIN("Login Name");

    private String fieldname;

    FieldFilterDropdownList(String fieldname) {
        this.fieldname = fieldname;
    }

    public String getFieldName() {
        return fieldname;
    }
}
