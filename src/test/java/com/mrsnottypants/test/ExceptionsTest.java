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
            if (key == 0) {
                throw new IllegalStateException("illegal");
            }
        }

        public static void biConsumer(Integer key, String value) {
            if ((key == null) || (value == null)) {
                throw new IllegalArgumentException("illegal");
            }
            if (key < 0) {
                throw new IndexOutOfBoundsException("out of bounds");
            }
            if (key == 0) {
                throw new IllegalStateException("illegal");
            }
        }

        public static Integer supplierGood() {
            return 1;
        }

        public static Integer supplierIllegalState() {
            throw new IllegalStateException("bad");
        }

        public static Integer supplierOutOfBounds() {
            throw new IndexOutOfBoundsException("bad");
        }
    }

    @Test
    public void testIsIllegalArgumentConsumer() {
        Assert.assertTrue(Exceptions.isIllegalArgument(ExceptionFactory::consumer, null));
        Assert.assertFalse(Exceptions.isIllegalArgument(ExceptionFactory::consumer, -1));
        Assert.assertFalse(Exceptions.isIllegalArgument(ExceptionFactory::consumer, 1));
    }

    @Test
    public void testIsIllegalStateConsumer() {
        Assert.assertTrue(Exceptions.isIllegalState(ExceptionFactory::consumer, 0));
        Assert.assertFalse(Exceptions.isIllegalState(ExceptionFactory::consumer, -1));
        Assert.assertFalse(Exceptions.isIllegalState(ExceptionFactory::consumer, 1));
    }

    @Test
    public void testIsOutOfBoundsConsumer() {
        Assert.assertFalse(Exceptions.isOutOfBounds(ExceptionFactory::consumer, null));
        Assert.assertTrue(Exceptions.isOutOfBounds(ExceptionFactory::consumer, -1));
        Assert.assertFalse(Exceptions.isOutOfBounds(ExceptionFactory::consumer, 1));
    }

    @Test
    public void testIsIllegalArgumentBiConsumer() {
        Assert.assertTrue(Exceptions.isIllegalArgument(ExceptionFactory::biConsumer, 1, null));
        Assert.assertFalse(Exceptions.isIllegalArgument(ExceptionFactory::biConsumer, -1, "yes"));
        Assert.assertFalse(Exceptions.isIllegalArgument(ExceptionFactory::biConsumer, 1, "no"));
    }

    @Test
    public void testIsIllegalStateBiConsumer() {
        Assert.assertTrue(Exceptions.isIllegalState(ExceptionFactory::biConsumer, 0, "maybe"));
        Assert.assertFalse(Exceptions.isIllegalState(ExceptionFactory::biConsumer, 0, null));
        Assert.assertFalse(Exceptions.isIllegalState(ExceptionFactory::biConsumer, 1, "no"));
    }

    @Test
    public void testIsOutOfBoundsBiConusmer() {
        Assert.assertFalse(Exceptions.isOutOfBounds(ExceptionFactory::biConsumer, 1, null));
        Assert.assertTrue(Exceptions.isOutOfBounds(ExceptionFactory::biConsumer, -1, "yes"));
        Assert.assertFalse(Exceptions.isOutOfBounds(ExceptionFactory::biConsumer, 1, "no"));
    }

    @Test
    public void testIsIllegalStateSupplier() {
        Assert.assertFalse(Exceptions.isIllegalState(ExceptionFactory::supplierGood));
        Assert.assertTrue(Exceptions.isIllegalState(ExceptionFactory::supplierIllegalState));
    }

    @Test
    public void testIsOutOfBoundsSupplier() {
        Assert.assertFalse(Exceptions.isOutOfBounds(ExceptionFactory::supplierGood));
        Assert.assertTrue(Exceptions.isOutOfBounds(ExceptionFactory::supplierOutOfBounds));
    }
}