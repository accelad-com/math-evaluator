package com.accelad.acctive.sim.kernel.core.evaluator.diff;

import java.util.List;

import com.accelad.acctive.sim.kernel.math.MathFactory;
import com.accelad.math.nilgiri.Field;
import com.accelad.math.nilgiri.autodiff.DifferentialFunction;
import com.accelad.math.nilgiri.autodiff.Variable;

class BetweenFunction<X extends Field<X>>
        extends DifferentialFunction<X> {

    private final MathFactory<X> DFFactory;

    private final DifferentialFunction<X> variable;
    private final DifferentialFunction<X> firstBound;
    private final DifferentialFunction<X> secondBound;

    public BetweenFunction(MathFactory<X> DFFactory, DifferentialFunction<X> x,
            DifferentialFunction<X> value1, DifferentialFunction<X> value2) {
        this.DFFactory = DFFactory;
        this.variable = x;
        this.firstBound = value1;
        this.secondBound = value2;
    }

    @Override
    public X getValue() {
        double v = variable.getValue().getReal();
        double a = firstBound.getValue().getReal();
        double b = secondBound.getValue().getReal();

        if (v >= Math.min(a, b) && v <= Math.max(a, b)) {
            return DFFactory.get(1d);
        }
        return DFFactory.get(0d);
    }

    @Override
    public double getReal() {
        return getValue().getReal();
    }

    @Override
    public String toString() {
        return "Between";
    }

    @Override
    public DifferentialFunction<X> diff(Variable<X> i_v1) {
        return DFFactory.zero();
    }

    @Override
    public String getFormula(List<Variable<X>> variables) {
        String v = variable.getFormula(variables);
        String a = firstBound.getFormula(variables);
        String b = secondBound.getFormula(variables);

        String formula = "((%s >= min(%s, %s) && %s <= max(%s, %s)) ? 1d : 0d)";
        formula = String.format(formula, new Object[] { v, a, b, v, a, b });

        return formula;
    }
}
