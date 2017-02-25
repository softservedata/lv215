/* RoomEquipmentDAOImpl 1.0 01/02/2017 */
package com.softserve.edu.schedule.dao.implementation;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.softserve.edu.schedule.dao.RoomEquipmentDAO;
import com.softserve.edu.schedule.dto.filter.Paginator;
import com.softserve.edu.schedule.dto.filter.RoomEquipmentFilter;
import com.softserve.edu.schedule.entity.RoomEquipment;
import com.softserve.edu.schedule.entity.RoomEquipment_;
import com.softserve.edu.schedule.service.implementation.specification.RoomEquipmentFilterSpecification;

/**
 * A class to provide DAO operations with RoomEquipment entity.
 *
 * @version 1.0 02 January 2017
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
@Repository
public class RoomEquipmentDAOImpl extends CrudDAOImpl<RoomEquipment>
        implements RoomEquipmentDAO {

    /**
     * Default constructor to provide entity class for DAO.
     *
     */
    public RoomEquipmentDAOImpl() {
        super(RoomEquipment.class);
    }

    /**
     * Delete room equipment from the database by given id.
     *
     * @param id
     *            a room equipment id to delete from the database.
     */
    @Override
    public void deleteById(final Long id) {
        RoomEquipment equipment = getById(id);
        delete(equipment);
    }

    /**
     * Find all room equipments entities in the database with applied filter.
     *
     * @param filter
     *            a filter to apply.
     *
     * @param paginator
     *            paginator to provide paging information
     *
     * @return List of the room equipment objects.
     */
    @Override
    public List<RoomEquipment> getRoomEquipmentsPageWithFilter(
            final RoomEquipmentFilter filter, final Paginator paginator) {
        CriteriaBuilder builder = getEm().getCriteriaBuilder();
        CriteriaQuery<RoomEquipment> criteriaQuery = builder
                .createQuery(RoomEquipment.class);
        Root<RoomEquipment> root = criteriaQuery.from(RoomEquipment.class);
        Predicate predicate = new RoomEquipmentFilterSpecification(filter)
                .toPredicate(root, criteriaQuery, builder);
        if (predicate != null) {
            criteriaQuery.where(predicate);
        }
        criteriaQuery.distinct(true);
        paginator.setPagesCount(getCountOfRoomEquipmentsWithFilter(filter));
        return getEm().createQuery(criteriaQuery)
                .setFirstResult(paginator.getOffset())
                .setMaxResults(paginator.getPageSize()).getResultList();
    }

    /**
     * Count room equipments entities in the database with specified filter.
     *
     * @param filter
     *            a filter to apply.
     *
     * @return Count of the room equipment entities in the database with
     *         specified predicate.
     */
    @Override
    public Long getCountOfRoomEquipmentsWithFilter(
            final RoomEquipmentFilter filter) {
        CriteriaBuilder qb = getEm().getCriteriaBuilder();
        CriteriaQuery<Long> cq = qb.createQuery(Long.class);
        Root<RoomEquipment> root = cq.from(RoomEquipment.class);
        cq.select(qb.countDistinct(root));
        Predicate predicate = new RoomEquipmentFilterSpecification(filter)
                .toPredicate(root, cq, qb);
        if (predicate != null) {
            cq.where(predicate);
        }
        return getEm().createQuery(cq).getSingleResult();
    }

    /**
     * Find room equipments entities in the database by given name.
     *
     * @param roomEquipmentName
     *            a name to find in database.
     *
     * @return List of the room equipment objects.
     */
    @Override
    public List<RoomEquipment> getRoomEquipmentsByName(
            final String roomEquipmentName) {
        CriteriaBuilder builder = getEm().getCriteriaBuilder();
        CriteriaQuery<RoomEquipment> cq = builder
                .createQuery(RoomEquipment.class);
        Root<RoomEquipment> root = cq.from(RoomEquipment.class);
        Predicate predicate = builder.like(root.get(RoomEquipment_.name),
                roomEquipmentName);
        cq.where(predicate);
        return getEm().createQuery(cq).getResultList();
    }
}
