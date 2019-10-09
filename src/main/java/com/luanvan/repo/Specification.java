package com.luanvan.repo;

import java.util.function.Predicate;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public interface Specification<T> {
    Predicate toPredicate(Root<T> root, CriteriaQuery query, CriteriaBuilder cb);
}