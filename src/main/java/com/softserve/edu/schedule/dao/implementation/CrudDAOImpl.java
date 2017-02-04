/*
 * CrudDAO<E>.java
 * 1.0
 * 04 Jan 2017
 * Copyright (c) Ped'ko Volodymyr
 */
package com.softserve.edu.schedule.dao.implementation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.softserve.edu.schedule.dao.CrudDAO;
import com.softserve.edu.schedule.dao.Order;

/**
 * A simple class to handle the database operation (CRUD).
 *
 * @param <E>
 *            Entity class
 *
 * @version 1.0 04 Jan 2016
 * @author Ped'ko Volodymyr
 *
 */
public abstract class CrudDAOImpl<E> implements CrudDAO<E> {

    /**
     * Generic entity class.
     */
    private Class<E> entityClass;

    /**
     * Entity manager for managing CRUD operations.
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * @param entityClass
     *            - Generic entity class.
     */
    public CrudDAOImpl(final Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    /**
     * Return a Transfer object if found.
     *
     * @param id
     *            of Transfer object
     * @return Transfer object object
     */
    @Override
    public E getById(final Long id) {
        return em.find(entityClass, id);
    }

    /**
     * Return a List of Transfer objects.
     *
     * @return List of Transfer objects
     */
    @Override
    public List<E> getAll() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<E> cq = builder.createQuery(entityClass);
        Root<E> root = cq.from(entityClass);
        cq.select(root);
        return em.createQuery(cq).getResultList();
    }

    /**
     * Return a List of searched Transfer objects.
     *
     * @return List of searched Transfer objects
     */
    @Override
    public List<E> search(final String field, final String pattern) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<E> cq = builder.createQuery(entityClass);
        Root<E> root = cq.from(entityClass);
        cq.where(builder.like(root.get(field),
                SEARCH_MASK + pattern + SEARCH_MASK));
        return em.createQuery(cq).getResultList();
    }

    /**
     * Return a List of sorted Transfer objects.
     *
     * @return List of sorted Transfer objects
     */
    @Override
    public List<E> sort(final String field, final Order order) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<E> cq = builder.createQuery(entityClass);
        Root<E> root = cq.from(entityClass);
        if (order == Order.ASC) {
            cq.orderBy(builder.asc(root.get(field)));
        } else {
            cq.orderBy(builder.desc(root.get(field)));
        }
        return em.createQuery(cq).getResultList();
    }

    /**
     * Saving Transfer object in database.
     *
     * @param element
     *            - Transfer object
     */
    @Override
    public void create(final E element) {
        em.persist(element);
    }

    /**
     * Updating Transfer object from database.
     *
     * @param element
     *            - Transfer object
     */
    @Override
    public void update(final E element) {
        em.merge(element);
    }

    /**
     * Deleting Transfer object from database.
     *
     * @param element
     *            - Transfer object
     */
    @Override
    public void delete(final E element) {
        em.remove(em.merge(element));
    }

    /**
     * Return example of Entity Manager.
     *
     * @return example of Entity Manager
     */
    public EntityManager getEm() {
        return em;
    }

}
