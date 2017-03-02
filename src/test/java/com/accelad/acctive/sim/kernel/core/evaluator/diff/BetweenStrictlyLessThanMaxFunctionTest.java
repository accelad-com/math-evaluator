package com.accelad.acctive.sim.kernel.core.evaluator.diff;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.accelad.acctive.sim.kernel.math.DoubleFactory;
import com.accelad.math.nilgiri.DoubleReal;
import com.accelad.math.nilgiri.autodiff.DifferentialFunction;
import com.accelad.math.nilgiri.autodiff.Variable;

public class BetweenStrictlyLessThanMaxFunctionTest {

    BetweenStrictlyLessThanMaxFunction<DoubleReal> function;

    @Mock
    DifferentialFunction<DoubleReal> x;
    @Mock
    DifferentialFunction<DoubleReal> value1;
    @Mock
    DifferentialFunction<DoubleReal> value2;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        DoubleFactory factory = new DoubleFactory();
        function = new BetweenStrictlyLessThanMaxFunction<>(factory, x, value1, value2);
    }

    @Test
    public void testGetRealFormula() {
        when(x.getFormula(any())).thenReturn("x");
        when(value1.getFormula(any())).thenReturn("value1");
        when(value2.getFormula(any())).thenReturn("value2");

        List<Variable<DoubleReal>> list = new ArrayList<>();
        String s = function.getFormula(list);

        String expcted = "((x >= min(value1, value2) && x <= max(value1, value2)) ? 1d : 0d)";
        assertEquals(expcted, s);
    }

    @Test
    public void should_return_1_when_argument_is_between_the_bounds_in_ascending_order() {
        DoubleReal value = applyBetweenFunction(4d, 3d, 5d);

        assertEquals(new DoubleReal(1d), value);
    }

    @Test
    public void should_return_1_when_argument_is_between_the_bounds_in_descending_order() {
        DoubleReal value = applyBetweenFunction(4d, 5d, 3d);

        assertEquals(new DoubleReal(1d), value);
    }

    @Test
    public void should_return_1_when_argument_is_same_as_lower_bound() {
        DoubleReal value = applyBetweenFunction(3d, 3d, 5d);

        assertEquals(new DoubleReal(1d), value);
    }

    @Test
    public void should_return_0_when_argument_is_same_as_upper_bound() {
        DoubleReal value = applyBetweenFunction(5d, 3d, 5d);

        assertEquals(new DoubleReal(0d), value);
    }

    @Test
    public void should_return_0_when_argument_is_less_than_lower_bound() {
        DoubleReal value = applyBetweenFunction(3d, 4d, 5d);

        assertEquals(new DoubleReal(0d), value);
    }

    @Test
    public void should_return_0_when_argument_is_greater_than_upper_bound() {
        DoubleReal value = applyBetweenFunction(5d, 3d, 4d);

        assertEquals(new DoubleReal(0d), value);
    }


    private DoubleReal applyBetweenFunction(double arg, double firstBound, double secondBound) {
        DoubleFactory factory = new DoubleFactory();
        DifferentialFunction<DoubleReal> argument = factory.val(arg);
        DifferentialFunction<DoubleReal> func1 = factory.val(firstBound);
        DifferentialFunction<DoubleReal> func2 = factory.val(secondBound);
        function = new BetweenStrictlyLessThanMaxFunction<>(factory, argument, func1, func2);

        return function.getValue();
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testDiff() {
        DoubleFactory factory = new DoubleFactory();
        Variable<DoubleReal> v = mock(Variable.class);

        DoubleReal result = function.diff(v).getValue();
        DoubleReal expected = factory.zero().getValue();

        assertEquals(expected, result);
    }
}
