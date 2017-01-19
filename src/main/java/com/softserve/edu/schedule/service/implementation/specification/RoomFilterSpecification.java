/* RoomFilterSpecification 1.0 01/17/2017 */
package com.softserve.edu.schedule.service.implementation.specification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import com.softserve.edu.schedule.dao.RoomEquipmentDAO;
import com.softserve.edu.schedule.dto.filter.RoomFilter;
import com.softserve.edu.schedule.entity.Room;
import com.softserve.edu.schedule.entity.RoomEquipment;
import com.softserve.edu.schedule.entity.Room_;

/**
 * A class to provide predicate based on given RoomFilter example. This
 * predicate is used to search rooms in database by given filter parameters.
 *
 * @version 1.0 17 January 2017
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
public class RoomFilterSpecification implements Specification<Room> {

    /**
     * RoomFilter example which provides parameters to build predicate.
     */
    private RoomFilter filter;

    /**
     * RoomEquipmentDAO example to provide database operations.
     */
    private RoomEquipmentDAO roomEquipmentDAO;

    /**
     * List of room specifications based on which the predicate is built.
     */
    private List<Specification<Room>> list = new ArrayList<>();

    /**
     * Constructor of RoomFilterSpecification.
     * 
     * @param filter
     *            RoomFilter example which provides parameters to build
     *            predicate.
     * 
     * @param roomEquipmentDAO
     *            RoomEquipmentDAO example to provide database operations.
     */
    public RoomFilterSpecification(final RoomFilter filter,
            final RoomEquipmentDAO roomEquipmentDAO) {
        this.filter = filter;
        this.roomEquipmentDAO = roomEquipmentDAO;
    }

    /**
     * Add location specification to specification list if filter contains
     * locationId parameter.
     */
    private void findByLocationId() {
        if (filter.getLocationId() > 0) {
            list.add((root, cq, cb) -> root.get(Room_.location)
                    .in(filter.getLocationId()));
        }
    }

    /**
     * Add room name specification to specification list if filter contains room
     * name parameter.
     */
    private void findByName() {
        if (filter.getName() != null && !filter.getName().equals("")) {
            list.add((root, cq, cb) -> cb.like(cb.lower(root.get(Room_.name)),
                    "%" + filter.getName().toLowerCase() + "%"));
        }
    }

    /**
     * Add room capacity specification to specification list if filter contains
     * maxCapacity and(or) minCapacity parameters.
     */
    private void findByMaxMinCapacity() {
        if (filter.getMaxCapacity() > 0 && filter.getMinCapacity() > 0) {
            list.add((root, cq, cb) -> cb.between(root.get(Room_.capacity),
                    filter.getMinCapacity(), filter.getMaxCapacity()));
        } else if (filter.getMaxCapacity() > 0) {
            list.add((root, cq, cb) -> cb.lessThan(root.get(Room_.capacity),
                    filter.getMaxCapacity()));
        } else if (filter.getMinCapacity() > 0) {
            list.add((root, cq, cb) -> cb.greaterThan(root.get(Room_.capacity),
                    filter.getMinCapacity()));
        }
    }

    /**
     * Add room equipments specifications to specification list if filter
     * contains equipments parameter.
     */
    private void findByEquipmentIds() {
        if (filter.getEquipments() != null
                && !filter.getEquipments().isEmpty()) {
            List<RoomEquipment> equipments = filter.getEquipments().stream()
                    .map(e -> roomEquipmentDAO.getById(e.getId()))
                    .collect(Collectors.toList());
            equipments.forEach(e -> list.add((root, cq, cb) -> cb.isMember(e,
                    root.get(Room_.equipments))));
        }
    }

    /**
     * Add sorting order to predicate if filter contains sortByField and
     * sortOrder parameters.
     * 
     * @param root
     *            a root of predicate
     * 
     * @param query
     *            a query to add sorting order.
     * 
     * @param cb
     *            a criteria builder of predicate.
     */
    private void setSortingParameters(final Root<Room> root,
            final CriteriaQuery<?> query, final CriteriaBuilder cb) {
        if (filter.getSortOrder() == 1) {
            if (filter.getSortByField() == 1) {
                query.orderBy(cb.asc(root.get(Room_.location)));
            }
            if (filter.getSortByField() == 2) {
                query.orderBy(cb.asc(root.get(Room_.name)));
            }
            if (filter.getSortByField() == 3) {
                query.orderBy(cb.asc(root.get(Room_.capacity)));
            }
        } else if (filter.getSortOrder() == 2) {
            if (filter.getSortByField() == 1) {
                query.orderBy(cb.desc(root.get(Room_.location)));
            }
            if (filter.getSortByField() == 2) {
                query.orderBy(cb.desc(root.get(Room_.name)));
            }
            if (filter.getSortByField() == 3) {
                query.orderBy(cb.desc(root.get(Room_.capacity)));
            }
        } else {
            query.orderBy(cb.asc(root.get(Room_.id)));
        }
    }

    /**
     * Build predicate based on given RoomFilter parameters.
     * 
     * @param root
     *            a root of predicate
     * 
     * @param query
     *            a query of predicate.
     * 
     * @param cb
     *            a criteria builder of predicate.
     * 
     * @return a Predicate example to search rooms in database by given filter
     *         parameters.
     */
    @Override
    public Predicate toPredicate(Root<Room> root, CriteriaQuery<?> query,
            CriteriaBuilder cb) {
        if (query.getResultType() != Long.class
                && query.getResultType() != long.class) {
            root.fetch(Room_.location, JoinType.LEFT);
            root.fetch(Room_.equipments, JoinType.LEFT);
            setSortingParameters(root, query, cb);
        }
        if (filter != null) {
            findByLocationId();
            findByName();
            findByMaxMinCapacity();
            findByEquipmentIds();
        }
        if (list.size() == 0)
            return null;
        Specifications<Room> spec = Specifications.where(list.get(0));
        for (int i = 1; i < list.size(); i++) {
            spec = spec.and(list.get(i));
        }
        return spec.toPredicate(root, query, cb);
    }
}
