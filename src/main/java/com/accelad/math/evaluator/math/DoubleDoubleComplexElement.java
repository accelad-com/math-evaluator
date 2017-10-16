package com.accelad.math.evaluator.math;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;

import com.accelad.math.nilgiri.DoubleDoubleComplex;
import com.google.common.base.MoreObjects;

public class DoubleDoubleComplexElement implements FieldElement<DoubleDoubleComplexElement> {

    public static final DoubleDoubleComplexElement ZERO = new DoubleDoubleComplexElement(
            DoubleDoubleComplex.ZERO);
    public static final DoubleDoubleComplexElement ONE = new DoubleDoubleComplexElement(
            DoubleDoubleComplex.ONE);
    private final DoubleDoubleComplex value;

    public DoubleDoubleComplexElement(DoubleDoubleComplex value) {
        this.value = value;
    }

    @Override
    public DoubleDoubleComplexElement add(DoubleDoubleComplexElement element) {
        return new DoubleDoubleComplexElement(value.plus(element.value));
    }

    @Override
    public DoubleDoubleComplexElement subtract(DoubleDoubleComplexElement element) {
        return new DoubleDoubleComplexElement(value.minus(element.value));
    }

    @Override
    public DoubleDoubleComplexElement negate() {
        return new DoubleDoubleComplexElement(value.negate());
    }

    @Override
    public DoubleDoubleComplexElement divide(DoubleDoubleComplexElement element) {
        return new DoubleDoubleComplexElement(value.div(element.value));
    }

    @Override
    public DoubleDoubleComplexElement reciprocal() {
        return new DoubleDoubleComplexElement(value.inverse());
    }

    @Override
    public DoubleDoubleComplexElement multiply(DoubleDoubleComplexElement element) {
        return new DoubleDoubleComplexElement(value.mul(element.value));
    }

    @Override
    public DoubleDoubleComplexElement multiply(final int n) {
        return new DoubleDoubleComplexElement(value.mul(new DoubleDoubleComplex(n)));
    }

    public DoubleDoubleComplex getValue() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof DoubleDoubleComplexElement) {
            return value.equals(((DoubleDoubleComplexElement) other).value);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public Field<DoubleDoubleComplexElement> getField() {
        return DoubleDoubleComplexField.getInstance();
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("value", value).toString();
    }
}
