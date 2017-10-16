package com.accelad.math.evaluator.math;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.util.LocalizedFormats;

import com.accelad.math.doubledouble.DoubleDouble;
import com.accelad.math.nilgiri.DoubleDoubleReal;

/**
 * Arbitrary precision decimal number.
 * <p>
 * This class is a simple wrapper around the standard <code>DoubleDouble</code>
 * in order to implement the {@link FieldElement} interface.
 * </p>
 */
public class DoubleDoubleRealElement implements FieldElement<DoubleDoubleRealElement>,
 Comparable<DoubleDoubleRealElement> {

    /** A DoubleDouble representing 0. */
    public static final DoubleDoubleRealElement ZERO = new DoubleDoubleRealElement(
            new DoubleDoubleReal(DoubleDouble.ZERO));

    /** A DoubleDouble representing 1. */
    public static final DoubleDoubleRealElement ONE = new DoubleDoubleRealElement(
            new DoubleDoubleReal(DoubleDouble.ONE));

    /** Underlying DoubleDouble. */
    private final DoubleDoubleReal d;

    /**
     * Build an instance from a DoubleDouble.
     *
     * @param val
     *            value of the instance
     */
    public DoubleDoubleRealElement(DoubleDoubleReal val) {
        d = val;
    }

    public DoubleDoubleRealElement(String string) {
        d = new DoubleDoubleReal(string);
    }

    /** {@inheritDoc} */
    @Override
    public DoubleDoubleRealElement add(DoubleDoubleRealElement a) {
        return new DoubleDoubleRealElement(d.plus(a.d));
    }

    /** {@inheritDoc} */
    @Override
    public DoubleDoubleRealElement subtract(DoubleDoubleRealElement a) {
        return new DoubleDoubleRealElement(d.minus(a.d));
    }

    /** {@inheritDoc} */
    @Override
    public DoubleDoubleRealElement negate() {
        return new DoubleDoubleRealElement(d.negate());
    }

    /**
     * {@inheritDoc}
     *
     * @throws MathArithmeticException
     *             if {@code a} is zero
     */
    @Override
    public DoubleDoubleRealElement divide(DoubleDoubleRealElement a)
            throws MathArithmeticException {
        try {
            return new DoubleDoubleRealElement(d.div(a.d));
        } catch (ArithmeticException e) {
            // Division by zero has occurred
            throw new MathArithmeticException(LocalizedFormats.ZERO_NOT_ALLOWED);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @throws MathArithmeticException
     *             if {@code this} is zero
     */
    @Override
    public DoubleDoubleRealElement reciprocal() throws MathArithmeticException {
        try {
            return new DoubleDoubleRealElement(d.inverse());
        } catch (ArithmeticException e) {
            // Division by zero has occurred
            throw new MathArithmeticException(LocalizedFormats.ZERO_NOT_ALLOWED);
        }
    }

    /** {@inheritDoc} */
    @Override
    public DoubleDoubleRealElement multiply(DoubleDoubleRealElement a) {
        return new DoubleDoubleRealElement(d.mul(a.d));
    }

    /** {@inheritDoc} */
    @Override
    public DoubleDoubleRealElement multiply(final int n) {
        return new DoubleDoubleRealElement(d.mul(new DoubleDoubleReal(n)));
    }

    /** {@inheritDoc} */
    @Override
    public int compareTo(DoubleDoubleRealElement a) {
        return d.compareTo(a.d);
    }

    public DoubleDoubleReal getValue() {
        return d;
    }

    /**
     * Get the double value corresponding to the instance.
     *
     * @return double value corresponding to the instance
     */
    public double doubleValue() {
        return d.doubleValue();
    }

    /**
     * Get the BigDecimal value corresponding to the instance.
     *
     * @return BigDecimal value corresponding to the instance
     */
    public DoubleDouble doubleDoubleValue() {
        return d.getDoubleDouble();
    }

    /** {@inheritDoc} */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other instanceof DoubleDoubleRealElement) {
            return d.equals(((DoubleDoubleRealElement) other).d);
        }
        return false;
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        return d.hashCode();
    }

    /** {@inheritDoc} */
    @Override
    public Field<DoubleDoubleRealElement> getField() {
        return DoubleDoubleRealField.getInstance();
    }

    @Override
    public String toString() {
        return "DoubleDoubleRealElement [d=" + d + "]";
    }

}
