package com.accelad.acctive.sim.kernel.core.evaluator.diff;

import java.util.List;

import com.accelad.acctive.sim.kernel.math.MathFactory;
import com.accelad.math.nilgiri.Field;
import com.accelad.math.nilgiri.autodiff.AbstractBinaryFunction;
import com.accelad.math.nilgiri.autodiff.DifferentialFunction;
import com.accelad.math.nilgiri.autodiff.Variable;

class StrictlyLessThanFunction<X extends Field<X>>
        extends AbstractBinaryFunction<X> {

    private final MathFactory<X> DFFactory;

    StrictlyLessThanFunction(MathFactory<X> DFFactory, DifferentialFunction<X> i_v1,
            DifferentialFunction<X> i_v2) {
        super(i_v1, i_v2);
        this.DFFactory = DFFactory;
    }

    @Override
    public X getValue() {
        if (larg().getValue().getReal() < rarg().getValue().getReal()) {
            return DFFactory.get(1d);
        }
        return DFFactory.get(0d);
    }

    @Override
    public double getReal() {
        if (larg().getReal() < rarg().getReal()) {
            return 1d;
        }
        return 0d;
    }

    @Override
    public String toString() {
        return "StrictlyLessThan";
    }

    @Override
    public DifferentialFunction<X> diff(Variable<X> i_v1) {
        return DFFactory.zero();
    }

    @Override
    public String getFormula(List<Variable<X>> variables) {
        String v = larg().getFormula(variables);
        String threshold = rarg().getFormula(variables);

        String formula = "((%s < %s ) ? 1d : 0d)";
        formula = String.format(formula, v, threshold);

        return formula;
    }
}
