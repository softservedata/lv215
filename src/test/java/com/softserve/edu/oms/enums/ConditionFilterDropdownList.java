package com.softserve.edu.oms.enums;

public enum ConditionFilterDropdownList {
    EQUALS("equals"),
    NOT_EQUAL_TO("not equal to"),
    START_WITH("starts with"),
    CONTAINS("contains"),
    DOES_NOT_CONTAIN("does not contain");

    private String nameOfConditionfilterField;

    ConditionFilterDropdownList(String nameOfConditionfilterField) {
        this.nameOfConditionfilterField = nameOfConditionfilterField;
    }

    public String getNameOfConditionFilterField() {
        return nameOfConditionfilterField;
    }
}
