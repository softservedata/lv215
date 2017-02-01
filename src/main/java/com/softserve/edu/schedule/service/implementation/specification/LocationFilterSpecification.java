package com.softserve.edu.schedule.service.implementation.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import com.softserve.edu.schedule.dto.filter.LocationFilter;
import com.softserve.edu.schedule.entity.Location;
import com.softserve.edu.schedule.entity.Location_;

public class LocationFilterSpecification implements Specification<Location> {

	private LocationFilter filter;

	private List<Specification<Location>> list = new ArrayList<>();

	public LocationFilterSpecification(final LocationFilter filter) {
		this.filter = filter;
	}

	private void findByName() {
		if (filter.getName() != null && !filter.getName().trim().equals("")) {
			list.add((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get(Location_.name),
					"%" + filter.getName() + "%"));
		}
	}

	private void findByAddress() {
		if (filter.getAddress() != null && !filter.getAddress().trim().equals("")) {
			list.add((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get(Location_.address),
					"%" + filter.getAddress() + "%"));
		}
	}

	private void setSortingParameters(final Root<Location> root, final CriteriaQuery<?> criteriaQuery,
			final CriteriaBuilder criteriaBuilder) {
		if (filter.getSortOrder() == 1) {
			if (filter.getSortByField() == 1) {
				criteriaQuery.orderBy(criteriaBuilder.asc(root.get(Location_.name)));
			}
			if (filter.getSortByField() == 2) {
				criteriaQuery.orderBy(criteriaBuilder.asc(root.get(Location_.address)));
			}
		} else if (filter.getSortOrder() == 2) {
			if (filter.getSortByField() == 1) {
				criteriaQuery.orderBy(criteriaBuilder.desc(root.get(Location_.name)));
			}
			if (filter.getSortByField() == 2) {
				criteriaQuery.orderBy(criteriaBuilder.desc(root.get(Location_.address)));
			}
		} else {
			criteriaQuery.orderBy(criteriaBuilder.asc(root.get(Location_.id)));
		}
	}

	@Override
	public Predicate toPredicate(Root<Location> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
		setSortingParameters(root, criteriaQuery, criteriaBuilder);
		if (filter != null){
			findByName();
			findByAddress();
		}
		if (list.size() == 0){
		return null;}
		Specifications<Location> spec = Specifications.where(list.get(0));
		for (int i=1; i<list.size(); i++){
			spec = spec.and(list.get(i));
		}
		return spec.toPredicate(root, criteriaQuery, criteriaBuilder);
	}

	
	
}
