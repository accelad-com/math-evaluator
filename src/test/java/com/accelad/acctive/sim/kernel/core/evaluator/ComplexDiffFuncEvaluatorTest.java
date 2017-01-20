package com.accelad.acctive.sim.kernel.core.evaluator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.accelad.acctive.sim.kernel.math.DoubleDoubleComplexMathFactory;
import com.accelad.acctive.sim.kernel.math.MathFactory;
import com.accelad.math.nilgiri.DoubleDoubleComplex;

public class ComplexDiffFuncEvaluatorTest {

    private MathFactory<DoubleDoubleComplex> dfFactory = new DoubleDoubleComplexMathFactory();
    private ComplexDiffFuncEvaluator evaluator = new ComplexDiffFuncEvaluator(dfFactory);

    @Test
    public void testEvaluateWithI() {
        DoubleDoubleComplex expected = new DoubleDoubleComplex(3, 2);

        DoubleDoubleComplex actual = evaluator.evaluate("3+2*i").getValue();

        assertEquals(expected, actual);
    }

    @Test
    public void testEvaluateWithJ() {
        DoubleDoubleComplex expected = new DoubleDoubleComplex(4, 5);

        DoubleDoubleComplex actual = evaluator.evaluate("4+5*j").getValue();

        assertEquals(expected, actual);
    }
}
