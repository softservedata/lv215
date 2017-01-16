/*
 * CrudDAO<E>.java
 * 1.0
 * 04 Jan 2017
 * Copyright (c) Ped'ko Volodymyr
 */
package com.softserve.edu.schedule.dao;

/**
 * A simple DAO interface to handle the database operation required to
 * manipulate CRUD functions.
 *
 * @version 1.0 04 Jan 2016
 * @author Ped'ko Volodymyr
 *
 */
public interface CrudDAO<E> extends ReadDAO<E> {

    /**
     * Constant for search method.
     */
    String SEARCH_MASK = "%";

    /**
     * Saving Transfer object in database.
     *
     * @param element
     *            - Transfer object
     */
    void create(final E element);

    /**
     * Updating Transfer object from database.
     *
     * @param element
     *            - Transfer object
     */
    void update(final E element);

    /**
     * Deleting Transfer object from database.
     *
     * @param element
     *            - Transfer object
     */
    void delete(final E element);

}
