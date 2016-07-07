package com.mrsnottypants.test;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Common pattern for testing if a method throws the expected exception
 *
 * Created by Eric on 7/3/2016.
 */
public class Exceptions {

    /**
     * Returns true if calling consumer on t will result in an exception of type e
     * @param consumer Method we expect to throw an exception
     * @param t Parameter passed to the consumer
     * @param e Class of the exception we expect
     * @param <T> Type of the parameter passed to the consumer
     * @param <E> Type of the exception expected
     * @return true if expected exception is thrown
     */
    public static <T, E extends Throwable> boolean isExpected(final Consumer<T> consumer, final T t, final Class<E> e) {
        boolean expected = false;
        try {
            consumer.accept(t);
        } catch (Throwable ex) {
            expected = (ex.getClass().equals(e));
        }
        return expected;
    }

    /**
     * Returns true if calling consumer on t will result in an IllegalArgumentException
     * @param consumer Method we expect to throw an exception
     * @param t Parameter passed to the consumer
     * @param <T> Type of the parameter passed to the consumer
     * @return true if expected exception is thrown
     */
    public static <T> boolean isIllegalArgument(final Consumer<T> consumer, final T t) {
        return isExpected(consumer, t, IllegalArgumentException.class);
    }

    /**
     * Returns true if calling consumer on t will result in an IllegalStateException
     * @param consumer Method we expect to throw an exception
     * @param t Parameter passed to the consumer
     * @param <T> Type of the parameter passed to the consumer
     * @return true if expected exception is thrown
     */
    public static <T> boolean isIllegalState(final Consumer<T> consumer, final T t) {
        return isExpected(consumer, t, IllegalStateException.class);
    }

    /**
     * Returns true if calling consumer on t will result in an IndexOutOfBoundsException
     * @param consumer Method we expect to throw an exception
     * @param t Parameter passed to the consumer
     * @param <T> Type of the parameter passed to the consumer
     * @return true if expected exception is thrown
     */
    public static <T> boolean isOutOfBounds(final Consumer<T> consumer, final T t) {
        return isExpected(consumer, t, IndexOutOfBoundsException.class);
    }

    /**
     * Returns true if calling bi-consumer on t will result in an exception of type e
     * @param consumer Method we expect to throw an exception
     * @param t 1st parameter passed to the consumer
     * @param u 2nd parameter passed to the consumer
     * @param e Class of the exception we expect
     * @param <T> Type of the 1st parameter passed to the consumer
     * @param <U> Type of the 2nd parameter passed to the consumer
     * @param <E> Type of the exception expected
     * @return true if expected exception is thrown
     */
    public static <T, U, E extends Throwable> boolean isExpected(final BiConsumer<T, U> consumer,
                                                                 final T t, final U u, final Class<E> e) {
        boolean expected = false;
        try {
            consumer.accept(t, u);
        } catch (Throwable ex) {
            expected = (ex.getClass().equals(e));
        }
        return expected;
    }

    /**
     * Returns true if calling bi-consumer on t and u will result in an IllegalArgumentException
     * @param consumer Method we expect to throw an exception
     * @param t 1st parameter passed to the consumer
     * @param u 2nd parameter passed to the consumer
     * @param <T> Type of the 1st parameter passed to the consumer
     * @param <U> Type of the 2nd parameter passed to the consumer
     * @return true if expected exception is thrown
     */
    public static <T, U> boolean isIllegalArgument(final BiConsumer<T, U> consumer, final T t, final U u) {
        return isExpected(consumer, t, u, IllegalArgumentException.class);
    }

    /**
     * Returns true if calling bi-consumer on t and u will result in an IllegalStateException
     * @param consumer Method we expect to throw an exception
     * @param t 1st parameter passed to the consumer
     * @param u 2nd parameter passed to the consumer
     * @param <T> Type of the 1st parameter passed to the consumer
     * @param <U> Type of the 2nd parameter passed to the consumer
     * @return true if expected exception is thrown
     */
    public static <T, U> boolean isIllegalState(final BiConsumer<T, U> consumer, final T t, final U u) {
        return isExpected(consumer, t, u, IllegalStateException.class);
    }

    /**
     * Returns true if calling bi-consumer on t and u will result in an IndexOutOfBoundsException
     * @param consumer Method we expect to throw an exception
     * @param t 1st parameter passed to the consumer
     * @param u 2nd parameter passed to the consumer
     * @param <T> Type of the 1st parameter passed to the consumer
     * @param <U> Type of the 2nd parameter passed to the consumer
     */
    public static <T, U> boolean isOutOfBounds(final BiConsumer<T, U> consumer, final T t, final U u) {
        return isExpected(consumer, t, u, IndexOutOfBoundsException.class);
    }

    /**
     * Returns true if calling supplier will result in an exception of type e
     * @param supplier Method we expect to throw an exception
     * @param e Class of the exception we expect
     * @param <T> Type of the parameter passed to the consumer
     * @param <E> Type of the exception expected
     * @return true if expected exception is thrown
     */
    public static <T, E extends Throwable> boolean isExpected(final Supplier<T> supplier, final Class<E> e) {
        boolean expected = false;
        try {
            supplier.get();
        } catch (Throwable ex) {
            expected = (ex.getClass().equals(e));
        }
        return expected;
    }

    /**
     * Returns true if calling supplier will result in an IllegalStateException
     * @param consumer Method we expect to throw an exception
     * @param <T> Type of the parameter passed to the consumer
     * @return true if expected exception is thrown
     */
    public static <T> boolean isIllegalState(final Supplier<T> consumer) {
        return isExpected(consumer, IllegalStateException.class);
    }

    /**
     * Returns true if calling supplier will result in an IndexOutOfBoundsException
     * @param consumer Method we expect to throw an exception
     * @param <T> Type of the parameter passed to the consumer
     * @return true if expected exception is thrown
     */
    public static <T> boolean isOutOfBounds(final Supplier<T> consumer) {
        return isExpected(consumer, IndexOutOfBoundsException.class);
    }

    // no reason to instantiate
    //
    private Exceptions() {}
}
