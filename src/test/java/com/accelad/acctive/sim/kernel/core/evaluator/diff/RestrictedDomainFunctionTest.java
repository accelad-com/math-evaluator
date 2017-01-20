package com.accelad.acctive.sim.kernel.core.evaluator.diff;

import static org.junit.Assert.assertEquals;

import java.util.function.BiConsumer;
import java.util.function.Function;

import org.junit.Before;
import org.junit.Test;

import com.accelad.acctive.sim.kernel.math.DoubleFactory;
import com.accelad.acctive.sim.kernel.math.MathFactory;
import com.accelad.math.nilgiri.DoubleReal;
import com.accelad.math.nilgiri.autodiff.DifferentialFunction;
import com.accelad.math.nilgiri.autodiff.Variable;

public class RestrictedDomainFunctionTest {

    private static final double[] xVector = { -11, -10, -1, 0, 1, 2, 10, 11 };
    private static final double[] fxVector = { 0, 111, 3, 1, 1, 3, 91, 0 };     // f(x) = x²-x+1
    private static final double[] dfdxVector = { 0, -21, -3, -1, 1, 3, 19, 0 }; // df/dx = 2x-1
    private static final double[] dfdyVector = { 0, 0, 0, 0, 0, 0, 0, 0 };      // df/dy = 0

    private final MathFactory<DoubleReal> mathFactory = new DoubleFactory();
    private final Variable<DoubleReal> x = mathFactory.var("x");
    private final Variable<DoubleReal> y = mathFactory.var("y");
    private RestrictedDomainFunction<DoubleReal> function;

    @Before
    public void setUp() throws Exception {
        Variable<DoubleReal> x0 = mathFactory.var("x0", new DoubleReal(-10));
        Variable<DoubleReal> x1 = mathFactory.var("x1", new DoubleReal(10));
        // p(x) = x²-x+1
        DifferentialFunction<DoubleReal> p = x.mul(x).minus(x).plus(mathFactory.one());
        function = new RestrictedDomainFunction<>(mathFactory, x, p, x0, x1);
    }

    @Test
    public void getValue() throws Exception {
        loopOnValues(this::assertValue, fxVector);
    }

    @Test
    public void getReal() throws Exception {
        loopOnValues(this::assertReal, fxVector);
    }

    @Test
    public void diffX() throws Exception {
        loopOnValues((xValue, expected) -> assertDiff(xValue, expected, x), dfdxVector);
    }

    @Test
    public void diffY() throws Exception {
        loopOnValues((xValue, expected) -> assertDiff(xValue, expected, y), dfdyVector);
    }

    private void loopOnValues(BiConsumer<Double, Double> biConsumer, double[] values) {
        for (int i = 0; i < xVector.length; i++) {
            biConsumer.accept(xVector[i], values[i]);
        }
    }

    private void assertValue(double xValue, double expected) {
        assertExpected(xValue, expected, f -> f.getValue().doubleValue());
    }

    private void assertReal(double xValue, double expected) {
        assertExpected(xValue, expected, DifferentialFunction::getReal);
    }

    private void assertDiff(double xValue, double expected, Variable<DoubleReal> variable) {
        assertExpected(xValue, expected, f -> f.diff(variable).getReal());
    }

    private void assertExpected(double xValue, double expected,
            Function<DifferentialFunction<DoubleReal>, Double> getter) {
        x.set(new DoubleReal(xValue));
        assertEquals(expected, getter.apply(function), 1e-16);
    }
}