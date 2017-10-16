package com.accelad.math.evaluator.core.evaluator.diff;

import java.util.List;

import com.accelad.math.evaluator.math.MathFactory;
import com.accelad.math.nilgiri.Field;
import com.accelad.math.nilgiri.autodiff.DifferentialFunction;
import com.accelad.math.nilgiri.autodiff.Variable;

class LatchFunction<X extends Field<X>>
        extends DifferentialFunction<X> {

    private final MathFactory<X> factory;
    private final DifferentialFunction<X> currentTime;
    private final DifferentialFunction<X> timeTrigger;
    private final DifferentialFunction<X> func;

    private X value;

    LatchFunction(MathFactory<X> factory, DifferentialFunction<X> currentTime,
            DifferentialFunction<X> timeTrigger, DifferentialFunction<X> func) {
        this.factory = factory;

        this.currentTime = currentTime;
        this.timeTrigger = timeTrigger;
        this.func = func;

        value = factory.get(0);
    }

    @Override
    public X getValue() {
        if (currentTime.getReal() <= timeTrigger.getReal()) {
            value = factory.get(func.getValue());
        }
        return value;
    }

    @Override
    public double getReal() {
        return getValue().getReal();
    }

    @Override
    public String toString() {
        return "Latch";
    }

    @Override
    public DifferentialFunction<X> diff(Variable<X> i_v1) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getFormula(List<Variable<X>> variables) {
        throw new UnsupportedOperationException();
    }
}
