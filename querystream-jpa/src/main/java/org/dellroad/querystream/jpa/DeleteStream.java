
/*
 * Copyright (C) 2018 Archie L. Cobbs. All rights reserved.
 */

package org.dellroad.querystream.jpa;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

import javax.persistence.FlushModeType;
import javax.persistence.LockModeType;
import javax.persistence.Parameter;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

import org.dellroad.querystream.jpa.querytype.DeleteType;

/**
 * Builder for JPA criteria bulk delete queries using a {@link java.util.stream.Stream}-like API.
 */
public interface DeleteStream<X> extends QueryStream<X, Root<X>, CriteriaDelete<X>, CriteriaDelete<X>, Query> {

    /**
     * Build and execute a JPA query based on this instance.
     *
     * <p>
     * Ultimately delegates to {@link Query#executeUpdate} and can throw any exception thrown by that method.
     *
     * @return the number of entities deleted
     */
    int delete();

// Narrowing overrides

    @Override
    DeleteType<X> getQueryType();

    @Override
    DeleteStream<X> bind(Ref<X, ? super Root<X>> ref);

    @Override
    DeleteStream<X> peek(Consumer<? super Root<X>> peeker);

    @Override
    DeleteStream<X> filter(SingularAttribute<? super X, Boolean> attribute);

    @Override
    DeleteStream<X> filter(Function<? super Root<X>, ? extends Expression<Boolean>> predicateBuilder);

    @Override
    DeleteStream<X> limit(int maxSize);

    @Override
    DeleteStream<X> skip(int num);

    @Override
    DeleteStream<X> withFlushMode(FlushModeType flushMode);

    @Override
    DeleteStream<X> withLockMode(LockModeType lockMode);

    @Override
    DeleteStream<X> withHint(String name, Object value);

    @Override
    DeleteStream<X> withHints(Map<String, Object> hints);

    @Override
    <T> DeleteStream<X> withParam(Parameter<T> parameter, T value);

    @Override
    DeleteStream<X> withParam(Parameter<Date> parameter, Date value, TemporalType temporalType);

    @Override
    DeleteStream<X> withParam(Parameter<Calendar> parameter, Calendar value, TemporalType temporalType);

    @Override
    DeleteStream<X> withParams(Iterable<? extends ParamBinding<?>> params);

    @Override
    DeleteStream<X> withLoadGraph(String name);

    @Override
    DeleteStream<X> withFetchGraph(String name);
}
