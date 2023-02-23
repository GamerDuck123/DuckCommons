package com.gamerduck.commons.consumers;

import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * Represents an operation that accepts four input arguments and returns no result. This is the four-arity specialization of Consumer. Unlike most other functional interfaces, QuadConsumer is expected to operate via side-effects.
 * This is a functional interface whose functional method is accept(Object, Object).
 * @see Consumer
 * @see BiConsumer
 * @see TriConsumer
 * @see QuintConsumer
 *
 *
 * @param <T> – the type of the first argument to the operation
 * @param <U> – the type of the second argument to the operation
 * @param <I> – the type of the third argument to the operation
 * @param <Z> – the type of the fourth argument to the operation
 */
@FunctionalInterface
public interface QuadConsumer<T, U, I, Z> {

    /**
     * Performs this operation on the given arguments.
     *
     * @param t the first input argument
     * @param u the second input argument
     * @param i the third input argument
     * @param z the fourth input argument
     */
    void accept(T t, U u, I i, Z z);

    /**
     * Returns a composed {@code QuadConsumer} that performs, in sequence, this
     * operation followed by the {@code after} operation. If performing either
     * operation throws an exception, it is relayed to the caller of the
     * composed operation.  If performing this operation throws an exception,
     * the {@code after} operation will not be performed.
     *
     * @param after the operation to perform after this operation
     * @return a composed {@code QuadConsumer} that performs in sequence this
     * operation followed by the {@code after} operation
     * @throws NullPointerException if {@code after} is null
     */
    default QuadConsumer<T, U, I, Z> andThen(QuadConsumer<? super T, ? super U, ? super I, ? super Z> after) {
        Objects.requireNonNull(after);

        return (l, r, e, f) -> {
            accept(l, r, e, f);
            after.accept(l, r, e, f);
        };
    }
}
