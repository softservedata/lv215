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

    public RoomFilterSpecification(RoomFilter filter) {
        this.filter = filter;
    }

    private void findByLocationId() {
        if (filter.getLocationId() > 0) {
            list.add((root, cq, cb) -> root.get(Room_.location)
                    .in(filter.getLocationId()));
        }
    }

    private void findByName() {
        if (filter.getName() != null) {
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
        if (filter.getEquipmentIds() != null
                && !filter.getEquipmentIds().isEmpty()) {
            List<RoomEquipment> equipments = filter.getEquipmentIds().stream()
                    .map(e -> roomEquipmentDAO
                            .getById(Long.parseLong(String.valueOf(e))))
                    .collect(Collectors.toList());
            equipments.forEach(e -> list.add((root, cq, cb) -> cb.isMember(e,
                    root.get(Room_.equipments))));
        }
    }

    @Override
    public Predicate toPredicate(Root<Room> root, CriteriaQuery<?> query,
            CriteriaBuilder cb) {
        if (query.getResultType() != Long.class
                && query.getResultType() != long.class) {
            root.fetch(Room_.location, JoinType.LEFT);
            root.fetch(Room_.equipments, JoinType.LEFT);
            query.orderBy(cb.asc(root.get(Room_.id)));
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
