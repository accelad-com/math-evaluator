package com.accelad.math.evaluator.math;

import java.io.Serializable;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;

/**
 * Representation of real numbers with arbitrary precision field.
 * <p>
 * This class is a singleton.
 * </p>
 *
 * @see DoubleDoubleRealElement
 * @since 2.0
 */
public class DoubleDoubleRealField implements Field<DoubleDoubleRealElement>, Serializable {

    /** Serializable version identifier */
    private static final long serialVersionUID = 4756431066541037559L;

    /**
     * Private constructor for the singleton.
     */
    private DoubleDoubleRealField() {
    }

    /**
     * Get the unique instance.
     *
     * @return the unique instance
     */
    public static DoubleDoubleRealField getInstance() {
        return LazyHolder.INSTANCE;
    }

    /** {@inheritDoc} */
    public DoubleDoubleRealElement getOne() {
        return DoubleDoubleRealElement.ONE;
    }

    /** {@inheritDoc} */
    public DoubleDoubleRealElement getZero() {
        return DoubleDoubleRealElement.ZERO;
    }

    /** {@inheritDoc} */
    public Class<? extends FieldElement<DoubleDoubleRealElement>> getRuntimeClass() {
        return DoubleDoubleRealElement.class;
    }

    // CHECKSTYLE: stop HideUtilityClassConstructor
    /**
     * Holder for the instance.
     * <p>
     * We use here the Initialization On Demand Holder Idiom.
     * </p>
     */
    private static class LazyHolder {
        /** Cached field instance. */
        private static final DoubleDoubleRealField INSTANCE = new DoubleDoubleRealField();
    }
    // CHECKSTYLE: resume HideUtilityClassConstructor

    /**
     * Handle deserialization of the singleton.
     *
     * @return the singleton instance
     */
    private Object readResolve() {
        // return the singleton instance
        return LazyHolder.INSTANCE;
    }

}
