package com.accelad.acctive.sim.kernel.math;

import java.io.Serializable;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;

public class DoubleDoubleComplexField implements Field<DoubleDoubleComplexElement>, Serializable {

    private DoubleDoubleComplexField() {
    }

    public static DoubleDoubleComplexField getInstance() {
        return LazyHolder.INSTANCE;
    }

    @Override
    public DoubleDoubleComplexElement getOne() {
        return DoubleDoubleComplexElement.ONE;
    }

    @Override
    public DoubleDoubleComplexElement getZero() {
        return DoubleDoubleComplexElement.ZERO;
    }

    @Override
    public Class<? extends FieldElement<DoubleDoubleComplexElement>> getRuntimeClass() {
        return DoubleDoubleComplexElement.class;
    }

    private static class LazyHolder {
        private static final DoubleDoubleComplexField INSTANCE = new DoubleDoubleComplexField();

        private LazyHolder() {
        }
    }

}
