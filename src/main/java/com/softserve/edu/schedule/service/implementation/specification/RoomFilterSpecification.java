/* RoomFilterSpecification 1.0 01/17/2017 */
package com.softserve.edu.schedule.service.implementation.specification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import com.softserve.edu.schedule.dao.RoomEquipmentDAO;
import com.softserve.edu.schedule.dto.filter.RoomFilter;
import com.softserve.edu.schedule.entity.Location;
import com.softserve.edu.schedule.entity.Location_;
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
     * Sort field location.
     */
    private static final int SORT_BY_LOCATION = 1;

    /**
     * Sort field room name.
     */
    private static final int SORT_BY_NAME = 2;

    /**
     * Sort field room capacity.
     */
    private static final int SORT_BY_CAPACITY = 3;

    /**
     * Sort order ascending.
     */
    private static final int SORT_ASC = 1;

    /**
     * Sort order descending.
     */
    private static final int SORT_DESC = 2;

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
     * Location join field for sorting purposes.
     */
    private Join<Room, Location> locationJoin;

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
            list.add((root, criteriaQuery, criteriaBuilder) -> root
                    .get(Room_.location).in(filter.getLocationId()));
        }
    }

    /**
     * Add room name specification to specification list if filter contains room
     * name parameter.
     */
    private void findByName() {
        if (filter.getName() != null && !filter.getName().equals("")) {
            list.add((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder
                    .like(root.get(Room_.name), "%" + filter.getName() + "%"));
        }
    }

    /**
     * Add room capacity specification to specification list if filter contains
     * maxCapacity and(or) minCapacity parameters.
     */
    private void findByMaxMinCapacity() {
        if (filter.getMaxCapacity() > 0 && filter.getMinCapacity() > 0) {
            list.add((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder
                    .between(root.get(Room_.capacity), filter.getMinCapacity(),
                            filter.getMaxCapacity()));
        } else if (filter.getMaxCapacity() > 0) {
            list.add((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder
                    .lessThan(root.get(Room_.capacity),
                            filter.getMaxCapacity()));
        } else if (filter.getMinCapacity() > 0) {
            list.add((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder
                    .greaterThan(root.get(Room_.capacity),
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
            equipments.forEach(e -> list.add(
                    (root, criteriQuery, criteriaBuilder) -> criteriaBuilder
                            .isMember(e, root.get(Room_.equipments))));
        }
    }

    /**
     * Add sorting order to predicate if filter contains sortByField and
     * sortOrder parameters.
     *
     * @param root
     *            a root of predicate
     *
     * @param criteriaQuery
     *            a query to add sorting order.
     *
     * @param criteriaBuilder
     *            a criteria builder of predicate.
     */
    private void setSortingParameters(final Root<Room> root,
            final CriteriaQuery<?> criteriaQuery,
            final CriteriaBuilder criteriaBuilder) {
        if (filter.getSortOrder() == SORT_ASC) {
            if (filter.getSortByField() == SORT_BY_LOCATION) {
                criteriaQuery.orderBy(
                        criteriaBuilder.asc(locationJoin.get(Location_.name)));
            }
            if (filter.getSortByField() == SORT_BY_NAME) {
                criteriaQuery
                        .orderBy(criteriaBuilder.asc(root.get(Room_.name)));
            }
            if (filter.getSortByField() == SORT_BY_CAPACITY) {
                criteriaQuery
                        .orderBy(criteriaBuilder.asc(root.get(Room_.capacity)));
            }
        } else if (filter.getSortOrder() == SORT_DESC) {
            if (filter.getSortByField() == SORT_BY_LOCATION) {
                criteriaQuery.orderBy(
                        criteriaBuilder.desc(locationJoin.get(Location_.name)));
            }
            if (filter.getSortByField() == SORT_BY_NAME) {
                criteriaQuery
                        .orderBy(criteriaBuilder.desc(root.get(Room_.name)));
            }
            if (filter.getSortByField() == SORT_BY_CAPACITY) {
                criteriaQuery.orderBy(
                        criteriaBuilder.desc(root.get(Room_.capacity)));
            }
        } else {
            criteriaQuery.orderBy(criteriaBuilder.asc(root.get(Room_.id)));
        }
    }

    /**
     * Build predicate based on given RoomFilter parameters.
     *
     * @param root
     *            a root of predicate
     *
     * @param criteriaQuery
     *            a query of predicate.
     *
     * @param criteriaBuilder
     *            a criteria builder of predicate.
     *
     * @return a Predicate example to search rooms in database by given filter
     *         parameters.
     */
    @Override
    public Predicate toPredicate(final Root<Room> root,
            final CriteriaQuery<?> criteriaQuery,
            final CriteriaBuilder criteriaBuilder) {
        locationJoin = root.join(Room_.location, JoinType.LEFT);
        root.join(Room_.equipments, JoinType.LEFT);
        setSortingParameters(root, criteriaQuery, criteriaBuilder);
        if (filter != null) {
            findByLocationId();
            findByName();
            findByMaxMinCapacity();
            findByEquipmentIds();
        }
        if (list.size() == 0) {
            return null;
        }
        Specifications<Room> spec = Specifications.where(list.get(0));
        for (int i = 1; i < list.size(); i++) {
            spec = spec.and(list.get(i));
        }
        return spec.toPredicate(root, criteriaQuery, criteriaBuilder);
    }
}
