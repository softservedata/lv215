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

public class RoomFilterSpecification implements Specification<Room> {

    private RoomFilter filter;

    private RoomEquipmentDAO roomEquipmentDAO;

    private List<Specification<Room>> list = new ArrayList<>();

    public RoomFilterSpecification(RoomFilter filter,
            RoomEquipmentDAO roomEquipmentDAO) {
        this.filter = filter;
        this.roomEquipmentDAO = roomEquipmentDAO;
    }

    private void findByLocationId() {
        if (filter.getLocationId() > 0) {
            list.add((root, cq, cb) -> root.get(Room_.location)
                    .in(filter.getLocationId()));
        }
    }

    private void findByName() {
        if (filter.getName() != null && !filter.getName().equals("")) {
            list.add((root, cq, cb) -> cb.like(cb.lower(root.get(Room_.name)),
                    "%" + filter.getName().toLowerCase() + "%"));
        }
    }

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

    private void setSortingParameters(Root<Room> root, CriteriaQuery<?> query,
            CriteriaBuilder cb) {
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
