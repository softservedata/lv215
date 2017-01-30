package com.softserve.edu.schedule.service.implementation.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import com.softserve.edu.schedule.dao.UserDAO;
import com.softserve.edu.schedule.dto.filter.SubjectFilter;
import com.softserve.edu.schedule.entity.Subject;
import com.softserve.edu.schedule.entity.Subject_;
import com.softserve.edu.schedule.entity.User;

public class SubjectFilterSpecification implements Specification<Subject> {

    private SubjectFilter filter;

    private UserDAO userDao;

    private List<Specification<Subject>> list = new ArrayList<>();

    public SubjectFilterSpecification(final SubjectFilter filter,
            final UserDAO userDAO) {
        this.filter = filter;
        this.userDao = userDAO;
    }

    private void findByName() {
        if (filter.getName() != null && !filter.getName().trim().equals("")) {
            list.add((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder
                    .like(root.get(Subject_.name),
                            "%" + filter.getName() + "%"));
        }
    }

    private void findByDescription() {
        if (filter.getDescription() != null
                && !filter.getDescription().trim().equals("")) {
            list.add((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder
                    .like(root.get(Subject_.description),
                            "%" + filter.getDescription() + "%"));
        }
    }

    private void findByUserId() {
        if (filter.getUserId() != null &&filter.getUserId() > 0) {
            User user = userDao.getById(filter.getUserId());
            list.add((root, criteriQuery, criteriaBuilder) -> criteriaBuilder
                    .isMember(user, root.get(Subject_.users)));
        }
    }

    private void setSortingParameters(final Root<Subject> root,
            final CriteriaQuery<?> criteriaQuery,
            final CriteriaBuilder criteriaBuilder) {
        if (filter.getSortOrder() == 1) {
            if (filter.getSortByField() == 1) {
                criteriaQuery
                        .orderBy(criteriaBuilder.asc(root.get(Subject_.name)));
            }
            if (filter.getSortByField() == 2) {
                criteriaQuery.orderBy(
                        criteriaBuilder.asc(root.get(Subject_.description)));
            }

        } else if (filter.getSortOrder() == 2) {
            if (filter.getSortByField() == 1) {
                criteriaQuery
                        .orderBy(criteriaBuilder.desc(root.get(Subject_.name)));
            }
            if (filter.getSortByField() == 2) {
                criteriaQuery.orderBy(
                        criteriaBuilder.desc(root.get(Subject_.description)));
            }
        } else {
            criteriaQuery.orderBy(criteriaBuilder.asc(root.get(Subject_.id)));
        }
    }

    @Override
    public Predicate toPredicate(Root<Subject> root,
            CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        root.join(Subject_.users, JoinType.LEFT);
        setSortingParameters(root, criteriaQuery, criteriaBuilder);
        if (filter != null) {
            findByName();
            findByDescription();
            findByUserId();
        }
        if (list.size() == 0) {
            return null;
        }
        Specifications<Subject> spec = Specifications.where(list.get(0));
        for (int i = 1; i < list.size(); i++) {
            spec = spec.and(list.get(i));
        }
        return spec.toPredicate(root, criteriaQuery, criteriaBuilder);
    }

}
