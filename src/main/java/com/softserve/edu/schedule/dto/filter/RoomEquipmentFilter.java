/* RoomEquipmentFilter 1.0 01/17/2017 */
package com.softserve.edu.schedule.dto.filter;

/**
 * A DTO class to storage and transport filter data.
 *
 * @version 1.0 17 January 2017
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
public class RoomEquipmentFilter {

    /**
     * Show filter parameter. If true filter will be shown.
     */
    private String showFilter;

    /**
     * Room equipment name for filter room equipments.
     */
    private String name;

    /**
     * Field id for sorting filter result.
     */
    private int sortByField;

    /**
     * Order id for sorting filter result.
     */
    private int sortOrder;

    /**
     * @return the showFilter
     */
    public String getShowFilter() {
        return showFilter;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the sortByField
     */
    public int getSortByField() {
        return sortByField;
    }

    /**
     * @return the sortOrder
     */
    public int getSortOrder() {
        return sortOrder;
    }

    /**
     * @param showFilter
     *            the showFilter to set
     */
    public void setShowFilter(final String showFilter) {
        this.showFilter = showFilter;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @param sortByField
     *            the sortByField to set
     */
    public void setSortByField(final int sortByField) {
        this.sortByField = sortByField;
    }

    /**
     * @param sortOrder
     *            the sortOrder to set
     */
    public void setSortOrder(final int sortOrder) {
        this.sortOrder = sortOrder;
    }

}
