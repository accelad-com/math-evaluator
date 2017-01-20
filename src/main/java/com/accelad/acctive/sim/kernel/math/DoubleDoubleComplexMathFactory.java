package com.accelad.acctive.sim.kernel.math;

import com.accelad.acctive.sim.kernel.core.evaluator.AbsFunction;
import com.accelad.acctive.sim.kernel.core.evaluator.MaxFunction;
import com.accelad.acctive.sim.kernel.core.evaluator.MinFunction;
import com.accelad.math.DoubleDouble;
import com.accelad.math.nilgiri.DoubleDoubleComplex;
import com.accelad.math.nilgiri.DoubleDoubleComplexFactory;
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

public class DoubleDoubleComplexMathFactory implements MathFactory<DoubleDoubleComplex> {

    DoubleDoubleComplexFactory factory = DoubleDoubleComplexFactory.instance();
    DifferentialFunctionFactory<DoubleDoubleComplex> dfFactory = new DifferentialFunctionFactory<>(
            factory);

    @Override
    public DoubleDoubleComplex get(DoubleDoubleComplex x) {
        return x;
    }

    @Override
    public DoubleDoubleComplex get(double value) {
        return factory.val(value);
    }

    @Override
    public DoubleDoubleComplex get(String literalValue) {
        return new DoubleDoubleComplex(new DoubleDouble(literalValue), new DoubleDouble());
    }

    @Override
    public DoubleDoubleComplex get(DoubleDouble value) {
        return new DoubleDoubleComplex(value, new DoubleDouble());
    }

    @Override
    public Constant<DoubleDoubleComplex> val(double val) {
        return dfFactory.val(factory.val(val));
    }

    @Override
    public Constant<DoubleDoubleComplex> val(String name) {
        return dfFactory.val(new DoubleDoubleComplex(new DoubleDouble(name), new DoubleDouble()));
    }

    @Override
    public Variable<DoubleDoubleComplex> var(String name) {
        return dfFactory.var(name, new DoubleDoubleComplex());
    }

    @Override
    public Variable<DoubleDoubleComplex> var(String name, double value) {
        return dfFactory.var(name, new DoubleDoubleComplex(value));
    }

    @Override
    public Variable<DoubleDoubleComplex> var(String name, double value,
            PreEvaluator<DoubleDoubleComplex> preEvaluator) {
        return dfFactory.var(name, new DoubleDoubleComplex(value), preEvaluator);
    }

    @Override
    public Variable<DoubleDoubleComplex> var(String name,
            PreEvaluator<DoubleDoubleComplex> preEvaluator) {
        return dfFactory.var(name, new DoubleDoubleComplex(), preEvaluator);
    }

    @Override
    public Constant<DoubleDoubleComplex> val(DoubleDoubleComplex value) {
        return dfFactory.val(value);
    }

    public ConstantVector<DoubleDoubleComplex> val(DoubleDoubleComplex... value) {
        return dfFactory.val(value);
    }

    public ConstantVector<DoubleDoubleComplex> zero(int size) {
        return dfFactory.zero(size);
    }

    @Override
    public Variable<DoubleDoubleComplex> var(String name, DoubleDoubleComplex value,
            PreEvaluator<DoubleDoubleComplex> preEvaluator) {
        return dfFactory.var(name, value, preEvaluator);
    }

    @Override
    public Variable<DoubleDoubleComplex> var(String name, DoubleDoubleComplex value) {
        return dfFactory.var(name, value);
    }

    public VariableVector<DoubleDoubleComplex> var(String name, DoubleDoubleComplex... value) {
        return dfFactory.var(name, value);
    }

    public VariableVector<DoubleDoubleComplex> var(String name, int size) {
        return dfFactory.var(name, size);
    }

    @SuppressWarnings("unchecked")
    @Override
    public DifferentialVectorFunction<DoubleDoubleComplex> function(
            DifferentialFunction<DoubleDoubleComplex>... value) {
        return dfFactory.function(value);
    }

    @Override
    public Zero<DoubleDoubleComplex> zero() {
        return dfFactory.zero();
    }

    @Override
    public One<DoubleDoubleComplex> one() {
        return dfFactory.one();
    }

    @Override
    public DifferentialFunction<DoubleDoubleComplex> abs(
            DifferentialFunction<DoubleDoubleComplex> i_x) {
        return new AbsFunction<>(i_x);
    }

    @Override
    public DifferentialFunction<DoubleDoubleComplex> cos(
            DifferentialFunction<DoubleDoubleComplex> value) {
        return dfFactory.cos(value);
    }

    @Override
    public DifferentialFunction<DoubleDoubleComplex> sin(
            DifferentialFunction<DoubleDoubleComplex> value) {
        return dfFactory.sin(value);
    }

    @Override
    public DifferentialFunction<DoubleDoubleComplex> tan(
            DifferentialFunction<DoubleDoubleComplex> value) {
        return dfFactory.tan(value);
    }

    @Override
    public DifferentialFunction<DoubleDoubleComplex> acos(
            DifferentialFunction<DoubleDoubleComplex> value) {
        return dfFactory.acos(value);
    }

    @Override
    public DifferentialFunction<DoubleDoubleComplex> asin(
            DifferentialFunction<DoubleDoubleComplex> value) {
        return dfFactory.asin(value);
    }

    @Override
    public DifferentialFunction<DoubleDoubleComplex> atan(
            DifferentialFunction<DoubleDoubleComplex> value) {
        return dfFactory.atan(value);
    }

    @Override
    public DifferentialFunction<DoubleDoubleComplex> cosh(
            DifferentialFunction<DoubleDoubleComplex> value) {
        return dfFactory.cosh(value);
    }

    @Override
    public DifferentialFunction<DoubleDoubleComplex> sinh(
            DifferentialFunction<DoubleDoubleComplex> value) {
        return dfFactory.sinh(value);
    }

    @Override
    public DifferentialFunction<DoubleDoubleComplex> tanh(
            DifferentialFunction<DoubleDoubleComplex> value) {
        return dfFactory.tanh(value);
    }

    @Override
    public DifferentialFunction<DoubleDoubleComplex> acosh(
            DifferentialFunction<DoubleDoubleComplex> value) {
        return dfFactory.acosh(value);
    }

    @Override
    public DifferentialFunction<DoubleDoubleComplex> asinh(
            DifferentialFunction<DoubleDoubleComplex> value) {
        return dfFactory.asinh(value);
    }

    @Override
    public DifferentialFunction<DoubleDoubleComplex> atanh(
            DifferentialFunction<DoubleDoubleComplex> value) {
        return dfFactory.atanh(value);
    }

    @Override
    public DifferentialFunction<DoubleDoubleComplex> exp(
            DifferentialFunction<DoubleDoubleComplex> value) {
        return dfFactory.exp(value);
    }

    @Override
    public DifferentialFunction<DoubleDoubleComplex> log(
            DifferentialFunction<DoubleDoubleComplex> value) {
        return dfFactory.log(value);
    }

    @Override
    public DifferentialFunction<DoubleDoubleComplex> pow(
            DifferentialFunction<DoubleDoubleComplex> value, Constant<DoubleDoubleComplex> exponent) {
        return dfFactory.pow(value, exponent);
    }

    @Override
    public DifferentialFunction<DoubleDoubleComplex> sqrt(
            DifferentialFunction<DoubleDoubleComplex> value) {
        return dfFactory.sqrt(value);
    }

    @Override
    public DifferentialFunction<DoubleDoubleComplex> square(
            DifferentialFunction<DoubleDoubleComplex> value) {
        return dfFactory.square(value);
    }

    @Override
    public DifferentialFunction<DoubleDoubleComplex> floor(
            DifferentialFunction<DoubleDoubleComplex> arg1) {
        return dfFactory.floor(arg1);
    }

    @Override
    public DifferentialFunction<DoubleDoubleComplex> max(
            DifferentialFunction<DoubleDoubleComplex> arg1,
            DifferentialFunction<DoubleDoubleComplex> arg2) {
        return new MaxFunction<>(arg1, arg2);
    }

    @Override
    public DifferentialFunction<DoubleDoubleComplex> min(
            DifferentialFunction<DoubleDoubleComplex> arg1,
            DifferentialFunction<DoubleDoubleComplex> arg2) {
        return new MinFunction<>(arg1, arg2);
    }
}
