package com.accelad.acctive.sim.kernel.math;

import com.accelad.acctive.sim.kernel.core.evaluator.AbsFunction;
import com.accelad.acctive.sim.kernel.core.evaluator.MaxFunction;
import com.accelad.acctive.sim.kernel.core.evaluator.MinFunction;
import com.accelad.math.DoubleDouble;
import com.accelad.math.nilgiri.DoubleDoubleReal;
import com.accelad.math.nilgiri.DoubleDoubleRealFactory;
import com.accelad.math.nilgiri.autodiff.Constant;
import com.accelad.math.nilgiri.autodiff.ConstantVector;
import com.accelad.math.nilgiri.autodiff.DifferentialFunction;
import com.accelad.math.nilgiri.autodiff.DifferentialFunctionFactory;
import com.accelad.math.nilgiri.autodiff.DifferentialVectorFunction;
import com.accelad.math.nilgiri.autodiff.One;
import com.accelad.math.nilgiri.autodiff.PreEvaluator;
import com.accelad.math.nilgiri.autodiff.Variable;
import com.accelad.math.nilgiri.autodiff.VariableVector;
import com.accelad.math.nilgiri.autodiff.Zero;

public class DoubleDoubleFactory implements MathFactory<DoubleDoubleReal> {
    DoubleDoubleRealFactory doubleRealFactory = DoubleDoubleRealFactory.instance();
    DifferentialFunctionFactory<DoubleDoubleReal> dfFactory = new DifferentialFunctionFactory<>(
            doubleRealFactory);

    @Override
    public DoubleDoubleReal get(DoubleDoubleReal x) {
        return new DoubleDoubleReal(x.getDoubleDouble());
    }

    @Override
    public DoubleDoubleReal get(double value) {
        return doubleRealFactory.val(value);
    }

    @Override
    public DoubleDoubleReal get(String literalValue) {
        return new DoubleDoubleReal(literalValue);
    }

    @Override
    public DoubleDoubleReal get(DoubleDouble value) {
        return new DoubleDoubleReal(value);
    }

    @Override
    public Constant<DoubleDoubleReal> val(double val) {
        return dfFactory.val(doubleRealFactory.val(val));
    }

    @Override
    public Constant<DoubleDoubleReal> val(String name) {
        return dfFactory.val(new DoubleDoubleReal(name));
    }

    @Override
    public Variable<DoubleDoubleReal> var(String i_name) {
        return dfFactory.var(i_name, new DoubleDoubleReal());
    }

    @Override
    public Variable<DoubleDoubleReal> var(String i_name, double value) {
        return dfFactory.var(i_name, new DoubleDoubleReal(value));
    }

    @Override
    public Variable<DoubleDoubleReal> var(String i_name, double value,
            PreEvaluator<DoubleDoubleReal> preEvaluator) {
        return dfFactory.var(i_name, new DoubleDoubleReal(value), preEvaluator);
    }

    @Override
    public Variable<DoubleDoubleReal> var(String i_name,
            PreEvaluator<DoubleDoubleReal> preEvaluator) {
        return dfFactory.var(i_name, new DoubleDoubleReal(), preEvaluator);
    }

    @Override
    public Constant<DoubleDoubleReal> val(DoubleDoubleReal i_x) {
        return dfFactory.val(i_x);
    }

    public ConstantVector<DoubleDoubleReal> val(DoubleDoubleReal... i_x) {
        return dfFactory.val(i_x);
    }

    public ConstantVector<DoubleDoubleReal> zero(int i_size) {
        return dfFactory.zero(i_size);
    }

    @Override
    public Variable<DoubleDoubleReal> var(String i_name, DoubleDoubleReal i_x,
            PreEvaluator<DoubleDoubleReal> preEvaluator) {
        return dfFactory.var(i_name, i_x, preEvaluator);
    }

    @Override
    public Variable<DoubleDoubleReal> var(String i_name, DoubleDoubleReal i_x) {
        return dfFactory.var(i_name, i_x);
    }

    public VariableVector<DoubleDoubleReal> var(String i_name, DoubleDoubleReal... i_x) {
        return dfFactory.var(i_name, i_x);
    }

    public VariableVector<DoubleDoubleReal> var(String i_name, int i_size) {
        return dfFactory.var(i_name, i_size);
    }

    @Override
    public DifferentialVectorFunction<DoubleDoubleReal> function(
            DifferentialFunction<DoubleDoubleReal>... i_x) {
        return dfFactory.function(i_x);
    }

    @Override
    public Zero<DoubleDoubleReal> zero() {
        return dfFactory.zero();
    }

    @Override
    public One<DoubleDoubleReal> one() {
        return dfFactory.one();
    }

    @Override
    public DifferentialFunction<DoubleDoubleReal> abs(DifferentialFunction<DoubleDoubleReal> i_x) {
        return new AbsFunction<>(i_x);
    }

    @Override
    public DifferentialFunction<DoubleDoubleReal> cos(DifferentialFunction<DoubleDoubleReal> i_x) {
        return dfFactory.cos(i_x);
    }

    @Override
    public DifferentialFunction<DoubleDoubleReal> sin(DifferentialFunction<DoubleDoubleReal> i_x) {
        return dfFactory.sin(i_x);
    }

    @Override
    public DifferentialFunction<DoubleDoubleReal> tan(DifferentialFunction<DoubleDoubleReal> i_x) {
        return dfFactory.tan(i_x);
    }

    @Override
    public DifferentialFunction<DoubleDoubleReal> acos(DifferentialFunction<DoubleDoubleReal> i_x) {
        return dfFactory.acos(i_x);
    }

    @Override
    public DifferentialFunction<DoubleDoubleReal> asin(DifferentialFunction<DoubleDoubleReal> i_x) {
        return dfFactory.asin(i_x);
    }

    @Override
    public DifferentialFunction<DoubleDoubleReal> atan(DifferentialFunction<DoubleDoubleReal> i_x) {
        return dfFactory.atan(i_x);
    }

    @Override
    public DifferentialFunction<DoubleDoubleReal> cosh(DifferentialFunction<DoubleDoubleReal> i_x) {
        return dfFactory.cosh(i_x);
    }

    @Override
    public DifferentialFunction<DoubleDoubleReal> sinh(DifferentialFunction<DoubleDoubleReal> i_x) {
        return dfFactory.sinh(i_x);
    }

    @Override
    public DifferentialFunction<DoubleDoubleReal> tanh(DifferentialFunction<DoubleDoubleReal> i_x) {
        return dfFactory.tanh(i_x);
    }

    @Override
    public DifferentialFunction<DoubleDoubleReal> acosh(
            DifferentialFunction<DoubleDoubleReal> i_x) {
        return dfFactory.acosh(i_x);
    }

    @Override
    public DifferentialFunction<DoubleDoubleReal> asinh(
            DifferentialFunction<DoubleDoubleReal> i_x) {
        return dfFactory.asinh(i_x);
    }

    @Override
    public DifferentialFunction<DoubleDoubleReal> atanh(
            DifferentialFunction<DoubleDoubleReal> i_x) {
        return dfFactory.atanh(i_x);
    }

    @Override
    public DifferentialFunction<DoubleDoubleReal> exp(DifferentialFunction<DoubleDoubleReal> i_x) {
        return dfFactory.exp(i_x);
    }

    @Override
    public DifferentialFunction<DoubleDoubleReal> log(DifferentialFunction<DoubleDoubleReal> i_x) {
        return dfFactory.log(i_x);
    }

    @Override
    public DifferentialFunction<DoubleDoubleReal> pow(DifferentialFunction<DoubleDoubleReal> i_x,
            Constant<DoubleDoubleReal> i_y) {
        return dfFactory.pow(i_x, i_y);
    }

    @Override
    public DifferentialFunction<DoubleDoubleReal> sqrt(DifferentialFunction<DoubleDoubleReal> i_x) {
        return dfFactory.sqrt(i_x);
    }

    @Override
    public DifferentialFunction<DoubleDoubleReal> square(
            DifferentialFunction<DoubleDoubleReal> i_x) {
        return dfFactory.square(i_x);
    }

    @Override
    public DifferentialFunction<DoubleDoubleReal> floor(
            DifferentialFunction<DoubleDoubleReal> arg1) {
        return dfFactory.floor(arg1);
    }

    @Override
    public DifferentialFunction<DoubleDoubleReal> max(DifferentialFunction<DoubleDoubleReal> arg1,
            DifferentialFunction<DoubleDoubleReal> arg2) {
        return new MaxFunction<>(arg1, arg2);
    }

    @Override
    public DifferentialFunction<DoubleDoubleReal> min(DifferentialFunction<DoubleDoubleReal> arg1,
            DifferentialFunction<DoubleDoubleReal> arg2) {
        return new MinFunction<>(arg1, arg2);
    }

}
