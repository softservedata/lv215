/*
 * ReadDAO<E>.java
 * 1.0
 * 04 Jan 2017
 * Copyright (c) Ped'ko Volodymyr
 */
package com.softserve.edu.schedule.dao;

import java.util.List;

/**
 * A simple DAO interface to handle the database operation required to
 * manipulate read functions.
 *
 * @version 1.0 04 Jan 2016
 * @author Ped'ko Volodymyr
 *
 */
public interface ReadDAO<E> {

    /**
     * Return a Transfer object if found.
     *
     * @param id
     *            of Transfer object
     * @return Transfer object object
     */
    E getById(final Long id);

    /**
     * Return a List of Transfer objects.
     *
     * @return List of Transfer objects
     */
    List<E> getAll();

    /**
     * Return a List of searched Transfer objects.
     *
     * @return List of searched Transfer objects
     */
    List<E> search(final String field, final String pattern);

    /**
     * Return a List of sorted Transfer objects.
     *
     * @return List of sorted Transfer objects
     */
    List<E> sort(final String field, final Order order);
}
