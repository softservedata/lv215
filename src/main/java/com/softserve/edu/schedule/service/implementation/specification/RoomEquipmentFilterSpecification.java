/* RoomEquipmentFilterSpecification 1.0 01/17/2017 */
package com.softserve.edu.schedule.service.implementation.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import com.softserve.edu.schedule.dto.filter.RoomEquipmentFilter;
import com.softserve.edu.schedule.entity.RoomEquipment;
import com.softserve.edu.schedule.entity.RoomEquipment_;

/**
 * A class to provide predicate based on given RoomEquipmentFilter example. This
 * predicate is used to search room equipments in database by given filter
 * parameters.
 *
 * @version 1.0 17 January 2017
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
public class RoomEquipmentFilterSpecification
        implements Specification<RoomEquipment> {

    /**
     * Sort field room equipment name.
     */
    private static final int SORT_BY_NAME = 1;

    /**
     * Sort order ascending.
     */
    private static final int SORT_ASC = 1;

    /**
     * Sort order descending.
     */
    private static final int SORT_DESC = 2;

    /**
     * RoomEquipmentFilter example which provides parameters to build predicate.
     */
    private RoomEquipmentFilter filter;

    /**
     * List of room equipments specifications based on which the predicate is
     * built.
     */
    private List<Specification<RoomEquipment>> list = new ArrayList<>();

    /**
     * Constructor of RoomFilterSpecification.
     *
     * @param filter
     *            RoomEquipmentFilter example which provides parameters to build
     *            predicate.
     */
    public RoomEquipmentFilterSpecification(final RoomEquipmentFilter filter) {
        this.filter = filter;
    }

    /**
     * Add room equipment name specification to specification list if filter
     * contains room equipment name parameter.
     */
    private void findByName() {
        if (filter.getName() != null && !filter.getName().equals("")) {
            list.add((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder
                    .like(root.get(RoomEquipment_.name),
                            "%" + filter.getName() + "%"));
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
    private void setSortingParameters(final Root<RoomEquipment> root,
            final CriteriaQuery<?> criteriaQuery,
            final CriteriaBuilder criteriaBuilder) {
        if (filter.getSortOrder() == SORT_ASC) {
            if (filter.getSortByField() == SORT_BY_NAME) {
                criteriaQuery.orderBy(
                        criteriaBuilder.asc(root.get(RoomEquipment_.name)));
            }
        } else if (filter.getSortOrder() == SORT_DESC) {
            if (filter.getSortByField() == SORT_BY_NAME) {
                criteriaQuery.orderBy(
                        criteriaBuilder.desc(root.get(RoomEquipment_.name)));
            }
        } else {
            criteriaQuery
                    .orderBy(criteriaBuilder.desc(root.get(RoomEquipment_.id)));
        }
    }

    /**
     * Build predicate based on given RoomEquipmentFilter parameters.
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
    public Predicate toPredicate(final Root<RoomEquipment> root,
            final CriteriaQuery<?> criteriaQuery,
            final CriteriaBuilder criteriaBuilder) {
        setSortingParameters(root, criteriaQuery, criteriaBuilder);
        if (filter != null) {
            findByName();
        }
        if (list.size() == 0) {
            return null;
        }
        Specifications<RoomEquipment> spec = Specifications.where(list.get(0));
        for (int i = 1; i < list.size(); i++) {
            spec = spec.and(list.get(i));
        }
        return spec.toPredicate(root, criteriaQuery, criteriaBuilder);
    }
}
