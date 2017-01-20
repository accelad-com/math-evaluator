package com.accelad.acctive.sim.kernel.core.evaluator.diff;

import java.util.List;

import com.accelad.acctive.sim.kernel.math.MathFactory;
import com.accelad.math.nilgiri.Field;
import com.accelad.math.nilgiri.autodiff.DifferentialFunction;
import com.accelad.math.nilgiri.autodiff.Variable;

class RestrictedDomainFunction<X extends Field<X>> extends DifferentialFunction<X> {

    private final MathFactory<X> mathFactory;

    private final DifferentialFunction<X> variable;
    private final DifferentialFunction<X> function;
    private final DifferentialFunction<X> firstBound;
    private final DifferentialFunction<X> secondBound;

    RestrictedDomainFunction(MathFactory<X> mathFactory, DifferentialFunction<X> variable,
            DifferentialFunction<X> function, DifferentialFunction<X> value1,
            DifferentialFunction<X> value2) {
        this.mathFactory = mathFactory;
        this.variable = variable;
        this.function = function;
        this.firstBound = value1;
        this.secondBound = value2;
    }

    @Override
    public X getValue() {
        double v = variable.getValue().getReal();
        double a = firstBound.getValue().getReal();
        double b = secondBound.getValue().getReal();

        if (v >= Math.min(a, b) && v <= Math.max(a, b)) {
            return function.getValue();
        }
        return mathFactory.get(0d);
    }

    @Override
    public double getReal() {
        return getValue().getReal();
    }

    @Override
    public String toString() {
        return "RestrictedDomain";
    }

    @Override
    public DifferentialFunction<X> diff(Variable<X> v) {
        return new RestrictedDomainFunction<>(mathFactory, variable, function.diff(v), firstBound,
                secondBound);
    }

    @Override
    public String getFormula(List<Variable<X>> variables) {
        String v = function.getFormula(variables);
        String a = firstBound.getFormula(variables);
        String b = secondBound.getFormula(variables);

        String formula = "((%s >= min(%s, %s) && %s <= max(%s, %s)) ? f(%d) : 0d)";
        formula = String.format(formula, v, a, b, v, a, b, v);

        return formula;
    }
}
