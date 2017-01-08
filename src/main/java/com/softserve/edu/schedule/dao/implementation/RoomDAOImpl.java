/* RoomDAOImpl 1.0 01/02/2017 */
package com.softserve.edu.schedule.dao.implementation;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import com.softserve.edu.schedule.dao.RoomDAO;
import com.softserve.edu.schedule.entity.Room;
import com.softserve.edu.schedule.entity.Room_;

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

}
