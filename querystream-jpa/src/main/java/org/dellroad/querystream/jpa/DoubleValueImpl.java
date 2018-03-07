
/*
 * Copyright (C) 2018 Archie L. Cobbs. All rights reserved.
 */

package org.dellroad.querystream.jpa;

import java.util.function.Function;

import javax.persistence.EntityManager;
import javax.persistence.criteria.AbstractQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.metamodel.SingularAttribute;

import org.dellroad.querystream.jpa.querytype.SearchType;

class DoubleValueImpl extends DoubleStreamImpl implements DoubleValue {

// Constructors

    DoubleValueImpl(EntityManager entityManager,
      QueryConfigurer<AbstractQuery<?>, Double, ? extends Expression<Double>> configurer) {
        super(entityManager, configurer);
    }

// Subclass required methods

    @Override
    DoubleValue create(EntityManager entityManager, SearchType<Double> queryType,
      QueryConfigurer<AbstractQuery<?>, Double, ? extends Expression<Double>> configurer) {
        return new DoubleValueImpl(entityManager, configurer);
    }

// Narrowing overrides (QueryStream)

    @Override
    public DoubleValue bind(Ref<Double, ? super Expression<Double>> ref) {
        return (DoubleValue)super.bind(ref);
    }

    @Override
    public DoubleValue filter(SingularAttribute<? super Double, Boolean> attribute) {       // makes no sense but needed for API
        return (DoubleValue)super.filter(attribute);
    }

    @Override
    public DoubleValue filter(Function<? super Expression<Double>, ? extends Expression<Boolean>> predicateBuilder) {
        return (DoubleValue)super.filter(predicateBuilder);
    }
}
