package com.mrsnottypants.test;

import org.junit.Test;
import org.junit.Assert;

/**
 * Created by Eric on 7/3/2016.
 */
public class ExceptionsTest {

    // something to throw exceptions
    //
    private static final class ExceptionFactory {

        public static void consumer(Integer key) {
            if (key == null) {
                throw new IllegalArgumentException("illegal");
            }
            if (key < 0) {
                throw new IndexOutOfBoundsException("out of bounds");
            }
        }

        public static void biConsumer(Integer key, String value) {
            if ((key == null) || (value == null)) {
                throw new IllegalArgumentException("illegal");
            }
            if (key < 0) {
                throw new IndexOutOfBoundsException("out of bounds");
            }
        }

        public static Integer supplierInBounds() {
            return 1;
        }

        public static Integer supplierOutOfBounds() {
            throw new IndexOutOfBoundsException("out of bounds");
        }
    }

    @Test
    public void testIsIllegalConsumer() {
        Assert.assertTrue(Exceptions.isIllegal(ExceptionFactory::consumer, null));
        Assert.assertFalse(Exceptions.isIllegal(ExceptionFactory::consumer, -1));
        Assert.assertFalse(Exceptions.isIllegal(ExceptionFactory::consumer, 1));
    }

    @Test
    public void testIsOutOfBoundsConsumer() {
        Assert.assertFalse(Exceptions.isOutOfBounds(ExceptionFactory::consumer, null));
        Assert.assertTrue(Exceptions.isOutOfBounds(ExceptionFactory::consumer, -1));
        Assert.assertFalse(Exceptions.isOutOfBounds(ExceptionFactory::consumer, 1));
    }

    @Test
    public void testIsIllegalBiConsumer() {
        Assert.assertTrue(Exceptions.isIllegal(ExceptionFactory::biConsumer, 1, null));
        Assert.assertFalse(Exceptions.isIllegal(ExceptionFactory::biConsumer, -1, "yes"));
        Assert.assertFalse(Exceptions.isIllegal(ExceptionFactory::biConsumer, 1, "no"));
    }

    @Test
    public void testIsOutOfBoundsBiConusmer() {
        Assert.assertFalse(Exceptions.isOutOfBounds(ExceptionFactory::biConsumer, 1, null));
        Assert.assertTrue(Exceptions.isOutOfBounds(ExceptionFactory::biConsumer, -1, "yes"));
        Assert.assertFalse(Exceptions.isOutOfBounds(ExceptionFactory::biConsumer, 1, "no"));
    }

    @Test
    public void testIsOutOfBoundsSupplier() {
        Assert.assertFalse(Exceptions.isOutOfBounds(ExceptionFactory::supplierInBounds));
        Assert.assertTrue(Exceptions.isOutOfBounds(ExceptionFactory::supplierOutOfBounds));
    }
}