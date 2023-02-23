package com.gamerduck.commons.consumers;

import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * Represents an operation that accepts three input arguments and returns no result. This is the three-arity specialization of Consumer. Unlike most other functional interfaces, TruiConsumer is expected to operate via side-effects.
 * This is a functional interface whose functional method is accept(Object, Object).
 * @see Consumer
 * @see BiConsumer
 * @see TriConsumer
 * @see QuintConsumer
 *
 * @param <T> – the type of the first argument to the operation
 * @param <U> – the type of the second argument to the operation
 * @param <I> – the type of the third argument to the operation
 */
@FunctionalInterface
public interface TriConsumer<T, U, I> {

    /**
     * Performs this operation on the given arguments.
     *
     * @param t the first input argument
     * @param u the second input argument
     * @param i the third input argument
     */
    void accept(T t, U u, I i);

    /**
     * Returns a composed {@code TriConsumer} that performs, in sequence, this
     * operation followed by the {@code after} operation. If performing either
     * operation throws an exception, it is relayed to the caller of the
     * composed operation.  If performing this operation throws an exception,
     * the {@code after} operation will not be performed.
     *
     * @param after the operation to perform after this operation
     * @return a composed {@code TriConsumer} that performs in sequence this
     * operation followed by the {@code after} operation
     * @throws NullPointerException if {@code after} is null
     */
    default TriConsumer<T, U, I> andThen(TriConsumer<? super T, ? super U, ? super I> after) {
        Objects.requireNonNull(after);

        return (l, r, e) -> {
            accept(l, r, e);
            after.accept(l, r, e);
        };
    }
}
