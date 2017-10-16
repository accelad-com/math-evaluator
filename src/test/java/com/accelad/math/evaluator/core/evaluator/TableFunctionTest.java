package com.accelad.math.evaluator.core.evaluator;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.accelad.math.evaluator.math.DoubleFactory;
import com.accelad.math.nilgiri.DoubleReal;
import com.accelad.math.nilgiri.autodiff.DifferentialFunction;

public class TableFunctionTest {

    TableFunction<DoubleReal> function;

    @Mock
    DifferentialFunction<DoubleReal> x;

    @Mock
    DifferentialFunction<DoubleReal> x1;
    @Mock
    DifferentialFunction<DoubleReal> x2;
    @Mock
    DifferentialFunction<DoubleReal> y1;
    @Mock
    DifferentialFunction<DoubleReal> y2;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        when(x1.getReal()).thenReturn(1d);
        when(x2.getReal()).thenReturn(2d);
        when(y1.getReal()).thenReturn(1d);
        when(y2.getReal()).thenReturn(2d);

        List<Point<DoubleReal>> points = new ArrayList<>();
        points.add(new Point<DoubleReal>(x1, y1));
        points.add(new Point<DoubleReal>(x2, y2));

        DoubleFactory factory = new DoubleFactory();

        function = new TableFunction<>(factory, x, points);
    }

}
