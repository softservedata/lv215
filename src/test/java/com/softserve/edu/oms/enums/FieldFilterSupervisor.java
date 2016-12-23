package com.softserve.edu.oms.enums;

public enum FieldFilterSupervisor {
    NAME("Name"), DESCRIPTION("Description");

    private String field;

    private FieldFilterSupervisor(String field) {
        this.field = field;
    }

    @Override
    public String toString() {
        return this.field;
    }
}
