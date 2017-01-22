/* RoomFilter 1.0 01/22/2017 */
package com.softserve.edu.schedule.dto.filter;

import java.time.LocalDate;

/**
 * A DTO class to transport filter data.
 *
 * @version 1.0 22 January 2017
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
public class DateFilter {

    /**
     * Date for filter meetings.
     */
    private LocalDate date;

    /**
     * Constructor of DateFilter
     */
    public DateFilter() {
        this.date = LocalDate.now();
    }

    /**
     * @return the date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * @param date
     *            the date to set
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }
}
