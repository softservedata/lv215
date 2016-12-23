package com.softserve.edu.oms.enums;

public enum SortingOrder {
    ASCENDING("ASC"),
    DESCENDING("DESC");

    private String sortingOrderInColumn;

    SortingOrder(String sortingOrderInColumn) {
        this.sortingOrderInColumn = sortingOrderInColumn;
    }

    public String getSortingOrderInColumn() {
        return sortingOrderInColumn;
    }


}
