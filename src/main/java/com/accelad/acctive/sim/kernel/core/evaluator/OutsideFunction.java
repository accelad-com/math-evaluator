package com.accelad.acctive.sim.kernel.core.evaluator;

import java.util.List;

import com.accelad.acctive.sim.kernel.math.MathFactory;
import com.accelad.math.nilgiri.Field;
import com.accelad.math.nilgiri.autodiff.DifferentialFunction;
import com.accelad.math.nilgiri.autodiff.Variable;

public class OutsideFunction<X extends Field<X>>
        extends DifferentialFunction<X> {

    private final MathFactory<X> DFFactory;

    private final DifferentialFunction<X> variable;
    private final DifferentialFunction<X> minFunction;
    private final DifferentialFunction<X> maxFunction;

    public OutsideFunction(MathFactory<X> DFFactory, DifferentialFunction<X> x,
            DifferentialFunction<X> value1, DifferentialFunction<X> value2) {
        super();
        this.DFFactory = DFFactory;
        this.variable = x;
        this.minFunction = value1;
        this.maxFunction = value2;
    }

    @Override
    public X getValue() {
        double v = variable.getValue().getReal();
        double min = minFunction.getValue().getReal();
        double max = maxFunction.getValue().getReal();

        if (min > v || v > max) {
            return DFFactory.get(1d);
        }
        return DFFactory.get(0d);
    }

    @Override
    public double getReal() {
        double v = variable.getReal();
        double min = minFunction.getReal();
        double max = maxFunction.getReal();

        if (min > v || v > max) {
            return 1d;
        }
        return 0d;
    }

    @Override
    public String toString() {
        return "Outside";
    }

    @Override
    public DifferentialFunction<X> diff(Variable<X> i_v1) {
        return DFFactory.zero();
    }

    @Override
    public String getFormula(List<Variable<X>> variables) {
        String v = variable.getFormula(variables);
        String min = minFunction.getFormula(variables);
        String max = maxFunction.getFormula(variables);

        String formula = "(((%s > %s && %s > %s) || (%s < %s && %s < %s)) ? 1d : 0d)";
        formula = String.format(formula, v, min, v, max, v, max, v, min);

        return formula;
    }
}
