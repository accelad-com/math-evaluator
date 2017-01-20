package com.accelad.acctive.sim.kernel.core.evaluator;

import com.accelad.math.nilgiri.Field;
import com.accelad.math.nilgiri.autodiff.DifferentialFunction;

class Formula<X extends Field<X>> {
    private DifferentialFunction<X> a, b;

    Formula(DifferentialFunction<X> x1, DifferentialFunction<X> y1, DifferentialFunction<X> x2,
            DifferentialFunction<X> y2) {
        a = (y2.minus(y1).div(x2.minus(x1)));
        b = y1.minus(a.mul(x1));
    }

    public DifferentialFunction<X> getA() {
        return a;
    }

    public DifferentialFunction<X> getB() {
        return b;
    }

    DifferentialFunction<X> get(DifferentialFunction<X> x) {
        return a.mul(x).plus(b);
    }
}
