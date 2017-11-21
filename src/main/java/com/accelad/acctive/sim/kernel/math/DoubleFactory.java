package com.accelad.acctive.sim.kernel.math;

import com.accelad.acctive.sim.kernel.core.evaluator.AbsFunction;
import com.accelad.acctive.sim.kernel.core.evaluator.MaxFunction;
import com.accelad.acctive.sim.kernel.core.evaluator.MinFunction;
import com.accelad.math.doubledouble.DoubleDouble;
import com.accelad.math.nilgiri.DoubleReal;
import com.accelad.math.nilgiri.DoubleRealFactory;
import com.accelad.math.nilgiri.autodiff.Constant;
import com.accelad.math.nilgiri.autodiff.DifferentialFunction;
import com.accelad.math.nilgiri.autodiff.DifferentialFunctionFactory;
import com.accelad.math.nilgiri.autodiff.One;
import com.accelad.math.nilgiri.autodiff.PreEvaluator;
import com.accelad.math.nilgiri.autodiff.Variable;
import com.accelad.math.nilgiri.autodiff.Zero;

public class DoubleFactory implements MathFactory<DoubleReal> {
    DoubleRealFactory doubleRealFactory = DoubleRealFactory.instance();
    DifferentialFunctionFactory<DoubleReal> dfFactory = new DifferentialFunctionFactory<>(
            doubleRealFactory);

    @Override
    public DoubleReal get(DoubleReal x) {
        return new DoubleReal(x.doubleValue());
    }

    @Override
    public DoubleReal get(double value) {
        return doubleRealFactory.val(value);
    }

    @Override
    public DoubleReal get(String literalValue) {
        return new DoubleReal(literalValue);
    }

    @Override
    public DoubleReal get(DoubleDouble value) {
        return doubleRealFactory.val(value.doubleValue());
    }

    @Override
    public Constant<DoubleReal> val(double val) {
        return dfFactory.val(doubleRealFactory.val(val));
    }

    @Override
    public Variable<DoubleReal> var(String i_name) {
        return dfFactory.var(i_name, new DoubleReal());
    }

    @Override
    public Variable<DoubleReal> var(String i_name, double value) {
        return dfFactory.var(i_name, new DoubleReal(value));
    }

    @Override
    public Variable<DoubleReal> var(String i_name, double value,
            PreEvaluator<DoubleReal> preEvaluator) {
        return dfFactory.var(i_name, new DoubleReal(value), preEvaluator);
    }

    @Override
    public Variable<DoubleReal> var(String i_name, PreEvaluator<DoubleReal> preEvaluator) {
        return dfFactory.var(i_name, new DoubleReal(), preEvaluator);
    }

    @Override
    public Constant<DoubleReal> val(DoubleReal i_x) {
        return dfFactory.val(i_x);
    }

    @Override
    public Variable<DoubleReal> var(String i_name, DoubleReal i_x,
            PreEvaluator<DoubleReal> preEvaluator) {
        return dfFactory.var(i_name, i_x, preEvaluator);
    }

    @Override
    public Variable<DoubleReal> var(String i_name, DoubleReal i_x) {
        return dfFactory.var(i_name, i_x);
    }

    @Override
    public Zero<DoubleReal> zero() {
        return dfFactory.zero();
    }

    @Override
    public One<DoubleReal> one() {
        return dfFactory.one();
    }

    @Override
    public DifferentialFunction<DoubleReal> abs(DifferentialFunction<DoubleReal> i_x) {
        return new AbsFunction<>(i_x);
    }

    @Override
    public DifferentialFunction<DoubleReal> cos(DifferentialFunction<DoubleReal> i_x) {
        return dfFactory.cos(i_x);
    }

    @Override
    public DifferentialFunction<DoubleReal> sin(DifferentialFunction<DoubleReal> i_x) {
        return dfFactory.sin(i_x);
    }

    @Override
    public DifferentialFunction<DoubleReal> tan(DifferentialFunction<DoubleReal> i_x) {
        return dfFactory.tan(i_x);
    }

    @Override
    public DifferentialFunction<DoubleReal> acos(DifferentialFunction<DoubleReal> i_x) {
        return dfFactory.acos(i_x);
    }

    @Override
    public DifferentialFunction<DoubleReal> asin(DifferentialFunction<DoubleReal> i_x) {
        return dfFactory.asin(i_x);
    }

    @Override
    public DifferentialFunction<DoubleReal> atan(DifferentialFunction<DoubleReal> i_x) {
        return dfFactory.atan(i_x);
    }

    @Override
    public DifferentialFunction<DoubleReal> cosh(DifferentialFunction<DoubleReal> i_x) {
        return dfFactory.cosh(i_x);
    }

    @Override
    public DifferentialFunction<DoubleReal> sinh(DifferentialFunction<DoubleReal> i_x) {
        return dfFactory.sinh(i_x);
    }

    @Override
    public DifferentialFunction<DoubleReal> tanh(DifferentialFunction<DoubleReal> i_x) {
        return dfFactory.tanh(i_x);
    }

    @Override
    public DifferentialFunction<DoubleReal> acosh(DifferentialFunction<DoubleReal> i_x) {
        return dfFactory.acosh(i_x);
    }

    @Override
    public DifferentialFunction<DoubleReal> asinh(DifferentialFunction<DoubleReal> i_x) {
        return dfFactory.asinh(i_x);
    }

    @Override
    public DifferentialFunction<DoubleReal> atanh(DifferentialFunction<DoubleReal> i_x) {
        return dfFactory.atanh(i_x);
    }

    @Override
    public DifferentialFunction<DoubleReal> exp(DifferentialFunction<DoubleReal> i_x) {
        return dfFactory.exp(i_x);
    }

    @Override
    public DifferentialFunction<DoubleReal> log(DifferentialFunction<DoubleReal> i_x) {
        return dfFactory.log(i_x);
    }

    @Override
    public DifferentialFunction<DoubleReal> pow(DifferentialFunction<DoubleReal> i_x,
            Constant<DoubleReal> i_y) {
        return dfFactory.pow(i_x, i_y);
    }

    @Override
    public DifferentialFunction<DoubleReal> sqrt(DifferentialFunction<DoubleReal> i_x) {
        return dfFactory.sqrt(i_x);
    }

    @Override
    public DifferentialFunction<DoubleReal> square(DifferentialFunction<DoubleReal> i_x) {
        return dfFactory.square(i_x);
    }

    @Override
    public DifferentialFunction<DoubleReal> floor(DifferentialFunction<DoubleReal> df) {
        return dfFactory.floor(df);
    }

    @Override
    public DifferentialFunction<DoubleReal> max(DifferentialFunction<DoubleReal> arg1,
            DifferentialFunction<DoubleReal> arg2) {
        return new MaxFunction<>(arg1, arg2);
    }

    @Override
    public DifferentialFunction<DoubleReal> min(DifferentialFunction<DoubleReal> arg1,
            DifferentialFunction<DoubleReal> arg2) {
        return new MinFunction<>(arg1, arg2);
    }

}
