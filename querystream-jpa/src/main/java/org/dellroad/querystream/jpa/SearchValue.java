
/*
 * Copyright (C) 2018 Archie L. Cobbs. All rights reserved.
 */

package org.dellroad.querystream.jpa;

import java.util.function.Function;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Selection;
import javax.persistence.metamodel.SingularAttribute;

/**
 * A {@link SearchStream} that is guaranteed to return at most a single result.
 */
public interface SearchValue<X, S extends Selection<X>> extends SearchStream<X, S> {

    /**
     * Build and evaluate a JPA query based on this instance and return the single result, if any.
     *
     * @return result of executed query
     * @throws javax.persistence.NoResultException if there is no result
     */
    default X value() {
        return this.toQuery().getSingleResult();
    }

// Narrowing overrides (SearchStream)

    @Override
    default <Y> SearchValue<Y, Selection<Y>> mapToSelection(Class<Y> type,
      Function<? super S, ? extends Selection<Y>> selectionFunction) {
        return ((SearchStreamImpl<Y, Selection<Y>>)SearchStream.super.mapToSelection(type, selectionFunction)).toValue();
    }

// Narrowing overrides (QueryStream)

    @Override
    SearchValue<X, S> bind(Ref<X, ? super S> ref);

    @Override
    SearchValue<X, S> filter(SingularAttribute<? super X, Boolean> attribute);

    @Override
    SearchValue<X, S> filter(Function<? super S, ? extends Expression<Boolean>> predicateBuilder);
}
