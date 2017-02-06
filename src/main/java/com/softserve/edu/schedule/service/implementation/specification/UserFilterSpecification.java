package com.softserve.edu.schedule.service.implementation.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import com.softserve.edu.schedule.dto.filter.UserFilter;
import com.softserve.edu.schedule.entity.User;
import com.softserve.edu.schedule.entity.User_;

public class UserFilterSpecification implements Specification<User> {

    UserFilter filter;

    private List<Specification<User>> list = new ArrayList<>();

    public UserFilterSpecification(final UserFilter filter) {
        this.filter = filter;
    }

    private void findByLastName() {
        if (filter.getLastName() != null
                && !filter.getLastName().trim().equals("")) {
            list.add((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder
                    .like(root.get(User_.lastName),
                            "%" + filter.getLastName() + "%"));
        }
    }

    private void findByPosition() {
        if (filter.getPosition() != null
                && !filter.getPosition().trim().equals("")) {
            list.add((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder
                    .like(root.get(User_.position),
                            "%" + filter.getPosition() + "%"));
        }
    }

    private void setSortingParameters(final Root<User> root,
            final CriteriaQuery<?> criteriaQuery,
            final CriteriaBuilder criteriaBuilder) {
        if (filter.getSortOrder() == 1) {
            if (filter.getSortByField() == 1) {
                criteriaQuery
                        .orderBy(criteriaBuilder.asc(root.get(User_.lastName)));
            }
            if (filter.getSortByField() == 2) {
                criteriaQuery
                        .orderBy(criteriaBuilder.asc(root.get(User_.position)));
            }
        } else if (filter.getSortOrder() == 2) {
            if (filter.getSortByField() == 1) {
                criteriaQuery
                        .orderBy(criteriaBuilder.desc(root.get(User_.lastName)));
            }
            if (filter.getSortByField() == 2) {
                criteriaQuery
                        .orderBy(criteriaBuilder.desc(root.get(User_.position)));
            }
        } else {
            criteriaQuery.orderBy(criteriaBuilder.asc(root.get(User_.lastName)));
        }
    }

    @Override
    public Predicate toPredicate(Root<User> root,
            CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        setSortingParameters(root, criteriaQuery, criteriaBuilder);
        if (filter != null) {
            findByLastName();
            findByPosition();
        }
        if (list.size() == 0) {
            return null;
        }
        Specifications<User> spec = Specifications.where(list.get(0));
        for (int i = 1; i < list.size(); i++) {
            spec = spec.and(list.get(i));
        }
        return spec.toPredicate(root, criteriaQuery, criteriaBuilder);
    }
}
