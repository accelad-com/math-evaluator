package com.accelad.math.evaluator.core.evaluator;

import com.accelad.math.nilgiri.Field;
import com.accelad.math.nilgiri.autodiff.DifferentialFunction;

public class Point<X extends Field<X>> {

    final DifferentialFunction<X> xFunction;
    final DifferentialFunction<X> yFunction;

    public Point(DifferentialFunction<X> xFunction, DifferentialFunction<X> yFunction) {
        this.xFunction = xFunction;
        this.yFunction = yFunction;
    }
}
