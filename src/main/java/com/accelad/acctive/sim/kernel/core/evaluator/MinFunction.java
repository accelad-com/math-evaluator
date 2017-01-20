package com.accelad.acctive.sim.kernel.core.evaluator;

import java.util.List;

import com.accelad.math.nilgiri.Field;
import com.accelad.math.nilgiri.autodiff.AbstractBinaryFunction;
import com.accelad.math.nilgiri.autodiff.DifferentialFunction;
import com.accelad.math.nilgiri.autodiff.Variable;

public class MinFunction<X extends Field<X>>
        extends AbstractBinaryFunction<X> {

    public MinFunction(DifferentialFunction<X> i_v1,
            DifferentialFunction<X> i_v2) {
        super(i_v1, i_v2);
    }

    @Override
    public X getValue() {
        if (larg().getValue().getReal() < rarg().getValue().getReal()) {
            return larg().getValue();
        }
        return rarg().getValue();
    }

    @Override
    public double getReal() {
        if (larg().getReal() < rarg().getReal()) {
            return larg().getReal();
        }
        return rarg().getReal();
    }

    @Override
    public String toString() {
        return "min";
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
