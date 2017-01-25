package com.softserve.edu.schedule.dto.filter;

public class Paginator {

    private int pageNumber = 0;
    private int pageSize = 10;

    public int getPageNumber() {
        return this.pageNumber;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public int getOffset() {
        return this.pageNumber * this.pageSize;
    }

    public void next() {
        this.pageNumber += 1;
    }

    public void first() {
        this.pageNumber = 1;
    }

    public void previous() {
        if (hasPrevious()) {
            this.pageNumber -= 1;
        }
    }

    public boolean hasPrevious() {
        return this.pageNumber > 0;
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
}
