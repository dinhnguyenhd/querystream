
/*
 * Copyright (C) 2018 Archie L. Cobbs. All rights reserved.
 */

package org.dellroad.querystream.jpa;

import java.util.function.Function;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.From;
import javax.persistence.criteria.JoinType;
import javax.persistence.metamodel.SingularAttribute;

import org.dellroad.querystream.jpa.querytype.SearchType;

/**
 * A {@link FromStream} that is guaranteed to return at most a single result.
 */
public interface FromValue<X, S extends From<?, X>> extends PathValue<X, S>, FromStream<X, S> {

// Narrowing overrides (FromStream)

    @Override
    default <Y> FromValue<Y, From<X, Y>> join(SingularAttribute<? super X, Y> attribute) {
        return this.join(attribute, JoinType.INNER);
    }

    @Override
    default <Y> FromValue<Y, From<X, Y>> join(SingularAttribute<? super X, Y> attribute, JoinType joinType) {
        if (attribute == null)
            throw new IllegalArgumentException("null attribute");
        if (!attribute.isAssociation())
            throw new IllegalArgumentException("attribute is not an association: " + attribute);
        if (joinType == null)
            throw new IllegalArgumentException("null joinType");
        return new FromValueImpl<>(this.getEntityManager(), new SearchType<>(attribute.getJavaType()),
           (builder, query) -> this.configure(builder, query).join(attribute, joinType));
    }

// Narrowing overrides (QueryStream)

    @Override
    FromValue<X, S> bind(Ref<X, ? super S> ref);

    @Override
    FromValue<X, S> filter(SingularAttribute<? super X, Boolean> attribute);

    @Override
    FromValue<X, S> filter(Function<? super S, ? extends Expression<Boolean>> predicateBuilder);
}
