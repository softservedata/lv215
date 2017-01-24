/* RoomDAOImpl 1.0 01/02/2017 */
package com.softserve.edu.schedule.dao.implementation;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.softserve.edu.schedule.dao.RoomDAO;
import com.softserve.edu.schedule.dao.RoomEquipmentDAO;
import com.softserve.edu.schedule.dto.filter.RoomFilter;
import com.softserve.edu.schedule.entity.Room;
import com.softserve.edu.schedule.entity.Room_;
import com.softserve.edu.schedule.service.implementation.specification.RoomFilterSpecification;

/**
 * A class to provide DAO operations with Room entity.
 *
 * @version 1.0 02 January 2017
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
@Repository
public class RoomDAOImpl extends CrudDAOImpl<Room> implements RoomDAO {

    /**
     * RoomEquipmentDAO example to provide database operations.
     *
     */
    @Autowired
    RoomEquipmentDAO roomEquipmentDAO;

    /**
     * Default constructor to provide entity class for DAO.
     *
     */
    public RoomDAOImpl() {
        super(Room.class);
    }

    /**
     * Return a Room object if found.
     *
     * @param id
     *            of Room object
     * @return room with given id
     */
    @Override
    public Room getById(Long id) {
        CriteriaBuilder builder = getEm().getCriteriaBuilder();
        CriteriaQuery<Room> cq = builder.createQuery(Room.class);
        Root<Room> root = cq.from(Room.class);
        root.fetch(Room_.location, JoinType.LEFT);
        root.fetch(Room_.equipments, JoinType.LEFT);
        cq.where(root.get(Room_.id).in(id));
        return getEm().createQuery(cq).getSingleResult();
    }

    /**
     * Find all rooms entities in the database with location and equipment
     * details.
     *
     * @return List of the room objects.
     */
    @Override
    public List<Room> getAllWithDetails() {
        CriteriaBuilder builder = getEm().getCriteriaBuilder();
        CriteriaQuery<Room> cq = builder.createQuery(Room.class);
        Root<Room> root = cq.from(Room.class);
        root.fetch(Room_.location, JoinType.LEFT);
        root.fetch(Room_.equipments, JoinType.LEFT);
        cq.distinct(true);
        return getEm().createQuery(cq).getResultList();
    }

    /**
     * Find a room in the database by name and location id.
     *
     * @param roomName
     *            a room name to find in the database.
     * 
     * @param locationId
     *            a location id to find room.
     * 
     * @return a room with given name and location Id.
     */
    @Override
    public Room getByNameAndLocationId(final String roomName,
            final Long locationId) {
        try {
            CriteriaBuilder builder = getEm().getCriteriaBuilder();
            CriteriaQuery<Room> cq = builder.createQuery(Room.class);
            Root<Room> root = cq.from(Room.class);
            root.fetch(Room_.location, JoinType.LEFT);
            root.fetch(Room_.equipments, JoinType.LEFT);
            Predicate predicate = builder.conjunction();
            predicate = builder.and(predicate,
                    builder.like(root.get(Room_.name), roomName));
            predicate = builder.and(predicate,
                    root.get(Room_.location).in(locationId));
            cq.where(predicate);
            return getEm().createQuery(cq).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Find all rooms entities in the database with applied filter
     *
     * @param roomFilter
     *            a filter to apply.
     *
     * @return List of the room objects.
     */
    @Override
    public List<Room> getRoomsWithFilter(final RoomFilter roomFilter) {
        CriteriaBuilder builder = getEm().getCriteriaBuilder();
        CriteriaQuery<Room> cq = builder.createQuery(Room.class);
        Root<Room> root = cq.from(Room.class);
        Predicate predicate = new RoomFilterSpecification(roomFilter,
                roomEquipmentDAO).toPredicate(root, cq, builder);
        if (predicate != null) {
            cq.where(predicate);
        }
        cq.distinct(true);
        return getEm().createQuery(cq).getResultList();
    }

}
