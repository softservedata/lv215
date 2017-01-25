/* Paginator 1.0 01/25/2017 */
package com.softserve.edu.schedule.dto.filter;

/**
 * A DTO class to storage and transport pagination data.
 *
 * @version 1.0 25 January 2017
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
public class Paginator {

    /**
     * Field for storage current page number.
     */
    private int pageNumber;
    /**
     * Field for storage current number of records per page.
     */
    private int pageSize = 10;
    /**
     * Field for storage current pages count.
     */
    private int pagesCount;

    /**
     * @return current page number
     */
    public int getPageNumber() {
        return this.pageNumber;
    }

    /**
     * @return current number of records per page
     */
    public int getPageSize() {
        return this.pageSize;
    }

    /**
     * @return current offset based on current page number and number of records
     *         per page
     */
    public int getOffset() {
        return this.pageNumber * this.pageSize;
    }

    /**
     * Switch to the next page
     */
    public void next() {
        if (hasNext()) {
            this.pageNumber += 1;
        }
    }

    /**
     * Switch to the previous page
     */
    public void previous() {
        if (hasPrevious()) {
            this.pageNumber -= 1;
        }
    }

    /**
     * @return true if current page has the previous page
     */
    public boolean hasPrevious() {
        return this.pageNumber > 0;
    }

    /**
     * @return true if current page has the next page
     */
    public boolean hasNext() {
        return this.pageNumber < this.pagesCount;
    }

    /**
     * @return the pagesCount
     */
    public int getPagesCount() {
        return pagesCount;
    }

    /**
     * @param pageNumber
     *            the pageNumber to set
     */
    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    /**
     * @param pageSize
     *            the pageSize to set
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * Calculate and set count of pages based on number of records in current
     * query result and current pageSize.
     * 
     * @param resultCount
     *            the total number of records in the current query result
     */
    public void setPagesCount(int resultCount) {
        this.pagesCount = resultCount / pageSize;
    }
}
