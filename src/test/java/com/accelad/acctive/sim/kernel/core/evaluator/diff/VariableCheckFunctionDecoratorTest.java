package com.accelad.acctive.sim.kernel.core.evaluator.diff;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import org.junit.Test;

import com.accelad.acctive.sim.kernel.math.DoubleFactory;
import com.accelad.acctive.sim.kernel.math.MathFactory;
import com.accelad.math.nilgiri.DoubleReal;
import com.accelad.math.nilgiri.autodiff.DifferentialFunction;
import com.accelad.math.nilgiri.autodiff.Variable;

public class VariableCheckFunctionDecoratorTest {

    private static final double TOLERANCE = 1e-24;

    private static final Function<Double, Double> SQUARE_FUNCTION = t -> Math.pow(t, 2);
    private static final Function<Double, Double> DERIVATIVE_SQUARE_FUNCTION = t -> 2 * t;

    private static final double[] VALUES = { -5, -2, -1, 0, 1, 2, 5 };

    private final MathFactory<DoubleReal> MATH_FACTORY = new DoubleFactory();
    private final Variable<DoubleReal> x = MATH_FACTORY.var("x");
    private final DifferentialFunction<DoubleReal> f = x.pow(2);
    private final VariableCheckFunctionDecorator<DoubleReal> fDeco = new VariableCheckFunctionDecorator<>(
            f);
    private final DifferentialFunction<DoubleReal> dfdxDeco = fDeco.diff(x);

    @Test
    public void decoratorDoesNotChangeInnerFunctionGetValue() throws Exception {
        forEachValueAssertFunctionAreEqual(SQUARE_FUNCTION, t -> getValue(fDeco, t));
    }

    @Test
    public void decoratorDoesNotChangeInnerFunctionGetReal() throws Exception {
        forEachValueAssertFunctionAreEqual(SQUARE_FUNCTION, t -> getReal(fDeco, t));
    }

    @Test
    public void decoratorDoesNotChangeInnerFunctionDiff() throws Exception {
        forEachValueAssertFunctionAreEqual(DERIVATIVE_SQUARE_FUNCTION, t -> getReal(dfdxDeco, t));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLimitedFunctionGetRealThrowsException() throws Exception {
        testLimitedFunctionKO(fDeco, this::getReal);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLimitedFunctionGetValueThrowsException() throws Exception {
        testLimitedFunctionKO(fDeco, this::getValue);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLimitedFunctionDerivativeGetRealThrowsException() throws Exception {
        testLimitedFunctionKO(dfdxDeco, this::getReal);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLimitedFunctionDerivativeGetValueThrowsException() throws Exception {
        testLimitedFunctionKO(dfdxDeco, this::getValue);
    }

    @Test
    public void testLimitedFunctionGetRealMustNotThrowException() throws Exception {
        testLimitedFunctionOK(fDeco, this::getReal);
    }

    @Test
    public void testLimitedFunctionGetValueMustNotThrowException() throws Exception {
        testLimitedFunctionOK(fDeco, this::getValue);
    }

    @Test
    public void testLimitedFunctionDerivativeGetRealMustNotThrowException() throws Exception {
        testLimitedFunctionOK(dfdxDeco, this::getReal);
    }

    @Test
    public void testLimitedFunctionDerivativeGetValueMustNotThrowException() throws Exception {
        testLimitedFunctionOK(dfdxDeco, this::getValue);
    }

    private void forEachValueAssertFunctionAreEqual(Function<Double, Double> f, Function<Double, Double> g) {
        forEachValue(v -> assertEquals(f.apply(v), g.apply(v), TOLERANCE));
    }

    private void testLimitedFunctionOK(DifferentialFunction<DoubleReal> function,
            BiConsumer<DifferentialFunction, Double> biConsumer) {
        addVariableCheckToDecorator(x -> x != 0.0);
        try {
            biConsumer.accept(function, 1.0);
        } catch (Exception e) {
            fail();
        }
    }

    private void testLimitedFunctionKO(DifferentialFunction<DoubleReal> function,
            BiConsumer<DifferentialFunction, Double> biConsumer) {
        double forbiddenValue = 0.0;
        addVariableCheckToDecorator(x -> x != forbiddenValue);
        biConsumer.accept(function, forbiddenValue);
    }

    private void addVariableCheckToDecorator(Predicate<Double> predicate) {
        fDeco.addArgTest(x, v -> predicate.test(v.getReal()), "error");
    }

    private double getReal(DifferentialFunction<DoubleReal> function, double value) {
        return getEvaluatedValue(function, value, DifferentialFunction::getReal);
    }

    private double getValue(DifferentialFunction<DoubleReal> function, double value) {
        return getEvaluatedValue(function, value, f -> f.getValue().doubleValue());
    }

    private double getEvaluatedValue(DifferentialFunction<DoubleReal> function, double value,
            Function<DifferentialFunction<DoubleReal>, Double> evaluator) {
        applyValueToXVariable(value);
        return evaluator.apply(function);
    }

    private void applyValueToXVariable(double value) {
        x.set(MATH_FACTORY.get(value));
    }

    private void forEachValue(Consumer<Double> xConsumer) {
        for (double nonRegValue : VALUES) {
            xConsumer.accept(nonRegValue);
        }
    }
}