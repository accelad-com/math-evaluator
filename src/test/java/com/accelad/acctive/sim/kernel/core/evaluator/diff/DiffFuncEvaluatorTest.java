package com.accelad.acctive.sim.kernel.core.evaluator.diff;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.accelad.acctive.sim.kernel.core.evaluator.AbsFunction;
import com.accelad.acctive.sim.kernel.math.DoubleFactory;
import com.accelad.math.nilgiri.DoubleReal;
import com.accelad.math.nilgiri.DoubleRealFactory;
import com.accelad.math.nilgiri.autodiff.DifferentialFunction;
import com.accelad.math.nilgiri.autodiff.DifferentialFunctionFactory;
import com.accelad.math.nilgiri.autodiff.Variable;

public class DiffFuncEvaluatorTest {
    private final DoubleRealFactory RNFactory = DoubleRealFactory.instance();
    private final DifferentialFunctionFactory<DoubleReal> DFFactory = new DifferentialFunctionFactory<>(
            RNFactory);

    DiffFuncEvaluator<DoubleReal> evaluator;

    @Before
    public void setUp() throws Exception {
        DoubleFactory factory = new DoubleFactory();
        evaluator = new DiffFuncEvaluator<>(factory);
    }

    @Test
    public void testEvaluatenegValue() {

        DifferentialFunction<DoubleReal> f = evaluator.evaluate("1E-14");

        assertEquals(1e-14, f.getValue().doubleValue(), 1e-16);

        f = evaluator.evaluate("1E14");

        assertEquals(1e14, f.getValue().doubleValue(), 1e-16);
    }

    @Test
    public void testEvaluatePositiveValue() {

        DifferentialFunction<DoubleReal> f = evaluator.evaluate("1E14");

        assertEquals(1e14, f.getValue().doubleValue(), 1e-16);
    }

    @Test
    public void testEvaluateFormula() {

        evaluator.addVariable(DFFactory.var("var", new DoubleReal(2)));
        DifferentialFunction<DoubleReal> f = evaluator.evaluate("10 + var");

        assertEquals(12d, f.getValue().doubleValue(), 1e-16);
    }

    @Test
    public void testEvaluateGreaterThan() {

        evaluator.addVariable(DFFactory.var("var", new DoubleReal(100)));
        DifferentialFunction<DoubleReal> f = evaluator.evaluate("10 * greaterthan(var,20)");

        assertEquals(10d, f.getValue().doubleValue(), 1e-16);
    }

    @Test
    public void testEvaluateLowerThan() {

        evaluator.addVariable(DFFactory.var("var", new DoubleReal(5)));
        DifferentialFunction<DoubleReal> f = evaluator.evaluate("10 * lowerthan(var,20)");

        assertEquals(10d, f.getValue().doubleValue(), 1e-16);
    }

    @Test
    public void testEvaluateBetween() {

        evaluator.addVariable(DFFactory.var("var", new DoubleReal(10)));
        DifferentialFunction<DoubleReal> f = evaluator.evaluate("10 * between(var, 5, 20)");

        assertEquals(10d, f.getValue().doubleValue(), 1e-16);
    }

    @Test
    public void should_return_the_absolute_function_when_given_abs_as_string() {
        DifferentialFunction<DoubleReal> f = evaluator.evaluate("abs(-20)");
        assertTrue(f instanceof AbsFunction);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEvaluateExplInvalidX0() {
        expl(1, 0.49).getValue();
    }

    private DifferentialFunction<DoubleReal> expl(double x, double x0) {
        evaluator.getVariables().clear();
        evaluator.addVariable(DFFactory.var("x", new DoubleReal(x)));
        evaluator.addVariable(DFFactory.var("x0", new DoubleReal(x0)));
        return evaluator.evaluate("expl(x, x0)");
    }

    private void assertExplx(double x, double x0, double expected) {
        assertEquals(expected, expl(x, x0).getValue().doubleValue(), 1e-16);
    }

    @Test
    public void testEvaluateExplOne() {
        double x = 1;
        assertExplx(x, 40, Math.exp(x));
    }

    private void testEvaluateExplDiff(double xValue) {
        evaluator.getVariables().clear();
        Variable<DoubleReal> x = DFFactory.var("x", new DoubleReal(xValue));
        evaluator.addVariable(x);
        evaluator.addVariable(DFFactory.var("x0", new DoubleReal(40)));
        double diffX = evaluator.evaluate("expl(x, x0)").diff(x).getValue().doubleValue();
        assertEquals(Math.exp(xValue), diffX, 1e-16);
    }

    @Test
    public void testEvaluateExplDiffMultiValues() {
        int nbPoints = 50;
        double min = -10, max = 10;
        for (int i = 0; i <= nbPoints; i++) {
            double xValue = min + i * (max - min) / nbPoints;
            testEvaluateExplDiff(xValue);
        }
    }

    @Test
    public void testEvaluateExplVeryLow() {
        double x = -50;
        double x0 = 40;
        double explX = (x + x0) * Math.exp(1 - x0);
        assertExplx(x, x0, explX);
    }

    @Test
    public void testEvaluateExplVeryHigh() {
        double x = 50;
        double x0 = 40;
        double explX = (x - x0 + 1) * Math.exp(x0);
        assertExplx(x, x0, explX);
    }

    @Test
    public void testExplLeftBoundContinuity() {
        double dx = 1e-9;
        double x0 = 40;
        double x = -39;
        double fxLeft = expl(x - dx, x0).getReal();
        double fxRight = expl(x + dx, x0).getReal();
        assertEquals(2.3e-26, fxRight - fxLeft, 1e-28);
    }

    @Test
    public void testExplRightBoundContinuity() {
        double dx = 1e-14;
        double x0 = 40;
        double x = 40;
        double fxLeft = expl(x - dx, x0).getReal();
        double fxRight = expl(x + dx, x0).getReal();
        assertEquals(3360.0, fxRight - fxLeft, 1e-1);
    }

    @Test
    public void testEvaluateoutside() {

        evaluator.addVariable(DFFactory.var("var", new DoubleReal(1)));
        DifferentialFunction<DoubleReal> f = evaluator.evaluate("10 * outside(var, 5, 20)");

        assertEquals(10d, f.getValue().doubleValue(), 1e-16);
    }

    // TODO Fix test
    // @Test
    public void testEvaluateTable() {

        evaluator.addVariable(DFFactory.var("var", new DoubleReal(1.5)));
        DifferentialFunction<DoubleReal> f = evaluator.evaluate("table(var, 2, 0, 0, 1E3, 1E3)");

        assertEquals(1.5d, f.getReal(), 1e-16);
    }

    @Test
    public void should_evaluate_acos_correctly() throws Exception {
        DifferentialFunction<DoubleReal> function = evaluator.evaluate("acos(0)");
        assertEquals(Math.PI / 2, function.getValue().doubleValue(), 0);
    }

    @Test
    public void should_evaluate_asin_correctly() throws Exception {
        DifferentialFunction<DoubleReal> function = evaluator.evaluate("asin(1)");
        assertEquals(Math.PI / 2, function.getValue().doubleValue(), 0);
    }

    @Test
    public void should_evaluate_atan_correctly() throws Exception {
        DifferentialFunction<DoubleReal> function = evaluator.evaluate("atan(1)");
        assertEquals(Math.PI / 4, function.getValue().doubleValue(), 0);
    }

    @Test
    public void should_evaluate_cosh_correctly() throws Exception {
        DifferentialFunction<DoubleReal> function = evaluator.evaluate("cosh(0)");
        assertEquals(1d, function.getValue().doubleValue(), 0);
    }

    @Test
    public void should_evaluate_sinh_correctly() throws Exception {
        DifferentialFunction<DoubleReal> function = evaluator.evaluate("sinh(0)");
        assertEquals(0d, function.getValue().doubleValue(), 0);
    }

    @Test
    public void should_evaluate_tanh_correctly() throws Exception {
        DifferentialFunction<DoubleReal> function = evaluator.evaluate("tanh(1)");
        assertEquals(0.76159415595576488811945828260479359041276859d,
                function.getValue().doubleValue(), 0);
    }

    @Test
    public void should_evaluate_acosh_correctly() throws Exception {
        DifferentialFunction<DoubleReal> function = evaluator.evaluate("acosh(1)");
        assertEquals(0, function.getValue().doubleValue(), 0);
    }

    @Test
    public void should_evaluate_asinh_correctly() throws Exception {
        DifferentialFunction<DoubleReal> function = evaluator.evaluate("asinh(0)");
        assertEquals(0d, function.getValue().doubleValue(), 0);
    }

    @Test
    public void should_evaluate_atanh_correctly() throws Exception {
        DifferentialFunction<DoubleReal> function = evaluator.evaluate(
                "atanh(0.76159415595576488811945828260479359041276859d)");
        assertEquals(1d, function.getValue().doubleValue(), 1e-12);
    }

    private void testDerivative(String function, double x, double expectedY) {
        Variable<DoubleReal> xVar = DFFactory.var("x", new DoubleReal(x));
        evaluator.addVariable(xVar);
        DifferentialFunction<DoubleReal> differentialFunction = evaluator.evaluate(function + "(x)");
        assertEquals(expectedY, differentialFunction.diff(xVar).getReal(), 1e-15);
    }

    @Test
    public void should_evaluate_acos_derivative_correctly() throws Exception {
        testDerivative("acos", 0, -1);
    }

    @Test
    public void should_evaluate_asin_derivative_correctly() throws Exception {
        testDerivative("asin", 0, 1);
    }

    @Test
    public void should_evaluate_atan_derivative_correctly() throws Exception {
        testDerivative("atan", 2, 1/5d);
    }

    @Test
    public void should_evaluate_cosh_derivative_correctly() throws Exception {
        testDerivative("cosh", 0, 0);
    }

    @Test
    public void should_evaluate_sinh_derivative_correctly() throws Exception {
        testDerivative("sinh", 0, 1);
    }

    @Test
    public void should_evaluate_tanh_derivative_correctly() throws Exception {
        testDerivative("tanh", 1, 0.4199743416140260693944967390417014d);
    }

    @Test
    public void should_evaluate_acosh_derivative_correctly() throws Exception {
        testDerivative("acosh", 2, 0.577350269189625764509148780501957455d);
    }

    @Test
    public void should_evaluate_asinh_derivative_correctly() throws Exception {
        testDerivative("asinh", 2, 0.447213595499957939281834733746255247088d);
    }

    @Test
    public void should_evaluate_atanh_derivative_correctly() throws Exception {
        testDerivative("atanh", 2, -1/3d);
    }
}
