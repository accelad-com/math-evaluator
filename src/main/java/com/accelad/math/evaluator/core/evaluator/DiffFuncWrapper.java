package com.accelad.math.evaluator.core.evaluator;

import java.util.List;

import com.accelad.math.evaluator.math.MathFactory;
import com.accelad.math.nilgiri.Field;
import com.accelad.math.nilgiri.autodiff.DifferentialFunction;
import com.accelad.math.nilgiri.autodiff.Variable;
import com.google.common.collect.RangeMap;

class DiffFuncWrapper<X extends Field<X>>
        extends DifferentialFunction<X> {

    final Variable<X> a, b;
    final DifferentialFunction<X> variable;
    final RangeMap<Double, Formula<X>> rangeMap;
    private final DifferentialFunction<X> func;

    DiffFuncWrapper(MathFactory<X> DFFactory, DifferentialFunction<X> variable,
            RangeMap<Double, Formula<X>> rangeMap, Variable<X> i_v1) {
        super();
        this.variable = variable;
        this.rangeMap = rangeMap;

        a = DFFactory.var("diffa", var -> {
            double v = variable.getReal();
            var.set(DFFactory.get(rangeMap.get(v).getA().getReal()));
        });
        b = DFFactory.var("diffb", var -> {
            double v = variable.getReal();
            var.set(DFFactory.get(rangeMap.get(v).getB().getReal()));
        });

        this.func = a.mul(variable).plus(b).diff(i_v1);
    }

    @Override
    public X getValue() {
        return func.getValue();
    }

    @Override
    public double getReal() {
        return func.getReal();
    }

    @Override
    public String getFormula(List<Variable<X>> variables) {
        return func.getFormula(variables);
    }

    @Override
    public boolean isPrecisionOK(int precision) {
        return func.isPrecisionOK(precision);
    }

    @Override
    public boolean isConstant() {
        return func.isConstant();
    }

    @Override
    public boolean isVariable() {
        return func.isVariable();
    }

    @Override
    public DifferentialFunction<X> diff(Variable<X> i_v1) {
        return func.diff(i_v1);
    }

    @Override
    public DifferentialFunction<X> plus(DifferentialFunction<X> i_v) {
        return func.plus(i_v);
    }

    @Override
    public DifferentialFunction<X> minus(DifferentialFunction<X> i_v) {
        return func.minus(i_v);
    }

    @Override
    public DifferentialFunction<X> mul(DifferentialFunction<X> i_v) {
        return func.mul(i_v);
    }

    @Override
    public DifferentialFunction<X> div(DifferentialFunction<X> i_v) {
        return func.div(i_v);
    }

    @Override
    public DifferentialFunction<X> inverse() {
        return func.inverse();
    }

    @Override
    public DifferentialFunction<X> negate() {
        return func.negate();
    }

    @Override
    public DifferentialFunction<X> mul(long i_n) {
        return func.mul(i_n);
    }

    @Override
    public DifferentialFunction<X> pow(int i_n) {
        return func.pow(i_n);
    }

    @Override
    public String toString() {
        return "DiffFuncWrapper [func=" + func + "]";
    }
}
