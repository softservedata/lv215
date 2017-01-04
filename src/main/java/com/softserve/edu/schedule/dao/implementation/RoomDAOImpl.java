/* RoomDAOImpl 1.0 01/02/2017 */
package com.softserve.edu.schedule.dao.implementation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.softserve.edu.schedule.dao.RoomDAO;
import com.softserve.edu.schedule.entity.Room;

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
public class RoomDAOImpl implements RoomDAO {

    /**
     * Query string to find all rooms in database without details.
     */
    private static final String FIND_ALL_QUERY = "select distinct r from Room r";

    /**
     * Query string to find all rooms in database with location and equipment
     * details.
     */
    private static final String FIND_ALL_WITH_DETAILS_QUERY = "select distinct r from Room r left join fetch r.location l left join fetch r.equipments e";

    /**
     * Entity Manager example to provide database operations.
     */
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Save new room entity into the database.
     *
     * @param room
     *            a new room to storage in database.
     */
    @Override
    public void add(Room room) {
        entityManager.persist(room);
    }

    /**
     * Update existed room entity in the database.
     *
     * @param room
     *            a room to update in database.
     */
    @Override
    public void update(Room room) {
        entityManager.merge(room);
    }

    /**
     * Find room entity in the database by Id.
     *
     * @param id
     *            a room id to find in the database.
     * @return an room object if room with this id exists in the database or
     *         Null if room not found
     */
    @Override
    public Room findById(Long id) {
        return entityManager.find(Room.class, id);
    }

    /**
     * Delete existed room entity from the database.
     *
     * @param room
     *            a room object to delete from database.
     */
    @Override
    public void delete(Room room) {
        room.getEquipments().clear();
        room = entityManager.merge(room);
        entityManager.remove(room);
    }

    /**
     * Delete existed room entity from the database by id.
     *
     * @param id
     *            a room id to delete from database.
     */
    @Override
    public void deleteById(Long id) {
        Room room = findById(id);
        delete(room);
    }

    /**
     * Find all rooms entities in the database.
     * 
     * @return List of the room objects.
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Room> findAll() {
        return entityManager.createQuery(FIND_ALL_QUERY).getResultList();
    }

    /**
     * Find all rooms entities in the database with location and equipment
     * details.
     * 
     * @return List of the room objects.
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Room> findAllWithDetails() {
        return entityManager.createQuery(FIND_ALL_WITH_DETAILS_QUERY)
                .getResultList();
    }

}
