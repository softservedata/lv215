package com.softserve.edu.schedule.dto.filter;

public class UserFilter {
    
    private String lastName;
    
    private String position;
    
    private int sortByField;
    
    private int sortOrder;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getSortByField() {
        return sortByField;
    }

    public void setSortByField(int sortByField) {
        this.sortByField = sortByField;
    }

    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }
}
