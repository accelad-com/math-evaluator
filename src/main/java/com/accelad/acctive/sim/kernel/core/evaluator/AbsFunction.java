package com.accelad.acctive.sim.kernel.core.evaluator;

import java.util.List;

import com.accelad.math.nilgiri.Field;
import com.accelad.math.nilgiri.autodiff.AbstractUnaryFunction;
import com.accelad.math.nilgiri.autodiff.DifferentialFunction;
import com.accelad.math.nilgiri.autodiff.Variable;

public class AbsFunction<X extends Field<X>>
        extends AbstractUnaryFunction<X> {

    public AbsFunction(DifferentialFunction<X> i_v1) {
        super(i_v1);
    }

    @Override
    public X getValue() {
        if (arg().getValue().getReal() < 0) {
            return arg().getValue().negate();
        }
        return arg().getValue();
    }

    @Override
    public double getReal() {
        if (arg().getValue().getReal() < 0) {
            return -arg().getReal();
        }
        return arg().getReal();
    }

    @Override
    public String toString() {
        return "abs";
    }

    @Override
    public DifferentialFunction<X> diff(Variable<X> i_v1) {
        return arg().mul(arg().diff(i_v1)).div(this);
    }

    @Override
    public String getFormula(List<Variable<X>> variables) {
        String var = arg().getFormula(variables);
        String formula = "Math.abs(%s)";
        formula = String.format(formula, var);

        return formula;
    }
}
