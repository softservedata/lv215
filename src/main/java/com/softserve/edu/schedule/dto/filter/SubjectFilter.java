package com.softserve.edu.schedule.dto.filter;

public class SubjectFilter {

    private String showFilter;

    private String name;

    private String Description;

    private Long userId;

    private int sortByField;

    private int sortOrder;

    public String getShowFilter() {
        return showFilter;
    }

    public void setShowFilter(String showFilter) {
        this.showFilter = showFilter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
