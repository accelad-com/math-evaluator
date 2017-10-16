package com.accelad.math.evaluator.core.evaluator.diff;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import com.accelad.math.evaluator.math.DoubleFactory;
import com.accelad.math.evaluator.math.MathFactory;
import com.accelad.math.nilgiri.DoubleReal;
import com.accelad.math.nilgiri.autodiff.Constant;
import com.accelad.math.nilgiri.autodiff.DifferentialFunction;
import com.accelad.math.nilgiri.autodiff.Variable;

public class GreaterThanFunctionTest {

    private static final MathFactory<DoubleReal> DOUBLE_FACTORY = new DoubleFactory();
    private static final Constant<DoubleReal> ONE = DOUBLE_FACTORY.val(1d);
    private static final Constant<DoubleReal> TWO = DOUBLE_FACTORY.val(2d);
    private static final Constant<DoubleReal> THREE = DOUBLE_FACTORY.val(3d);

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void should_return_1d_if_equals_to_bound() {
        GreaterThanFunction<DoubleReal> greaterThanFunction = getGreaterThanFunction(ONE, ONE);

        double result = greaterThanFunction.getReal();

        assertThat(result, is(1d));
    }

    @Test
    public void should_return_1d_if_greater_than_bound() {
        GreaterThanFunction<DoubleReal> greaterThanFunction = getGreaterThanFunction(TWO, ONE);

        double result = greaterThanFunction.getReal();

        assertThat(result, is(1d));
    }

    @Test
    public void should_return_0d_if_lower_than_bound() {
        GreaterThanFunction<DoubleReal> greaterThanFunction = getGreaterThanFunction(TWO, THREE);

        double result = greaterThanFunction.getReal();

        assertThat(result, is(0d));
    }

    @Test
    public void testDiff() {
        DifferentialFunction<DoubleReal> f1 = mock(DifferentialFunction.class);
        DifferentialFunction<DoubleReal> f2 = mock(DifferentialFunction.class);
        Variable<DoubleReal> v = mock(Variable.class);
        GreaterThanFunction<DoubleReal> func = getGreaterThanFunction(f1, f2);

        DoubleReal result = func.diff(v).getValue();
        DoubleReal expected = DOUBLE_FACTORY.zero().getValue();

        assertEquals(expected, result);
    }

    private GreaterThanFunction<DoubleReal> getGreaterThanFunction(
            DifferentialFunction<DoubleReal> f1, DifferentialFunction<DoubleReal> f2) {
        return new GreaterThanFunction<>(DOUBLE_FACTORY, f1, f2);
    }
}
