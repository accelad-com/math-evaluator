package com.accelad.math.evaluator.core.evaluator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.accelad.math.evaluator.math.DoubleFactory;
import com.accelad.math.evaluator.math.MathFactory;
import com.accelad.math.nilgiri.DoubleReal;
import com.accelad.math.nilgiri.autodiff.DifferentialFunction;
import com.accelad.math.nilgiri.autodiff.Variable;

public class AbsFunctionTest {

    private AbsFunction<DoubleReal> function;

    private final MathFactory<DoubleReal> factory = new DoubleFactory();

    @Test
    public void should_return_one_when_given_positive_one() {
        function = new AbsFunction<>(factory.one());
        double returnedValue = function.getReal();

        assertEquals(1d, returnedValue, 1e-12);
    }

    @Test
    public void should_return_one_when_given_negative_one() {
        function = new AbsFunction<>(factory.one().negate());
        double returnedValue = function.getReal();

        assertEquals(1d, returnedValue, 1e-12);
    }

    @Test
    public void should_return_zero_when_given_zero() {
        function = new AbsFunction<>(factory.zero());
        double returnedValue = function.getReal();

        assertEquals(0d, returnedValue, 1e-12);
    }

    @Test
    public void should_return_one_when_derivate_a_given_variable() {
        Variable<DoubleReal> x = factory.var("x", 1d);

        function = new AbsFunction<>(x);
        double returnedValue = function.diff(x).getReal();

        assertEquals(1d, returnedValue, 1e-12);
    }

    @Test
    public void should_return_NaN_when_derivate_a_polynomial_function_with_variable_set_to_zero() {
        Variable<DoubleReal> x = factory.var("x", 0d);
        DifferentialFunction<DoubleReal> polynomialFunction = x.pow(2);

        function = new AbsFunction<>(polynomialFunction);
        double returnedValue = function.diff(x).getReal();

        assertTrue(Double.isNaN(returnedValue));
    }

    @Test
    public void should_return_a_negative_number_when_derivate_a_polynomial_function_with_negative_variable() {
        Variable<DoubleReal> x = factory.var("x", -10d);
        DifferentialFunction<DoubleReal> polynomialFunction = x.pow(2);

        function = new AbsFunction<>(polynomialFunction);
        double returnedValue = function.diff(x).getReal();

        assertEquals(-20d, returnedValue, 1e-12);
    }

}
