package com.accelad.acctive.sim.kernel.core.evaluator.diff;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.Test;

import com.accelad.acctive.sim.kernel.math.DoubleFactory;
import com.accelad.acctive.sim.kernel.math.MathFactory;
import com.accelad.math.nilgiri.DoubleReal;
import com.accelad.math.nilgiri.autodiff.DifferentialFunction;
import com.accelad.math.nilgiri.autodiff.Variable;

public class LowerThanFunctionTest {

    @Test
    public void testDiff() {
        MathFactory<DoubleReal> factory = new DoubleFactory();
        DifferentialFunction<DoubleReal> f1 = mock(DifferentialFunction.class);
        DifferentialFunction<DoubleReal> f2 = mock(DifferentialFunction.class);
        Variable<DoubleReal> v = mock(Variable.class);
        LowerThanFunction<DoubleReal> func = new LowerThanFunction<>(factory, f1, f2);

        DoubleReal result = func.diff(v).getValue();
        DoubleReal expected = factory.zero().getValue();

        assertEquals(expected, result);
    }
}
