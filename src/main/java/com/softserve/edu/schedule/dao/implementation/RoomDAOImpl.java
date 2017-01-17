/* RoomDAOImpl 1.0 01/02/2017 */
package com.softserve.edu.schedule.dao.implementation;

import java.util.List;

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
import com.softserve.edu.schedule.entity.RoomEquipment;
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
@Repository("roomDAO")
public class RoomDAOImpl extends CrudDAOImpl<Room> implements RoomDAO {

    @Autowired
    RoomEquipmentDAO roomEquipmentDAO;

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
     * Overridden default constructor to provide entity class for DAO.
     * 
     */
    public RoomDAOImpl() {
        super(Room.class);
    }

    /**
     * Delete existed room entity from the database by id.
     *
     * @param id
     *            a room id to delete from database.
     */
    @Override
    public void deleteById(final Long id) {
        Room room = getById(id);
        delete(room);
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
     * Find a room in the database by name.
     *
     * @param roomName
     *            a room name to find in the database.
     * @return a room with given name.
     */
    @Override
    public Room getByName(String roomName) {
        CriteriaBuilder builder = getEm().getCriteriaBuilder();
        CriteriaQuery<Room> cq = builder.createQuery(Room.class);
        Root<Room> root = cq.from(Room.class);
        root.fetch(Room_.location, JoinType.LEFT);
        root.fetch(Room_.equipments, JoinType.LEFT);
        cq.where(builder.like(root.get(Room_.name),
                SEARCH_MASK + roomName + SEARCH_MASK));
        return getEm().createQuery(cq).getSingleResult();
    }

    /**
     * Find all rooms entities in the database with location and equipment
     * details which has capacity in given interval.
     * 
     * @param minCapacity
     *            a minimum capacity.
     * @param maxCapacity
     *            a minimum capacity.
     * 
     * @return List of the room objects.
     */
    @Override
    public List<Room> getRoomsByCapacity(int minCapacity, int maxCapacity) {
        CriteriaBuilder builder = getEm().getCriteriaBuilder();
        CriteriaQuery<Room> cq = builder.createQuery(Room.class);
        Root<Room> root = cq.from(Room.class);
        root.fetch(Room_.location, JoinType.LEFT);
        root.fetch(Room_.equipments, JoinType.LEFT);
        if (maxCapacity > 0 && minCapacity > 0) {
            cq.where(builder.between(root.get(Room_.capacity), minCapacity,
                    maxCapacity));
        } else if (maxCapacity > 0) {
            cq.where(builder.lessThan(root.get(Room_.capacity), maxCapacity));
        } else if (minCapacity > 0) {
            cq.where(
                    builder.greaterThan(root.get(Room_.capacity), minCapacity));
        }
        cq.distinct(true);
        return getEm().createQuery(cq).getResultList();
    }

    /**
     * Find all rooms entities in the database with location and equipment
     * details by given location id.
     * 
     * @param locationId
     *            a location id to find rooms.
     * 
     * @return List of the room objects.
     */
    @Override
    public List<Room> getRoomsByLocationId(final long locationId) {
        CriteriaBuilder builder = getEm().getCriteriaBuilder();
        CriteriaQuery<Room> cq = builder.createQuery(Room.class);
        Root<Room> root = cq.from(Room.class);
        root.fetch(Room_.location, JoinType.LEFT);
        root.fetch(Room_.equipments, JoinType.LEFT);
        cq.where(root.get(Room_.location).in(locationId));
        cq.distinct(true);
        return getEm().createQuery(cq).getResultList();
    }

    /**
     * Find all rooms entities in the database with location and equipment
     * details by given equipments list.
     * 
     * @param equipments
     *            an equipments list to find rooms.
     * 
     * @return List of the room objects.
     */
    @Override
    public List<Room> getRoomsWithEquipments(List<RoomEquipment> equipments) {
        CriteriaBuilder builder = getEm().getCriteriaBuilder();
        CriteriaQuery<Room> cq = builder.createQuery(Room.class);
        Root<Room> root = cq.from(Room.class);
        root.fetch(Room_.location, JoinType.LEFT);
        root.fetch(Room_.equipments, JoinType.LEFT);
        Predicate predicate = builder.conjunction();
        for (RoomEquipment equipment : equipments) {
            Predicate newPredicate = builder.isMember(equipment,
                    root.get(Room_.equipments));
            predicate = builder.and(predicate, newPredicate);
        }
        cq.where(predicate);
        cq.distinct(true);
        return getEm().createQuery(cq).getResultList();
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
