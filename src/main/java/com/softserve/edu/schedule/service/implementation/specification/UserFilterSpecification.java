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
    
    /**
     * Constant for search method.
     */
    public static final String SEARCH_MASK = "%";

    /**
     * Constant for search method.
     */
    public static final String SEARCH_EMPTY = "";
    
    /**
     * Constant for sort method.
     */
    public static final int ONE = 1;

    /**
     * Constant for sort method.
     */
    public static final int TWO = 2;

    /**
     * UserFilter example which provides parameters to build predicate.
     */
    UserFilter filter;

    /**
     * List of user specifications based on which the predicate is built.
     */
    private List<Specification<User>> list = new ArrayList<>();

    /**
     * Constructor of UserFilterSpecification.
     * 
     * @param filter
     *            UserFilter example which provides parameters to build
     *            predicate.
     */
    public UserFilterSpecification(final UserFilter filter) {
        this.filter = filter;
    }

    /**
     * Add user last name specification to specification list if filter contains
     * user lastName parameter.
     */
    private void findByLastName() {
        if (filter.getLastName() != null
                && !filter.getLastName().trim().equals(SEARCH_EMPTY)) {
            list.add((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder
                    .like(root.get(User_.lastName),
                            SEARCH_MASK + filter.getLastName() + SEARCH_MASK));
        }
    }

    /**
     * Add user position specification to specification list if filter contains
     * user position parameter.
     */
    private void findByPosition() {
        if (filter.getPosition() != null
                && !filter.getPosition().trim().equals(SEARCH_EMPTY)) {
            list.add((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder
                    .like(root.get(User_.position),
                            SEARCH_MASK + filter.getPosition() + SEARCH_MASK));
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
    private void setSortingParameters(final Root<User> root,
            final CriteriaQuery<?> criteriaQuery,
            final CriteriaBuilder criteriaBuilder) {
        if (filter.getSortOrder() == ONE) {
            if (filter.getSortByField() == ONE) {
                criteriaQuery
                        .orderBy(criteriaBuilder.asc(root.get(User_.lastName)));
            }
            if (filter.getSortByField() == TWO) {
                criteriaQuery
                        .orderBy(criteriaBuilder.asc(root.get(User_.position)));
            }
        } else if (filter.getSortOrder() == TWO) {
            if (filter.getSortByField() == ONE) {
                criteriaQuery
                        .orderBy(criteriaBuilder.desc(root.get(User_.lastName)));
            }
            if (filter.getSortByField() == TWO) {
                criteriaQuery
                        .orderBy(criteriaBuilder.desc(root.get(User_.position)));
            }
        } else {
            criteriaQuery.orderBy(criteriaBuilder.asc(root.get(User_.lastName)));
        }
    }

    /**
     * Build predicate based on given UserFilter parameters.
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
     * @return a Predicate example to search subjects in database by given
     *         filter parameters.
     */
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
        for (int i = ONE; i < list.size(); i++) {
            spec = spec.and(list.get(i));
        }
        return spec.toPredicate(root, criteriaQuery, criteriaBuilder);
    }
}
